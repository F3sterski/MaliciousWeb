package org.apache.struts.maliweb.model;

/**
 * Created by szkol_000 on 21.01.2017.
 */
public class Malicious {
    String name = "";
    String time = "1";

    public Malicious(String time, String name) {
        this.time = time;
        this.name = name;
    }

    public Malicious(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
