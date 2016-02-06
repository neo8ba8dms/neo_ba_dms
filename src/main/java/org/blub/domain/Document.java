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

    /*
    In the 82045 this is not meant to be an explicit attribute. I'm going with the SDN way, because it is more graph-like.
    But I'm trying to keep the naming-way of the 82045.
     */
    @Relationship(type="relFromDocumentToExternalObject")
    private Document_version_external_object_reference_relationship document_version_external_object_reference_relationships;
    @JsonBackReference //fixes issue, where cyclic dependencies lead to wrong JSON-response
    @Relationship(type = "referenceToNewVersion", direction = Relationship.OUTGOING)
    private Document successorDocument;
    private String pathToFile;
    private Date wasVersionedAt;
    @Relationship(type = "relFromDocumentToDocument")
    private Set<Document_relationship> documentRelationships;

    ////////////////////////////////////////////////////////////////////////

    public Long getId() {
        return graphId;
    }

    public void setId(Long id) {
        this.graphId = id;
    }

    public String getDocument_id() {
        return document_id;
    }

    public void setDocument_id(String document_id) {
        this.document_id = document_id;
    }

    public Document_version_external_object_reference_relationship getDocument_version_external_object_reference_relationships() {
        return document_version_external_object_reference_relationships;
    }

    public void setDocument_version_external_object_reference_relationships(Document_version_external_object_reference_relationship document_version_external_object_reference_relationships) {
        this.document_version_external_object_reference_relationships = document_version_external_object_reference_relationships;
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public Document getSuccessorDocument() {
        return successorDocument;
    }

    public void setSuccessorDocument(Document successorDocument) {
        this.successorDocument = successorDocument;
    }

    public Date getWasVersionedAt() {
        return wasVersionedAt;
    }

    public void setWasVersionedAt(Date wasVersionedAt) {
        this.wasVersionedAt = wasVersionedAt;
    }

    public Set<Document_relationship> getDocumentRelationships() {
        return documentRelationships;
    }

    public void setDocumentRelationships(Set<Document_relationship> documentRelationships) {
        this.documentRelationships = documentRelationships;
    }
}
