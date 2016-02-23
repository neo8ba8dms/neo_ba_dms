package org.blub.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;


/*
This class is not being used, but remains for documentation purposes.
Pressing the app into the way of the 82045 is here especially difficult.
The better solution is relating the party to the subclasses of Address.
 */
@RelationshipEntity(type = "relFromPartyToAddress")
public class Party_to_address_relationship {

    @GraphId private Long graphId;
    @StartNode private Party relates_party;
    @EndNode private Address relating_address;

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

    public Address getRelating_address() {
        return relating_address;
    }

    public void setRelating_address(Address relating_address) {
        this.relating_address = relating_address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Party_to_address_relationship that = (Party_to_address_relationship) o;

        if (graphId != null ? !graphId.equals(that.graphId) : that.graphId != null) return false;
        if (relates_party != null ? !relates_party.equals(that.relates_party) : that.relates_party != null)
            return false;
        return relating_address != null ? relating_address.equals(that.relating_address) : that.relating_address == null;

    }

    @Override
    public int hashCode() {
        int result = graphId != null ? graphId.hashCode() : 0;
        result = 31 * result + (relates_party != null ? relates_party.hashCode() : 0);
        result = 31 * result + (relating_address != null ? relating_address.hashCode() : 0);
        return result;
    }
}
