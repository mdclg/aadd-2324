package servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import journals.modelo.Cliente;

/**
 * Servlet implementation class ServletLogin
 */
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		
		Cookie[] cookies = request.getCookies();
		Cookie cookieAADD = null;
		if (cookies != null) {
		    for (Cookie c : cookies) {
		        if (c.getName().equals("aadd")) {
		            cookieAADD = c;
		            break;
		        }
		    }
		    // Damos la salida en texto normal
		    if (cookieAADD != null) {
		    	String usuario = cookieAADD.getValue();
		        request.setAttribute("usuario", usuario);
		        ServletContext app = getServletConfig().getServletContext();
		        RequestDispatcher rd = app.getNamedDispatcher("ServletCookie");
		        // Reenviamos la petición capturando cualquier excepción que se produzca
		        try {
		            rd.include(request, response);
		            return;
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		}
		
		PrintWriter out = response.getWriter();
		// Obtenemos la ruta física el fichero del formulario
		String pathFichero = getServletConfig().getServletContext().getRealPath("login.html");
		BufferedReader br = new BufferedReader(new FileReader(pathFichero));
		String linea;
		while ((linea = br.readLine()) != null) {
			out.println(linea);
		}
		br.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean identificado = false;
		// Recuperamos el objeto sesión
		HttpSession sesion = request.getSession(true);
		// Obtenemos el número de identificaciones fallidas
		Integer numFallos = (Integer) sesion.getAttribute("fallos");
		if (numFallos == null) {
			numFallos = 0;
			sesion.setAttribute("fallos", numFallos);
		} else if (numFallos.intValue() == 3) {
			PrintWriter out = response.getWriter();
			out.println("Número fallos identificación excedido");
			return;
		}

		String usuario = request.getParameter("username");
		String clave = request.getParameter("clave");
		// Obtenemos el objeto ServletContext
		ServletContext app = getServletConfig().getServletContext();
		// Accedemos a la referencia de la tabla hash
		HashMap<String, Cliente> usuariosHash = (HashMap<String, Cliente>) app.getAttribute("usuarios");
		Cliente c = null;
		if (usuariosHash == null) {
			identificado = false;
		} else {
			// Obtenemos el objeto cliente por usuario
			c = (Cliente) usuariosHash.get(usuario);
			if (c == null) {
				identificado = false;
			} else {
				if (c.getPassword().equals(clave)) {
					identificado = true;
				} else {
					identificado = false;
				}
			}
		}

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html> <head> <title> Login </title> </head> <body>");
		if (identificado) {
			Cookie cookie = new Cookie("aadd", c.getUsername());
		    cookie.setMaxAge(60 * 60 * 24 * 7); // Cálculo segundos semana
		    response.addCookie(cookie);
			out.println("<B><P>Identificación correcta</B></P>");
		}
		else {
			out.println("<B><P>Error de identificación</B></P>");
		}
		out.println("</body>");
		out.println("</html>");
		if (identificado) {
			// Guardamos el objeto usuario en la sesión
			sesion.setAttribute("usuario", c);
			// Reseteamos número fallos
			sesion.setAttribute("fallos", 0);
		} else {
			// Incrementamos el número de fallos
			int fallos = ((Integer) sesion.getAttribute("fallos")).intValue();
			sesion.setAttribute("fallos", ++fallos);
		}
	}

}
