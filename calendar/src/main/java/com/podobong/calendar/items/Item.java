package com.podobong.calendar.items;

import android.graphics.drawable.Drawable;

import java.util.Calendar;

public class Item {
    private Calendar day;
    private Drawable drawable;
    private boolean isCurrentMonth;
    private boolean isChecked;

    public Item(Calendar day, boolean isCurrentMonth) {
        this.day = day;
        this.isCurrentMonth = isCurrentMonth;
    }

    public Calendar getDay() {
        return day;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public boolean getCurrentMonth() {
        return isCurrentMonth;
    }

    public boolean getChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
