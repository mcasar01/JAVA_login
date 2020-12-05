import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import java.util.Arrays;


@SuppressWarnings({ "serial", "unused" })
@WebServlet("/login")
public class Login extends HttpServlet {
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String email = request.getParameter("email");
        //String pass = request.getParameter("pass");
        char [] pass = request.getParameter("pass").toCharArray();
        
        
        if(Validate.checkUser(email, pass))
        {
            RequestDispatcher rs = request.getRequestDispatcher("Welcome");
            rs.forward(request, response);
            //System.out.println("Password:" + passw);
        }
        else
        {
           out.println("Username or Password incorrect");
           RequestDispatcher rs = request.getRequestDispatcher("index.html");
           rs.include(request, response);
        }
        
      
        borrarChar(pass);
        
    }

	//Método para el borrado del array
	private void borrarChar(char[] pass) {
		for(int i = 0; i < pass.length; i++) {
			pass[i] =0;
		}
		
	}
	
}