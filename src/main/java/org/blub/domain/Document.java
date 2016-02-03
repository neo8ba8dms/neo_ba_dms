package org.blub.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Date;
import java.util.Set;

@NodeEntity
public class Document {

    @GraphId Long id;
    private String name;
    @Relationship(type="referesTo")
    private Set<External_object_reference> externalObjects;
    @Relationship(type = "referenceToNewVersion")
    private Document successorDocument;
    private String pathToFile;
    private Date wasVersionedAt;
    private Set<DocumentRelationship> documentRelationships;

    ////////////////////////////////////////////////////////////////////////

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
