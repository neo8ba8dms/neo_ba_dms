package org.blub.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.NodeEntity;

@JsonIdentityInfo(generator=JSOGGenerator.class)
@NodeEntity
public class Postal_address extends Address{

    private String postal_box;
    private String postal_code;
    private String country;

    public String getPostal_box() {
        return postal_box;
    }

    public void setPostal_box(String postal_box) {
        this.postal_box = postal_box;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Postal_address that = (Postal_address) o;

        if (postal_box != null ? !postal_box.equals(that.postal_box) : that.postal_box != null) return false;
        if (postal_code != null ? !postal_code.equals(that.postal_code) : that.postal_code != null) return false;
        return country != null ? country.equals(that.country) : that.country == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (postal_box != null ? postal_box.hashCode() : 0);
        result = 31 * result + (postal_code != null ? postal_code.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }
}
