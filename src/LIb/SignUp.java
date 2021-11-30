package LIb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class Screate
 */
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SignUp() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		try
		{
		
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); 
			Connection con = DriverManager.getConnection("Jdbc:Odbc:BookData");
		int i=1;
		PreparedStatement st= con.prepareStatement("insert into users(name,email,address,phnum,password) values(?,?,?,?,?)");
		PreparedStatement st1= con.prepareStatement("insert into users_login(email,password) values(?,?)");
		String sfname=request.getParameter("name");
		String semail = request.getParameter("email");
		String sphone = request.getParameter("phnum");
		String saddress = request.getParameter("address");
		String spassword = request.getParameter("password");
		Statement stt = con. createStatement();
		ResultSet rst= stt.executeQuery("select email from users_login");
		while(rst.next())
		{
				String a=rst.getString(1);
				if(a.equals(semail))
				{
					//out.print("User Id already Exists");
					i=0;
					response.sendRedirect("ReRegister.html");
				}
		}
     	if(i==1)
		{
			st.setString(1,sfname);
			st.setString(2,semail);
			st.setString(3,sphone);
			st.setString(4,saddress);
			st.setString(5,spassword);
			st.executeUpdate();
			st1.setString(1,semail);
			st1.setString(2,spassword);
			st1.executeUpdate();
			response.sendRedirect("UserPage.html");
		}
		}
		catch(Exception e)
		{
			out.println(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
