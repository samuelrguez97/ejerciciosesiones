package ejerciciosesiones;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/borrar/noticias")

public class borrarServlet_2 extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw;
		Connection conexion = null;
		String[] datos = null;
		try {
				datos = req.getParameterValues("borrar");
				pw = resp.getWriter();
				conexion = DriverManager.getConnection ("jdbc:mysql://localhost/inmobiliaria", "root", "practicas");
				pw.println("<html>");
				pw.println("<head>");
				pw.println("<meta charset=\'UTF-8\'>");
				pw.println("<title>Eliminación de noticias</title>");
				pw.println("</head>");
				pw.println("<body>");
				pw.println("<h1>Gestión de noticias</h1>");
				pw.println("<h3>Eliminación de noticias</h3>");
				for (String id: datos) {
					Statement s = conexion.createStatement();
					ResultSet rs = s.executeQuery("SELECT * FROM noticias WHERE id = '"+ id +"'");
					if (rs.next()) {
						pw.println("<label>Noticia eliminada</label>");
						pw.println("<br />");
						pw.println("<ul>");
						pw.println("<li>Titulo: "+ rs.getString(2) +"</li>");
						pw.println("<li>Texto: "+ rs.getString(3) +"</li>");
						pw.println("<li>Categoría: "+ rs.getString(4) +"</li>");
						pw.println("<li>Fecha: "+ rs.getDate(5) +"</li>");
						pw.println("<li>Imagen: "+ rs.getString(6) +"</li>");
						pw.println("</ul>");
						PreparedStatement ps = conexion.prepareStatement("DELETE FROM noticias WHERE id = '"+ id +"'");
						ps.executeUpdate();
						if(rs != null) {
							rs.close();
						}
					}
				}
				pw.println("</body>");
				pw.println("<label>[ <a href='../borrar'>Eliminar más noticias</a> | <a href='loginExito'>Menú principal</a> ]</label>");
				pw.println("</html>");
		} catch (SQLException e) {
			resp.resetBuffer();
			throw new ServletException(e);
		} finally {
			try {
				if(conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				throw new ServletException(e);
			}
			
		}
	}
}



