package com.podobong.calendar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.podobong.calendar.adapters.ViewPagerAdapter;
import com.podobong.calendar.items.EventDay;
import com.podobong.calendar.listeners.OnDayClickListener;
import com.podobong.calendar.R;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CalendarView extends LinearLayout {
    private ViewPagerAdapter adapter;

    public CalendarView(Context context) {
        super(context);
        init(context);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        View view = View.inflate(context, R.layout.calendar_view, this);

        final ViewPager viewPager = view.findViewById(R.id.viewPager);
        adapter = new ViewPagerAdapter(context);
        viewPager.setAdapter(adapter);
        int position = Integer.MAX_VALUE / 2;
        viewPager.setCurrentItem(position);
        setTitle(position);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                setTitle(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        ImageButton prev = view.findViewById(R.id.prev);
        prev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            }
        });

        ImageButton next = view.findViewById(R.id.next);
        next.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        });
    }

    private void setTitle(int position) {
        int diff = position - Integer.MAX_VALUE / 2;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, diff);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        TextView year_month = findViewById(R.id.year_month);
        String[] month_str = getResources().getStringArray(R.array.months);
        String ym, ym_fmt;
        if (Locale.getDefault().getLanguage().equals("ko")) {
            ym = getResources().getString(R.string.year_month);
            ym_fmt = String.format(ym, year, month_str[month]);
        } else {
            ym = getResources().getString(R.string.year_month);
            ym_fmt = String.format(ym, month_str[month], year);
        }
        year_month.setText(ym_fmt);
    }

    public void setEvents(List<EventDay> events) {
        if (adapter != null) {
            adapter.setEvents(events);
            adapter.notifyDataSetChanged();
        }
    }

    public void setDefaultImage(Drawable drawable) {
        if (adapter != null) {
            adapter.setDefaultImage(drawable);
            adapter.notifyDataSetChanged();
        }
    }

    public void setOnDayClickListener(OnDayClickListener onDayClickListener) {
        if (adapter != null) {
            adapter.setOnDayClickListener(onDayClickListener);
            adapter.notifyDataSetChanged();
        }
    }
}