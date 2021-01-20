package rs.weather.api.helpers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HttpHelper {
    public static String get(String urlAsString) {
        String result = StringUtils.EMPTY;
        try {
            URL url = new URL(urlAsString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                result = response.toString();
            } else {
                //TODO: Add logging to a log file instead of stdout
                System.out.println("Failed to fetch GET request " + urlAsString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
