# timeit!

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
The purpose of the app is to keep track of your habits/actions on a day to day basis which will allow the user to follow their desired schedule

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category:** Productivity
- **Mobile:** This app syncs with the user's Google account signed in on the device, aggregates data from the user's Google Calendar, stores data on the device, and syncs its data with their phone's backup
- **Story:** 
- **Market:**
- **Habit:**
- **Scope:**

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**
* User shall be able to add/modify events in Google Calendar API
* User shall be able to add action events
* Events shall be displayed on main page
* User shall be able to add/modify habit event timer
* Events in Google Calendar must be displayed in this section
* Habits shall be displayed on screen
* User can track time of the event he has added

**Optional Nice-to-have Stories**
* User may change the action background to have different image/gif animations 
* User may change the habit background to have a different image/gif animation 
* User may change different types of themes
* May improve UI format and have introduction screen

### 2. Screen Archetypes

1) Clock Screen (Stream)
   * events shall be displayed on main page
   * user can track time  of the event he has added
2) Google Calendar (Detail)
   * user can add/modify events in google calendar api
   * events through google calendar api must be displayed in this section
3) Habit Tracker (Stream)
   * user can add/modify habit event timer
   * habits shall be displayed on screen
4) Action List (Stream)
   * user can add action events
5) Activity Tracker (Detail)
   * user can add action events and be displayed on main page



### 3. Navigation

**Tab Navigation** (Tab to Screen)
* Watch
* Calendar
* Goals

**Flow Navigation** (Screen to Screen)

* 1) Clock Screen
    * Stream (Habit Tracker)
* 2) Google Calendar
    * Same Page
* 3) Habit Tracker
    * Same Page
* 4) Action List
    * Same Page
* 2) Activity Tracker
    * Same Page

## Wireframes
<img src="https://github.com/Codepath-Team-2021-Time-App/Sec/blob/main/Screenshot%202021-11-11%20194123.png" width=600>

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema 
[This section will be completed in Unit 9]

### Models
[Add table of models]
| Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | calendarId      | String   |Calendar identifier. To retrieve calendar IDs call the calendarList.list method. If you want to access the primary calendar of the currently logged in user, use the "primary" keyword.  |
   | eventId        | string | Event identifier |
   | Summary        | string | title of the calendar |
   | description         | string     | Description of the calndar |
   | location       | string   | Geographic location of the calendar as free-form text. Optional |
   | summary | string   | Title of the calendar. |
   
### Networking
#### List of network requests by screen
- Google API Screen
      - (Read/GET) read all events from the user and display to Google API Frame
         ```swift
         import com.google.api.services.calendar.Calendar;

        // Initialize Calendar service with valid OAuth credentials
        Calendar service = new Calendar.Builder(httpTransport, jsonFactory, credentials)
            .setApplicationName("applicationName").build();

        // Retrieve the calendar
        com.google.api.services.calendar.model.Calendar calendar =
            service.calendars().get('primary').execute();

        System.out.println(calendar.getSummary());
                   // TODO: Do something with posts...
                    }
                 }

- Timeline Frame
      - (Read/GET) read all events from user and display to Timeline Frame
        import com.google.api.services.calendar.Calendar;

        import com.google.api.services.calendar.Calendar;
        import com.google.api.services.calendar.model.Event;

        // Initialize Calendar service with valid OAuth credentials
        Calendar service = new Calendar.Builder(httpTransport, jsonFactory, credentials)
            .setApplicationName("applicationName").build();

        // Retrieve an event
        Event event = service.events().get('primary', "eventId").execute();

        System.out.println(event.getSummary());

- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
- import com.google.api.services.calendar.Calendar;

        // Initialize Calendar service with valid OAuth credentials
        Calendar service = new Calendar.Builder(httpTransport, jsonFactory, credentials)
            .setApplicationName("applicationName").build();

        // Retrieve the calendar
        com.google.api.services.calendar.model.Calendar calendar =
            service.calendars().get('primary').execute();

        System.out.println(calendar.getSummary());

