package jaas;

import java.security.Principal;
import java.util.Objects;

public class SimplePrincipal implements Principal {

    private String descr;
    private String value;

    public SimplePrincipal(String descr, String value) {
        this.descr = descr;
        this.value = value;
    }

    public SimplePrincipal(String descrAndValue) {
        String[] dv = descrAndValue.split("=");
        this.descr = dv[0];
        this.value = dv[1];
    }

    @Override
    public String getName() {
        return descr + "=" + value;
    }

    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null) return false;
        if (getClass() != otherObject.getClass()) return false;
        var other = (SimplePrincipal) otherObject;
        return Objects.equals(getName(), other.getName());
    }

    public int hashCode() {
        return Objects.hashCode(getName());
    }
}
