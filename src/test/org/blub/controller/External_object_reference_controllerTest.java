package org.blub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.blub.Application;
import org.blub.domain.External_object_reference;
import org.blub.repository.External_object_reference_repository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
    @Transactional makes spring create a proxy for the database.
    Every writing-action is executed against this proxy.
    Reading happens still from real database(and/or proxy).
    This ensures a consistent database, but is not as good as testing against the real database.
    I'm lacking the deep understanding of this, so the previous text may be incorrect.
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@Transactional //makes shure, that nothing gets persisted(so no explicit deleting necessary)
public class External_object_reference_controllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private External_object_reference_repository external_object_reference_repository;

    private MockMvc mockMvc;

    @Before
    public void setUp(){

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testList() throws Exception {
        //create 2 entities, to make shure, there are at least 2 available
        External_object_reference eor1 = new External_object_reference();
        eor1.setDescription("description1");
        eor1.setId("myId");
        eor1.setType("myType");
        External_object_reference eor2 = new External_object_reference();
        eor2.setDescription("description2");
        eor2.setId("myId2");
        eor2.setType("myType2");
        external_object_reference_repository.save(eor1);
        external_object_reference_repository.save(eor2);

        mockMvc.perform(get("/api/eor")).andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").exists())
        .andExpect(jsonPath("$[1].id").exists())
        .andExpect(jsonPath("$[0].blubtest").doesNotExist()); //some key, that is not in database
    }

    @Test
    public void testCreate() throws Exception {
        External_object_reference eor = new External_object_reference();
        eor.setDescription("Description1");
        eor.setId("myId");
        eor.setType("myType");
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/api/eor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(eor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("myId")));
    }

    @Test
    public void testFind() throws Exception {
        External_object_reference eor = new External_object_reference();
        eor.setDescription("Description1");
        eor.setId("myId");
        eor.setType("myType");
        external_object_reference_repository.save(eor);

        mockMvc.perform(get("/api/eor/" + eor.getGraphId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("myId")));
    }

    @Test
    public void testDelete() throws Exception {
        External_object_reference eor = new External_object_reference();
        eor.setDescription("Description1");
        eor.setId("myId");
        eor.setType("myType");
        external_object_reference_repository.save(eor);

        mockMvc.perform(delete("/api/eor/" + eor.getGraphId()))
                .andExpect(status().isOk());
        Object deletedObjectShouldBeNull = external_object_reference_repository.findOne(eor.getGraphId());
        assertThat(deletedObjectShouldBeNull, is(nullValue()));
    }

    @Test
    public void testUpdate() throws Exception {
        External_object_reference eor = new External_object_reference();
        eor.setDescription("Description1");
        eor.setId("myId");
        eor.setType("myType");
        external_object_reference_repository.save(eor);
        External_object_reference updatedEor = new External_object_reference();
        updatedEor.setDescription("updatedDescription");
        updatedEor.setId("updatedId");
        updatedEor.setType("updatedType");
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(put("/api/eor/"+ eor.getGraphId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updatedEor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("updatedId")));
    }
}