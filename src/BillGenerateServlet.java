import java.io.IOException;
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

@WebServlet(urlPatterns = {"/billBooking"})
public class BillGenerateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String foodSnack = req.getParameter("snack");
		String foodDrink = req.getParameter("drink");
		 HttpSession session = req.getSession();
		 String useremail = (String) session.getAttribute("useremail");
		//String useremail = "ttm@gmail";
		
		System.out.println("Email is:"+useremail);
		 Connection con;
	        Statement stmt;
	        ResultSet rs;
	        PreparedStatement ps ;
	        try{
	        	Class.forName("com.mysql.jdbc.Driver");
			      con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB","root", "root35");
			        stmt = (Statement) con.createStatement();
			         ps=(PreparedStatement) con.prepareStatement
			                  ("insert into tempfood values(?,?,?)");
			         ps.setString(1, null);
			         ps.setString(2, useremail);
			         ps.setString(3, foodSnack);
			         
			         int i=ps.executeUpdate();
				        System.out.println("Food Add Success:");
				        resp.sendRedirect("foodBook.jsp"); 
			        
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req,resp);
	}

	public int getQuantity(){
		int quantity = 0;
		Connection con;
        Statement stmt;
        ResultSet rs;
        PreparedStatement ps ;
        try{
        	Class.forName("com.mysql.jdbc.Driver");
		      con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB","root", "root35");
		        stmt = (Statement) con.createStatement();
		         ResultSet res = (ResultSet) stmt.executeQuery("select * from food");
		        while(res.next()){
		        	String qa = res.getString(6);
		        	quantity = Integer.parseInt(qa);
		        }
		        
        }catch(Exception e){
        	e.printStackTrace();
        }
	
		return quantity;
	}
	
}
