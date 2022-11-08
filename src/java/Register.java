import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

public class Register extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            String a1 = request.getParameter("pname");
            String a2 = request.getParameter("eno");
            String a3 = request.getParameter("no");
            String a4 = request.getParameter("utr");
            String a5 = request.getParameter("date");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/ems?useSSL=false&allowPublicKeyRetrieval=true","root","budhrani");

            Statement stmt=connect.createStatement();
            String q = "select S_No from participants order by S_No desc limit 1;";
            
            ResultSet rs = stmt.executeQuery(q);
            int i = 0;
            if(rs.next()){
                i = rs.getInt(1);
            }
            i++;
            q = "select * from participants where pname = '"+a1+"' and mobileNo = '"+a3+"'";
            rs = stmt.executeQuery(q);
            if(rs.next()){
                i = rs.getInt(1);
                q = "delete from participants where S_No = "+i+";";
                stmt.executeUpdate(q);
                q = "delete from transactions where S_No = "+i+";";
                stmt.executeUpdate(q);
            }
            String query = "insert into participants values('"+i+"','"+a1+"','"+a2+"','"+a3+"');";
            stmt.executeUpdate(query);
            query= "insert into Transactions values('"+i+"','"+a1+"','"+a4+"','"+a5+"');";
            stmt.executeUpdate(query);
            RequestDispatcher rd = request.getRequestDispatcher("Registrations.html");
            out.println("<center><h3> Participant Registered </h3></center>");
            out.println("<center><br> <strong>Note:- Participant registration can be cancelled if the payment is not successfull or entered incorrect UTR No. if any of this case exist your registration will be remove. <br>You can refill the form with the correct UTR No., if you have done the payment succesfully</center>");
            rd.include(request, response);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
