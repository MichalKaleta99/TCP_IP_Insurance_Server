import javax.xml.crypto.Data;
import java.net.*;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Client {
    private static int id;
    private static Socket s;
    public static void main(String[] args) {
        if (establishConnection()!=null){
            id = askUserForId();
            askServer(s, id);
            displayData(s);
        }
    }

    private static Socket establishConnection() {
        try {
            s = new Socket("localhost", 4999);
            return s;
        }
        catch(Exception e){
            System.out.println("Error when establishing connection to server because of "+e);
        }
        return null;
    }

    private static DataOutputStream askServer(Socket s, int id){
        try {
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.write(id);
            return dos;
        }
        catch(Exception e){
            System.out.println("Error when asking server because of "+e);
        }
        return null;
    }

    private static int askUserForId() {
        System.out.println("Please type user ID: ");
        Scanner console = new Scanner(System.in);

        if (console.hasNext("stop")) {
            System.out.println("***Terminated***");
            System.exit(0);
        }
        while(!console.hasNextInt()) {
            System.out.println("Input is not a valid integer! Please type user ID again and remember to type int! ");
            console.next();
        }
        int providedId = console.nextInt();
        while(providedId<=0) {
            System.out.println("User ID should be greater than 0! ");
            providedId = console.nextInt();
        }
        return providedId;
    }


    private static void displayData(Socket s){
        try {
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
            System.out.println("Error when receiving data from client server because of"+e);
        }
    }

}

