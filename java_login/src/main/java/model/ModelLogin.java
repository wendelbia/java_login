package model;

public class ModelLogin {

	private static final long serialVersionUID = 1L;
	private String login;
	private String senha;
	
	//getter pega o atributo e o setter altera esse atributo
	//pego o atributo login e retorno ele
	public String getLogin() {
		return login;
	}
	//pego o atributo login e altero pelo parâmetro que está nessa funcao
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
