package LIb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Search_Book
 */
public class OverAllBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OverAllBooks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		//long i=1;
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("Jdbc:Odbc:BookData");
			//Statement sst=con.createStatement();
			PreparedStatement st=con.prepareStatement("insert into Order(name,item,quantity,place) values(?,?,?,?)");
			String s1=request.getParameter("name");
			String s2=request.getParameter("item");
			String s3=request.getParameter("qty");
			String s4=request.getParameter("place");
			st.setString(1,s1);
			st.setString(2,s2);
			st.setString(3,s3);
			st.setString(4,s4);
//			out.println("<html><head> <style> body{ background-image:url(all11.jpg);  background-size:cover; background-repeat:no-repeat; } div{ color:white; } </style> </head> ");
//			out.print("<body>");
//			out.print("<div>");
			st.executeUpdate();
			out.print("<p>Thank you for Your Order");
			response.sendRedirect("UserPage.html");
//			ResultSet rset =sst.executeQuery("select * from Books") ;
//			 while(rset.next())
//			{
//				 	out.print("\nBook No:"+i+++"<br>");
//				 	out.print("\nName :"+ rset.getString(1)+"<br>"); 
//					out.print("\nId :" +rset.getString(2)+"<br>");
//					out.print("\nAuthor :"+ rset.getString(3)+"<br>");
//					out.print("\nEdition :"+ rset.getString(4)+"<br>");
//					out.print("\nVolume :"+ rset.getString(5)+"<br>");
//					out.print("\nDetail :" +rset.getString(6)+"<br>");
//					out.print("\nPublication :" +rset.getString(7)+"<br>");
//					out.print("\nPrice :" +rset.getString(8)+"<br>");
//					out.print("\nRackno :" +rset.getString(9)+"<br>");
//					out.print("\n");
//			 } 
//			out.print("</body>");
//			out.print("</div>");
		}
		catch(Exception e)
		{
			out.print(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
