package org.blub.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@JsonIdentityInfo(generator=JSOGGenerator.class)
@NodeEntity
public class Person extends Party{

    private String last_name;
    private String first_name;

    @Relationship(type = "relFromPersonToPerson", direction = Relationship.OUTGOING)
    private Set<Person_person_relationship> person_person_relationships;

    @Relationship(type = "relFromPersonToOrganisation", direction = Relationship.OUTGOING)
    private Set<Person_organisation_relationship> person_organisation_relationships;

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public Set<Person_person_relationship> getPerson_person_relationships() {
        return person_person_relationships;
    }

    public void setPerson_person_relationships(Set<Person_person_relationship> person_person_relationships) {
        this.person_person_relationships = person_person_relationships;
    }

    public Set<Person_organisation_relationship> getPerson_organisation_relationships() {
        return person_organisation_relationships;
    }

    public void setPerson_organisation_relationships(Set<Person_organisation_relationship> person_organisation_relationships) {
        this.person_organisation_relationships = person_organisation_relationships;
    }

    //important to use the equals and hashcode from superclass here otherwise no good access of @graphId

}
