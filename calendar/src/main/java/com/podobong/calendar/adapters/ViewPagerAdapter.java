package com.podobong.calendar.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.podobong.calendar.R;
import com.podobong.calendar.items.EventDay;
import com.podobong.calendar.items.Item;
import com.podobong.calendar.listeners.OnDayClickListener;
import com.podobong.calendar.adapters.GridViewAdapter;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private List<EventDay> events;
    private Drawable defaultImage;
    private OnDayClickListener onDayClickListener;

    public ViewPagerAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    @NonNull
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.page, container, false);
        GridView gridView = view.findViewById(R.id.gridView);
        final GridViewAdapter adapter = new GridViewAdapter(defaultImage);

        int diff = position - Integer.MAX_VALUE / 2;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, diff);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        LinkedHashMap<Calendar, Boolean> days = getDaysOfMonth(year, month);
        for (LinkedHashMap.Entry<Calendar, Boolean> day : days.entrySet()) {
            adapter.addItem(new Item(day.getKey(), day.getValue()));
        }

        if (events != null) {
            for (EventDay event : events) {
                for (Item item : adapter.getItems()) {
                    if (isSameDay(item.getDay(), event.getDay())) {
                        item.setChecked(true);
                        item.setDrawable((Drawable) event.getDrawable());
                    }
                }
            }
        }

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onDayClickListener.onDayClick(adapter.getItem(position));
            }
        });

        gridView.setAdapter(adapter);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == object);
    }

    private LinkedHashMap<Calendar, Boolean> getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        int dow = calendar.get(Calendar.DAY_OF_WEEK);
        int firstDay;
        if (dow != 1) {
            firstDay = - (dow - 1);
        } else {
            firstDay = - (dow + 6);
        }

        LinkedHashMap<Calendar, Boolean> days = new LinkedHashMap<>();
        for (int i = 0; i < 42; i++) {
            Calendar day = Calendar.getInstance();
            day.set(year, month, 1);
            day.add(Calendar.DAY_OF_MONTH, firstDay + i);

            boolean isCurrentMonth;
            if (day.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)) {
                isCurrentMonth = true;
            } else {
                isCurrentMonth = false;
            }

            days.put(day, isCurrentMonth);
        }

        return days;
    }

    private boolean isSameDay(Calendar day1, Calendar day2) {
        if (day1.get(Calendar.YEAR) == day2.get(Calendar.YEAR)
                && day1.get(Calendar.MONTH) == day2.get(Calendar.MONTH)
                && day1.get(Calendar.DAY_OF_MONTH) == day2.get(Calendar.DAY_OF_MONTH)) {
            return true;
        }
        return false;
    }

    public void setEvents(List<EventDay> events) {
        this.events = events;
    }

    public void setDefaultImage(Drawable drawable) {
        defaultImage = drawable;
    }

    public void setOnDayClickListener(OnDayClickListener onDayClickListener) {
        this.onDayClickListener = onDayClickListener;
    }
}
