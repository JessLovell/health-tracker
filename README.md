# Health Tracker
## Features
:heavy_check_mark: | Feature | Description
---- | --- | ---
:heavy_check_mark: | **Finger Exercises:** | One key part of health is building finger strength and endurance. On the main page, display a number and a button. The number should increase when the button is clicked.
:heavy_check_mark: | **Stopwatch:** | Still on the main page, add a stopwatch. Have a button to `Start/Pause` and `Reset` the clock. The start/pause button should toggle between saying “start” and “pause.” And you should only be able to reset when the stopwatch is paused.
:heavy_check_mark: | **Inspiring Image Carousel:** | At the top of our main page, we want to inspire our users with images of the type of person they can become. Display an image with a caption below it.
:heavy_check_mark: | **Navigation:** | Move your Finger Exercises and Stopwatch into their own pages of your app. Add buttons on the homepage to link to those pages, and ensure that the user can use the back button on the device to return to the app homepage. (It’s good practice to include some sort of back button on the page as well.)
:white_check_mark: | **Health Notifications:** | Create a new activity for Notifications. Allow users to set up reminders to drink water, which should appear every 2 hours in the notification bar. (For testing, you might want to shorten this to 15 or 30 seconds.)
:white_check_mark:| **Styling:** | Apply consistent styling across the various Activities in your app. Make sure they have a consistent look and feel, and that buttons are in similar locations on each screen.
:heavy_check_mark: |**Database Setup:** | Set up a database to contain Exercise data. Each Exercise should have a title, quantity, and description, as well as a timestamp. For testing, add a single Exercise to the database.
:heavy_check_mark: |**Displaying the Exercise Diary:** | Add a new Activity to hold the Exercise Diary. In that view, display all entries from the Exercise database in a ListView. (At this point, that should show a single row.) Make sure that Exercise looks reasonable.
:heavy_check_mark: | **Adding to the Exercise Diary:** | At the top of the Exercise Diary activity, add a form that allows a user to enter data about an exercise. When they hit submit, the information about that exercise should be stored in the database and displayed in the ListView. You can choose how the timestamp works: either let the user enter when they completed the exercise, or use the time when they hit submit on the form.
:heavy_check_mark: | **Backend:** | Add `POST` and `GET` requests to a server to save databases.
:heavy_check_mark: | **Display Exercises from the server:** | When a user opens the Exercise Diary page, the app should make a request to the server to retrieve all the Exercises in the server’s database. It should display both those Exercises and the ones that are local to the device within the Diary page.
:heavy_check_mark: | **Post Exercises to the server:** | When a user creates a new Exercise locally on their device, in addition to saving it to the local database, the app should POST it to the server.
:x: |**Camera:** | Add the ability for users to enter a profile picture. Give them the option of taking a new photo with the camera, or using images already on their phone. Your app will need to request the appropriate permissions for this. Once users take a profile picture, you should display it on the homescreen.
:heavy_check_mark: | **Location:** | When a user adds an exercise, they should be able to include their current location.
:heavy_check_mark: | **SharedPrefs:** | Ensure that the user’s username is correctly displayed throughout the app. Also, modify the homepage to include some key stats, such as the current finger exercise count and the total number of times the user has visited the homepage.
:white_check_mark: | **Auth on the backend:** | Add auth to your Spring backend, so that users can log in. Require users to be logged in to post an Exercise, and associate users and exercises.
:x: | **SharedPrefs for login:** | Allow the user to log in to your app, using the login functionality for the backend. Store their token in SharedPrefs, and ensure it’s secret.

![Home Page of HealthTracker](/resources/home_page.png)
![Finger Exerciser Page](/resources/finger_ex.png)
![StopWatch Page](/resources/stopwatcher.png)
![Exercise Journal](/resources/journal.png)
![Notifications](/resources/set_notification.png)
![Notifications](/resources/notification.png)

