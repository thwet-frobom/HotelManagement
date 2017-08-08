import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

@WebServlet(urlPatterns = {"/testID"})
public class TestID extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ans = "";
		try {
			ans = view();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Answer is:"+ans);
		
		//System.out.println("Ans is:");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req,resp);
	}

/*public String getUserID(){
		
		String ur ="Hotel Admin";
		String squery = "select max(user_id) from user where userrole='"+ur+"'";
		ResultSet res = null;
		String result = null;
		Statement stmt = null;
		
		try {
			
			res = (ResultSet) stmt.executeQuery(squery);
			 result = res.getString(1);

        	System.out.println("User id:"+result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		 //ResultSet rs=st.executeQuery("select * from master_panel where choice_type='"+ur+"'");
		 System.out.println("Res:"+squery);
			  
		return result;
}*/

public  String view() throws ClassNotFoundException
{

	   String ans = "";
    try
    {
    	String ur ="Hotel Admin";
        String sql="select max(user_id) from user where userrole='"+ur+"'";
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB", "root", "root35");
        Statement stmt=(Statement) con.createStatement();
        ResultSet rs=(ResultSet) stmt.executeQuery(sql);

     
        while(rs.next())
        {
           ans = rs.getString(1);

        }

    } 
    catch (SQLException e)
    {
        e.printStackTrace();
    }
    
	return ans;

}

}