package adv_task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Db_con_5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        try (sc; 
             Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "pratap", "123")) {
            
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con.setAutoCommit(false);

            PreparedStatement pm1 = con.prepareStatement("SELECT * FROM bankmanagement70 WHERE accno = ?");
            PreparedStatement pm2 = con.prepareStatement("UPDATE bankmanagement70 SET ACCBAL = ACCBAL + ? WHERE accno = ?");

            System.out.println("Enter the account number to withdraw from:");
            long saccno = Long.parseLong(sc.nextLine());

            if (!checkAccountExists(pm1, saccno)) {
                System.out.println("Invalid source account number.");
                return;
            }

            double balance = getAccountBalance(pm1, saccno);

            System.out.println("Enter the account number to transfer to:");
            long raccno = Long.parseLong(sc.nextLine());

            if (!checkAccountExists(pm1, raccno)) {
                System.out.println("Invalid target account number.");
                return;
            }

            System.out.println("Enter the amount to transfer:");
            float amount = Float.parseFloat(sc.nextLine());

            if (amount <= balance) {
                if (executeTransaction(pm2, -amount, saccno) && executeTransaction(pm2, amount, raccno)) {
                    con.commit();
                    System.out.println("Transaction successful.");
                } else {
                    con.rollback();
                    System.out.println("Transaction failed.");
                }
            } else {
                con.rollback();
                System.out.println("Insufficient account balance.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean checkAccountExists(PreparedStatement pm, long accNo) throws Exception {
        pm.setLong(1, accNo);
        try (ResultSet rs = pm.executeQuery()) {
            return rs.next();
        }
    }

    private static double getAccountBalance(PreparedStatement pm, long accNo) throws Exception {
        pm.setLong(1, accNo);
        try (ResultSet rs = pm.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble(3); 
            }
            throw new Exception("Account not found.");
        }
    }


    private static boolean executeTransaction(PreparedStatement pm, double amount, long accNo) throws Exception {
        pm.setDouble(1, amount);
        pm.setLong(2, accNo);
        return pm.executeUpdate() > 0;
    }
}
