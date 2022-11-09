import javax.xml.crypto.Data;
import java.net.*;
import java.io.*;
import java.util.Scanner;


public class Client {

    public static void main(String[] args) throws IOException{
        try{
            Socket s = new Socket("localhost", 4999);
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());


            System.out.println("Please type user id: ");
            Scanner x = new Scanner(System.in);
            int providedId = x.nextInt();

            dos.write(providedId);


            InputStream input = s.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            char[] buffer = new char[1];

            int charsRead = 0;

            while ((charsRead = reader.read(buffer)) != -1) {
                String message = new String(buffer).substring(0, charsRead);
                System.out.print(message);
            }

        }
        catch(Exception e){
            System.out.println(e);
        }

    }
}
