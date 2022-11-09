import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        DbFunctions db = new DbFunctions();
        Connection conn= db.connectToDb("mydb","postgres","admin");

        db.displayData(conn, 1);
    }
}