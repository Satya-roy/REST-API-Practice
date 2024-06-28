import java.io.FileInputStream;
import java.io.InputStream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            InputStream in = new FileInputStream("/Users/satya/Code 2.0/REST-API-Practice/InputOutputStream/InputStream/src/TextFile.txt");
            int countBytes = in.available(); // returns number of bytes
            System.out.println(countBytes);
            byte[] content = new byte[100];
            in.read(content);

            String fileContent = new String(content);
            System.out.println("File Content : " + fileContent);


            in.close();
        } catch(Exception e) {
            //handle exception
        }
    }
}