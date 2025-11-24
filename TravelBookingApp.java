import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * A simple Java application that fetches and displays user's location based on IP address.
 * Uses ipinfo.io API for IP geolocation.
 * Parses JSON response using Gson and displays the data in a Swing GUI.
 * 
 * Requirements:
 * - Add gson library to the classpath
 *   You can download gson from: https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar
 * 
 * Compilation example:
 * javac -cp ".;gson-2.10.1.jar" TravelBookingApp.java
 * 
 * Running example:
 * java -cp ".;gson-2.10.1.jar" TravelBookingApp
 */
public class TravelBookingApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("User Location from IP");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            textArea.setFont(new Font("Arial", Font.PLAIN, 16));
            frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

            frame.setVisible(true);

            String locationInfo = fetchUserLocation();
            if (locationInfo != null) {
                textArea.setText(locationInfo);
            } else {
                textArea.setText("Failed to fetch location information.");
            }
        });
    }

    /**
     * Fetch user's location based on IP via ipinfo.io API.
     * @return formatted string with city, region, country or null if failed
     */
    private static String fetchUserLocation() {
        String apiUrl = "http://ipinfo.io/json";
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            int status = con.getResponseCode();
            if (status != 200) {
                return null;
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder responseContent = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                responseContent.append(line);
            }
            in.close();

            // Parse JSON response with Gson
            JsonObject jsonObject = JsonParser.parseString(responseContent.toString()).getAsJsonObject();

            String city = jsonObject.has("city") ? jsonObject.get("city").getAsString() : "Unknown City";
            String region = jsonObject.has("region") ? jsonObject.get("region").getAsString() : "Unknown Region";
            String country = jsonObject.has("country") ? jsonObject.get("country").getAsString() : "Unknown Country";

            return String.format("City: %s%nRegion: %s%nCountry: %s", city, region, country);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
