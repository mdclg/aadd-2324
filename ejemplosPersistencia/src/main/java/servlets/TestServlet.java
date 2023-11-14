package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.AsyncContext;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet(name="TestServlet", urlPatterns = {"/TestServlet"},
asyncSupported = true)
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private int contador = 0;

	/**
	 * Default constructor.
	 */
	public TestServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("llama a init");
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("llama a service");
		super.service(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AsyncContext contextoAsincrono = request.startAsync();
		contextoAsincrono.start(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0; i<10;i++) {
					System.out.println("hola "+Thread.currentThread().getName());
					try {
						Thread.sleep(5000);
					}
					catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
				contextoAsincrono.complete();
				
			}
			
		});
		
		System.out.println("llama a doGet");
		
		HttpSession sesion = request.getSession();
		List<String> listaSesion = (List<String>) sesion.getAttribute("listasesion");
		
		if(listaSesion != null) {
			for(String s:listaSesion) {
				System.out.println("doGet en la sesion hay: "+s);
			}
		}
		ServletContext contexto = getServletConfig().getServletContext();
		
		List<String> listaContexto = (List<String>) contexto.getAttribute("listacontexto");
		if(listaContexto != null) {
			for(String s:listaContexto) {
				System.out.println("doGet en el contexto hay: "+s);
			}
		}
		synchronized (this) {
			++contador;

			

			PrintWriter out = response.getWriter();
			out.print("<html>");
			out.print("<body>");
			out.println("Entra al doGet y ya van " + contador + " veces");
			out.println("</body>");
			out.println("</html>");
			out.close();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("llama a doPot");
		
		String nombre=request.getParameter("identificador");
		
		HttpSession sesion = request.getSession();
		List<String> listaSesion = (List<String>)sesion.getAttribute("listasesion");
		
		if(listaSesion == null || !listaSesion.contains(nombre)) {
			if(listaSesion == null) {
				listaSesion = new ArrayList<String>();						
			}
			listaSesion.add(nombre);
			sesion.setAttribute("listasesion", listaSesion);
			
		}
		
		ServletContext contexto = getServletConfig().getServletContext();
		List<String> listacontexto = (List<String>)contexto.getAttribute("listacontexto");
		
		if(listacontexto == null || !listacontexto.contains(nombre)) {
			if(listacontexto == null) {
				listacontexto = new ArrayList<String>();						
			}
			listacontexto.add(nombre);
			contexto.setAttribute("listacontexto", listacontexto);
			
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.print("<html>");
		out.print("<body>");
		out.println("Entra al doPost. Hola "+nombre);
		out.println("</body>");
		out.println("</html>");
		out.close();
		
		
	}

}
