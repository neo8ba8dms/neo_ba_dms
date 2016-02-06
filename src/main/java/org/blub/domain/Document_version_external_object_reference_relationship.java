package org.blub.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "relFromDocumentToExternalObject")
public class Document_version_external_object_reference_relationship {

    @GraphId Long graphId;
    @StartNode Document relates;
    @EndNode External_object_reference relating;

    /////////////////////////////////////////////////

    public Long getGraphId() {
        return graphId;
    }

    public void setGraphId(Long graphId) {
        this.graphId = graphId;
    }

    public Document getRelates() {
        return relates;
    }

    public void setRelates(Document relates) {
        this.relates = relates;
    }

    public External_object_reference getRelating() {
        return relating;
    }

    public void setRelating(External_object_reference relating) {
        this.relating = relating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document_version_external_object_reference_relationship that = (Document_version_external_object_reference_relationship) o;

        return graphId != null ? graphId.equals(that.graphId) : that.graphId == null;

    }

    @Override
    public int hashCode() {
        return graphId != null ? graphId.hashCode() : 0;
    }
}
