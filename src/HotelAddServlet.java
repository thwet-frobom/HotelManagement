import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Hashtable;

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

import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadBean;
import javazoom.upload.UploadFile;

@WebServlet(urlPatterns = {"/portalHotel"})
public class HotelAddServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Ok for show all add hotel");
		
		Statement stmt = null;
        ResultSet rs;
		HttpSession session=req.getSession(true);
		PrintWriter out = resp.getWriter();
		
			// add image code...
			MultipartFormDataRequest mreq =null;
			Hashtable ht=null;
			String file="";
			UploadFile uFile = null;
			try {
				mreq = new MultipartFormDataRequest(req);
				ht = (mreq).getFiles();
				uFile=(UploadFile)ht.get("image");
				UploadBean upBean=new UploadBean();
				upBean.setFolderstore(req.getRealPath("images"));
				upBean.store(mreq,"image");
				file = uFile.getFileName();
				System.out.println ("File : "+file);
			}
			catch (Exception ex) {
				System.out.println (ex);
			}
			
			
			
			String uid = "";
			try {
				uid = view();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("UIDIS:"+uid);
            //String button = req.getParameter("modify");
            String hotelAddButton = mreq.getParameter("modify");
            
           // System.out.println("Button is:"+button);
            System.out.println("Multipart Button is:"+hotelAddButton);
            
            
			 try{
				 Class.forName("com.mysql.jdbc.Driver");
			    Connection    con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB","root", "root35");
			        stmt = (Statement) con.createStatement();  
		        	

		            String hotelname = mreq.getParameter("hotelname");
					System.out.println(hotelname);
					String userid = mreq.getParameter("userid");
					System.out.println(userid);
					String address = mreq.getParameter("address");
					System.out.println(address);
		            String cImage = file;;
		            
		            //String hotelid = req.getParameter("hotelid");
		            
			        if(hotelAddButton.equals("Add")){
			        	
						cImage = "images/"+cImage;
						System.out.println(cImage);
						System.out.println();
			        	 PreparedStatement ps=(PreparedStatement) con.prepareStatement
				                  ("insert into hotel values(?,?,?,?,?)");
				        
				        ps.setString(1, null);
				        ps.setString(2, userid);
			            ps.setString(3,hotelname);
			            ps.setString(4, address);
				        ps.setString(5, cImage);
				        
				      
				        int i=ps.executeUpdate();
				        System.out.println("Hotel Add Success:");
			        }else if(hotelAddButton.equals("Update")){
			        	 String query = "update hotel set user_id='"+userid+"',hotel_name='"+hotelname+"',address='"+address+"',hotelImages='"+cImage+"' where hotel_id="+4;
			        	 Statement stmt1 = (Statement) con.createStatement();
			        	 int i = stmt1.executeUpdate(query);
			        	 //out.println("query" + query);
			        	 out.println("update successfully");
			        }
			       
		         /* if(i>0)
		          {
		        	 
		      			
		        	  session.setAttribute("content_page","AddHotelSuccess.jsp");
		      		
		          }
		          else
		          {
		        	  session.setAttribute("content_page","fail.jsp");
		          }
		          resp.sendRedirect("Admin.jsp");*/
		          
		        }
		        catch(Exception se)
		        {
		            se.printStackTrace();
		        }
			
		      
			
			//resp.sendRedirect("template.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	

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
