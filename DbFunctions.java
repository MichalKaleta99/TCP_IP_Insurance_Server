import java.sql.*;
import java.util.ArrayList;

public class DbFunctions {

    public ArrayList<UserInfo> getUserInfo() {
        return ArrayList<userInfo> userInfo;
    }

    ArrayList<UserInfo> userInfo = new ArrayList<UserInfo>();
    public Connection connectToDb(String dbname, String user, String pass) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, pass);
        } catch (Exception e) {
            System.out.println("Cannot connect to DB because of "+e);
        }
        return conn;
    }

    public void createTable(Connection conn, String tableName) {
        Statement statement;
        try {
            String query = "Create table " + tableName + "(id bigint, nick text, login text, password text, insert_time timestamp, primary key(id));";
            conn.createStatement().executeUpdate(query);
            System.out.println("Table created");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insertRow(Connection conn, String tableName, int id, String nick, String login, String password) {
        try {
            String query = String.format("INSERT INTO %s (id, nick, login, password) values(%d, '%s', '%s', '%s');", tableName, id, nick, login, password);
            System.out.println(query);
            conn.createStatement().executeUpdate(query);
            System.out.println("inserted succesfully");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Inserting failed");
        }
    }

    public int readUserData(Connection conn, int id) {
        userInfo.clear();
        Statement statement;
        ResultSet result = null;
        int rowCounter = 0;
        try {
            String query = String.format("SELECT nick, brand, model, insurer, price FROM users INNER JOIN vehicles ON users.login = vehicles.login INNER JOIN insurance_offers ON vehicles.id=insurance_offers.vehicle_id WHERE users.id= %d", id);
            statement = conn.createStatement();
            result = statement.executeQuery(query);
            while (result.next()) {
                userInfo.add(new UserInfo(result.getString("nick"), result.getString("brand"),result.getString("model"), result.getString("insurer"), result.getFloat("price")));
                if(result.isLast()){
                    rowCounter=result.getRow();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Selecting data failed because of: " + e);
        }
        return rowCounter;
    }

    public void displayData(Connection conn, int id){
        int insuranceCounter=readUserData(conn, id);
        int i = 1;
        System.out.println("User " + userInfo.get(0) + " (whose login id is " + id + ") has " + insuranceCounter + " insurances and here is full list of them:");

        for (UserInfo userInfo : userInfo) {
            System.out.println(i+".");
            System.out.println("Car - "+userInfo.getCarBrand()+" "+userInfo.getCarModel());
            System.out.println("Insurance company - "+userInfo.getInsurance());
            System.out.println("Cost of insurance - "+userInfo.getPrice()+" $");
            i++;
        }

    }

}
