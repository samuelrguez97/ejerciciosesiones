package ejerciciosesiones;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/insertar/noticias")

public class insertarServlet_2 extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw;
		Connection conexion = null;
		try {
				pw = resp.getWriter();
				
				String titulo = req.getParameter("titulo");
				String texto = req.getParameter("texto");
				String categoria = req.getParameter("categoria");
				Calendar calendario = new GregorianCalendar();
				int ano = calendario.get(Calendar.YEAR);
				int mes = calendario.get(Calendar.MONTH);
				int dia = calendario.get(Calendar.DAY_OF_MONTH);
				String fecha_actual = ""+ ano +"-"+ mes +"-"+ dia +"";
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date parsed = format.parse(fecha_actual);
		        java.sql.Date fecha_date = new java.sql.Date(parsed.getTime());
		        String imagen = "(No hay)";
		        
				conexion = DriverManager.getConnection ("jdbc:mysql://localhost/inmobiliaria", "root", "practicas");
				PreparedStatement ps = conexion.prepareStatement("INSERT INTO noticias(titulo, texto, categoria, fecha, imagen) VALUES(?, ?, ?, ?, ?)");
				
				ps.setString(1, titulo);
				ps.setString(2, texto);
				ps.setString(3, categoria);
				ps.setDate(4, fecha_date);
				ps.setString(5, null);
				
				ps.executeUpdate();
				
				pw.println("<html>");
				pw.println("<head>");
				pw.println("<meta charset=\'UTF-8\'>");
				pw.println("<title>Consulta de noticias</title>");
				pw.println("</head>");
				pw.println("<body>");
				pw.println("<h1>Gestión de noticias</h1>");
				pw.println("<h3>Insertar nueva noticia</h3>");
				pw.println("<label>La noticia ha sido recibida correctamente: </label>");
				pw.println("<br />");
				pw.println("<ul>");
				pw.println("<li>Titulo: "+ titulo +"</li>");
				pw.println("<li>Texto: "+ texto +"</li>");
				pw.println("<li>Categoría: "+ categoria +"</li>");
				pw.println("<li>Fecha: "+ fecha_date +"</li>");
				pw.println("<li>Imagen: "+ imagen +"</li>");
				pw.println("</ul>");
				pw.println("<label>[ <a href='../insertar'>Insertar otra noticia</a> | <a href='../loginExito'>Menú principal</a> ]</label>");
				pw.println("</body>");
				pw.println("</html>");
		} catch (SQLException | ParseException e) {
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

