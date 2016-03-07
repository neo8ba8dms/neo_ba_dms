package org.blub.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@JsonIdentityInfo(generator=JSOGGenerator.class)
@NodeEntity
public class Organisation_role extends Party_role{

    @Relationship(type = "relFromOrganisationRoleToDocument", direction = Relationship.OUTGOING)
    private Set<Document> referes_to;

    @Relationship(type = "relFromOrganisationRoleToOrganisation", direction = Relationship.OUTGOING)
    private Organisation is_role_of;

    @Override
    public Set<Document> getReferes_to() {
        return referes_to;
    }

    @Override
    public void setReferes_to(Set<Document> referes_to) {
        this.referes_to = referes_to;
    }

    @Override
    public Organisation getIs_role_of() {
        return is_role_of;
    }

    public void setIs_role_of(Organisation is_role_of) {
        this.is_role_of = is_role_of;
    }
}
