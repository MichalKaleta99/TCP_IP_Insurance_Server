import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;

public class Server {
    private static ArrayList<UserInfo> userInfo = new ArrayList<UserInfo>();
    private static StringBuilder response = new StringBuilder();
    private static Socket s;
    private static DbFunctions db = new DbFunctions();


    public static void main(String[] args) {

        if (establishConnection()!=null){
            int id = readClientsResponse();
            connectToDb(id);
            createResponseToClient(id);
            sendResponseToClient();
        }

    }

    private static Socket establishConnection() {
        try {
            ServerSocket ss = new ServerSocket(4999);
            s = ss.accept();
            return s;
        }
        catch(Exception e){
            System.out.println("Error when creating Server Socket because of "+e);
        }
        return null;
    }

    private static int readClientsResponse(){
        try {
            DataInputStream msg = new DataInputStream(s.getInputStream());
            int id =(Integer)msg.read();
            return id;
        }
        catch(Exception e){
            System.out.println("Error when creating Server Socket because of "+e);
        }
        return -1;
    }

    private static void connectToDb(int id){
        try{
            Connection conn= db.connectToDb("mydb","postgres","admin");

            userInfo = db.readUserData(conn, id);
        }
        catch(Exception e){
            System.out.println("Error when connecting to DB because of  "+e);
        }
    }

    private static void createResponseToClient(int id){
        int i = 1;
        response.append("User " + userInfo.get(0) + " (whose login id is " + id + ") has " + db.getRowCounter() + " insurances and here is full list of them:\n");
        for (UserInfo info : userInfo) {
            response.append((i+".\n"));
            response.append("Car - "+info.getCarBrand()+" "+info.getCarModel()+"\n");
            response.append("Insurance company - "+info.getInsurance()+"\n");
            response.append("Cost of insurance - "+info.getPrice()+" $\n");
            i++;
        }
    }

    private static void sendResponseToClient(){
        try{
            PrintWriter writer = new PrintWriter(s.getOutputStream());
            writer.println(response);
            writer.close();

        }
        catch (Exception e){
            System.out.println("Sending response to client failed because of "+e);
        }
    }
}
