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
 * Servlet implementation class ServletBorrarCarrito
 */
@WebServlet("/borrarProductoDelCarrito")
public class ServletBorrarCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletBorrarCarrito() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Map<String, Integer> carrito = (ConcurrentHashMap<String, Integer>) session.getAttribute("carrito");

		String producto = request.getParameter("producto");
		if (producto != null) {
			borrarProductoDelCarrito(carrito, producto);
		}

		// Retornar la vista con parámetro "carrito"
		request.setAttribute("paresCarrito", carrito);
		getServletContext().getRequestDispatcher("/vista-carrito.jsp").forward(request, response);
	}
	
	private void borrarProductoDelCarrito(Map<String, Integer> carrito, String producto) {
		if (carrito.get(producto) == 1) {
			carrito.remove(producto);
		} else if(carrito.get(producto) > 1){
			int numeroArticulos = (Integer) carrito.get(producto).intValue();
			carrito.put(producto, Integer.valueOf(numeroArticulos - 1));
		}
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
