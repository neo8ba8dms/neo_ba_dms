package org.blub.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@JsonIdentityInfo(generator=JSOGGenerator.class)
@RelationshipEntity(type = "relFromOrganisationToPerson")
public class Organisation_person_relationship extends Party_relationship{

    @JsonIgnore
    @StartNode
    private Organisation relates_party;

    @EndNode
    private Person relating_party;

    @Override
    public Organisation getRelates_party() {
        return relates_party;
    }

    public void setRelates_party(Organisation relates_party) {
        this.relates_party = relates_party;
    }

    @Override
    public Person getRelating_party() {
        return relating_party;
    }

    public void setRelating_party(Person relating_party) {
        this.relating_party = relating_party;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Organisation_person_relationship that = (Organisation_person_relationship) o;

        if (relates_party != null ? !relates_party.equals(that.relates_party) : that.relates_party != null)
            return false;
        return relating_party != null ? relating_party.equals(that.relating_party) : that.relating_party == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (relates_party != null ? relates_party.hashCode() : 0);
        result = 31 * result + (relating_party != null ? relating_party.hashCode() : 0);
        return result;
    }
}
