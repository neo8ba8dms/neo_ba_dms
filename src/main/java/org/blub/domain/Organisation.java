package org.blub.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@JsonIdentityInfo(generator=JSOGGenerator.class)
@NodeEntity
public class Organisation extends Party{

    private String name;

    @Relationship(type = "relFromOrganisationToPerson")
    private Set<Organisation_person_relationship> organisation_person_relationships;

    @Relationship(type = "relFromOrganisationToOrganisation")
    private Set<Organisation_organisation_relationship> organisation_organisation_relationships;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Organisation_person_relationship> getOrganisation_person_relationships() {
        return organisation_person_relationships;
    }

    public void setOrganisation_person_relationships(Set<Organisation_person_relationship> organisation_person_relationships) {
        this.organisation_person_relationships = organisation_person_relationships;
    }

    public Set<Organisation_organisation_relationship> getOrganisation_organisation_relationships() {
        return organisation_organisation_relationships;
    }

    public void setOrganisation_organisation_relationships(Set<Organisation_organisation_relationship> organisation_organisation_relationships) {
        this.organisation_organisation_relationships = organisation_organisation_relationships;
    }

    //important to use the equals and hashcode from superclass here otherwise no good access of @graphId

}
