# Travel Booking System - User Location by IP

This is a simple Java application that fetches and displays the user's location based on their public IP address using the ipinfo.io API.

## Features
- Makes an HTTP GET request to an external IP geolocation API
- Parses the JSON response to extract city, region, and country information using Gson
- Displays the location information in a Swing GUI window

## Setup & Running

### Prerequisites
- Java JDK installed (11 or above recommended)
- Gson library (download jar file [here](https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar))

### Compilation
Download the gson jar file and place it in the same directory as TravelBookingApp.java, then run:

```bash
javac -cp ".;gson-2.10.1.jar" TravelBookingApp.java
```

### Running
Run the compiled program using the command:

```bash
java -cp ".;gson-2.10.1.jar" TravelBookingApp
```

The GUI window will show your estimated location based on your IP address.

## Notes
- This program uses the free API from http://ipinfo.io which has usage limits.
- Ensure internet connectivity for the application to fetch IP data.
