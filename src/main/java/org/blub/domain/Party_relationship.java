package org.blub.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/*
Implementing the party-relations, by staying close to the the 82045 turned out to be difficult.
One part of the solution is to make this class abstract and handle concrete relations afterwards.
The probably better solution would be to delete this class and just use annotations for the concrete relationships.
But since staying close to the 82045 is a big part of this work, this is the way to go.
 */
@JsonIdentityInfo(generator=JSOGGenerator.class)
@RelationshipEntity(type = "relFromPartyToParty")
public abstract class Party_relationship {

    @GraphId private Long graphId;
    @JsonIgnore //this one does not get inherited by subclasses and must therefore be added manually every time
    private Party relates_party;
    private Party relating_party;

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

    public Party getRelating_party() {
        return relating_party;
    }

    public void setRelating_party(Party relating_party) {
        this.relating_party = relating_party;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Party_relationship that = (Party_relationship) o;

        if (graphId != null ? !graphId.equals(that.graphId) : that.graphId != null) return false;
        if (relates_party != null ? !relates_party.equals(that.relates_party) : that.relates_party != null)
            return false;
        return relating_party != null ? relating_party.equals(that.relating_party) : that.relating_party == null;

    }

    @Override
    public int hashCode() {
        int result = graphId != null ? graphId.hashCode() : 0;
        result = 31 * result + (relates_party != null ? relates_party.hashCode() : 0);
        result = 31 * result + (relating_party != null ? relating_party.hashCode() : 0);
        return result;
    }
}
