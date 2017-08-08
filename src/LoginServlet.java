import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
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

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String email = req.getParameter("email");
         String password = req.getParameter("password");
         System.out.println("User Name From:"+email);
         System.out.println("Password From:"+password);
		 /*String username = "ttt@gmail.com";
         String password1 = "11111";*/
         HttpSession session = req.getSession();
         
         session.setAttribute("useremail", email);
         
         PrintWriter out=resp.getWriter();
         
         if(checkContainEmail(email,password)){
        	 resp.sendRedirect("roomBook.jsp");
         }else{
        	 out.print("Sorry, username or password error!");
        	 
  			req.getRequestDispatcher("userLogin.jsp").include(req, resp);
         }
       
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
		
	}

	public static boolean checkContainEmail(String email, String password){
		boolean flag = false;
		Connection conn=null;
		Statement stmt;
		ResultSet rs;
        String url="jdbc:mysql://localhost:3306/";
        String dbName="hotelDB";
        String driver="com.mysql.jdbc.Driver";
		try
        {
			  Class.forName(driver);  
	           conn =  (Connection) DriverManager.getConnection(url+dbName,"root", "root35");
            String sql = "SELECT * From user WHERE e_mail = ? AND password = ?";
            PreparedStatement   prep = (PreparedStatement) conn.prepareStatement(sql);
            prep.setString(1, email);
	        prep.setString(2,password);   
            ResultSet res = (ResultSet) prep.executeQuery();
            if(res.next()){
            	return true;
            }
            else{
            	flag = false;
            }
           

           
        } 
        catch (Exception e)
        {
            System.out.println("cannot connect");
            e.printStackTrace();
        }
		return flag;
	}

	
	
}
