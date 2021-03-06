package org.jboss.as.console.client.rbac;

class ResourceRef {
    private static final String OPT = "opt:/";
    String address;
    boolean optional;

    ResourceRef(String resourceRef) {
        if(resourceRef.startsWith(OPT))
        {
            this.address = resourceRef.substring(5, resourceRef.length());
            optional = true;
        }
        else
        {
            this.address = resourceRef;
            optional = false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceRef that = (ResourceRef) o;

        if (optional != that.optional) return false;
        if (!address.equals(that.address)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = address.hashCode();
        result = 31 * result + (optional ? 1 : 0);
        return result;
    }
}
