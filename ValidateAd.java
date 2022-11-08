import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValidateAd extends HttpServlet {
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
        PrintWriter out = response.getWriter();
        String un = request.getParameter("Ausername");
        String pw = request.getParameter("Apassword");
        response.setContentType("text/html");
        
        String u1 = "A101";
        String p1 = "1234";
        
        
        String u2 = "A102";
        String p2 = "5678";
        
        
        String u3 = "A103";
        String p3 = "ABCD";
        
        
        String u4 = "A104";
        String p4 = "abcd";
        
        if(un.equals(u1)&&pw.equals(p1)){
            RequestDispatcher rd = request.getRequestDispatcher("AdminEvent.html"); 
            rd.forward(request, response);
        }
        else if(un.equals(u2)&&pw.equals(p2)){
            RequestDispatcher rd = request.getRequestDispatcher("AdminEvent.html");    
            rd.forward(request, response);
        }
        else if(un.equals(u3)&&pw.equals(p3)){
            RequestDispatcher rd = request.getRequestDispatcher("AdminEvent.html");    
            rd.forward(request, response);
        }
        else if(un.equals(u4)&&pw.equals(p4)){
            RequestDispatcher rd = request.getRequestDispatcher("AdminEvent.html");    
            rd.forward(request, response);
        }   
        else{
            out.println("<center><h1>!! Please Enter Valid Username & Password for Admin !!</h1></center>");
            RequestDispatcher rd = request.getRequestDispatcher("Alogin.html");
            rd.include(request, response);
        }
    }   
}
