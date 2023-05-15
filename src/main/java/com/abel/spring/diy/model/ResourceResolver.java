package com.abel.spring.diy.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ResourceResolver {
    String basePackage;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public ResourceResolver(String basePackage) {
        this.basePackage = basePackage;
    }

    public <R> List<R> scan(Function<Resource, R> mapper) {
        String basePackagePath = this.basePackage.replace(".", "/");
        // 扫描路径下所有的class文件（包含Jar包里的）
        try {
            List<R> collector = new ArrayList<>();
            scan0(basePackagePath, basePackagePath, collector, mapper);
            return collector;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    <R> void scan0(String basePackagePath, String location, List<R> collector,
                   Function<Resource, R> mapper) throws IOException, URISyntaxException {
        logger.atDebug().log("scan path: {}", location);
        Enumeration<URL> resources = getContextClassLoader().getResources(location);
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            URI uri = url.toURI();
            String uriStr = stripTrailingSlash(uri.toString());
            String uriBaseStr = uriStr.substring(0, uriStr.length() - basePackagePath.length());
            if (uriBaseStr.startsWith("file:")) {
                uriBaseStr = uriBaseStr.substring(5);
            }
            if (uriStr.startsWith("jar:")) {
                scanFile(true, uriBaseStr, jarUriToPath(basePackagePath, uri), collector, mapper);
            } else {
                scanFile(false, uriBaseStr, Paths.get(uri), collector, mapper);
            }
        }
    }

    Path jarUriToPath(String basePackagePath, URI jarUri) throws IOException {
        return FileSystems.newFileSystem(jarUri, Map.of()).getPath(basePackagePath);
    }

    <R> void scanFile(boolean isJar, String base, Path root, List<R> collector, Function<Resource, R> mapper) throws IOException {
        String baseDir = stripTrailingSlash(base);
        Files.walk(root).filter(Files::isRegularFile).forEach(file -> {
            Resource res = null;
            if (isJar) {
                res = new Resource(baseDir, stripLeadingSlash(file.toString()));
            } else {
                String path = file.toString();
                String name = stripLeadingSlash(path.substring(baseDir.length()));
                res = new Resource("file:" + path, name);
            }
            logger.atDebug().log("found resource: {}", res);
            R r = mapper.apply(res);
            if (r != null) {
                collector.add(r);
            }
        });
    }

    ClassLoader getContextClassLoader() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        return loader == null ? getClass().getClassLoader() : loader;
    }

    String stripLeadingSlash(String path) {
        return path.startsWith("/") || path.startsWith("\\") ? path.substring(1) : path;
    }

    String stripTrailingSlash(String path) {
        return (path.endsWith("/") || path.endsWith("\\")) ? path.substring(0, path.length() - 1) : path;
    }
}
