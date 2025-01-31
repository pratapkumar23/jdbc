package adv_task;

import java.sql.*;
import java.util.*;

public class Db_con_lab {
	
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		
		try(sc;)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con= DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1521/orcl","pratap","123");
			
		Statement smt=con.createStatement();
		try
		{
			smt.execute("create table Employee_info(empId number(10) primary key,empName varchar2(35) ,empSalary number(10,2),empAddress varchar2(20),empMailId varchar2(20),empPhNo number(10))");
				System.out.println("table created sucessfully");	
		}
		catch(Exception e)
		{
			System.out.println("table already available ");
		}
		
		while(true)
		{
			System.out.println("----------operations are --------------");
			System.out.println("1.insert data");
			System.out.println("2.Update employee salary,empMailId and empPhNo with the help of eid");
			System.out.println("3.Find those employee whose name starts with 'A'");
			System.out.println("4.Count how many employee record present in table");
			System.out.println("5.delete employee who is getting maximum salary");
			System.out.println("6.Show all employee");
			
			System.out.print("enter your choice :-");
			int choice=Integer.parseInt(sc.nextLine());
			
			switch(choice)
			{	
			case 1:
				System.out.print("enter employee id :-");
				int id=Integer.parseInt(sc.nextLine());
				
				System.out.print("enter employee name :-");
				String name=sc.nextLine();
				
				System.out.print("enter employee salary :-");
				double salary=Double.parseDouble(sc.nextLine());
				
				System.out.print("enter employee address :-");
				String address=sc.nextLine();
				
				System.out.print("enter employee mail_id :-");
				String mailid=sc.nextLine();
				
				System.out.print("enter employee number :-");
				long number=Long.parseLong(sc.nextLine());
				
			int k=smt.executeUpdate("insert into Employee_info values("+id+",'"+name+"',"+salary+",'"+address+"','"+mailid+"',"+number+")");
			
			if(k>0) {
				System.out.println("insertion sucessful ");
			}
					break;
					
			case 2:
				
				break;
				
			case 3:
				break;
				
			case 4:	
				break;
				
			case 5:
				break;
				
			case 6:
				break;
			
			case 7:
				System.exit(0);
				
			default:
				System.out.println("invalid choice ");
			
			}		
		}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
