package adv_task;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Scanner;

public class Db_con_4 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        try (sc) {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "pratap", "123");

            CallableStatement cs = con.prepareCall("{call studetais(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            CallableStatement cs2 = con.prepareCall("{call sturetrivedetails(?,?,?,?,?,?,?,?,?,?,?)}");

            while (true) {
                System.out.println("-------choices are ----- "
                        + "\t\n1.add student details"
                        + "\t\n2.view student details via rollno"
                        + "\t\n3.exit");

                System.out.print("enter your choice :-");
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("enter student roll no :-");
                        int rollno = Integer.parseInt(sc.nextLine());
                        cs.setInt(1, rollno);

                        System.out.println("enter student name :-");
                        String name = sc.nextLine();
                        cs.setString(2, name);

                        System.out.print("enter student branch :- ");
                        String branch = sc.nextLine();
                        cs.setString(3, branch);

                        System.out.print("enter student city :-");
                        String city = sc.nextLine();
                        cs.setString(4, city);

                        System.out.println("enter student state :- ");
                        String state = sc.nextLine();
                        cs.setString(5, state);

                        System.out.println("enter student pincode :-");
                        int pincode = Integer.parseInt(sc.nextLine());
                        cs.setInt(6, pincode);

                        System.out.println("enter student mail id :-");
                        String mailId = sc.nextLine();
                        cs.setString(7, mailId);

                        System.out.println("enter student phone number :-");
                        long phno = Long.parseLong(sc.nextLine());
                        cs.setLong(8, phno);

                        System.out.println("enter student odia mark :-");
                        int odiaMark = Integer.parseInt(sc.nextLine());
                        cs.setInt(9, odiaMark);

                        System.out.println("enter student english mark :-");
                        int englishMark = Integer.parseInt(sc.nextLine());
                        cs.setInt(10, englishMark);

                        System.out.println("enter student hindi mark :-");
                        int hindiMark = Integer.parseInt(sc.nextLine());
                        cs.setInt(11, hindiMark);

                        System.out.println("enter student math mark :-");
                        int mathMark = Integer.parseInt(sc.nextLine());
                        cs.setInt(12, mathMark);

                        System.out.println("enter student science mark :-");
                        int scienceMark = Integer.parseInt(sc.nextLine());
                        cs.setInt(13, scienceMark);

                        System.out.println("enter student history and geography mark :-");
                        int hisAndGeoMark = Integer.parseInt(sc.nextLine());
                        cs.setInt(14, hisAndGeoMark);

                        double totalmark = odiaMark + englishMark + hindiMark + mathMark + scienceMark + hisAndGeoMark;
                        System.out.println(totalmark);
                        cs.setDouble(15, totalmark);

                        double percentage = (totalmark / 600) * 100;
                        double roundedValue = Math.round(percentage * 100.0) / 100.0;
                        cs.setDouble(16, roundedValue);
                        System.out.println(roundedValue);
                        String result = "";

                        if (percentage > 35) {
                            result = "pass";
                        } else {
                            result = "fail";
                        }
                        cs.setString(17, result);

                        cs.execute();

                        System.out.println("details updated successfully.");
                        break;

                    case 2:
                        System.out.println("enter student roll no to retrieve student details...");
                        int rrollno = Integer.parseInt(sc.nextLine());

                        cs2.setInt(1, rrollno);

                        cs2.registerOutParameter(2, Types.VARCHAR);
                        cs2.registerOutParameter(3, Types.VARCHAR);
                        cs2.registerOutParameter(4, Types.VARCHAR);
                        cs2.registerOutParameter(5, Types.VARCHAR);
                        cs2.registerOutParameter(6, Types.INTEGER);
                        cs2.registerOutParameter(7, Types.VARCHAR);
                        cs2.registerOutParameter(8, Types.BIGINT);
                        cs2.registerOutParameter(9, Types.DOUBLE);
                        cs2.registerOutParameter(10, Types.DOUBLE);
                        cs2.registerOutParameter(11, Types.VARCHAR);
                        

                        cs2.execute();

                        
                            System.out.println("-----------Student Details-----------");
                            System.out.println("Student Roll No: " + rrollno);
                            System.out.println("Student Name: " + cs2.getString(2));
                            System.out.println("Student Branch: " + cs2.getString(3));
                            System.out.println("Student City: " + cs2.getString(4));
                            System.out.println("Student State: " + cs2.getString(5));
                            System.out.println("Student Pincode: " + cs2.getInt(6));
                            System.out.println("Student Mail: " + cs2.getString(7));
                            System.out.println("Student Phone Number: " + cs2.getLong(8));
                            System.out.println("Student Total Mark: " + cs2.getDouble(9));
                            System.out.println("Student Percentage: " + cs2.getDouble(10));
                            System.out.println("Student Result: " + cs2.getString(11));
                     
                        break;

                    case 3:
                        con.close();
                        System.exit(0);

                    default:
                        System.out.println("invalid option:");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
