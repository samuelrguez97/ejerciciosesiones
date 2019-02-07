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

@WebServlet(urlPatterns = "/insertar")

public class insertarServlet extends HttpServlet {
	
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
				rs = s.executeQuery ("SELECT DISTINCT categoria FROM noticias");
				pw.println("<html>");
				pw.println("<head>");
				pw.println("<meta charset=\'UTF-8\'>");
				pw.println("<title>Insertar noticias</title>");
				pw.println("</head>");
				pw.println("<body>");
				pw.println("<h1>Gestión de noticias</h1>");
				pw.println("<h3>Insertar nueva noticia</h3>");
				pw.println("<form action='insertar/noticias' method='POST' style='width: 500px'>");
				pw.println("<fieldset>");
				pw.println("<label>Titulo:* </label>");
				pw.println("<input required type='text' name='titulo' />");
				pw.println("<br />");
				pw.println("<label>Texto:* </label>");
				pw.println("<textarea required name='texto'></textarea>");
				pw.println("<br />");
				pw.println("<label>Categoría:* </label>");
				pw.println("<select required name='categoria'>");
				while (rs.next()) {
					pw.println("<option value='"+ rs.getString(1) +"' />"+ rs.getString(1) +"");
				}
				pw.println("</select>");
				pw.println("<br />");
				pw.println("<input type='submit' name='submit' value='Insertar noticia' />");
				pw.println("</fieldset>");
				pw.println("</form>");
				pw.println("<label>NOTA: los datos marcados con * son obligatorios.</label>");
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


