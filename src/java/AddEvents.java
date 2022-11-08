import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddEvents extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        String a1=request.getParameter("EventNo");
        String a2=request.getParameter("EventName");
        String a3=request.getParameter("CoName");
        String a4=request.getParameter("CoNo");
        String a5=request.getParameter("fee");
        String a6=request.getParameter("venue");
        String a7=request.getParameter("date");
        a1=a1.replace("'", "");
        a2=a2.replace("'", "");
        a3=a3.replace("'", "");
        a4=a4.replace("'", "");
        a5=a5.replace("'", "");
        a6=a6.replace("'", "");
        a7=a7.replace("'", "");
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/ems?useSSL=false&allowPublicKeyRetrieval=true","root","budhrani");
            Statement stmt=connect.createStatement();
            ResultSet rs = null;
            connect.setAutoCommit(false);
            String query="insert into events values('"+a1+"','"+a2+"','"+a3+"','"+a4+"','"+a5+"','"+a6+"','"+a7+"');";
            stmt.executeUpdate(query);
            System.out.println("Event Added to Database");
            RequestDispatcher rd = request.getRequestDispatcher("Create.html");
            rd.include(request, response);
            out.println("<br><center><h3> Event Added Thank You For Your Cooperation </h3></center>");
            connect.commit();
            connect.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VaPa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VaPa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
        
