package ejerciciosesiones;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/loginExito")

public class loginExito extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset=\'UTF-8\'>");
		pw.println("<title>Menu</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<h1>Gestion de noticias</h1>");
		pw.println("<hr/>");
		pw.println("<ul>");
		pw.println("<li><a href='consultar'>Consultar noticias</a></li>");
		pw.println("<li><a href='insertar'>Insertar nueva noticia</a></li>");
		pw.println("<li><a href='borrar'>Eliminar noticias</a></li>");
		pw.println("</ul>");
		pw.println("<p><a href='logOut'>Desloguearse</a></p>");
		pw.println("</body>");
		pw.println("</html>");
	}

	
}
