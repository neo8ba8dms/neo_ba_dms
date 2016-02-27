package org.blub.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@JsonIdentityInfo(generator=JSOGGenerator.class)
@RelationshipEntity(type = "relFromPersonToOrganisation")
public class Person_organisation_relationship extends Party_relationship{

    @JsonIgnore
    @StartNode
    private Person relates_party;
    @EndNode
    private Organisation relating_party;

    @Override
    public Person getRelates_party() {
        return relates_party;
    }

    public void setRelates_party(Person relates_party) {
        this.relates_party = relates_party;
    }

    @Override
    public Organisation getRelating_party() {
        return relating_party;
    }

    public void setRelating_party(Organisation relating_party) {
        this.relating_party = relating_party;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Person_organisation_relationship that = (Person_organisation_relationship) o;

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
