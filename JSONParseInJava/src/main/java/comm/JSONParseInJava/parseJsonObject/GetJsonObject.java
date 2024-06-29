package comm.JSONParseInJava.parseJsonObject;

import comm.JSONParseInJava.createJsonFile.CreateJsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GetJsonObject {
    // Assuming I am getting JSON object via API or file read

    public void getJsonObject() {
        CreateJsonObject createJsonObject = new CreateJsonObject();
        try {
            Object obj = new JSONParser().parse(createJsonObject.createJSONObject());

            JSONObject jo = (JSONObject) obj;

            System.out.println(jo.get("firstName"));
            System.out.println(jo.get("lastName"));
            System.out.println(jo.get("age"));

            Object addressObj = jo.get("address");
            JSONObject addressJSON = (JSONObject) addressObj;
            System.out.println(addressJSON);

            JSONArray phoneArray =(JSONArray) jo.get("phoneNumbers");

            for(Object pNum : phoneArray) {
                JSONObject phoneNum = (JSONObject) pNum;
                System.out.println(phoneNum);
            }
        } catch(Exception e) {
            // handle exception
        }
    }

    public static void main(String []args) {
        GetJsonObject jsonObject = new GetJsonObject();

        jsonObject.getJsonObject();
    }
}
