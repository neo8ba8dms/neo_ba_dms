package org.blub.repository;

import org.blub.domain.Organisation;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false) //stop spring boot from exposing entities over REST automatically
public interface OrganisationRepository extends GraphRepository<Organisation>{
}
