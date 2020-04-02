package com.nextevent.JavaBeans;

import org.threeten.bp.LocalDate;
import org.threeten.bp.YearMonth;

import java.util.HashMap;

public class CalendarMonth {
    private YearMonth month;
    private HashMap<Event, LocalDate> events;

    public CalendarMonth(YearMonth month, HashMap<Event, LocalDate> events) {
        this.month = month;
        this.events = events;
    }

    public YearMonth getMonth() {
        return month;
    }

    public HashMap<Event, LocalDate> getEvents() {
        return events;
    }
}
