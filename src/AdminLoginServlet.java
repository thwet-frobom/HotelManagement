import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

@WebServlet(urlPatterns = { "/loginAdmin" })
public class AdminLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        System.out.println("User Name From:" + email);
        System.out.println("Password From:" + password);
        /*
         * String username = "TTMPM"; String password = "12345";
         */

        Connection con;
        Statement stmt;
        ResultSet rs;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB", "root", "root35");
            stmt = (Statement) con.createStatement();
           // String sql = "Select * from user";
            rs = (ResultSet) stmt.executeQuery("Select * from user");
            PrintWriter pw = resp.getWriter();

            while (rs.next()) {

                String eMail = rs.getString(8);
                String userPass = rs.getString(3);

                if (email.equals(eMail) && password.equals(userPass)) {
                    /*
                     * RequestDispatcher rd = req.getRequestDispatcher("/adminLogin.jsp"); rd.forward(req, resp);
                     */

                    resp.sendRedirect("modifyHotel.jsp");
                } else {
                    /*
                     * RequestDispatcher rd = req.getRequestDispatcher("/index.jsp"); rd.forward(req, resp);
                     */
                    pw.println("Please Try Again:");

                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(req, resp);
    }

}
