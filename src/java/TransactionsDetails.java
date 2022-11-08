/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author budhr
 */
public class TransactionsDetails extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Event Page</title>");   
            out.println("<link rel=\"stylesheet\" href=\"total2.css\">");
            out.println("<link href=\"https://fonts.googleapis.com/css2?family=Balsamiq+Sans&display=swap\" rel=\"stylesgeet\">");
            out.println("</head>");
            out.println("<body>");
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/ems?useSSL=false&allowPublicKeyRetrieval=true","root","budhrani");
                Statement stmt=connect.createStatement();
                String query = "select*from transactions order by S_No";
                ResultSet rs = stmt.executeQuery(query);
                out.println("<center><h1>Transactions Details</h1></center>");
                out.println("<br>");
                out.println("<div>");
                out.println("<center>");
                out.println("<table border=1 width=50% height=50%>");
                out.println("<tr><th>S No.</th><th>Participant Name</th><th>UTR Number</th><th>Date</th></tr>");
                while(rs.next()){
                    String n = rs.getString(1);
                    String no= rs.getString(2);
                    String co = rs.getString(3);
                    String cono = rs.getString(4);
                    out.println("<tr><td>"+n+"</td><td>"+no+"</td><td>"+co+"</td><td>"+cono+"</td>"+"</tr>");
                }
                connect.close();

                out.println("</table>");
                out.println("</center>");
                out.println("</body>");
                out.println("</html>");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ViewEvent1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ViewEvent1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
    