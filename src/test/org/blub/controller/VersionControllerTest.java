package org.blub.controller;

import org.blub.Application;
import org.blub.domain.Document;
import org.blub.domain.Document_id;
import org.blub.repository.DocumentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@Transactional //makes shure, that nothing gets persisted(so no explicit deleting necessary)
public class VersionControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private DocumentRepository documentRepository;

    private MockMvc mockMvc;

    @Before
    public void setUp(){

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testGetVersionHistory() throws Exception {
        Document document1 = new Document();
        Document document2 = new Document();
        Document document3 = new Document();
        Document document4 = new Document();
        documentRepository.save(document1); //saving 2 times necessary, because nodes have to exist in Neo4J to get connected
        documentRepository.save(document2);
        documentRepository.save(document3);
        documentRepository.save(document4);
        document1.setSuccessorDocument(document2);
        document2.setSuccessorDocument(document3);
        document3.setSuccessorDocument(document4);
        documentRepository.save(document1);
        documentRepository.save(document2);
        documentRepository.save(document3);

       mockMvc.perform(get("/api/versionHistory/" + document4.getGraphId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[1]").exists())
                .andExpect(jsonPath("$[2]").exists())
                .andExpect(jsonPath("$[3]").exists())
                .andExpect(jsonPath("$[4]").doesNotExist());
    }

    @Test
    public void testGetOneVersion() throws Exception {
        Document document = new Document();
        Document_id document_id = new Document_id();
        document_id.setId("test-doc-id");
        document.setPrimary_document_id(document_id);
        documentRepository.save(document);

        mockMvc.perform(get("/api/versionDetail/" + document.getGraphId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.primary_document_id.id", is("test-doc-id")));
    }
}