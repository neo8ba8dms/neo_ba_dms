package org.blub.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Document_id {

    @GraphId Long graphId;
    private String id;

    @Relationship(type = "relFromDocumentIdToDocumentIdDomain")
    private Document_id_domain domain;

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

    public Document_id_domain getDomain() {
        return domain;
    }

    public void setDomain(Document_id_domain domain) {
        this.domain = domain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document_id that = (Document_id) o;

        if (graphId != null ? !graphId.equals(that.graphId) : that.graphId != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return domain != null ? domain.equals(that.domain) : that.domain == null;

    }

    @Override
    public int hashCode() {
        int result = graphId != null ? graphId.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (domain != null ? domain.hashCode() : 0);
        return result;
    }
}
