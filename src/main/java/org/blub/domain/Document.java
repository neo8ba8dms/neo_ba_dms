package org.blub.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Date;
import java.util.Set;

@NodeEntity
public class Document {

    @GraphId Long graphId; //required for neo4j(must be Long)http://docs.spring.io/spring-data/neo4j/docs/current/reference/html/#__graphid_neo4j_id_field
    private String document_id; //this is the organisation-internal id
    private String path_to_file;
    private Date created_at;

    /*
    From the 82045:"Specifies the language(s) in which a document version is produced.
        The predefined values shall be selected from the 2-letter language code as given in ISO 639-1.
        NOTE
        It should be noted that ISO 639-1 provides the language code by lower-case characters.
    Interpretation/implementation: For simplicity text field with button to add(no delete) and a div for output.
    Could of course be made much better.
     */
    private Set<String> language;

    /*
    From the 82045: "Specifies language-bound descriptive text associated with a Document_version."
    Interpretation/implementation: clicking on button "new description" make modal with ass. attributes.
    Additionally one div to show all descriptions(only ass. with one version).
     */
    @Relationship(type = "relFromDocumentToDescription")
    private Set<Description> descriptions;

    /*
    In the 82045 this is not meant to be an explicit attribute. I'm going with the SDN way, because it is more graph-like.
    But I'm trying to keep the naming-way of the 82045.
     */
    @Relationship(type="relFromDocumentToExternalObject")
    private Document_version_external_object_reference_relationship document_version_external_object_reference_relationships;
    @JsonBackReference //fixes issue, where cyclic dependencies lead to wrong JSON-response
    @Relationship(type = "referenceToNewVersion", direction = Relationship.OUTGOING)
    private Document successorDocument;

    /*
    In the 82045 this is not meant to be an explicit attribute. I'm going with the SDN way, because it is more graph-like.
    But I'm trying to keep the naming-way of the 82045.
     */
    @Relationship(type = "relFromDocumentToDocument")
    private Set<Document_relationship> document_relationships;

    ////////////////////////////////////////////////////////////////////////


    public Long getGraphId() {
        return graphId;
    }

    public void setGraphId(Long graphId) {
        this.graphId = graphId;
    }

    public String getDocument_id() {
        return document_id;
    }

    public void setDocument_id(String document_id) {
        this.document_id = document_id;
    }

    public String getPath_to_file() {
        return path_to_file;
    }

    public void setPath_to_file(String path_to_file) {
        this.path_to_file = path_to_file;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Set<String> getLanguage() {
        return language;
    }

    public void setLanguage(Set<String> language) {
        this.language = language;
    }

    public Set<Description> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Set<Description> descriptions) {
        this.descriptions = descriptions;
    }

    public Document_version_external_object_reference_relationship getDocument_version_external_object_reference_relationships() {
        return document_version_external_object_reference_relationships;
    }

    public void setDocument_version_external_object_reference_relationships(Document_version_external_object_reference_relationship document_version_external_object_reference_relationships) {
        this.document_version_external_object_reference_relationships = document_version_external_object_reference_relationships;
    }

    public Document getSuccessorDocument() {
        return successorDocument;
    }

    public void setSuccessorDocument(Document successorDocument) {
        this.successorDocument = successorDocument;
    }

    public Set<Document_relationship> getDocument_relationships() {
        return document_relationships;
    }

    public void setDocument_relationships(Set<Document_relationship> document_relationships) {
        this.document_relationships = document_relationships;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document document = (Document) o;

        if (graphId != null ? !graphId.equals(document.graphId) : document.graphId != null) return false;
        return document_id != null ? document_id.equals(document.document_id) : document.document_id == null;

    }

    @Override
    public int hashCode() {
        int result = graphId != null ? graphId.hashCode() : 0;
        result = 31 * result + (document_id != null ? document_id.hashCode() : 0);
        return result;
    }
}
