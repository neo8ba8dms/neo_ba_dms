package org.blub.domain;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Physical_address extends Address{

    private String street;
    private String street_number;
    private String postal_code;
    private String town;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet_number() {
        return street_number;
    }

    public void setStreet_number(String street_number) {
        this.street_number = street_number;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Physical_address that = (Physical_address) o;

        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (street_number != null ? !street_number.equals(that.street_number) : that.street_number != null)
            return false;
        if (postal_code != null ? !postal_code.equals(that.postal_code) : that.postal_code != null) return false;
        return town != null ? town.equals(that.town) : that.town == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (street_number != null ? street_number.hashCode() : 0);
        result = 31 * result + (postal_code != null ? postal_code.hashCode() : 0);
        result = 31 * result + (town != null ? town.hashCode() : 0);
        return result;
    }
}
