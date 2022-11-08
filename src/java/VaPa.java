import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VaPa extends HttpServlet {
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String u=request.getParameter("Pausername");
        String p=request.getParameter("Papassword");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/ems?useSSL=false&allowPublicKeyRetrieval=true","root","budhrani");
            Statement stmt=connect.createStatement();
            ResultSet rs = null;
            String query = "select*from plogindetails where up="+'"'+u+'"'+" and pp="+'"'+p+'"';
            rs=stmt.executeQuery(query);
            if(rs.next()){
                RequestDispatcher rd = rd=request.getRequestDispatcher("ParticipantEvent.html");
                rd.forward(request, response);
            }
            else{
                out.print("<center><h1> Username And Password Doesnot Match </h1><center>");
                RequestDispatcher rd=request.getRequestDispatcher("Plogin.html");
                rd.include(request, response);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VaPa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VaPa.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.close();
    }
}
