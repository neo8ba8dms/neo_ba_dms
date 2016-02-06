package org.blub.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Date;

/*
    Does this get a separate node in neo4J? --> yes
    Needs a Relationship to classes that use it.
    Naming scheme: "relFrom<using class>ToLogbookEntry"
 */
@NodeEntity
public class Logbook_entry {

    @GraphId Long graphId;
    private String description;
    private Document is_related_to;
    private Date date_and_time;

    /////////////////////////////////////////

    public Long getGraphId() {
        return graphId;
    }

    public void setGraphId(Long graphId) {
        this.graphId = graphId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Document getIs_related_to() {
        return is_related_to;
    }

    public void setIs_related_to(Document is_related_to) {
        this.is_related_to = is_related_to;
    }

    public Date getDate_and_time() {
        return date_and_time;
    }

    public void setDate_and_time(Date date_and_time) {
        this.date_and_time = date_and_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Logbook_entry that = (Logbook_entry) o;

        return graphId != null ? graphId.equals(that.graphId) : that.graphId == null;

    }

    @Override
    public int hashCode() {
        return graphId != null ? graphId.hashCode() : 0;
    }
}
