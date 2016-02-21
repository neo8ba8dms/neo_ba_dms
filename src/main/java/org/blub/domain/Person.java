package org.blub.domain;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Person extends Party{

    private String last_name;
    private String first_name;

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Person person = (Person) o;

        if (last_name != null ? !last_name.equals(person.last_name) : person.last_name != null) return false;
        return first_name != null ? first_name.equals(person.first_name) : person.first_name == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (last_name != null ? last_name.hashCode() : 0);
        result = 31 * result + (first_name != null ? first_name.hashCode() : 0);
        return result;
    }
}
