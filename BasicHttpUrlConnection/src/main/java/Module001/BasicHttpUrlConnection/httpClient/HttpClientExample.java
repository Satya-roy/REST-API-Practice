package Module001.BasicHttpUrlConnection.httpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClientExample {
    private static final String baseUrl = "https://jsonmock.hackerrank.com/api/tvseries";

    public static void main(String[] args) {
        try {
            HttpClientExample httpClient = new HttpClientExample();
            String response = httpClient.sendGetRequest();
            System.out.println(response);
        } catch(Exception e) {
            //handle exception
        }
    }

    public String sendGetRequest() throws Exception {
        URL url = new URL(baseUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        //Configure the request
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        System.out.println("ResponseCode" + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;

        StringBuilder response = new StringBuilder();

        while((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();

        return response.toString();
    }
}
