package org.blub.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "relFromDocumentToDocument")
public class Document_relationship {

    @GraphId private Long graphId;
    private Document_relationship_type relation_type;

    /*
        This is ignored to avoid a really nasty circular dependencies error.
        The relates_document is conceptionally always the updated document.
        I.e. it can always be inferred.
     */
    @JsonIgnore
    @StartNode private Document relates_document;

    @JsonManagedReference //fixes issue, where cyclic dependencies lead to wrong JSON-response
    @EndNode private Document relating_document;

    public Long getGraphId() {
        return graphId;
    }

    public void setGraphId(Long graphId) {
        this.graphId = graphId;
    }

    public Document_relationship_type getRelation_type() {
        return relation_type;
    }

    public void setRelation_type(Document_relationship_type relation_type) {
        this.relation_type = relation_type;
    }

    public Document getRelates_document() {
        return relates_document;
    }

    public void setRelates_document(Document relates_document) {
        this.relates_document = relates_document;
    }

    public Document getRelating_document() {
        return relating_document;
    }

    public void setRelating_document(Document relating_document) {
        this.relating_document = relating_document;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document_relationship that = (Document_relationship) o;

        if (graphId != null ? !graphId.equals(that.graphId) : that.graphId != null) return false;
        if (relation_type != null ? !relation_type.equals(that.relation_type) : that.relation_type != null) return false;
        if (relates_document != null ? !relates_document.equals(that.relates_document) : that.relates_document != null)
            return false;
        return relating_document != null ? relating_document.equals(that.relating_document) : that.relating_document == null;

    }

    @Override
    public int hashCode() {
        int result = graphId != null ? graphId.hashCode() : 0;
        result = 31 * result + (relation_type != null ? relation_type.hashCode() : 0);
        return result;
    }
}
