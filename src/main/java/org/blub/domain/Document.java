package org.blub.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@NodeEntity
public class Document {

    @GraphId Long id;

    private String name;

    @Relationship(type="referesTo")
    private Set<External_object_reference> externalObjects;

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
}
