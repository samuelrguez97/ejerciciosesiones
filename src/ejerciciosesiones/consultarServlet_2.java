package ejerciciosesiones;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/consultar/noticias")

public class consultarServlet_2 extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw;
		Connection conexion = null;
		ResultSet rs = null;
		ResultSet rs_2 = null;
		String categoria = null;
		try {
				categoria = req.getParameter("categoria");
				pw = resp.getWriter();
				conexion = DriverManager.getConnection ("jdbc:mysql://localhost/inmobiliaria", "root", "practicas");
				Statement s = conexion.createStatement();
				Statement s_2 = conexion.createStatement();
				rs = s.executeQuery ("SELECT DISTINCT categoria FROM noticias");
				if (categoria.equals("todas")) {
					rs_2 = s_2.executeQuery("SELECT * FROM noticias");
				} else {
					rs_2 = s_2.executeQuery("SELECT * FROM noticias WHERE categoria = '"+ categoria +"'");
				}
				pw.println("<html>");
				pw.println("<head>");
				pw.println("<meta charset=\'UTF-8\'>");
				pw.println("<title>Consulta de noticias</title>");
				pw.println("</head>");
				pw.println("<body>");
				pw.println("<h1>Gestión de noticias</h1>");
				pw.println("<h3>Consulta de noticias</h3>");
				pw.println("<label>Mostrar noticias de la categoria: </label>");
				pw.println("<select name='categoria'>");
				while (rs.next()) {
					if (rs.getString(1).equals(categoria)) {
						pw.println("<option selected value='"+ rs.getString(1) +"' />"+ rs.getString(1) +"");
					} else if (categoria.equals("todas")) {
						pw.println("<option selected value='todas' /> todas");
					} else {
							pw.println("<option value='"+ rs.getString(1) +"' />"+ rs.getString(1) +"");
					}
				}
				pw.println("</select>");
				pw.println("<table>");
				pw.println("<thead>");
				pw.println("<tr>");
				pw.println("<th>Título</th>");
				pw.println("<th>Texto</th>");
				pw.println("<th>Categoriía</th>");
				pw.println("<th>Fecha</th>");
				pw.println("<th>Imagen</th>");
				pw.println("</tr>");
				pw.println("</thead>");
				pw.println("<tbody>");
				while (rs_2.next()) {
					pw.println("<tr>");
					pw.println("<td>"+ rs_2.getString(2) +"</td>");
					pw.println("<td>"+ rs_2.getString(3) +"</td>");
					pw.println("<td>"+ rs_2.getString(4) +"</td>");
					pw.println("<td>"+ rs_2.getDate(5) +"</td>");
					pw.println("<td>"+ rs_2.getString(6) +"</td>");
					pw.println("</tr>");
				}
				pw.println("</tbody>");
				pw.println("</table>");
				pw.println("</body>");
				pw.println("</html>");
		} catch (SQLException e) {
			resp.resetBuffer();
			throw new ServletException(e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(rs_2 != null) {
					rs_2.close();
				}
				if(conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				throw new ServletException(e);
			}
			
		}
	}
}


