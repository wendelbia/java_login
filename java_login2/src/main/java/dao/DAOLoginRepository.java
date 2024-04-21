package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOLoginRepository {

	private Connection connection;
	//toda vez que eu chamar essa classe automaticamente vai chamar a conexao com o banco
	public DAOLoginRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	//método do retorna valor boolean true ou false que verifica no bd se existe a senha e o login
	public boolean validarAutenticacao(ModelLogin modelLogin) throws Exception {
		//sql recebe o comando sql
		String sql = "select * from login where upper(login) = upper(?) and upper(senha) = upper(?)";
		//PreparedStatement: prepara esse comando pra enviar pro banco de dados
		//Um objeto que  representa precompilacao de uma declaracao sql
		//Uma SQL statement he precompilada e armazenada em um PreparedStatement objeto. 
		//esse objeto entao pode ser usedo para a eficiencia de execucao dessa declaracao multiplas quatidades.
		//prepareStatement cria um objeto PreparedStatement para ser enviado padronizado para o banco de dados
		PreparedStatement statement = connection.prepareStatement(sql);
		//objeto statement chama o metodo que altera a String na coluna 1 do banco de dados pela objeto q chamada o metodo 
		statement.setString(1, modelLogin.getLogin());
		statement.setString(2, modelLogin.getSenha());
		
		//objeto do tipo Result que faz um escaneamento na tabela
		//executeQuery o comando sql e retorna o resultado
		ResultSet resultSet = statement.executeQuery() ;
		//vai varrer a tabela ate achar 
		if(resultSet.next()) {
			return true;//se tiver validado entao autenticou
		}
		return false;//não foi autenticado
	}
}
