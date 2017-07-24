package org.apache.struts.maliweb.model;

/**
 * Created by szkol_000 on 12.05.2017.
 */
public class ReplaceMalice extends Malicious{
    private ReplaceMalice(String time, String name) {
        super(time, "Replace");
    }

    public ReplaceMalice(String name) {
        super(name);
    }

    public ReplaceMalice() {
        super("Replace");
    }

    String fromString = "";
    String toString = "";

    public String getFromString() {
        return fromString;
    }

    public void setFromString(String fromString) {
        this.fromString = fromString;
    }

    public String getToString() {
        return toString;
    }

    public void setToString(String toString) {
        this.toString = toString;
    }
}
