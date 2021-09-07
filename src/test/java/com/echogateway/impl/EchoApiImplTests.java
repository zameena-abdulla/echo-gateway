package com.echogateway.impl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import com.echogateway.EchoApiController;
import com.echogateway.WithMockOAuth2Scope;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EchoApiImplTests {
	
	private MockMvc mockMvc;

    @Autowired
    private EchoApiController echoApiController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(echoApiController)
                    .build();
    }
    
    @Test
    @WithMockOAuth2Scope(scope = "vz_test")
    public void testSchedule() throws Exception {
    	System.out.println(mockMvc);
    	 this.mockMvc.perform(get("/echo"))
         .andExpect(status().isOk())
         .andReturn();                
    }
    
    @Test(expected = NestedServletException.class)
    @WithMockOAuth2Scope(scope = "vz_test1")
    public void testSchedule1() throws Exception {
    	System.out.println(mockMvc);
    	 this.mockMvc.perform(get("/echo"))
         .andReturn();                
    }
}

