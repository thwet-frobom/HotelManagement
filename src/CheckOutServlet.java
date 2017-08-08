import java.io.IOException;
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

@WebServlet(urlPatterns = {"/checkout"})
public class CheckOutServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//String roomno = req.getParameter("roomno");
		//String[] roomno = req.getParameterValues("roomno");
		
		String email = req.getParameter("email");
        /* Delete All Records by email*/
        deleteUserFromTempRoom(email);
        deleteUserFromTempFood(email);
     	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req,resp);
	}

	public void deleteUserFromTempRoom(String email){
		
		Connection con;
        Statement stmt;
        ResultSet rs;
        ResultSet rs1;
        PreparedStatement ps ;
        
        try{
        	Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB","root", "root35");
            stmt = (Statement) con.createStatement();
            rs = (ResultSet) stmt.executeQuery("Select * from  temproom");
            
           PreparedStatement pst = (PreparedStatement) con.prepareStatement("delete from temproom where useremail =?");
       	 pst.setString(1, email);
       	 pst.executeUpdate();
       	 //out.println("<body><h3>deleted with id " + email + "</h3></body></html>");
        }catch(Exception e){
        	e.printStackTrace();
        }
	}
	
	public void deleteUserFromTempFood(String email)
	{
		Connection con;
        Statement stmt;
        ResultSet rs;
        ResultSet rs1;
        PreparedStatement ps ;
        
        try{
        	Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB","root", "root35");
            stmt = (Statement) con.createStatement();
            rs = (ResultSet) stmt.executeQuery("Select * from  tempfood");
            
           PreparedStatement pst = (PreparedStatement) con.prepareStatement("delete from tempfood where useremail =?");
       	 pst.setString(1, email);
       	 pst.executeUpdate();
       	 //out.println("<body><h3>deleted with id " + email + "</h3></body></html>");
        }catch(Exception e){
        	e.printStackTrace();
        }
	}
	/*
	public static void updateRoom(String email, String[]roomno){
		Connection con;
        Statement stmt;
        ResultSet rs;
        PreparedStatement ps ;
        try{
        	Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB","root", "root35");
            stmt = (Statement) con.createStatement();
            rs = (ResultSet) stmt.executeQuery("Select * from room");
          if(roomno.length!=0){
    		 for(int i =0 ; i<roomno.length; i++ ){
              	int singleNo = Integer.parseInt(roomno[i]);
              	String rNO = String.valueOf(singleNo);
              	String price = "";
              	while(rs.next()){
              		String roomNo = rs.getString(3);
              		
              		if(rNO.equals(roomNo)){
              			String updateQty= "UPDATE room SET isBooked=" + 0 + " WHERE roomNo='"+singleNo+"'";

                  	    ps = (PreparedStatement) con.prepareStatement(updateQty);

                  		ps.executeUpdate(updateQty);
              		}
              	}
              	String updateQty= "UPDATE room SET isBooked=" + 1 + " WHERE roomNo='"+singleNo+"'";

          	    ps = (PreparedStatement) con.prepareStatement(updateQty);

          		ps.executeUpdate(updateQty);
        }
	
  		System.out.println(" Room Update Call Successfully:");
        }
        }
           catch(Exception e){
        	e.printStackTrace();
        }
	}
	
	public static boolean checkRoomExist(String email, String[]roomno){
		boolean flag = false;
		Connection con;
        Statement stmt;
        ResultSet rs;
        PreparedStatement ps ;
        try{
        	Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB","root", "root35");
            stmt = (Statement) con.createStatement();
            rs = (ResultSet) stmt.executeQuery("Select * from temproom");
          if(roomno.length!=0){
    		 for(int i =0 ; i<roomno.length; i++ ){
              	int singleNo = Integer.parseInt(roomno[i]);
              	String rNO = String.valueOf(singleNo);
              	String price = "";
              	while(rs.next()){
              		String roomNo = rs.getString(4);
              		
              		if(rNO.equals(roomNo)){
              			flag = true;
              		}else{
              			return false;
              		}
              	}
              	
        }
	
        }
        }
           catch(Exception e){
        	e.printStackTrace();
        }
		return flag;
	}
	*/
}
