package com.s2u2m.slancer.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public abstract class AbS2u2mSpringTest {

    @Autowired
    protected MockMvc mockMvc;

    protected <RT> RT httpGet(String url, Class<RT> ct) throws Exception {
        MvcResult result = mockMvc.perform(
                get(url).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(result.getResponse().getContentAsString(), ct);
    }

    protected <RT> RT httpPost(String url, Object content, Class<RT> ct)
            throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        MvcResult result = mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(content))
        ).andExpect(status().isOk()).andReturn();

        return mapper.readValue(
                result.getResponse().getContentAsString(), ct);
    }

}