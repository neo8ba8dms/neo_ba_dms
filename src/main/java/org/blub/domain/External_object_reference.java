package org.blub.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class External_object_reference {

    @GraphId Long id;

    //// TODO: 19.01.16 Datenmodel und Implementierung zusammenführen

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*
        equals and hashCode are Intellij-generated
        Necessary to avoid error, where SDN duplicates this object in document.
        Without this SDN duplicates the External_object_reference on "documentRepository.save()",
        which probably is an error and definitely leads to an error in this app.
     */
    //
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        External_object_reference that = (External_object_reference) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
