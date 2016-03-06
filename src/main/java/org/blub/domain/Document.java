package org.blub.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Date;
import java.util.Set;

@JsonIdentityInfo(generator=JSOGGenerator.class)
@NodeEntity
public class Document {

    @GraphId Long graphId; //required for neo4j(must be Long)http://docs.spring.io/spring-data/neo4j/docs/current/reference/html/#__graphid_neo4j_id_field
    @Relationship(type = "relFromDocumentToDocumentId")
    private Document_id primary_document_id; //this is the organisation-internal id
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
    Interpretation/implementation: clicking on button "new description" make modal with assosiated attributes.
    Additionally one div to show all descriptions(only assosiated with one version).
     */
    @Relationship(type = "relFromDocumentToDescription", direction = Relationship.OUTGOING)
    private Set<Description> descriptions;

    /*
    In the 82045 this is not meant to be an explicit attribute. I'm going with the SDN way, because it is more graph-like.
    But I'm trying to keep the naming-way of the 82045.
     */
    @Relationship(type="relFromDocumentToExternalObject", direction = Relationship.OUTGOING)
    private Set<External_object_reference> external_object_references;
    @JsonIgnore
    @Relationship(type = "referenceToNewVersion", direction = Relationship.OUTGOING)
    private Document successorDocument;

    /*
    In the 82045 this is not meant to be an explicit attribute. I'm going with the SDN way, because it is more graph-like.
    But I'm trying to keep the naming-way of the 82045.
     */
    @Relationship(type = "relFromDocumentToDocument", direction = Relationship.OUTGOING)
    private Set<Document_relationship> document_relationships;

    @Relationship(type = "relFromDocumentToDocumentClass", direction = Relationship.OUTGOING)
    private Set<Document_class> classified_as; //values of IEC 61355
    ////////////////////////////////////////////////////////////////////////


    public Long getGraphId() {
        return graphId;
    }

    public void setGraphId(Long graphId) {
        this.graphId = graphId;
    }

    public Document_id getPrimary_document_id() {
        return primary_document_id;
    }

    public void setPrimary_document_id(Document_id primary_document_id) {
        this.primary_document_id = primary_document_id;
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

    public Set<External_object_reference> getExternal_object_references() {
        return external_object_references;
    }

    public void setExternal_object_references(Set<External_object_reference> external_object_references) {
        this.external_object_references = external_object_references;
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

    public Set<Document_class> getClassified_as() {
        return classified_as;
    }

    public void setClassified_as(Set<Document_class> classified_as) {
        this.classified_as = classified_as;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document document = (Document) o;

        if (graphId != null ? !graphId.equals(document.graphId) : document.graphId != null) return false;
        if (primary_document_id != null ? !primary_document_id.equals(document.primary_document_id) : document.primary_document_id != null)
            return false;
        return created_at != null ? created_at.equals(document.created_at) : document.created_at == null;

    }

    @Override
    public int hashCode() {
        int result = graphId != null ? graphId.hashCode() : 0;
        result = 31 * result + (primary_document_id != null ? primary_document_id.hashCode() : 0);
        result = 31 * result + (created_at != null ? created_at.hashCode() : 0);
        return result;
    }
}
