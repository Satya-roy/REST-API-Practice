package comm.JSONParseInJava.createJsonFile;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CreateJsonObject {
    public String createJSONObject () {
        JSONObject jo = new JSONObject();

        jo.put("firstName", "John");
        jo.put("lastName", "Smith");
        jo.put("age", 25);

        Map<String, String> address = new HashMap<>();
        address.put("Street Address", "21 2nd Street");
        address.put("City", "NewYork");
        address.put("State", "NY");
        address.put("postalCode", "10021");

        jo.put("address", address);

        JSONArray ja = new JSONArray();

        Map<String, String> ph1 = new HashMap<>();
        ph1.put("type", "fax");
        ph1.put("number", "213-234-7859");

        Map<String, String> ph2 = new HashMap<>();
        ph2.put("type", "home");
        ph2.put("number", "614-123-8559");

        ja.add(ph1);
        ja.add(ph2);

        jo.put("phoneNumbers", ja);

        return jo.toString();
    }
}
