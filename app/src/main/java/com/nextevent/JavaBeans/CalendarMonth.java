package com.nextevent.JavaBeans;

import org.threeten.bp.LocalDate;
import org.threeten.bp.YearMonth;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Holds all the events for a month.
 *
 * @author Abel Anderson
 * @since 02/04/2020
 * @version 1.1
 */
public class CalendarMonth {
    private YearMonth month;
    private HashMap<LocalDate, ArrayList<Event>> events;

    public CalendarMonth(YearMonth month, HashMap<LocalDate, ArrayList<Event>> events) {
        this.month = month;
        this.events = events;
    }

    public YearMonth getMonth() {
        return month;
    }

    public HashMap<LocalDate, ArrayList<Event>> getEvents() {
        return events;
    }
}
