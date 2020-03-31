package com.nextevent.JavaBeans;

import org.threeten.bp.YearMonth;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CalendarMonth {
    private YearMonth month;
    private HashMap<Event, Date> events;

    public CalendarMonth(YearMonth month, HashMap<Event, Date> events) {
        this.month = month;
        this.events = events;
    }

    public YearMonth getMonth() {
        return month;
    }

    public HashMap<Event, Date> getEvents() {
        return events;
    }
}
