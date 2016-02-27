package org.blub.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/*
    Does this get a separate node in neo4J? --> yes
    Needs a Relationship to classes that use it.
    Naming scheme: "relFrom<using class>ToDescription"
 */
@JsonIdentityInfo(generator=JSOGGenerator.class)
@NodeEntity
public class Description {

    @GraphId Long graphId;
    private String textual_description;
    private Description_enumeration_type type_of;
    private String language_code;

    public Long getGraphId() {
        return graphId;
    }

    public void setGraphId(Long graphId) {
        this.graphId = graphId;
    }

    public String getTextual_description() {
        return textual_description;
    }

    public void setTextual_description(String textual_description) {
        this.textual_description = textual_description;
    }

    public Description_enumeration_type getType_of() {
        return type_of;
    }

    public void setType_of(Description_enumeration_type type_of) {
        this.type_of = type_of;
    }

    public String getLanguage_code() {
        return language_code;
    }

    public void setLanguage_code(String language_code) {
        this.language_code = language_code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Description that = (Description) o;

        return graphId != null ? graphId.equals(that.graphId) : that.graphId == null;

    }

    @Override
    public int hashCode() {
        return graphId != null ? graphId.hashCode() : 0;
    }
}
