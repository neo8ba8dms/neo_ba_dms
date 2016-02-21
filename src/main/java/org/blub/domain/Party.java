package org.blub.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity //gives extending entities the additional label: ":Party"
public abstract class Party {

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

        Party party = (Party) o;

        return graphId != null ? graphId.equals(party.graphId) : party.graphId == null;

    }

    @Override
    public int hashCode() {
        return graphId != null ? graphId.hashCode() : 0;
    }
}
