package com.uniovi.sdi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletCarrito
 */
@WebServlet("/incluirEnCarrito")
public class ServletCarrito extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletCarrito() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		Map<String, Integer> carrito = (ConcurrentHashMap<String, Integer>) session.getAttribute("carrito");

		// No hay carrito (nuevo usuario), creamos uno y lo insertamos en sesión
		if (carrito == null) {
			carrito = new ConcurrentHashMap<String, Integer>(); // nombre, número de unidades compradas del producto
			session.setAttribute("carrito", carrito);
		}

		String producto = request.getParameter("producto");
		if (producto != null) {
			insertarEnCarrito(carrito, producto); // y mostramos el contenido del mismo
		}
		
		// Retornar la vista con parámetro "carrito"
		request.setAttribute("paresCarrito", carrito);
		getServletContext().getRequestDispatcher("/vista-carrito.jsp").forward(request,
		response);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD><TITLE>Tienda SDI: carrito</TITLE></HEAD>");
		out.println("<BODY>");
		out.println(carritoEnHTML(carrito) + "<br>");
		//out.println("<a href=\"tienda.html\">Volver</a></BODY></HTML>");
		out.println("<a href=\"index.jsp\">Volver</a></BODY></HTML>");
	}

	private void insertarEnCarrito(Map<String, Integer> carrito, String producto) {
		if (carrito.get(producto) == null) {
			carrito.put(producto, Integer.valueOf(1));
		} else {
			int numeroArticulos = (Integer) carrito.get(producto).intValue();
			carrito.put(producto, Integer.valueOf(numeroArticulos + 1));
		}
	}

	private String carritoEnHTML(Map<String, Integer> carrito) {
		String carritoEnHTML = "";

		for (String key : carrito.keySet()) {
			carritoEnHTML += "<p>[" + key + "], " + carrito.get(key) + " unidades</p>";
		}

		return carritoEnHTML;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
