import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class StorePw extends HttpServlet {
    public Connection connect = null;
    public Statement stmt;
    public ResultSet resultSet;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        response.setContentType("text/html");
        
        PrintWriter out=response.getWriter();
        String up=request.getParameter("Pusername");
        String pp=request.getParameter("Ppassword");
        String cp=request.getParameter("Cpassword");
        String name=request.getParameter("Pname");
        if(up!=""&&pp!=""){
            if(pp.equals(cp)){
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/ems?useSSL=false&allowPublicKeyRetrieval=true","root","budhrani");
                    stmt=connect.createStatement();
                    String q = "insert into plogindetails values(" +'"'+ up+'"' + "," +'"'+ pp+'"' + ","+'"'+ name +'"' +");";
                    String r = "select up from plogindetails where up = "+'"'+up+'"';
                    resultSet=stmt.executeQuery(r);
                    if (resultSet.next()){
                        out.println("<center><h1>!! Username Already Taken !!</h1></center>");
                        RequestDispatcher rd = request.getRequestDispatcher("Psignup.html");
                        rd.include(request, response);
                    }
                    else{
                        stmt.executeUpdate(q);
                        RequestDispatcher rd = request.getRequestDispatcher("Plogin.html");
                        rd.forward(request, response);
                        connect.close();
                    }     
                }   
                catch (SQLException ex) {
                    Logger.getLogger(StorePw.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(StorePw.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                out.println("<center><h1>!! Please Enter Password And Confirm Password Same !!</h1></center>");
                RequestDispatcher rd = request.getRequestDispatcher("Psignup.html");
                rd.include(request, response);
            }
        }  
        else{
            out.println("<center><h1>!! Either Username or Password is empty !!</h1></center>");
            RequestDispatcher rd = request.getRequestDispatcher("Psignup.html");
            rd.include(request, response);
        }
    }
}
