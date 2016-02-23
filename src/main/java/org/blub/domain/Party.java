package org.blub.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity //gives extending entities the additional label: ":Party"
public abstract class Party {

    @GraphId private Long graphId;

    @Relationship(type = "relFromPartyToPostalAddress")
    private Postal_address postal_address;

    @Relationship(type = "relFromPartyToElectronicAddress")
    private Electronic_address electronic_address;

    @Relationship(type = "relFromPartyToPhysicalAddress")
    private Physical_address physical_address;

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

        return graphId != null ? graphId.equals(party.graphId) : party.graphId == null;

    }

    @Override
    public int hashCode() {
        return graphId != null ? graphId.hashCode() : 0;
    }
}
