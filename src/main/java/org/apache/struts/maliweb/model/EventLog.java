package org.apache.struts.maliweb.model;

/**
 * Created by szkol_000 on 31.01.2017.
 */
public class EventLog {
    private String time;
    private String name;

    public EventLog(String name, String time) {
        this.name = name;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
