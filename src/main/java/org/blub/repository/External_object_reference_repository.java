package org.blub.repository;

import org.blub.domain.External_object_reference;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false) //stop spring boot from exposing entities over REST automatically
public interface External_object_reference_repository extends GraphRepository<External_object_reference> {

}
