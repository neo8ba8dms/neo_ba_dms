package org.blub.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public abstract class Address {

    @GraphId private Long graphId;

    public Long getGraphId() {
        return graphId;
    }

    public void setGraphId(Long graphId) {
        this.graphId = graphId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        return graphId != null ? graphId.equals(address.graphId) : address.graphId == null;

    }

    @Override
    public int hashCode() {
        return graphId != null ? graphId.hashCode() : 0;
    }
}
