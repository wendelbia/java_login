package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOLoginRepository {

	//chamo a classe Connection declarando um atributo
	private Connection connection;
	
	//método construtor que inicializará automaticamente a conexao
	public DAOLoginRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	//método de validacao
	public boolean validarAutenticacao(ModelLogin modelLogin) throws Exception {
		//comando sql que seleciona o login
		String sql = "select * from login where upper(login) = upper(?) and upper(senha) = upper(?)";
		//declaracao que envia o comando para o banco
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, modelLogin.getLogin());
		statement.setString(2, modelLogin.getSenha());
		//ResultSet faz a pesquisa no banco de dados e criar um tabela com esse conjunto de resultados 
		//Um objeto ResultSet mantém um cursor apontando para sua linha atual de dados. Inicialmente o 
		//cursor é posicionado antes da primeira linha. O próximo método move o cursor para a próxima 
		//linha e, como retorna falso quando não há mais linhas no objeto ResultSet, ele pode ser usado 
		//em um loop while para iterar pelo conjunto de resultados.
		//executeQuery() Executa a consulta SQL neste objeto PreparedStatement e retorna 
		//o objeto ResultSet gerado pela consulta.
		
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()) {
			return true; //autenticaco
		}
		return false;//não autenticado
	}
	
	
}
