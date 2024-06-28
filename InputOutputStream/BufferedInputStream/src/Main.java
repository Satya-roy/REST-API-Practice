import java.io.BufferedReader;
import java.io.FileReader;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/satya/Code 2.0/REST-API-Practice/InputOutputStream/BufferedInputStream/src/text.txt"));
            String inputLine = "";
            StringBuilder response = new StringBuilder();

            while((inputLine = bufferedReader.readLine())!=null) {
                response.append(inputLine);
            }
            System.out.println(response.toString());
            bufferedReader.close();
        } catch(Exception e) {
            //handle exception
        }
    }
}