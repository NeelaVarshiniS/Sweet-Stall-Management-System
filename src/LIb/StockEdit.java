package LIb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.*;


/**
 * Servlet implementation class StockEdit
 */
public class StockEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StockEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		int i=1;
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con = DriverManager.getConnection("Jdbc:Odbc:BookData");
			int s1 = Integer.parseInt(request.getParameter("item_id"));
			String s2 = request.getParameter("item_name");
			int s3 = Integer.parseInt(request.getParameter("quantity"));
			Statement st = con. createStatement();
			ResultSet rst= st.executeQuery("select * from Stock");
			while(rst.next())
			{
					String a=rst.getString(1);
					if(a.equals(s1))
						
					{
						PreparedStatement rst1= con.prepareStatement("delete from Stock where item_id='"+s1+"'");
						rst1.executeUpdate();
						i=0;
					}
			}
			PreparedStatement st1=con.prepareStatement("insert into Stock(item_id,item_name,quantity) values(?,?,?)");
			st1.setInt(1, s1);
			st1.setString(2, s2);
			st1.setInt(3, s3);
			st1.executeUpdate();
			response.sendRedirect("AdminDashboard.html");
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
