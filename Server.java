import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
    ArrayList<UserInfo> userInfo = new ArrayList<UserInfo>();
    static StringBuilder response = new StringBuilder();

    public static void main(String[] args) throws IOException {
            try{
                ServerSocket ss = new ServerSocket(4999);
                Socket s = ss.accept();

                DataInputStream msg = new DataInputStream(s.getInputStream());
                int id =(Integer)msg.read();

                DbFunctions db = new DbFunctions();
                Connection conn= db.connectToDb("mydb","postgres","admin");

                ArrayList<UserInfo> userInfo = db.readUserData(conn, id);
                int i = 1;
                response.append("User " + userInfo.get(0) + " (whose login id is " + id + ") has " + db.getRowCounter() + " insurances and here is full list of them:\n");
                for (UserInfo info : userInfo) {
                    response.append((i+".\n"));
                    response.append("Car - "+info.getCarBrand()+" "+info.getCarModel()+"\n");
                    response.append("Insurance company - "+info.getInsurance()+"\n");
                    response.append("Cost of insurance - "+info.getPrice()+" $\n");
                    i++;
                }
                PrintWriter writer = new PrintWriter(s.getOutputStream());
                writer.println(response);
                writer.close();

            }
            catch (Exception e){
                System.out.println(e);
            }
        }


}
