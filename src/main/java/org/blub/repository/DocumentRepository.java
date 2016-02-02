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


    /*
    This returns the set of versions to a given id(of one newest version). It is unordered.
    The ordering only takes place with an angular-filter in the html.
    This was surprisingly difficult(2 hours) an looks pretty nasty, so feel free to make it better.
     */
    @Query("match (n:Document)-[:referenceToNewVersion*0..]->(m:Document) where id(m)={0}  " +
            "with collect(n) + collect(m) as result unwind result as result2 return result2")
    Iterable<Document> versionHistoryForADocument(Long id);
}
