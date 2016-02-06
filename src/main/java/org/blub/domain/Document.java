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
    @Relationship(type="referesTo")
    private Set<External_object_reference> externalObjects;
    @JsonBackReference //fixes issue, where cyclic dependencies lead to wrong JSON-response
    @Relationship(type = "referenceToNewVersion", direction = Relationship.OUTGOING)
    private Document successorDocument;
    private String pathToFile;
    private Date wasVersionedAt;
    @Relationship(type = "relToDocument")
    private Set<DocumentRelationship> documentRelationships;

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

    public Set<External_object_reference> getExternalObjects() {
        return externalObjects;
    }

    public void setExternalObjects(Set<External_object_reference> externalObjects) {
        this.externalObjects = externalObjects;
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

    public Set<DocumentRelationship> getDocumentRelationships() {
        return documentRelationships;
    }

    public void setDocumentRelationships(Set<DocumentRelationship> documentRelationships) {
        this.documentRelationships = documentRelationships;
    }
}
