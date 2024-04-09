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
//intercepta todas as requisicoes que vierem do projeto ou mapeamento
@WebFilter(urlPatterns = {"/principal/*"})
public class FilterAutenticao extends HttpFilter implements Filter {
    //chamo a conexao   
    private static Connection connection;
    //metodo contrutor
    public FilterAutenticao() {
    
    }
    //encerra os processos quando o servidor é parado
    //daria um break nos processos de conexao com o banco
	public void destroy() {
		try {
			connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	/*intercepta as requisicoes e as respostas no sistema
	 * tudo que fizer no sistema vai fazer por aqui
	 * validacao de autenticacao
	 * dar commit e rollback de transacoes do banco
	 * validar e fazer redirecionamento de paginas*/
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			//no parametro nao temos HttpServlet apenas ServletRequest portanto
			/*o servlet conteiner cria um HttpServletRequest objecto 
			 * e passa-o como um argumento to the servlet's servicemethods (doGet, doPost, etc).*/
			HttpServletRequest req = (HttpServletRequest) request;
			/* Provides(fornece/providencia) a way to identify a user across more than one page request or visit to 
			 * a Web site and to store(armazenar) information about that user.
			 * The servlet container uses this interface to create a session between an HTTP 
			 * client and an HTTP server. The session persists for a specified time period, 
			 * across more than one connection or page request from the user. 
			 * A session usually corresponds to one user, who may visit a site many times. 
			 * The server can maintain a session (in many ways(de muitas maneiras)) 
			 * such as(tais como) using cookies or rewriting URLs.*/
			HttpSession session = req.getSession();
			//usuarioLogado recebe a sessao que declaro como String
			String usuarioLogado = (String) session.getAttribute("usuario");
			String urlParaAutenticar = req.getServletPath();//url que está sendo acessada
			
			//valida se está logado senao redireciona para a tela de Login
			if(usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {//nao esta logado entao
				//redireciona para o index.jsp
				RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
				request.setAttribute("msg", "Por favor realize o login!");
				//envia a requesicao para o servlet feita pelo RequestDispatcher
				redireciona.forward(request, response);
				return;//para a execucao e redireciona para o login
			}else {
				chain.doFilter(request, response);
			}
			//deu tudo certo, entao comita as alteracoes no banco de dados
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
			try {
				connection.rollback();
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
		}	
		
	}
	//inicia os processos ou recursos quando o servidor sobi o projeto
	//inicia a conexao com o banco
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnectionBanco.getConnection();
	}

}
