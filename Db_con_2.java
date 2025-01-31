package adv_task;
import java.util.*;

import java.sql.*;
public class Db_con_2 
{
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		
		try(sc;)
		{
		Class.forName("oracle.jdbc.driver.OracleDriver");
			
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "pratap", "123");
        
        Statement stm=con.createStatement();
        
        while(true)
        {
        	System.out.println("---------operations are------------");
        	
        	System.out.println("1.add product");
        	System.out.println("2.view all product ");
        	System.out.println("3.enter bycode for specific product");
        	System.out.println("4.exit");
        	
        	System.out.print("enter your choice -");
        	
        	int choice=Integer.parseInt(sc.nextLine());
        	
        	switch(choice)
        	{
        	     case 1:
        	    	 	System.out.print("enter product code :-");
        	    	 	int pcode=Integer.parseInt(sc.nextLine());
        	    	 	
        	    	 	System.out.print("enter product name :-");
        	    	 	String pname=sc.nextLine();
        	    	 	
        	    	 	System.out.print("enter product price :-");
        	    	 	double price=Double.parseDouble(sc.nextLine());
        	    	 	
        	    	 	System.out.print("enter product quantity :-");
        	    	 	int quantity=Integer.parseInt(sc.nextLine());
        	    	 	
        	    	 	int k=stm.executeUpdate("Insert into product70 values("+pcode+",'"+pname+"',"+price+","+quantity+")");
        	    	 	
        	    	 	if (k>0)
        	    	 		System.out.println("product added sucessfully");
        	    	 break;
        	    	 
        	     case 2:
        	    	 ResultSet rs=stm.executeQuery("select * from product70 ");
        	    	 
        	    	 System.out.println("--------product details---------");
        	    	 
        	    	 while(rs.next())
        	    	 {
        	    		 	System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getInt(4));
        	    	 }
        	    	 break;
        	    	 
        	     case 3:
        	    	 System.out.print("enter pcode details for find speicific row :-");
        	    	 int pcode1=Integer.parseInt(sc.nextLine());
        	    	 
        	    	 ResultSet rs1=stm.executeQuery("select * from product70 where pcode="+pcode1);
        	    	 
 	    		 	System.out.println(rs1.getInt(1)+"\t"+rs1.getString(2)+"\t"+rs1.getDouble(3)+"\t"+rs1.getInt(4));
	 
        	    	 break;
        	    	 
        	     case 4: 
        	    	 System.exit(0);
        	}
        }
        
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
