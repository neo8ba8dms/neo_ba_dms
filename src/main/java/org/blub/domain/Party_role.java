package org.blub.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@JsonIdentityInfo(generator=JSOGGenerator.class)
@NodeEntity
public class Party_role {

    @GraphId private Long graphId;
    private Role_type role;

    @Relationship(type = "relFromPartyRoleToDocument")
    private Set<Document> referes_to;

    private Party is_role_of;

    public Long getGraphId() {
        return graphId;
    }

    public void setGraphId(Long graphId) {
        this.graphId = graphId;
    }

    public Role_type getRole() {
        return role;
    }

    public void setRole(Role_type role) {
        this.role = role;
    }

    public Set<Document> getReferes_to() {
        return referes_to;
    }

    public void setReferes_to(Set<Document> referes_to) {
        this.referes_to = referes_to;
    }

    public Party getIs_role_of() {
        return is_role_of;
    }

    public void setIs_role_of(Party is_role_of) {
        this.is_role_of = is_role_of;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Party_role that = (Party_role) o;

        if (graphId != null ? !graphId.equals(that.graphId) : that.graphId != null) return false;
        if (role != that.role) return false;
        if (referes_to != null ? !referes_to.equals(that.referes_to) : that.referes_to != null) return false;
        return is_role_of != null ? is_role_of.equals(that.is_role_of) : that.is_role_of == null;

    }

    @Override
    public int hashCode() {
        int result = graphId != null ? graphId.hashCode() : 0;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (referes_to != null ? referes_to.hashCode() : 0);
        result = 31 * result + (is_role_of != null ? is_role_of.hashCode() : 0);
        return result;
    }
}
