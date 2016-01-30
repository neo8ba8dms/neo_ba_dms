package org.blub.repository;

import org.blub.domain.Document;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends GraphRepository<Document>{

    /*
    This returns all documents, that do not have any following Versions. I.e. the most recent documents.
    Every time you klick on update-document, this list changes.
    This query requires, that there is a label "Document", which is created by SDN.
    SDN derives it from the annotated classname of "domain.Document".
    Check for all labels in the database with:
    match (n) return distinct(labels(n));
     */
    @Query(" match (n:Document) where not((n)-[:referenceToNewVersion]->(:Document)) return n")
    Iterable<Document> allNewestDocumentVersions();
}
