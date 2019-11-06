package br.com.project.util.all;

import java.io.Serializable;

import javax.annotation.PostConstruct;

public interface ActionViewPadrao extends Serializable {

	abstract void limparLista() throws Exception;
	
	abstract String save() throws Exception;
	
	abstract void saveNotReturn() throws Exception;
	
	abstract void saveEdit() throws Exception;
	
	abstract void excluir() throws Exception;
	
	abstract String ativar() throws Exception;
	
	/**
	 * 
	 *  @PostConstruct realiza inicialização de métodos, valores ou variáveis.
	 * @throws Exception
	 * */
	@PostConstruct
	abstract String novo() throws Exception;
	
	abstract String editar() throws Exception;
	
	abstract void setarVariavelisNulas() throws Exception;
	
	abstract void consultarUsuario() throws Exception;
	
	abstract void statusOperation(EstatusPersistencia a) throws Exception;
	
	abstract String redirecionaNewUsuario() throws Exception;
	
	abstract String redirecionarFindUsuario() throws Exception;
	
	abstract void addMsg(String Msg);
	
}
