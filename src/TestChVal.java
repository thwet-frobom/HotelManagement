import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
@WebServlet(urlPatterns = {"/bookingTest"})
public class TestChVal extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] single = req.getParameterValues("single");
		String[] doubleroom = req.getParameterValues("double");
		
		/*String singleRoom = req.getParameter("singleRoom");
		String doubleRoom = req.getParameter("doubleRoom");
		int singleNo = Integer.parseInt(singleRoom);
		int doubleNo = Integer.parseInt(doubleRoom);*/
		
        Connection con;
        Statement stmt;
        ResultSet rs;
        PreparedStatement ps ;
        
        try{
        Class.forName("com.mysql.jdbc.Driver");
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB","root", "root35");
        stmt = (Statement) con.createStatement();
        rs = (ResultSet) stmt.executeQuery("Select * from room");
        PrintWriter pw = resp.getWriter();
        for(int i =0 ; i<single.length; i++ ){
        	int singleNo = Integer.parseInt(single[i]);
        	String updateQty= "UPDATE room SET isBooked=" + 1 + " WHERE roomNo='"+singleNo+"'";

    	    ps = (PreparedStatement) con.prepareStatement(updateQty);

    		ps.executeUpdate(updateQty);
    		
        }
        for(int i = 0; i< doubleroom.length; i++){
        	int doubleNo = Integer.parseInt(doubleroom[i]);
        	String updateDouble= "UPDATE room SET isBooked=" + 1 + " WHERE roomNo='"+doubleNo+"'";
    		
    		
    		ps = (PreparedStatement) con.prepareStatement(updateDouble);

    		ps.executeUpdate(updateDouble);
        }
        int allPrice=0;
        while(rs.next())
        {
        	String roonNo = rs.getString(3);
          	 int roomCost = Integer.parseInt(rs.getString(6));
          	 for(String s: single){
          		 if(roonNo.equals(s)){
          			 allPrice = allPrice+roomCost;
          		 }
          	 }
          	 
          	for(String s: doubleroom){
         		 if(roonNo.equals(s)){
         			 allPrice = allPrice+roomCost;
         		 }
         	 }
       	 
        	
        }
        
       pw.println("All Price is:"+allPrice);
        }
        catch (Exception e){
       	 System.out.println(e);
        }
      
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req,resp);
	}

	
}
