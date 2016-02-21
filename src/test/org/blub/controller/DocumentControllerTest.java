package org.blub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.blub.Application;
import org.blub.domain.Document;
import org.blub.domain.External_object_reference;
import org.blub.repository.DocumentRepository;
import org.blub.repository.External_object_reference_repository;
import org.blub.service.DocumentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@Transactional //makes shure, that nothing gets persisted(so no explicit deleting necessary)
public class DocumentControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private External_object_reference_repository external_object_reference_repository;

    private MockMvc mockMvc;

    @Mock
    private DocumentService documentService;

    @Before
    public void setUp(){

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        MockitoAnnotations.initMocks(this);
    }

    /*
        There could be more data in neo4J than just the testdata created in this test.
     */
    @Test
    public void testListMostRecentDocumentsOfEachVersioningPath() throws Exception {
        Document document1_v0 = new Document(); //first document version zero
        Document document2_v0 = new Document();
        Document document3_v0 = new Document();
        Document document3_v1 = new Document();
        Document document3_v2 = new Document();
        document1_v0.setDocument_id("shouldBeReturned1");
        document2_v0.setDocument_id("shouldBeReturned2");
        document3_v2.setDocument_id("shouldBeReturned3");
        documentRepository.save(document1_v0);
        documentRepository.save(document2_v0);
        documentRepository.save(document3_v0);
        documentRepository.save(document3_v1);
        documentRepository.save(document3_v2);
        document3_v0.setSuccessorDocument(document3_v1);
        document3_v1.setSuccessorDocument(document3_v2);
        documentRepository.save(document3_v0);
        documentRepository.save(document3_v1);

        mockMvc.perform(get("/api/documents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.document_id=='shouldBeReturned1')]").exists()) //search in all returned documents
                .andExpect(jsonPath("$[?(@.document_id=='shouldBeReturned2')]").exists()) //for the given document_id
                .andExpect(jsonPath("$[?(@.document_id=='shouldBeReturned3')]").exists()) //return this document if true
                .andExpect(jsonPath("$[?(@.document_id=='thisIsWrong')]").doesNotExist());
    }

    @Test
    public void testCreate() throws Exception {
        Document document = new Document();
        document.setDocument_id("blubTestId");
        External_object_reference eor = new External_object_reference();
        external_object_reference_repository.save(eor);
        HashSet<External_object_reference> eorSet = new HashSet<>();
        eorSet.add(eor);
        document.setExternal_object_references(eorSet);
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/api/documents")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(document)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.document_id", is("blubTestId")))
                .andExpect(jsonPath("$.external_object_references[0]").exists())
                .andExpect(jsonPath("$.external_object_references[1]").doesNotExist())
                .andExpect(jsonPath("$.language").doesNotExist())
                .andExpect(jsonPath("$.created_at").exists());
    }

    @Test
    public void testFind() throws Exception {
        Document document = new Document();
        document.setDocument_id("blubId");
        documentRepository.save(document);

        mockMvc.perform(get("/api/documents/" + document.getGraphId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.document_id", is("blubId")));
    }

    @Test
    public void testDelete() throws Exception {
        Document document = new Document();
        documentRepository.save(document);

        assertThat(documentRepository.findOne(document.getGraphId()), is(notNullValue()));
        mockMvc.perform(delete("/api/documents/" + document.getGraphId()))
                .andExpect(status().isOk());
        assertThat(documentRepository.findOne(document.getGraphId()), is(nullValue()));
    }

    //without fileupload(which is difficult)
    @Test
    public void testUpdate() throws Exception {
        Document document = new Document();
        document.setDocument_id("blubTestId");
        External_object_reference eor = new External_object_reference();
        eor.setDescription("testDescription");
        external_object_reference_repository.save(eor);
        HashSet<External_object_reference> eorSet = new HashSet<>();
        eorSet.add(eor);
        document.setExternal_object_references(eorSet);
        documentRepository.save(document);
        ObjectMapper mapper = new ObjectMapper();

        Document updatedDocument = new Document();
        updatedDocument.setDocument_id("updatedId");
        updatedDocument.setGraphId(document.getGraphId());

        mockMvc.perform(post("/api/documents/" + document.getGraphId())
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("documentString", mapper.writeValueAsString(updatedDocument)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.document_id", is("updatedId")));

        assertThat(documentRepository.findOne(updatedDocument.getGraphId()),
                is(equalTo(documentRepository.findOne(document.getGraphId()))));
    }

    // TODO: 21.02.16 fix it(view comment)
    /*
    Does go into the DocumentService.downloadDocument--method and throws a FileNotFoundException.
    What it should do is replace the method with nothing.
     */
    @Test
    public void testDownloadFile() throws Exception {
        MockHttpServletResponse response = new MockHttpServletResponse();
//        Mockito.doNothing().when(documentService).downloadDocument(response, "documentrepository", "documentNameTest",
//                "fileNameTest", "txt");
        mockMvc.perform(get("/api/documents/download/documentrepository/documentNameTest/fileNameTest.txt"))
                .andExpect(status().isOk());
    }
}