package com.example.HttpRequests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestRestTest {

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * ScheduleController HttpRequests
     */
    @Test
    public void returnShedulePage(){
        String requiredSchedule = "WEEKEND";
        String url = "http://localhost:"+port+"/schedule/"+requiredSchedule;
        ResponseEntity responseEntity = (ResponseEntity) restTemplate.getForEntity(url,String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    /**
     * ProfileController HttpRequests
     */
    @Test
    public void returnProfilePage(){
        String url = "http://localhost:"+port+"/details/";
        ResponseEntity responseEntity = restTemplate.getForEntity(url,String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);


    }



    /**
     * PublicController HttpRequests
     */
    @Test
    public void returnRegisterPage(){
        String url = "http://localhost:"+port+"/public/register";
        ResponseEntity responseEntity = restTemplate.getForEntity(url,String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    /**
     * IdeaController HttpRequests
     */
    @Test
    public void returnIdeaPage(){
        String url = "http://localhost:"+port+"/idea";
        ResponseEntity responseEntity = restTemplate.getForEntity(url,String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    /**
     * LoginController HttpRequests
     * displayLoginPage test cases
     */
    @Test
    public void returnLoginPage(){
        String url = "http://localhost:"+port+"/login";
        ResponseEntity responseEntity = restTemplate.getForEntity(url,String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void returnProfilePageWhenUrlHasRequestParams(){
        String url = "http://localhost:"+port+"/login?register=false&logout=false";
        ResponseEntity responseEntity = restTemplate.getForEntity(url,String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);


    }

    @Test
    public void returnProfilePageWhenUrlHasRequestParamRegisterTrue(){
        String url = "http://localhost:"+port+"/login?register=true";
        ResponseEntity responseEntity = restTemplate.getForEntity(url,String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);


    }

    @Test
    public void returnProfilePageWhenUrlHasRequestParamLogoutTrue(){
        String url = "http://localhost:"+port+"/loginlogout=true";
        ResponseEntity responseEntity = restTemplate.getForEntity(url,String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);


    }

    @Test
    public void returnProfilePageWhenUrlHasRequestParamErrorTrue(){
        String url = "http://localhost:"+port+"/login?error=true";
        ResponseEntity responseEntity = restTemplate.getForEntity(url,String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    /**
     * LoginController HttpRequests
     * logout method, test cases
     */
    @Test
    public void redirectToLoginPageWithLogoutAction(){
        String url = "http://localhost:"+port+"/logout";
        ResponseEntity responseEntity = restTemplate.getForEntity(url,String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }



}
