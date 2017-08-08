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

@WebServlet(urlPatterns = {"/portalAdminAddDelete"})
public class HotelAdminAddDeleteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Statement stmt = null;
        ResultSet rs;
		HttpSession session=req.getSession(true);
		PrintWriter out = resp.getWriter();
		
		String name = req.getParameter("name");  
        String password = req.getParameter("password");  
        String cpassword = req.getParameter("cpassword");  
        String nrc = req.getParameter("nrc");  
        String hpaddress = req.getParameter("address");  
        String phone = req.getParameter("phone");  
        String email = req.getParameter("email");  
        String gender = req.getParameter("gender"); 
        
        String hotelid = req.getParameter("hotelid");
        
        String button = req.getParameter("modify");
        System.out.println("Button is:"+button);
        
        try{
        	
        	 Class.forName("com.mysql.jdbc.Driver");
			    Connection    con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB","root", "root35");
			        stmt = (Statement) con.createStatement();  
		        	
			        
        	if(button.equals("Submit")){
            	
            	PreparedStatement pst =(PreparedStatement) con.prepareStatement("insert into user values(?,?,?,?,?,?,?,?,?,?)");//try2 is the name of the table  

                pst.setString(1, null);
                pst.setString(2,name);  
                pst.setString(3,password);        
                pst.setString(4,cpassword);
                pst.setString(5,nrc);
                pst.setString(6,hpaddress);
                pst.setString(7,phone);
                pst.setString(8,email);
                pst.setString(9, gender);
                pst.setString(10, "Hotel Admin");
               /* pst.setString(8,Password1);
                pst.setString(9,Password2);
    */

                int i = pst.executeUpdate();
                con.close(); 
                System.out.println("Hotel Admin Add Successfully");
            }else if(button.equals("Delete")){
            	 PreparedStatement pst = (PreparedStatement) con.prepareStatement("delete from hotel where hotel_id =?");
            	 pst.setString(1, hotelid);
            	 pst.executeUpdate();
            	 out.println("<body><h3>deleted with id " + hotelid + "</h3></body></html>");
            }
        }catch(Exception e){
        	e.printStackTrace();
        }
       
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req,resp);
	}

	
}
