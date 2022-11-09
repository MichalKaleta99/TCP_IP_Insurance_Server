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
            Scanner id = new Scanner(System.in);
            int x = id.nextInt();
            System.out.println(x);
            dos.write(x);
            dos.flush();
            dos.close();
            s.close();
        }
        catch(Exception e){
            System.out.println(e);
        }

    }
}
