package personal.louchen.fastapi.utils;

import java.io.Serializable;

/**
 * @author Eric.Lou
 */
public class NameValuePairUtil implements Cloneable, Serializable {
    private static final long serialVersionUID = 153134524369023108L;

    private final String name;
    @SuppressWarnings("serial")
    private final Object value;

    public NameValuePairUtil(final String name, final Object value) {
        this.name = notNull(name, "Name");
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public String toString() {
        return name + "=" + value;
    }

    public boolean equals(final Object object) {
        if(this == object) {
            return true;
        } else if(!(object instanceof NameValuePairUtil)) {
            return false;
        } else {
            final NameValuePairUtil that = (NameValuePairUtil) object;
            return name.equals(that.name) && equals(value, that.value);
        }
    }

    public int hashCode() {
        final byte hash = 17;
        int hash1 = hashCode(hash, name);
        hash1 = hashCode(hash1, value);
        return hash1;
    }

    @Override
    public Object clone()
        throws CloneNotSupportedException {
        return super.clone();
    }

    private static <T> T notNull(final T argument, final String name) {
        if(argument == null) {
            throw new IllegalArgumentException(name + " may not be null");
        } else {
            return argument;
        }
    }

    private static boolean equals(final Object obj1, final Object obj2) {
        return obj1 == null ? obj2 == null : obj1.equals(obj2);
    }

    private static int hashCode(final int seed, final int hashcode) {
        return seed * 37 + hashcode;
    }

    private static int hashCode(final int seed, final Object obj) {
        return hashCode(seed, obj != null ? obj.hashCode() : 0);
    }
}