## Resources
- [Android Buttons](https://developer.android.com/guide/topics/ui/controls/button)
- [Android UI Events](https://developer.android.com/guide/topics/ui/ui-events.html)
- [System.currentTimeMills](https://www.tutorialspoint.com/java/lang/system_currenttimemillis.htm)
- [StopWatchTutorial](https://www.c-sharpcorner.com/article/creating-stop-watch-android-application-tutorial/)
- [Carousel Library](https://github.com/sayyam/carouselview)
- [StopWatchTutorial](https://www.c-sharpcorner.com/article/creating-stop-watch-android-application-tutorial/)
- [Room Tutorial](https://medium.freecodecamp.org/room-sqlite-beginner-tutorial-2e725e47bfab)
- [Recycler Views](http://www.vogella.com/tutorials/AndroidRecyclerView/article.html)
- [Espresso Testing Tutorial](https://medium.com/mindorks/android-testing-part-1-espresso-basics-7219b86c862b)
- [Android Espresso Docs](https://developer.android.com/training/testing/espresso/)
- [Unlock Screen During Testing](https://stackoverflow.com/questions/30596446/espresso-test-fails-with-noactivityresumedexception-often)
- [Volley Requests](https://developer.android.com/training/volley/simple)
- [Gson to List](https://stackoverflow.com/questions/8371274/how-to-parse-json-array-with-gson/8371455)
- [Volley POST Requests](https://www.itsalif.info/content/android-volley-tutorial-http-get-post-put)
- [Requesting Permissions](https://developer.android.com/training/permissions/requesting)
- [Location Pretty Print](https://stackoverflow.com/questions/22323974/how-to-get-city-name-by-latitude-longitude-in-android) Second Comment
- [Hiding Android fields](https://stackoverflow.com/questions/4622517/hide-a-edittext-make-it-visible-by-clicking-a-menu)
- [Shared Preferences](https://developer.android.com/training/data-storage/shared-preferences#java)


### Images
- [Puppy Sleeping Image](https://unsplash.com/photos/BHO7K8Zf16w)
- [Vegetables Image](https://unsplash.com/photos/sTPy-oeA3h0)
- [Hydrate Water Image](https://unsplash.com/photos/_IiwQMmgbZ8)
- [Track Runners Image](https://unsplash.com/photos/atSaEOeE8Nk)



## Daily Change Log
- Day 1:
    - Complete Finger Exercises and Stretch Goal
    - Research Toggle Buttons - get nowhere after 1 hr
    - Research threads and timers - computer crashes, lunch time.
- Day 2:
    - Complete Image Carousel (import this Library!) - 2 hours researching and implementing
        - Need to cycle through quotes.
    - Create new Activity STOPWATCH!
    - Create new Activity FingerExercises
- Day 3:
    - Add `back` button to nav bar
    - Work on notifications - maybe it works?  ¯\_(ツ)_/¯
    - Decide to work on :sparkles:styling:sparkles: and :watch: (find md emojies)
- Saturday & Sunday:
    - Fix Stopwatch formatting
    - Notifications work on test phone
    - Setup the database for Exercise Journal
    - Recycler view added
    - Add to Database works!

- Day 4:
    - Create Testing for Main Activity (discovered that :phone: screen must be unlocked to run tests :laughing:)
- Day 5:
    - Testing for Journal and Database
    - Styling & Layout
    - Computer crashed :sob:
- Day 6:
    - [Create Server](https://github.com/JessLovell/health-tracker-backend) with routes `/exercise`
    - Render Server and Local entries on the recycle view
    - Save to server database!!! VICTORY!!!
- Day 7:
    - Add hard coded text to the Strings.xml file.
    - Get the user location
    - Cannot render the location to add on server and recycler :sob: :woman_shrugging:
    - Updated databases local and server :woman_cartwheeling:
- Day 8:
    - Shared Preferences works!
    - Stats display on the homepage!
- Day 9:
    - Backend Server has `signup` and `login`
    - Working on login from application 
