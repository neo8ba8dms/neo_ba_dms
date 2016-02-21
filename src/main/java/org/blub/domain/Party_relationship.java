package org.blub.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "relFromPartyToParty")
public class Party_relationship {

    @GraphId private Long graphId;
    @StartNode private Party relates_party;
    @EndNode private Party relating_relating;

    public Long getGraphId() {
        return graphId;
    }

    public void setGraphId(Long graphId) {
        this.graphId = graphId;
    }

    public Party getRelates_party() {
        return relates_party;
    }

    public void setRelates_party(Party relates_party) {
        this.relates_party = relates_party;
    }

    public Party getRelating_relating() {
        return relating_relating;
    }

    public void setRelating_relating(Party relating_relating) {
        this.relating_relating = relating_relating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Party_relationship that = (Party_relationship) o;

        if (graphId != null ? !graphId.equals(that.graphId) : that.graphId != null) return false;
        if (relates_party != null ? !relates_party.equals(that.relates_party) : that.relates_party != null)
            return false;
        return relating_relating != null ? relating_relating.equals(that.relating_relating) : that.relating_relating == null;

    }

    @Override
    public int hashCode() {
        int result = graphId != null ? graphId.hashCode() : 0;
        result = 31 * result + (relates_party != null ? relates_party.hashCode() : 0);
        result = 31 * result + (relating_relating != null ? relating_relating.hashCode() : 0);
        return result;
    }
}
