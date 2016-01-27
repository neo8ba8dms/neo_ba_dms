package org.blub.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.sql.Timestamp;
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

    private Timestamp wasVersionedAt;

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

    public Timestamp getWasVersionedAt() {
        return wasVersionedAt;
    }

    public void setWasVersionedAt(Timestamp wasVersionedAt) {
        this.wasVersionedAt = wasVersionedAt;
    }
}
