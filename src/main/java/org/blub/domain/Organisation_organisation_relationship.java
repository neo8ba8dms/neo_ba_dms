package org.blub.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.RelationshipEntity;

@JsonIdentityInfo(generator=JSOGGenerator.class)
@RelationshipEntity(type = "relFromOrganisationToOrganisation")
public class Organisation_organisation_relationship extends Party_relationship{
}
