package LIb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * Servlet implementation class Stock
 */
public class Stock extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Stock() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		//long i=1;
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("Jdbc:Odbc:BookData");
			String s1 = request.getParameter("item_id");
			int s2=Integer.parseInt(s1);
			int a=0;
			Statement sst=con.createStatement();
			String s="select * from Stock where item_id='"+s1+"'";
			ResultSet rset =sst.executeQuery(s) ;
			out.print("<html>");
			out.print("<body>");
			out.print("<form name='cform' method='get' action='StockEdit'>");
			 while(rset.next())
			{
				    a=rset.getInt(1);
				    if(a==s2)
				    {
				    out.print("<input type='number' name='item_id' value='"+rset.getInt(1)+"'/>");
				 	out.print("<input type='text' name='item_name' value='"+rset.getString(2)+"'/>");
				 	out.print("<input type='number' name='quantity' value='"+rset.getInt(3)+"'/>");
				 	out.print("<input type='submit' name='submit' value='edit'/>");
				    }
			 }  
			 out.print("</form>");
			 out.print("</body>");
			 out.print("</html>");
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
