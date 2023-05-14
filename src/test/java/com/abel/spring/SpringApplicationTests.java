package com.abel.spring;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest ( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@TestPropertySource ( properties = { "management.port=0" } )
@AutoConfigureMockMvc
class SpringApplicationTests {

//    @LocalServerPort
//    private int port;
//
//    @Value ( "${local.management.port}" )
//    private int mgt;
//
//    @Autowired
//    private TestRestTemplate testRestTemplate;
//
//    @Test
//    void shouldReturn200WhenSendingRequestToController () throws Exception {
//        @SuppressWarnings ( "rawtypes" )
//        ResponseEntity <Map> entity = this.testRestTemplate.getForEntity (
//                "http://localhost:" + this.port + "/hello-world", Map.class );
//
//        then ( entity.getStatusCode () ).isEqualTo ( HttpStatus.OK );
//    }
//
//    @Test
//    void shouldReturn200WhenSendingRequestToManagementEndpoint () throws Exception {
//        @SuppressWarnings ( "rawtypes" )
//        ResponseEntity <Map> entity = this.testRestTemplate.getForEntity (
//                "http://localhost:" + this.mgt + "/actuator", Map.class );
//        then ( entity.getStatusCode () ).isEqualTo ( HttpStatus.OK );
//    }

}
