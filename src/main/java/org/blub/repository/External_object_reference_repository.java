package org.blub.repository;

import org.blub.domain.External_object_reference;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface External_object_reference_repository extends GraphRepository<External_object_reference> {

}
