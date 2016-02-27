package org.blub.domain;

import org.neo4j.ogm.annotation.RelationshipEntity;

@RelationshipEntity(type = "relFromOrganisationToOrganisation")
public class Organisation_organisation_relationship extends Party_relationship{
}
