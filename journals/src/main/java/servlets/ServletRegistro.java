package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import journals.modelo.Cliente;

/**
 * Servlet implementation class ServletRegistro
 */
public class ServletRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegistro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cliente c = new Cliente();
		c.setNombre(request.getParameter("nombre"));
		c.setApellidos(request.getParameter("apellidos"));
		c.setNif(request.getParameter("nif"));
		c.setUsermail(request.getParameter("usermail"));
		c.setUsername(request.getParameter("username"));
		c.setPassword(request.getParameter("clave"));
		
		// Recupera el contexto de la aplicación
		ServletContext app = getServletConfig().getServletContext();
		// Intenta localizar la tabla de usuarios
		HashMap<String, Cliente> usuariosHash = (HashMap<String,Cliente>) app.getAttribute("usuarios");
		// Si no existe, la crea
		if ( usuariosHash == null ) {
		    usuariosHash = new HashMap();
		    app.setAttribute("usuarios", usuariosHash);
		}
		// Intenta guardar un usuario. Si existe el identificador, devuelve un error
		boolean error = false;
		if ( usuariosHash.get(c.getUsername()) != null ) {
			error = true;
		    String referer = request.getHeader("referer");
		    response.setHeader("refresh", "3; URL=" + referer);
			//response.sendError(500, "Identificador de usuario duplicado");
		    //return;
		} else {
		    usuariosHash.put(c.getUsername(), c);
		}
		app.setAttribute("usuarios", usuariosHash);
		
		// Establecemos el tipo MIME de la respuesta
        response.setContentType("text/html");
        // Escribimos la respuesta
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        // Establecemos el título de la página HTML
        out.println("<title>" + "Procesamiento Datos Cliente" + "</title>");
        out.println("</head>");
        // Cuerpo de la página
        out.println("<body>");
        if (!error) { 
            out.println("<body><B><P> Datos Cliente Procesados </P> </B>");
        } else {
            out.println("<body><H1> Error: usuario duplicado </H1></body></html>");
        }
        out.println("</body>");
        out.println("</html>");
	}

}