package com.FootballMatch.footballClient;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Client {
    //https://jsonmock.hackerrank.com/api/football_matches?year=<year>&page=<page>
    private static String baseUrl = "https://jsonmock.hackerrank.com/api/football_matches";

    public void getFootballMatches() {
        Map<String, Long> goals = new HashMap<>();
        try {
            int page = 1;
            while(true) {
                //1. Create URL
                URL url = new URL(baseUrl + "?year=2011&page=" + page );
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                //2. Open an HTTP connection
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while((inputLine = br.readLine()) != null) {
                    content.append(inputLine);
                }
                br.close();
                connection.disconnect();

                JSONObject jo = (JSONObject) new JSONParser().parse(content.toString());
                JSONArray ja = (JSONArray) jo.get("data");

                for(Object dataObj : ja) {
                    JSONObject data = (JSONObject) dataObj;
                    String team1 = (String) data.get("team1");
                    String team2 = (String) data.get("team2");

                    Long team1Goals = Long.parseLong((String) data.get("team1goals"));
                    Long team2Goals = Long.parseLong((String) data.get("team2goals"));

                    if(!goals.containsKey(team1)) {
                        goals.put(team1, team1Goals);
                    }
                    else {
                        goals.put(team1, goals.get(team1) + team1Goals);
                    }

                    if(!goals.containsKey(team2)) {
                        goals.put(team2, team2Goals);
                    }
                    else {
                        goals.put(team2, goals.get(team2) + team2Goals);
                    }

                }

                long totalPages = (long) jo.get("total_pages");
                // break if page>=totalPages
                if(page >= totalPages) {
                    break;
                }
                page++;
                System.out.println(page + ", " + totalPages);
            }

            for(Map.Entry<String, Long> entry : goals.entrySet()) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }

        } catch(Exception e) {
            //handle exception
        }
    }

    public static void main(String []args) {
        Client client = new Client();

        client.getFootballMatches();
    }
}
