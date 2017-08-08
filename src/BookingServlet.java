import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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

@WebServlet(urlPatterns = { "/booking" })
public class BookingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        HttpSession session = req.getSession();

        String[] single = req.getParameterValues("single");
        String[] doubleroom = req.getParameterValues("double");

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dateobj = new Date();
        String cudate = df.format(dateobj);

        /*
         * String fdate = "02/02/2017"; String edate = "01/02/2017";
         */

        System.out.println("Today Date is:" + cudate);

        String fromdate = req.getParameter("fromdate");
        String todate = req.getParameter("todate");

        session.setAttribute("fromdate", fromdate);
        session.setAttribute("todate", todate);

        System.out.println("From Date is:" + fromdate);
        System.out.println("To Date is:" + todate);

        String useremail = (String) session.getAttribute("useremail");

        System.out.println("Email from user is:" + useremail);

        Connection con;
        Statement stmt;
        ResultSet rs;
        PreparedStatement ps;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB", "root", "root35");
            stmt = (Statement) con.createStatement();
            rs = (ResultSet) stmt.executeQuery("Select * from temproom");
            PrintWriter pw = resp.getWriter();

            if (rs.next()) {
                if (checkDates(fromdate, todate)) {
                    if (checkEndDateSingleRoom(todate, single) || checkEndDateDoubleRoom(todate, doubleroom)) {
                        hotelAdminSingleRoomAdd(single, useremail, fromdate, todate);
                        hotelAdminDoubleRoomAdd(doubleroom, useremail, fromdate, todate);
                    } else if (!checkStartDateSingleRoom(fromdate, single) || !checkStartDateDoubleRoom(fromdate, doubleroom)) {
                        hotelAdminSingleRoomAdd(single, useremail, fromdate, todate);
                        hotelAdminDoubleRoomAdd(doubleroom, useremail, fromdate, todate);
                    } else {
                        resp.sendRedirect("checkInRoom.jsp");
                    }

                } else {
                    pw.println("Sorry: Please Check Date:");
                }
            }

        }

        catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(req, resp);
    }

    public static void tempRoomAdd(String roomNo, String price, String useremail, String fromdate, String todate) throws ClassNotFoundException, SQLException {
        Connection con;
        Statement stmt;
        ResultSet rs;
        PreparedStatement ps;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB", "root", "root35");
            stmt = (Statement) con.createStatement();
            ps = (PreparedStatement) con.prepareStatement("insert into temproom values(?,?,?,?,?,?)");
            ps.setString(1, null);
            ps.setString(2, useremail);
            ps.setString(3, price);
            ps.setString(4, roomNo);
            ps.setString(5, fromdate);
            ps.setString(6, todate);

            int i = ps.executeUpdate();
            System.out.println("Room Add Success:");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean checkDates(String date1, String date2) {
        boolean flag = false;
        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Date1 is :" + date1);
        System.out.println("Date2 is:" + date2);

        try {
            Date fd = myFormat.parse(date1);
            Date sd = myFormat.parse(date2);

            System.out.println("Date Format 1:" + fd);
            System.out.println("Date Format 2:" + sd);

            if (date1.compareTo(date2) < 0) {
                flag = true;
                // System.out.println("Today Date is Lesser than my Date");
            } else if (date1.compareTo(date2) > 0) {

                // System.out.println("Today Date is Greater than my date");
                flag = false;
            } else {
                // System.out.println("Both Dates are equal");
                flag = true;

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return flag;
    }

    public static void hotelAdminSingleRoomAdd(String[] single, String useremail, String fromdate, String todate) {
        Connection con;
        Statement stmt;
        ResultSet rs;
        PreparedStatement ps;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB", "root", "root35");
            stmt = (Statement) con.createStatement();
            rs = (ResultSet) stmt.executeQuery("Select * from room");
            if (single.length != 0) {
                for (int i = 0; i < single.length; i++) {
                    int singleNo = Integer.parseInt(single[i]);
                    String roomno = String.valueOf(singleNo);
                    String price = "";
                    while (rs.next()) {
                        String roomNo = rs.getString(3);

                        if (roomno.equals(roomNo)) {
                            price = rs.getString(6);
                        }
                    }

                    tempRoomAdd(roomno, price, useremail, fromdate, todate);
                }

                System.out.println("Temp Room Add Call Successfully:");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void hotelAdminDoubleRoomAdd(String[] doubleroom, String useremail, String fromdate, String todate) {
        Connection con;
        Statement stmt;
        ResultSet rs;
        PreparedStatement ps;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB", "root", "root35");
            stmt = (Statement) con.createStatement();
            rs = (ResultSet) stmt.executeQuery("Select * from room");

            if (doubleroom.length != 0) {
                for (int i = 0; i < doubleroom.length; i++) {
                    int doubleNo = Integer.parseInt(doubleroom[i]);
                    String roomno = String.valueOf(doubleNo);

                    String price = "";
                    while (rs.next()) {
                        String roomNo = rs.getString(3);

                        if (roomno.equals(roomNo)) {
                            price = rs.getString(6);
                        }
                    }
                    tempRoomAdd(roomno, price, useremail, fromdate, todate);
                    System.out.println("Temp Room Add Call Successfully:");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean checkEndDateSingleRoom(String endDate, String[] singleroom) {

        Connection con;
        Statement stmt;
        ResultSet rs;
        PreparedStatement ps;
        String edate = null;
        boolean flag = false;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB", "root", "root35");
            stmt = (Statement) con.createStatement();
            rs = (ResultSet) stmt.executeQuery("Select * from temproom");
            while (rs.next()) {

                // String edate = rs.getString(6);
                if (singleroom.length != 0) {
                    for (int i = 0; i < singleroom.length; i++) {
                        int doubleNo = Integer.parseInt(singleroom[i]);
                        String roomno = String.valueOf(doubleNo);

                        String price = "";
                        while (rs.next()) {
                            String roomNo = rs.getString(4);

                            if (roomno.equals(roomNo)) {
                                edate = rs.getString(6);
                                System.out.println("End Date from Database:" + edate);
                            }
                        }

                    }
                }
            }
            if (checkDates(endDate, edate)) {
                flag = true;
            } else {
                flag = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static boolean checkEndDateDoubleRoom(String endDate, String[] doubleRoom) {

        Connection con;
        Statement stmt;
        ResultSet rs;
        PreparedStatement ps;
        String edate = null;
        boolean flag = false;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB", "root", "root35");
            stmt = (Statement) con.createStatement();
            rs = (ResultSet) stmt.executeQuery("Select * from temproom");
            while (rs.next()) {

                // String edate = rs.getString(6);
                if (doubleRoom.length != 0) {
                    for (int i = 0; i < doubleRoom.length; i++) {
                        int doubleNo = Integer.parseInt(doubleRoom[i]);
                        String roomno = String.valueOf(doubleNo);

                        String price = "";
                        while (rs.next()) {
                            String roomNo = rs.getString(4);

                            if (roomno.equals(roomNo)) {
                                edate = rs.getString(6);
                                System.out.println("End Date from Database:" + edate);
                            }
                        }

                    }
                }
            }

            if (checkDates(endDate, edate)) {
                flag = true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static boolean checkStartDateSingleRoom(String startDate, String[] singleroom) {
        Connection con;
        Statement stmt;
        ResultSet rs;
        PreparedStatement ps;
        String sdate = null;
        boolean flag = false;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB", "root", "root35");
            stmt = (Statement) con.createStatement();
            rs = (ResultSet) stmt.executeQuery("Select * from temproom");
            while (rs.next()) {

                // String edate = rs.getString(6);
                if (singleroom.length != 0) {
                    for (int i = 0; i < singleroom.length; i++) {
                        int doubleNo = Integer.parseInt(singleroom[i]);
                        String roomno = String.valueOf(doubleNo);

                        String price = "";
                        while (rs.next()) {
                            String roomNo = rs.getString(4);

                            if (roomno.equals(roomNo)) {
                                sdate = rs.getString(5);
                                System.out.println("End Date from Database:" + sdate);
                            }
                        }

                    }
                }
            }

            if (checkDates(startDate, sdate)) {
                flag = true;
            } else {
                flag = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;

    }

    public static boolean checkStartDateDoubleRoom(String startDate, String[] doubleRoom) {

        Connection con;
        Statement stmt;
        ResultSet rs;
        PreparedStatement ps;
        String sdate = null;
        boolean flag = false;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB", "root", "root35");
            stmt = (Statement) con.createStatement();
            rs = (ResultSet) stmt.executeQuery("Select * from temproom");
            while (rs.next()) {

                // String edate = rs.getString(6);
                if (doubleRoom.length != 0) {
                    for (int i = 0; i < doubleRoom.length; i++) {
                        int doubleNo = Integer.parseInt(doubleRoom[i]);
                        String roomno = String.valueOf(doubleNo);

                        String price = "";
                        while (rs.next()) {
                            String roomNo = rs.getString(4);

                            if (roomno.equals(roomNo)) {
                                sdate = rs.getString(5);
                                System.out.println("End Date from Database:" + sdate);
                            }
                        }

                    }
                }
            }

            if (checkDates(startDate, sdate)) {
                flag = true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    /*
     * public void singleRoomUpdate(String[]single, String useremail, String fromdate, String todate){ Connection con; Statement
     * stmt; ResultSet rs; PreparedStatement ps ; try{ Class.forName("com.mysql.jdbc.Driver"); con = (Connection)
     * DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB","root", "root35"); stmt = (Statement)
     * con.createStatement(); rs = (ResultSet) stmt.executeQuery("Select * from room"); if(single.length!=0){ for(int i =0 ;
     * i<single.length; i++ ){ int singleNo = Integer.parseInt(single[i]); String roomno = String.valueOf(singleNo); String
     * price = ""; while(rs.next()){ String roomNo = rs.getString(3); if(roomno.equals(roomNo)){ price = rs.getString(6); } }
     * String updateQty= "UPDATE room SET isBooked=" + 1 + " WHERE roomNo='"+singleNo+"'"; ps = (PreparedStatement)
     * con.prepareStatement(updateQty); ps.executeUpdate(updateQty); tempRoomAdd(roomno,price,useremail,fromdate,todate); }
     * System.out.println("Temp Room Add Call Successfully:"); } } catch(Exception e){ e.printStackTrace(); } } public void
     * doubleRoomAdd(String[]doubleroom, String useremail, String fromdate, String todate){ Connection con; Statement stmt;
     * ResultSet rs; PreparedStatement ps ; try{ Class.forName("com.mysql.jdbc.Driver"); con = (Connection)
     * DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelDB","root", "root35"); stmt = (Statement)
     * con.createStatement(); rs = (ResultSet) stmt.executeQuery("Select * from room"); if(doubleroom.length!=0){ for(int i = 0;
     * i< doubleroom.length; i++){ int doubleNo = Integer.parseInt(doubleroom[i]); String roomno = String.valueOf(doubleNo);
     * String price = ""; while(rs.next()){ String roomNo = rs.getString(3); if(roomno.equals(roomNo)){ price = rs.getString(6);
     * } } String updateDouble= "UPDATE room SET isBooked=" + 1 + " WHERE roomNo='"+doubleNo+"'"; ps = (PreparedStatement)
     * con.prepareStatement(updateDouble); ps.executeUpdate(updateDouble); tempRoomAdd(roomno,price,useremail,fromdate,todate);
     * System.out.println("Temp Room Add Call Successfully:"); } } }catch(Exception e){ } }
     */

}
