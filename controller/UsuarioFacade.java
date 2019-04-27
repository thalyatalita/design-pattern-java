package controller;

import java.sql.SQLException;

import dao.UsuarioDAO;
import model.Usuario;

/*
 * padãro sigleton com fachade
 */

public class UsuarioFacade {
	/*um atributo marcado com volatile, o seu valor sera 
	compartilhado entre todas as threads que acessam o objeto*/
	private volatile static UsuarioFacade INSTANCE;
	
	public static UsuarioFacade getINSTANCE() {
		if(INSTANCE == null) {
			/*O synchronized garante que a execução deste método
			 *  seja realizada apenas por uma Thread por vez, 
			 *  utilizando um mecanismo de lock. A Thread
			 *   que começa a executar o método "pega" o lock, 
			 *   liberando-o ao término da execução do método*/ 
			synchronized (UsuarioFacade.class) {
				if(INSTANCE == null) {
					INSTANCE = new UsuarioFacade();
				}
			}
		}
		return INSTANCE;
	}
	/*
	 * fachada para logar
	 */
	public boolean facadeLogin(String nome, String senha) throws SQLException {
		Usuario daoLog = new UsuarioDAO().login(nome, senha);
		if(daoLog!=null) {
			return true;
		}
		else{
			return false;
		}
	}
}