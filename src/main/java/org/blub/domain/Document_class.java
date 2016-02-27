package org.blub.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Set;

/*
    Does this get a separate node in neo4J? --> yes
    Needs a Relationship to classes that use it.
    Naming scheme: "relFrom<using class>ToDocumentClass"
 */
@JsonIdentityInfo(generator=JSOGGenerator.class)
@NodeEntity
public class Document_class {

    @GraphId Long graphId;
    private String id; // the values in the IEC 61355
    private Classification_system uses_classification_system = new Classification_system(); //there is now only one
    private String description; //changed from Set<Description> to String for simplicity/clarity

    public Long getGraphId() {
        return graphId;
    }

    public void setGraphId(Long graphId) {
        this.graphId = graphId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Classification_system getUses_classification_system() {
        return uses_classification_system;
    }

    public void setUses_classification_system(Classification_system uses_classification_system) {
        this.uses_classification_system = uses_classification_system;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document_class that = (Document_class) o;

        if (graphId != null ? !graphId.equals(that.graphId) : that.graphId != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (uses_classification_system != null ? !uses_classification_system.equals(that.uses_classification_system) : that.uses_classification_system != null)
            return false;
        return description != null ? description.equals(that.description) : that.description == null;

    }

    @Override
    public int hashCode() {
        int result = graphId != null ? graphId.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (uses_classification_system != null ? uses_classification_system.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
