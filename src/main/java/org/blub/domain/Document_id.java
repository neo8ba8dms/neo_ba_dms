package org.blub.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Document_id {

    @GraphId Long graphId;
    private String id;
    private Document_id_domain domain;

    //// TODO: 21.02.16 test wether this bidirectional relationship gets persisted right in neo4j(should be 2 separate edges)
    @Relationship(type = "relFromDocumentIdToDocument", direction = Relationship.OUTGOING)
    private Document identifies;

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

    public Document getIdentifies() {
        return identifies;
    }

    public void setIdentifies(Document identifies) {
        this.identifies = identifies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document_id that = (Document_id) o;

        if (graphId != null ? !graphId.equals(that.graphId) : that.graphId != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (domain != null ? !domain.equals(that.domain) : that.domain != null) return false;
        return identifies != null ? identifies.equals(that.identifies) : that.identifies == null;

    }

    @Override
    public int hashCode() {
        int result = graphId != null ? graphId.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (domain != null ? domain.hashCode() : 0);
        result = 31 * result + (identifies != null ? identifies.hashCode() : 0);
        return result;
    }
}
