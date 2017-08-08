import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

@WebServlet(urlPatterns = {"/register"})
public class TestServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 /*RequestDispatcher rd = req.getRequestDispatcher("/registerUser.jsp");
			rd.forward(req, resp);*/
		resp.setContentType("text/html");  
        PrintWriter pw = resp.getWriter(); 
        Connection conn=null;
        String url="jdbc:mysql://localhost:3306/";
        String dbName="hotelDB";
        String driver="com.mysql.jdbc.Driver";
        
        try{  
            String name = req.getParameter("name");  
            String password = req.getParameter("password");  
            String cpassword = req.getParameter("cpassword");  
            String nrc = req.getParameter("nrc");  
            String address = req.getParameter("address");  
            String phone = req.getParameter("phone");  
            String email = req.getParameter("email");  
            String gender = req.getParameter("gender");  
           /* String Password1 = req.getParameter("password1");  
            String Password2 = req.getParameter("password2");  */

           
            
            
            Class.forName(driver);  
            conn =  (Connection) DriverManager.getConnection(url+dbName,"root", "root35");
            PreparedStatement pst =(PreparedStatement) conn.prepareStatement("insert into user values(?,?,?,?,?,?,?,?,?,?)");//try2 is the name of the table  

           
            if(checkContainEmail(email)){
            	pw.println("You have already Registered:");
            }else{
            	pst.setString(1, null);
                pst.setString(2,name);  
                pst.setString(3,password);        
                pst.setString(4,cpassword);
                pst.setString(5,nrc);
                pst.setString(6,address);
                pst.setString(7,phone);
                pst.setString(8,email);
                pst.setString(9, gender);
                pst.setString(10, "user");
               /* pst.setString(8,Password1);
                pst.setString(9,Password2);
    */

                int i = pst.executeUpdate();
                conn.close(); 
                RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
        		rd.forward(req, resp);
            }
            
            /*String msg=" ";
            if(i!=0){  
              msg="Record has been inserted";
              pw.println("<font size='6' color=blue>" + msg + "</font>");  


            }  
            else{  
              msg="failed to insert the data";
              pw.println("<font size='6' color=blue>" + msg + "</font>");
             }  */
            //pst.close();
          }  
          catch (Exception e){  
           e.printStackTrace();
          }  

       /* RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
		rd.forward(req, resp);*/
  
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		doGet(req, resp);
  }

	public static boolean checkContainEmail(String email){
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
            String sql = "SELECT * From user WHERE e_mail = ?";
            PreparedStatement   prep = (PreparedStatement) conn.prepareStatement(sql);
            prep.setString(1, email);
	           
            ResultSet res = prep.executeQuery();
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
