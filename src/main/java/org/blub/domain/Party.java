package org.blub.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@JsonIdentityInfo(generator=JSOGGenerator.class)
@NodeEntity //gives extending entities the additional label: ":Party"
public abstract class Party {

    @GraphId private Long graphId;

    @Relationship(type = "relFromPartyToPostalAddress")
    private Postal_address postal_address;

    @Relationship(type = "relFromPartyToElectronicAddress")
    private Electronic_address electronic_address;

    @Relationship(type = "relFromPartyToPhysicalAddress")
    private Physical_address physical_address;

    //private Set<Party_relationship> party_relations; deleted because of decision to handle relations concrete

    public Long getGraphId() {
        return graphId;
    }

    public void setGraphId(Long graphId) {
        this.graphId = graphId;
    }

    public Postal_address getPostal_address() {
        return postal_address;
    }

    public void setPostal_address(Postal_address postal_address) {
        this.postal_address = postal_address;
    }

    public Electronic_address getElectronic_address() {
        return electronic_address;
    }

    public void setElectronic_address(Electronic_address electronic_address) {
        this.electronic_address = electronic_address;
    }

    public Physical_address getPhysical_address() {
        return physical_address;
    }

    public void setPhysical_address(Physical_address physical_address) {
        this.physical_address = physical_address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Party party = (Party) o;

        if (graphId != null ? !graphId.equals(party.graphId) : party.graphId != null) return false;
        if (postal_address != null ? !postal_address.equals(party.postal_address) : party.postal_address != null)
            return false;
        if (electronic_address != null ? !electronic_address.equals(party.electronic_address) : party.electronic_address != null)
            return false;
        return physical_address != null ? physical_address.equals(party.physical_address) : party.physical_address == null;

    }

    @Override
    public int hashCode() {
        int result = graphId != null ? graphId.hashCode() : 0;
        result = 31 * result + (postal_address != null ? postal_address.hashCode() : 0);
        result = 31 * result + (electronic_address != null ? electronic_address.hashCode() : 0);
        result = 31 * result + (physical_address != null ? physical_address.hashCode() : 0);
        return result;
    }
}
