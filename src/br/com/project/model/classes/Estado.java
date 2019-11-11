package br.com.project.model.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name = "estado")
@SequenceGenerator(name = "estado_seq", sequenceName = "estado_seq", initialValue = 1 , allocationSize = 1 )
public class Estado implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa( descricaoCampo = "Código", campoConsulta = "est_id")
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO, generator = "estado_seq")
	private Long est_id;
	
	@IdentificaCampoPesquisa( descricaoCampo = "Nome", campoConsulta = "est_nome", principal = 1)
	@Column(nullable = false, length = 80)
	private String est_nome;
	
	@Column(nullable = true, length = 15)
	private String est_sigla;
	
	@Version
	@Column(name = "versionNum")
	private int versionNum;
	
	@NotAudited
	@OneToMany(mappedBy = "estado", orphanRemoval = false)
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private List<Cidade> cidades = new ArrayList<Cidade>();
	
	
	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Long getEst_id() {
		return est_id;
	}

	public void setEst_id(Long est_id) {
		this.est_id = est_id;
	}

	public String getEst_nome() {
		return est_nome;
	}

	public void setEst_nome(String est_nome) {
		this.est_nome = est_nome;
	}

	public String getEst_sigla() {
		return est_sigla;
	}

	public void setEst_sigla(String est_sigla) {
		this.est_sigla = est_sigla;
	}

	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((est_id == null) ? 0 : est_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estado other = (Estado) obj;
		if (est_id == null) {
			if (other.est_id != null)
				return false;
		} else if (!est_id.equals(other.est_id))
			return false;
		return true;
	}	
}
