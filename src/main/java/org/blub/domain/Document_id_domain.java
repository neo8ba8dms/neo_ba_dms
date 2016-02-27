package org.blub.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/*
From 82045: The Document_id_domain is a collection of attributes providing information about the context of the
identification of a document.
 */
/*
This would be a great candidate for a non-nodeEntity-class, but SDN does not support that:
https://github.com/neo4j/neo4j-ogm/issues/57
Therefore I will model all domain-objects as nodes(or relationships).
 */
@JsonIdentityInfo(generator=JSOGGenerator.class)
@NodeEntity
public class Document_id_domain {

    @GraphId Long graphId;
    private String id; //Specifies the identification of a domain in which a document identification applies.
    private String description;

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

        Document_id_domain that = (Document_id_domain) o;

        if (graphId != null ? !graphId.equals(that.graphId) : that.graphId != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;

    }

    @Override
    public int hashCode() {
        int result = graphId != null ? graphId.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
