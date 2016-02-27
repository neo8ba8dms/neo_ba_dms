package org.blub.domain;

import org.neo4j.ogm.annotation.RelationshipEntity;

@RelationshipEntity(type = "relFromOrganisationToPerson")
public class Organisation_person_relationship extends Party_relationship{
}
