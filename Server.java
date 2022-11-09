import java.io.IOException;
import java.net.ServerSocket;
import java.sql.Connection;
import java.util.Scanner;
import java.net.*;
import java.io.*;

public class Server {

        public static void main(String[] args) throws IOException {
            try{
             //   ServerSocket ss = new ServerSocket(4999);
             //   Socket s = ss.accept();

             //   DataInputStream msg = new DataInputStream(s.getInputStream());
             //   int id =(Integer)msg.read();
             //   ss.close();
                System.out.println("Please type user id: ");
                Scanner id = new Scanner(System.in);
                int x = id.nextInt();
                DbFunctions db = new DbFunctions();
                Connection conn= db.connectToDb("mydb","postgres","admin");
                db.readUserData(conn, x);
                String test = String.valueOf(db.getUserInfo());
                System.out.println(test);




            }
            catch (Exception e){
                System.out.println(e);
            }



        }

}
