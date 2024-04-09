package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {
	//jdbc->java database connectivity é um conjunto de classes e interfaces escritas em java que fazem
	//o envio de instrucoes SQL pra qualquer banco de dados realacional
	private static String banco = "jdbc:postgresql://localhost:5433/java_login?autoReconnect=true";
	private static String user = "postgres";
	private static String senha = "admin";
	//A interface Connection representa uma conexao ao banco de dados
	private static Connection connection = null;
	
	//metodo do tipo connection que retorna a conexao
	//getConnection abri uma conexao através desse metodo da classe DriverManager
	public static Connection getConnection() {
		return connection;
	}
	
	//static representa o metodo conectar sem precisar instanciá-lo
	static {
		conectar();
	}
	//esse método quando for chamado será conectado ao banco
	public SingleConnectionBanco() {
		conectar();
	}
	//metodo que faz as conexoes com o banco
	private static void conectar() {
		try {
			if (connection == null) {
				//driver é um programa de software que diz ao sistema operacional do 
				//seu computador como se comunicar com uma peça específica de hardware.
				//a funcao é controlar um dispositivo acima dele e cuidar para que a solicitacao seja executada
				Class.forName("org.postgresql.Driver");//carrega o driver de conexao do banco
				connection = DriverManager.getConnection(banco, user, senha);
				connection.setAutoCommit(false);//para nao efetuar alteracoes no banco sem nosso comando
			}
		} catch (Exception e) {
			e.printStackTrace();//mostra qualquer erro no momento de conectar
		}
	}

}
