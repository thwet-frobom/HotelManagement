import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;

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

@WebServlet(urlPatterns = {"/food"})
public class FoodModifyServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Statement stmt = null;
        ResultSet rs;
        PrintWriter out =resp.getWriter();
        HttpSession session=req.getSession();  
        
		try{
			 Class.forName("com.mysql.jdbc.Driver");
		    Connection    con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB","root", "root35");
		        stmt = (Statement) con.createStatement();  
	        String hotelid = req.getParameter("hotelid");
	        String foodname = req.getParameter("foodname");
	        String foodtype = req.getParameter("foodtype");
	        String quantity = req.getParameter("quantity");
	        String foodprice = req.getParameter("foodprice");
	        String foodid = req.getParameter("foodid");
	        
		    String buttonadd = req.getParameter("modify");
		    
		    if(buttonadd.equals("Add")){
		    	PreparedStatement ps=(PreparedStatement) con.prepareStatement
		                  ("insert into food values(?,?,?,?,?,?)");
		    	
		    	ps.setString(1, null);
		    	ps.setString(2, hotelid);
		    	ps.setString(3, foodname);
		    	ps.setString(4, foodtype);
		    	ps.setString(5, foodprice);
		    	ps.setString(6, quantity);
		    	
		    	
		    	  int i=ps.executeUpdate();
			        System.out.println("Success:");
		    }else if(buttonadd.equals("Update")) {
		    	String foodID = (String) session.getAttribute("foodid");
		    	
		    	System.out.println("Food ID IS:"+foodID);
		    	
		    	 String query = "update food set hotel_id='"+hotelid+"',food_name='"+foodname+"',food_type='"+foodtype+"',food_price='"+foodprice+"',quantity='"+quantity+"' where food_id="+foodID;
				    Statement upstmt = (Statement) con.createStatement();
				    int i = upstmt.executeUpdate(query);
				    //out.println("query" + query);
				   System.out.println("update successfully");
		    }else if(buttonadd.equals("Delete")){
		    	 PreparedStatement pst = (PreparedStatement) con.prepareStatement("delete from food where food_id =?");
		    	 pst.setString(1, foodid);
		    	 pst.executeUpdate();
		    	 out.println("<body><h3>deleted with id " + foodid + "</h3></body></html>");
		    }
		   
		   
	          
	        }
	        catch(Exception se)
	        {
	            se.printStackTrace();
	        }
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req,resp);
	}

	
}
