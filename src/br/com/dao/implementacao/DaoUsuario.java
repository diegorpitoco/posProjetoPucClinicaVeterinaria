package br.com.dao.implementacao;

import java.util.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.Usuario;
import br.com.repository.interfaces.RepositoryUsuario;

@Repository
public class DaoUsuario extends ImplementacaoCrud<Usuario> implements RepositoryUsuario {

	private static final long serialVersionUID = 1L;

	@Override
	public Date getUltimoAcessoUsuarioLogado(String name) {
		SqlRowSet rowSet = super.getJdbcTemplate().queryForRowSet(
				"select usu_ultimoacesso from usuario where usu_inativo is false and usu_login = ?",
				new Object[] { name });
		return rowSet.next() ? rowSet.getDate("usu_ultimoacesso") : null;
	}

	@Override
	public void updateUltimoAcessoUser(String login) {
		String sql = "update usuario set usu_ultimoacesso = current_timestamp where usu_inativo is false and usu_login = ? ";
		super.getSimpleJdbcTemplate().update(sql, login);
	}

	@Override
	public boolean existeUsuario(String usu_login) {
		StringBuilder builder = new StringBuilder();
		builder.append(" select count(1) >= 1 from usuario where usu_login = '")
		.append(usu_login).append("'");
		
		return super.getSimpleJdbcTemplate().queryForObject(builder.toString(), Boolean.class);
	}

}
