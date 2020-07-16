package com.podobong.calendar.items;

import android.graphics.drawable.Drawable;

import java.util.Calendar;

public class EventDay {
    private Calendar day;
    private Object drawable;

    public EventDay(Calendar day) {
        this.day = day;
    }

    public EventDay(Calendar day, Drawable drawable) {
        this.day = day;
        this.drawable = drawable;
    }

    public Calendar getDay() {
        return day;
    }

    public Object getDrawable() {
        return drawable;
    }
}
