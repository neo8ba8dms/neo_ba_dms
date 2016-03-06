package org.blub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.blub.Application;
import org.blub.domain.*;
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

        //handle new document-id's
        Document_id_domain document_id_domain = new Document_id_domain();
        document_id_domain.setId("document-id-domain-example");
        Document_id document_id1 = new Document_id();
        document_id1.setDomain(document_id_domain);
        document_id1.setId("doc-id-1");
        Document_id document_id2 = new Document_id();
        document_id2.setDomain(document_id_domain);
        document_id2.setId("doc-id-2");
        Document_id document_id3 = new Document_id();
        document_id3.setDomain(document_id_domain);
        document_id3.setId("doc-id-3");

        document1_v0.setPrimary_document_id(document_id1);
        document2_v0.setPrimary_document_id(document_id2);
        document3_v2.setPrimary_document_id(document_id3);
        //end handle new document-id's

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
                .andExpect(jsonPath("$[?(@.primary_document_id.id=='doc-id-1')]").exists()) //search in all returned documents
                .andExpect(jsonPath("$[?(@.primary_document_id.id=='doc-id-2')]").exists()) //for the given document_id
                .andExpect(jsonPath("$[?(@.primary_document_id.id=='doc-id-3')]").exists()) //return this document if true
                .andExpect(jsonPath("$[?(@.primary_document_id.id=='this is wrong')]").doesNotExist());
    }

    @Test
    public void testCreate() throws Exception {
        Document document = new Document();
        Document_id document_id = new Document_id();
        document_id.setId("test-doc-id");
        document.setPrimary_document_id(document_id);

        //in real app this gets created automatically by marshaller
        document.setDocument_relationships(new HashSet<Document_relationship>());
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
                .andExpect(jsonPath("$.primary_document_id.id", is("test-doc-id")))
                .andExpect(jsonPath("$.external_object_references[0]").exists())
                .andExpect(jsonPath("$.external_object_references[1]").doesNotExist())
                .andExpect(jsonPath("$.language").doesNotExist())
                .andExpect(jsonPath("$.created_at").exists());
    }

    @Test
    public void testFind() throws Exception {
        Document document = new Document();
        Document_id document_id = new Document_id();
        document_id.setId("test-doc-id");
        document.setPrimary_document_id(document_id);
        documentRepository.save(document);

        mockMvc.perform(get("/api/documents/" + document.getGraphId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.primary_document_id.id", is("test-doc-id")));
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
        Document_id document_id = new Document_id();
        document_id.setId("test-doc-id");
        document.setPrimary_document_id(document_id);
        External_object_reference eor = new External_object_reference();
        eor.setDescription("testDescription");
        external_object_reference_repository.save(eor);
        HashSet<External_object_reference> eorSet = new HashSet<>();
        eorSet.add(eor);
        document.setExternal_object_references(eorSet);
        documentRepository.save(document);
        ObjectMapper mapper = new ObjectMapper();

        Document updatedDocument = new Document();
        Document_id document_id_updated = new Document_id();
        document_id_updated.setId("test-doc-id-updated");
        updatedDocument.setPrimary_document_id(document_id_updated);
        updatedDocument.setGraphId(document.getGraphId());

        mockMvc.perform(post("/api/documents/" + document.getGraphId())
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("documentString", mapper.writeValueAsString(updatedDocument)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.primary_document_id.id", is("test-doc-id-updated")));

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