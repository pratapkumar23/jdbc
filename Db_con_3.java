package adv_task;
import java.sql.*;
import java.util.Scanner;

public class Db_con_3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (sc) {
            
            Class.forName("oracle.jdbc.driver.OracleDriver");

            
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl", "pratap", "123");

            Statement stm = con.createStatement();

          
            try {
                stm.execute("CREATE TABLE Employee ("
                        + "eid NUMBER(10) PRIMARY KEY, "
                        + "ename VARCHAR2(20), "
                        + "edesg VARCHAR2(20), "
                        + "b_sal NUMBER(10,2), "
                        + "hra NUMBER(10,2), "
                        + "da NUMBER(10,2), "
                        + "total_sal NUMBER(10,2))");
                System.out.println("Table created successfully.");
            } catch (Exception e) {
                System.out.println("Table already available.");
            }

            while (true) {
                System.out.println("--------------- Operations -------------");
                System.out.println("1. Add Employee"
                        + "\n2. View Employees"
                        + "\n3. View Employee By ID"
                        + "\n4. Update Employee by ID"
                        + "\n5. Delete Employee by ID"
                        + "\n6. Exit");

                System.out.println("Enter your choice:");
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter employee id: ");
                        int id = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter employee name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter employee designation: ");
                        String degs = sc.nextLine();
                       
                        System.out.println("Enter employee basic salary: ");
                        double b_sal = Double.parseDouble(sc.nextLine());
                        
                        if (b_sal < 12000) {
                            System.out.println("Basic salary cannot be less than 12000.");
                            break;
                        }

                        double hra = b_sal * 0.96;
                        double da = b_sal * 0.61;
                        double total_sal = b_sal + hra + da;

                        int k = stm.executeUpdate("INSERT INTO Employee VALUES ("
                                + id + ", '" + name + "', '" + degs + "', " + b_sal + ", " + hra + ", " + da + ", " + total_sal + ")");
                        if (k > 0) {
                            System.out.println("Insertion successful");
                        }
                        break;

                    case 2:
                        ResultSet rs1 = stm.executeQuery("SELECT * FROM Employee");
                        System.out.println("--------------- Employee Details ---------------");
                        System.out.println("eid\tename\tedesg\teb_sal\thra\tda\ttotal_sal");
                        System.out.println("----\t-----\t-----\t-------\t---\t--\t---------");
                        System.out.println();
                        
                        while (rs1.next()) {
                            System.out.println(rs1.getInt(1) + "\t" + rs1.getString(2) + "\t" + rs1.getString(3) + "\t" +
                                    rs1.getDouble(4) + "\t" + rs1.getDouble(5) + "\t" + rs1.getDouble(6) + "\t" + rs1.getDouble(7));
                        }
                        break;

                    case 3:
                        System.out.print("Enter employee id to view: ");
                        int eid = Integer.parseInt(sc.nextLine());
                        ResultSet rs2 = stm.executeQuery("SELECT * FROM Employee WHERE eid =" + eid);
                        if (rs2.next()) {
                        	System.out.println("eid\tename\tedesg\teb_sal\thra\tda\ttotal_sal");
                            System.out.println("----\t-----\t-----\t-------\t---\t--\t---------");
                                                   	
                            System.out.println(rs2.getInt(1) + "\t" + rs2.getString(2) + "\t" + rs2.getString(3) + "\t" +
                                    rs2.getDouble(4) + "\t" + rs2.getDouble(5) + "\t" + rs2.getDouble(6) + "\t" + rs2.getDouble(7));
                            System.out.println();

                        } else {
                            System.out.println("Employee not found.");
                        }
                        break;

                    case 4:
                        System.out.print("Enter employee id to update: ");
                        int eid1 = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter new basic salary: ");
                        double newBsal = Double.parseDouble(sc.nextLine());

                        double newHra = newBsal * 0.96;
                        double newDa = newBsal * 0.61;
                        double newTotalSal = newBsal + newHra + newDa;

                        int updated = stm.executeUpdate("UPDATE Employee SET b_sal = " + newBsal +
                                ", hra = " + newHra + ", da = " + newDa + ", total_sal = " + newTotalSal +
                                " WHERE eid = " + eid1);
                        if (updated > 0) {
                            System.out.println("Employee updated successfully.");
                        } else {
                            System.out.println("Employee not found.");
                        }
                        break;

                    case 5:
                        System.out.print("Enter employee id to delete: ");
                        int deleteId = Integer.parseInt(sc.nextLine());

                        int deleted = stm.executeUpdate("DELETE FROM Employee WHERE eid = " + deleteId);
                        if (deleted > 0) {
                            System.out.println("Employee deleted successfully.");
                        } else {
                            System.out.println("Employee not found.");
                        }
                        break;

                    case 6:
                        System.out.println("Exit ");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
