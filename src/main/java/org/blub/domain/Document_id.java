package org.blub.domain;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Document_id {

    private String id;
    private Document_id_domain domain;

    //// TODO: 21.02.16 test wether this bidirectional relationship gets persisted right in neo4j(should be 2 separate edges)
    @Relationship(type = "relFromDocumentIdToDocument", direction = Relationship.OUTGOING)
    private Document identifies;


}
