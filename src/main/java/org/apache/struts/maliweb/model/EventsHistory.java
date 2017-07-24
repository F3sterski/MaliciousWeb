package org.apache.struts.maliweb.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by szkol_000 on 31.01.2017.
 */
public class EventsHistory {
    List<EventLog> eventHistory;

    public List<EventLog> getEventHistory() {
        return eventHistory;
    }

    public void setEventHistory(List<EventLog> eventHistory) {
        this.eventHistory = eventHistory;
    }

    public void addEventToList(EventLog el){
       if(eventHistory == null) eventHistory = new ArrayList<>();
       eventHistory.add(el);
    }
}
