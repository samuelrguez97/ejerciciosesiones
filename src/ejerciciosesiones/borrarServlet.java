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

@WebServlet(urlPatterns = "/borrar")

public class borrarServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw;
		Connection conexion = null;
		ResultSet rs = null;
		try {
				pw = resp.getWriter();
				conexion = DriverManager.getConnection ("jdbc:mysql://localhost/inmobiliaria", "root", "practicas");
				Statement s = conexion.createStatement();
				rs = s.executeQuery ("SELECT * FROM noticias");
				pw.println("<html>");
				pw.println("<head>");
				pw.println("<meta charset=\'UTF-8\'>");
				pw.println("<title>Borrado de noticias</title>");
				pw.println("</head>");
				pw.println("<body>");
				pw.println("<h1>Gestión de noticias</h1>");
				pw.println("<h3>Borrado de noticias</h3>");
				pw.println("<form action='borrar/noticias' method='POST'>");
				pw.println("<table>");
				pw.println("<thead>");
				pw.println("<tr>");
				pw.println("<th>Título</th>");
				pw.println("<th>Texto</th>");
				pw.println("<th>Categoría</th>");
				pw.println("<th>Fecha</th>");
				pw.println("<th>Imagen</th>");
				pw.println("<th>Borrar</th>");
				pw.println("</tr>");
				pw.println("</thead>");
				pw.println("<tbody>");
				while (rs.next()) {
					pw.println("<tr>");
					pw.println("<td>"+ rs.getString(2) +"</td>");
					pw.println("<td>"+ rs.getString(3) +"</td>");
					pw.println("<td>"+ rs.getString(4) +"</td>");
					pw.println("<td>"+ rs.getDate(5) +"</td>");
					pw.println("<td>"+ rs.getString(6) +"</td>");
					pw.println("<td><input type='checkbox' name='borrar' value='"+ rs.getInt(1) +"'/></td>");
					pw.println("</tr>");
				}
				pw.println("</tbody>");
				pw.println("</table>");
				pw.println("<input type='submit' name='submit' value='Eliminar noticias marcadas'>");
				pw.println("</form>");
				pw.println("<label>[ <a href='loginExito'>Menú principal</a> ]</label>");
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
				if(conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				throw new ServletException(e);
			}
			
		}
	}
}


