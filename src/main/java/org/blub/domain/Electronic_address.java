package org.blub.domain;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Electronic_address extends Address{

    private String telephone_number;
    private String electronic_mail_address;

    public String getTelephone_number() {
        return telephone_number;
    }

    public void setTelephone_number(String telephone_number) {
        this.telephone_number = telephone_number;
    }

    public String getElectronic_mail_address() {
        return electronic_mail_address;
    }

    public void setElectronic_mail_address(String electronic_mail_address) {
        this.electronic_mail_address = electronic_mail_address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Electronic_address that = (Electronic_address) o;

        if (telephone_number != null ? !telephone_number.equals(that.telephone_number) : that.telephone_number != null)
            return false;
        return electronic_mail_address != null ? electronic_mail_address.equals(that.electronic_mail_address) : that.electronic_mail_address == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (telephone_number != null ? telephone_number.hashCode() : 0);
        result = 31 * result + (electronic_mail_address != null ? electronic_mail_address.hashCode() : 0);
        return result;
    }
}
