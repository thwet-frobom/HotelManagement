import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

@WebServlet(urlPatterns = {"/calculatebill"})
public class CalculateBillServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		//String useremail = "ttt@gmail.com";
		/*
		String fromdate = (String) session.getAttribute("fromdate");
		String todate = (String) session.getAttribute("todate");
		
		 System.out.println("From Date of Book"+fromdate);
		 System.out.println("To date of Book"+todate);*/
		String useremail = req.getParameter("email");
		
		Connection con;
        Statement stmt;
        ResultSet rs;
        ResultSet rs1;
        PreparedStatement ps ;
       
      
        PrintWriter pw = resp.getWriter();
       
        try{
        	String rp = getRoomPrice(useremail);
        	int rp1 = Integer.parseInt(rp);
        	long days = getDays();
        	System.out.println("Days is:"+days);
        	System.out.println("Room Price is::"+rp1*days);
        	
        	String fp = getFoodPrice(useremail);
        	int fp1 = Integer.parseInt(fp);
        	
        	int price = (int) ((rp1*days) + fp1);
        	 pw.println("Your Bill is:"+price);
        }catch(Exception e){
        	e.printStackTrace();
        }
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req,resp);
		
	}
	
	public String getRoomPrice(String useremail){
		int price = 0;
		Connection con;
        Statement stmt;
        ResultSet rs;
        ResultSet rs1;
        PreparedStatement ps ;
       // String useremail = "ttt@gmail.com";
        
        try{
        Class.forName("com.mysql.jdbc.Driver");
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB","root", "root35");
        stmt = (Statement) con.createStatement();
        rs = (ResultSet) stmt.executeQuery("Select * from  temproom");
        
       // rs = (ResultSet) stmt.executeQuery("Select * from temproom where useremail='"+useremail+"'");
        System.out.println("Result Set is:"+rs);        
       // rs1 = (ResultSet) stmt.executeQuery("select * from tempfood where useremail='"+useremail+"'");
     
        while(rs.next()){
        	String userEmail = rs.getString(2);
        	String rprice = rs.getString(3);
           
            if(checkEmail(useremail,userEmail)){
            	int rprice1 = Integer.parseInt(rprice);
            	
             price = price +rprice1;
            }
        	
        }
        
 
        
        }
        
        catch (Exception e){
       	 System.out.println(e);
        }
		return String.valueOf(price);
	}
	
	public String getFoodPrice(String useremail){
		int price = 0;
		Connection con;
        Statement stmt;
        ResultSet rs;
        ResultSet rs1;
        PreparedStatement ps ;
       // String useremail = "ttt@gmail.com";
        
        try{
        Class.forName("com.mysql.jdbc.Driver");
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB","root", "root35");
        stmt = (Statement) con.createStatement();
        rs = (ResultSet) stmt.executeQuery("Select * from  tempfood");
        
        while(rs.next()){
        	String userEmail = rs.getString(2);
        	String rprice = rs.getString(3);
           
       
            if(checkEmail(useremail,userEmail)){
            	int rprice1 = Integer.parseInt(rprice);
            	
             price = price +rprice1;
            }
        	
        }
        
 
        
        }
        
        catch (Exception e){
       	 System.out.println(e);
        }
		return String.valueOf(price);
	}
	
	public boolean checkEmail(String  email1, String email2){
		String e1 = new String(email1);
	    String e2 = new String(email2);

	    boolean flag = false;
	     Pattern emailFinder = Pattern.compile("gmail.com");
	     Matcher emailmatcher = emailFinder.matcher(email1);
	    // System.out.println("EmailMatcher:"+emailmatcher);
	     Matcher emailmatcher1 = emailFinder.matcher(email2);
	    // System.out.println("EmailMatcher1:"+emailmatcher1);
	     
	     if (emailmatcher.find() && emailmatcher1.find()) {

	            email1 = email1.replaceAll(".","");
	            email2 = email2.replaceAll(".","");

	            if(email1.equals(email2)){
	                //System.out.println("The values match");
	            	flag=true;
	            }

	     }
		return flag;
	}

	public static long getDays(){
		Connection con;
        Statement stmt;
        ResultSet rs;
        ResultSet rs1;
        PreparedStatement ps ;
        
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		/*String inputString1 = "23/01/1997";
		String inputString2 = "27/04/1997";*/

		long day = 0 ;
		
		try {
			 Class.forName("com.mysql.jdbc.Driver");
		        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB","root", "root35");
		        stmt = (Statement) con.createStatement();
		        rs = (ResultSet) stmt.executeQuery("Select * from  temproom");
		        Date fd1 = null;
		        Date td1 = null;
		        while(rs.next()){
		        	String fd = rs.getString(5);
		        	String td = rs.getString(6);
		        	
		        	 fd1 = myFormat.parse(fd);
		        	 td1 = myFormat.parse(td);
		        }
		        if (fd1.compareTo(td1)<0){
		        	long diff = td1.getTime() - fd1.getTime();
				     day = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			    	 System.out.println("Today Date is Lesser than my Date");
			    }
			    else if (fd1.compareTo(td1)>0){
			        System.out.println("Today Date is Greater than my date"); 
			    }else{
			        System.out.println("Both Dates are equal"); 
			    }
		    
		    System.out.println("Days:"+day);
		  
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		return day;
	}
	
}
