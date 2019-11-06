package br.com.project.util.all;

import org.springframework.stereotype.Component;

@Component
public abstract class BeanViewAbstract implements ActionViewPadrao {

	private static final long serialVersionUID = 1L;

	@Override
	public void limparLista() throws Exception {

	}

	@Override
	public String save() throws Exception {
	
		return null;
	}

	@Override
	public void saveNotReturn() throws Exception {

	}

	@Override
	public void saveEdit() throws Exception {

	}

	@Override
	public void excluir() throws Exception {

	}

	@Override
	public String ativar() throws Exception {
		return null;
	}

	@Override
	public String novo() throws Exception {
		return null;
	}

	@Override
	public String editar() throws Exception {
		return null;
	}

	@Override
	public void setarVariavelisNulas() throws Exception {

	}

	@Override
	public void consultarUsuario() throws Exception {

	}

	@Override
	public void statusOperation(EstatusPersistencia a) throws Exception {
		Messagens.responseOperation(a);
	}

	@Override
	public String redirecionaNewUsuario() throws Exception {
		return null;
	}

	@Override
	public String redirecionarFindUsuario() throws Exception {
		return null;
	}

	@Override
	public void addMsg(String msg) {
		Messagens.msg(msg);
	}
	
	
	protected void sucesso() throws Exception{
		statusOperation(EstatusPersistencia.SUCESSO);
	}
	
	protected void error() throws Exception{
		statusOperation(EstatusPersistencia.ERRO);
	}

}
