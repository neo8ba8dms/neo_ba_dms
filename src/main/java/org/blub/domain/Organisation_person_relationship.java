package org.blub.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.RelationshipEntity;

@JsonIdentityInfo(generator=JSOGGenerator.class)
@RelationshipEntity(type = "relFromOrganisationToPerson")
public class Organisation_person_relationship extends Party_relationship{
}
