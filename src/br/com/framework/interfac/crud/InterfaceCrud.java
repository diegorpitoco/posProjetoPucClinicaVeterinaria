package br.com.framework.interfac.crud;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public interface InterfaceCrud<T> extends Serializable{

	// Salva os dados.
	void save(T obj) throws Exception;
	
	// Realiza a persist�ncia.
	void persist(T obj) throws Exception;
	
	// Salva ou atualiza.
	void saveOrUpdate(T obj) throws Exception;
	
	//Realiza o update/ atualiza��o de dados.
	void update(T obj) throws Exception;
	
	//Realiza o delete de dados
	void delete(T obj) throws Exception;
	
	//Realiza o merge"Ele salva ou atualiza e retorna o objeto em persist�ncia".
	T merge(T obj) throws Exception;
	
	// carrega a lista de dados de determinada classe
	List<T> findList(Class<T> objs) throws Exception;
	
	Object findById(Class<T> entidade, Long id) throws Exception;
	
	T findByPorId(Class<T> entidade, Long id) throws Exception;
	
	List<T> findListByQueryDinamica(String s) throws Exception;
	
	//executar update com HQL
	void executeUpdateQueryDinamica(String s) throws Exception;
	
	//executar update com SQL
	void executeUpdateSQLDinamica(String s) throws Exception;
	
	
	//Limpa a sess�o de hibernate
	void clearSession() throws Exception;
	
	//Retira um objeto da sess�o do hibernate
	void evict(Object objs) throws Exception;
	
	
	Session getSession() throws Exception;
	
	
	List<?> getListSQLDinamica(String sql) throws Exception;
	
	//JDBC do spring
	JdbcTemplate getJdbcTemplate();
	
	SimpleJdbcTemplate getSimpleJdbcTemplate();
	
	SimpleJdbcInsert getSimpleJdbcInsert();
	
	Long totalRegistro(String table) throws Exception;
	
	Query obterQuery(String query) throws Exception;
	
	//Fazer carregamento dinamico com jsf e primefaces
	List<T> findListByQueryDinamica(String query, int iniciaNoRegistro, int maximoResultado);
}
