package br.com.project.acessos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum Permissao {

	ADMIN("ADMIN", "Administrador"),
	MED("MED", "Disponibiliza_Agenda, Realiza_Consulta, Solicita_Exames, Emite_Receita"),
	Secretaria("SECRET", "Marca_Consulta, Cadastra_Cliente"),
	Cliente("Clien", "Solicita_Consulta, ");
	
	
	private String valor = "";
	private String descricao="";
	
	
	
	private Permissao(String name, String descricao) {
		this.valor = name;
		this.descricao = descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public String getValor() {
		return valor;
	}
	
	@Override
	public String toString() {
		return getValor();
	}
	
	
	public static List<Permissao> getListPermissao(){
		List<Permissao> permissoes = new ArrayList<Permissao>();
		for(Permissao permissao: Permissao.values()) {
			permissoes.add(permissao);
		}
		Collections.sort(permissoes, new Comparator<Permissao>() {

			@Override
			public int compare(Permissao o1, Permissao o2) {
				// TODO Auto-generated method stub
				return new Integer(o1.ordinal()).compareTo(new Integer(o2.ordinal()));
			}			
		});
		
		return permissoes;
	}
	

	private Permissao() {
	
	}
	
	
	
	

}
