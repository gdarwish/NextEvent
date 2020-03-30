package com.nextevent.JavaBeans;

import org.threeten.bp.YearMonth;

import java.util.ArrayList;

public class CalendarMonth {
    private YearMonth month;
    private ArrayList<Event> events;

    public CalendarMonth(YearMonth month, ArrayList<Event> events) {
        this.month = month;
        this.events = events;
    }

    public YearMonth getMonth() {
        return month;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }
}
