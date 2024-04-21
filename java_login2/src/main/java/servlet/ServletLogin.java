package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOLoginRepository;
import model.ModelLogin;

//o chamado Controller sao as servlets 
@WebServlet(urlPatterns = {"/principal/ServletLogin", "/ServletLogin"})//mapeamento de url que vem da tela
public class ServletLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private DAOLoginRepository daoLoginRepository = new DAOLoginRepository();

    public ServletLogin() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//logout
		String acao = request.getParameter("acao");
		if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout")) {
			request.getSession().invalidate();//invalida a sessao
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			redirecionar.forward(request, response);
		}else {
			doPost(request, response);
		}
		
	}
	//recebe os dados enviados pelo formulario
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//variaveis recebem do index os valores 
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		String url = request.getParameter("url");
		
		try {
			//se as variaveis ñ forem nulas nem vazias entao
			if(login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
				//instacio o objeto da Model
				ModelLogin modelLogin = new ModelLogin();
				//altera os valores das variaveis
				modelLogin.setLogin(login);
				modelLogin.setSenha(senha);
				
				if (daoLoginRepository.validarAutenticacao(modelLogin)) {
					//Retorna a sessao atual associada com essa requisicao, ou se a requicao nao exister é cria uma             
					request.getSession().setAttribute("usuario", modelLogin.getLogin());
					
					if(url == null || url.equals("null")) {
						url = "principal/principal.jsp";
					}
					RequestDispatcher redirecionar = request.getRequestDispatcher(url);
					//forward envia uma requisicao para outro recurso como: servlet,jsp,html
					redirecionar.forward(request, response);
				}else {
					RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
					request.setAttribute("msg", "Informe o login e senha corretamente!");
					redirecionar.forward(request, response);
				}
			}else {
				RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
				request.setAttribute("msg", "Informe o login e senha corretamente!");
				redirecionar.forward(request, response);
			}
		} catch (Exception e) {
			e.getStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}

}


















