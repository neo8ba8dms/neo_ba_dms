package org.blub.repository;

import org.blub.domain.Document;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends GraphRepository<Document>{
}
