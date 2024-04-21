package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import connection.SingleConnectionBanco;

@WebFilter(urlPatterns = {"/principal/*"})//intercepta todas as requisicoes que vierem do projeto ou mapeamento
public class FilterAutenticao extends HttpFilter implements Filter {

	//chamo a conexao com o banco
	private static Connection connection;
	
    public FilterAutenticao() {
    }
    //encerra os processs quando o servidor he parado
    //mataria os processos de conexao com banco
	public void destroy() {
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/* intercepta as requisicoes e as respostas no sistema
	 * tudo que fizer no sistema vai fazer por aqui
	 * validacao de autenticacao
	 * da commit e rolback de transacoes do banco
	 * validar e fazer redirecionamento de paginas*/
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			//como nao temos no parametro o HttpServletRequest chamamos aqui
			//The servlet container creates an HttpServletRequest object 
			//and passes it as an argument to the servlet's service methods (doGet, doPost, etc).
			HttpServletRequest req = (HttpServletRequest) request;
			//cria uma maneira de identificar um usuario para que através das paginas seja identificado esse usuario
			HttpSession session = req.getSession();
			//atributo que recebe o usuario logado para a sessao
			String usuarioLogado = (String) session.getAttribute("usuario");
			//Returns the part of this request's URL that calls the servlet
			String urlParaAutenticar = req.getServletPath();//URL que está sendo acessada
			//validar se está logado, senao redireciona para a tela de login
			if(usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {//nao tá logado
				RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
				request.setAttribute("msg", "Por favor realize o login");
				redireciona.forward(request, response);
				return;//para a execucao e redirecionamento para o login 
			}else {
				chain.doFilter(request, response);
			}
			connection.commit();//deu tudo certo, entao comita as alteracoes no banco de dados
		} catch (Exception e) {
			e.printStackTrace();
			
			RequestDispatcher redireciona = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redireciona.forward(request, response);
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	//inicia os processos ou recursos quando o servidor sob o projeto
	//inicar a conexao com o bd
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnectionBanco.getConnection();
	}

}
