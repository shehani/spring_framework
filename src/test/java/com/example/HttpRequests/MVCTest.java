package com.example.HttpRequests;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MVCTest {

    @Autowired
    private MockMvc mockMvc;

    @Value(value = "${local.server.port}")
    private int port;

    /**
     * AdminController HttpRequests
     * displayChurch method, test cases
     */
    @Test
    @Transactional // to fix "org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: com.example.model.Church.personList: could not initialize proxy - no Session"
    public void returnChurchPage() throws Exception{
        String username = "shehan@gmail.com";
        String password = "shehan";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .request(HttpMethod.GET, "http://localhost:"+port+"/admin/displayChurch")
                        .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder()
                                .encodeToString((username + ":" + password).getBytes())))
                .andReturn();
        ModelAndView modelAndView = mvcResult.getModelAndView();
        assertThat(modelAndView).isNotNull();
        assertThat(modelAndView.getViewName()).isEqualTo("church.html");


    }
}
