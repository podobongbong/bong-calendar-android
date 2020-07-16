package com.podobong.calendarexample;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import com.podobong.calendar.CalendarView;
import com.podobong.calendar.items.EventDay;
import com.podobong.calendar.items.Item;
import com.podobong.calendar.listeners.OnDayClickListener;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CalendarView calendarView = findViewById(R.id.calendarView);

        Calendar today = Calendar.getInstance();
        Calendar christmas = Calendar.getInstance();
        christmas.set(2020, 11, 25);

        ArrayList<EventDay> schedules = new ArrayList<>();
        schedules.add(new EventDay(today));
        Drawable christmasIcon = getResources().getDrawable(R.drawable.check);
        schedules.add(new EventDay(christmas, christmasIcon));

        calendarView.setEvents(schedules);

        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(Item item) {
                Toast.makeText(
                        getApplicationContext(),
                        Integer.toString(item.getDay().get(Calendar.DAY_OF_MONTH)),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}