package project.RESTAPI_TV_SHOE.result;

import java.io.*;
import java.math.*;
import java.net.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.net.http.*;
import com.google.gson.*;

class Result {
    /*
     * Complete the 'showsInProduction' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     * 1. INTEGER startYear
     * 2. INTEGER endYear
     *
     * Base url: https://jsonmock.hackerrank.com/api/tvseries
     */
    public static List<String> showsInProduction(int startYear, int endYear) {
        String baseUrl = "https://jsonmock.hackerrank.com/api/tvseries";
        List<String> allSeries = new ArrayList<>();

        try {
            int page = 1;
            while (true) {
                String urlString = baseUrl + "?page=" + page;
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                System.out.println(content.toString());
                in.close();
                conn.disconnect();

                JSONObject jsonResponse = (JSONObject) new JSONParser().parse(content.toString());
                JSONArray data = (JSONArray) jsonResponse.get("data");
                for (Object obj : data) {
                    JSONObject series = (JSONObject) obj;
                    String name = (String) series.get("name");
                    String runtimeOfSeries = (String) series.get("runtime_of_series");

                    if (isWithinProductionPeriod(runtimeOfSeries, startYear, endYear)) {
                        allSeries.add(name);
                    }
                }
                page++;

                long totalPages = (long) jsonResponse.get("total_pages");
                if (page >= totalPages) {
                    break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Collections.sort(allSeries);
        return allSeries;
    }

    private static boolean isWithinProductionPeriod(String runtime, int startYear, int endYear) {
        runtime = runtime.replace("(I) ", "").replace("(II) ", "");
        String[] years = runtime.replace("(", "").replace(")", "").split("-");

        int start = Integer.parseInt(years[0]);
        int end =  ( years.length > 1 && !years[1].equals(" ")) ? Integer.parseInt(years[1]) : -1;

        if (endYear == -1) {
            return start >= startYear && end == -1;
        }
        return start >= startYear && end <= endYear && end != -1;
    }

    public static void main(String[] args) {
        List<String> result = showsInProduction(2006, 2011);
        for (String name : result) {
            System.out.println(name);
        }
    }
}

