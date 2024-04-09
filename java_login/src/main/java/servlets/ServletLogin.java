package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOLoginRepository;
import model.ModelLogin;


@WebServlet(urlPatterns = {"/principal/ServletLogin","/ServletLogin"})//mapeamento de url que vem da tela
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //instacio a classe DAOLoginRepository   
    private DAOLoginRepository daoLoginRepository = new DAOLoginRepository();
	
    public ServletLogin() {
    	
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//para que a tela não fique em branco
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//as variaveis recebem os valores vindos dos inputs
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");
		try {
			//validacao
			//se o login nao for nulo e nem vazio e senha nao for nula e nem vazia entao
			if (login !=null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
				//instacio a Model
				ModelLogin modelLogin = new ModelLogin();
				//altero o valor da model pelo valor vindo dos inputs
				modelLogin.setLogin(login);
				modelLogin.setSenha(senha);
				//chamo a funcao daoLoginRepository.validarAutenticacao(modelLogin)
				//se foi validada, se no banco de dados foi achado esse login entao
				if(daoLoginRepository.validarAutenticacao(modelLogin)) {
					//entao retorna a sessao atual associada com a requisicao
					//request abri a sessao e a funcao setAttribute 
					//da nome de usuario ao obejto que chama a funcao getLogin: modelLogin.getLogin()
					request.getSession().setAttribute("usuario", modelLogin.getLogin());
					//faco um condicao, se o atributo url for null ou um string nula entao
					//o atributo url recebe o endereco do arquivo principal da pasta principal
					if(url == null || url.equals("null")) {
						//se deu certo
						url = "principal/principal.jsp";
					}
				//Servlet Request Dispatcher é uma interface
					//cuja implementacao define que um objeto pode despachar solicitacoes para qualquer recurso (como html, imagem,
					//jsp,servlet etc) no servidor.
					//ela é utilizada em dois casos:
					//para incluir a reposta de um servlet em outro e para encaminhar a solicitacao do cliente para outro servlet
					//para honrar a solicitacao(ou seja, o cliente chama um servlet, mas a resposta ao cliente é dada por outro servlet)
				RequestDispatcher redirecionar = request.getRequestDispatcher(url);
				//forward encaminha uma solicitacao para outro recurso no servidor
				redirecionar.forward(request, response);
				}else {
					RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
					request.setAttribute("msg", "Informe o login e senha corretamente!");
					
				}
			}else {
				RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
				request.setAttribute("msg", "Informe o login e senha corretamente!");
				redirecionar.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}

}
