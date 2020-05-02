package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Pais;
import service.PaisService;

@WebServlet("/ManterPais.do")
public class ManterPaisController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pNome = request.getParameter("nome");
		String pPopulacao = request.getParameter("populacao");
		String pArea = request.getParameter("area");
		String acao = request.getParameter("acao");
	
		Pais pais = new Pais();
		pais.setNome(pNome);
		pais.setPopulacao(pPopulacao);
		pais.setArea(pArea);

		PaisService cs = new PaisService();
		RequestDispatcher dispatcher = null;

		switch (acao) {
		case "Incluir":
			cs.criar(pais);
			pais = cs.carregar(pais.getId());

			request.setAttribute("pais", pais);
			dispatcher = request.getRequestDispatcher("Pais.jsp");
			break;
		case "Listar":
			ArrayList<Pais> paises = cs.listarTodos();
			request.setAttribute("paises", paises);
			dispatcher = request.getRequestDispatcher("ListaDePaises.jsp");
		}


		dispatcher.forward(request, response);
	}
}