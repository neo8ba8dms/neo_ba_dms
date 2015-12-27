package org.blub.repository;

import org.blub.domain.Document;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "documents", path = "documents")
public interface DocumentRepository extends GraphRepository<Document>{
}
