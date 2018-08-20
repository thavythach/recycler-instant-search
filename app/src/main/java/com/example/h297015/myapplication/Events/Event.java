package com.example.h297015.myapplication.Events;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDate;

public class Event {
    private String mName;
    private LocalDate mDate;
    private String mLocation;
    private String mRegister;


    public Event(String name, LocalDate date,  String city, String country) {
        this.mName = name;
        this.mDate = date;
        this.mLocation = city + ", " + country;
        this.mRegister = "Register";
    }

    public String getName() {
        return mName;
    }

    public String getDate() {
        return mDate.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
    }

    public String getLocation() {
        return mLocation;
    }

    public String getRegister() {
        return mRegister;
    }

    public static int getLastEventId() {
        return lastEventId;
    }

    private static int lastEventId = 0;


    public static ArrayList<Event> createEventsList(int numContacts) {
        ArrayList<Event> events = new ArrayList<Event>();

        for (int i = 1; i <= numContacts; i++) {
            events.add(
                    new Event("Operator Conference",
                            createRandomDate(2018,2019),
                            "Beijing",
                            "China")
            );
        }

        return events; // TODO: sort by date
    }

    public static int createRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public static LocalDate createRandomDate(int startYear, int endYear) {
        int day = createRandomIntBetween(1, 28);
        int month = createRandomIntBetween(1, 12);
        int year = createRandomIntBetween(startYear, endYear);
        return LocalDate.of(year, month, day);
    }
}