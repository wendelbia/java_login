package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {
	private static String banco = "jdbc:postgresql://localhost:5433/java_login?autoReconnect=true";
	private static String user = "postgres";
	private static String senha = "admin";
	private static Connection connection = null;
	
	public static Connection getConnection() {
		return connection;
	}
	static {
		conectar();
	}
	//faz uma conexao com o banco de dados
	private static void conectar() {
		try {
			if(connection == null) {
				Class.forName("org.postgresql.Driver"); //carrega o driver de conexao do banco
				connection = DriverManager.getConnection(banco, user, senha);
				connection.setAutoCommit(false);//para nao efetuar alteracoes no banco sem nosso comando
			}
		} catch (Exception e) {
			e.printStackTrace();//mostra qualquer erro no momento de conectar
		}
	}
}
