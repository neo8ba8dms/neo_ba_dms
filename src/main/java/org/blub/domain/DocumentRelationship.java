package org.blub.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "relToDocument")
public class DocumentRelationship {

    @GraphId private Long id;
    private String name;

    /*
        This is ignored to avoid a really nasty circular dependencies error.
        The startDocument is conceptionally always the updated document.
        I.e. it can always be inferred.
     */
    @JsonIgnore
    @StartNode private Document startDocument;

    @JsonManagedReference //fixes issue, where cyclic dependencies lead to wrong JSON-response
    @EndNode private Document endDocument;

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

    public Document getStartDocument() {
        return startDocument;
    }

    public void setStartDocument(Document startDocument) {
        this.startDocument = startDocument;
    }

    public Document getEndDocument() {
        return endDocument;
    }

    public void setEndDocument(Document endDocument) {
        this.endDocument = endDocument;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentRelationship that = (DocumentRelationship) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (startDocument != null ? !startDocument.equals(that.startDocument) : that.startDocument != null)
            return false;
        return endDocument != null ? endDocument.equals(that.endDocument) : that.endDocument == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
