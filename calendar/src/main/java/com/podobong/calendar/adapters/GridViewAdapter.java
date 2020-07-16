package com.podobong.calendar.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.podobong.calendar.items.Item;
import com.podobong.calendar.R;

import java.util.ArrayList;
import java.util.Calendar;

public class GridViewAdapter extends BaseAdapter {
    private ArrayList<Item> items = new ArrayList<>();
    private Context context;
    private Drawable defaultImage;

    public GridViewAdapter(Drawable drawable) {
        defaultImage = drawable;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Item getItem(int position) {
        return items.get(position);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        context = parent.getContext();
        if (defaultImage == null) {
            defaultImage = context.getResources().getDrawable(R.drawable.check);
        }
        Item item = items.get(position);

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item, parent, false);
        }

        Calendar day = item.getDay();
        TextView day_text = view.findViewById(R.id.day);
        day_text.setText(Integer.toString(day.get(Calendar.DAY_OF_MONTH)));
        if (!item.getCurrentMonth()) {
            day_text.setTextColor(Color.GRAY);
        }

        ImageView icon = view.findViewById(R.id.icon);
        if (item.getChecked()) {
            if (item.getDrawable() != null) {
                icon.setImageDrawable(item.getDrawable());
            } else {
                if (defaultImage != null) {
                    icon.setImageDrawable(defaultImage);
                }
            }
        }

        return view;
    }
}
