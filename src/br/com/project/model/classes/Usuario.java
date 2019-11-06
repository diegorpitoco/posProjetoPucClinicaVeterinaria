package br.com.project.model.classes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

@Audited
@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long usu_codigo;

	private boolean usu_inativo = false;
	
	private String usu_login = null;

	private String usu_senha;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date usu_ultimoacesso;


	public boolean getUsu_inativo() {
		return usu_inativo;
	}

	public void setUsu_inativo(boolean usu_inativo) {
		this.usu_inativo = usu_inativo;
	}

	public String getUsu_login() {
		return usu_login;
	}

	public void setUsu_login(String usu_login) {
		this.usu_login = usu_login;
	}

	public String getUsu_senha() {
		return usu_senha;
	}

	public void setUsu_senha(String usu_senha) {
		this.usu_senha = usu_senha;
	}

	public void setUsu_codigo(Long usu_codigo) {
		this.usu_codigo = usu_codigo;
	}
	
	public Long getUsu_codigo() {
		return usu_codigo;
	}
	
	public Date getUsu_ultimoacesso() {
		return usu_ultimoacesso;
	}

	public void setUsu_ultimoacesso(Date usu_ultimoacesso) {
		this.usu_ultimoacesso = usu_ultimoacesso;
	}
}
