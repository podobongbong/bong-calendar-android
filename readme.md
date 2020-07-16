# BongCalendar
BongCalendar is a custom CalendarView for Android.

## Features
- Calendar view
- Set schedules
- Set custom icons for schedules
- Set click listener for days

## How to Use
### Gradle Setting
To use BongCalendar, ```jcenter()``` must be defined in **PROJECT'S** ```build.gradle``` file:
```
allprojects {
    repositories {
        jcenter()
    }
}
```

Then add the library in **MODULE'S** ```build.gradle``` file:
```
dependencies {
    implementation 'com.podobong.calendar:calendar:1.0.0'
}
```

### CalendarView in XML
After setting gradle, you can use the CalendarView in XML:
```xml
<com.podobong.calendar.CalendarView
    android:id="@+id/calendarView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```

### CalendarView in Java
The CalendarView can be used in Java code too:
```java
CalendarView calendarView = findViewById(R.id.calendarView);
```

### Setting Schedules
To add schedules in the calendar, use ```EventDay```:
```java
CalendarView calendarView = findViewById(R.id.calendarView);

Calendar today = Calendar.getInstance();
Calendar christmas = Calendar.getInstance();
christmas.set(2020, 11, 25);

ArrayList<EventDay> schedules = new ArrayList<>();
Drawable icon = getResources().getDrawable(R.drawable.christmas);
schedules.add(new EventDay(today));             // Default icon
schedules.add(new EventDay(christmas, icon));   // Custom icon

calendarView.setEvents(schedules);
```

### Setting a Click Listener
Using ```OnDayClickListener```, you can set click listener:
```java
calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(Item item) {
                Toast.makeText(
                        getApplicationContext(),
                        Integer.toString(item.getDay().get(Calendar.DAY_OF_MONTH)),
                        Toast.LENGTH_LONG).show();
            }
        });
```

## Reference
- Applandeo/Material-Calendar-View (https://github.com/Applandeo/Material-Calendar-View)
