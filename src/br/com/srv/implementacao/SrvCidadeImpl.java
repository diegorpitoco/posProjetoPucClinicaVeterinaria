package br.com.srv.implementacao;

import org.springframework.stereotype.Service;

import br.com.repository.interfaces.RepositoryCidade;
import br.com.srv.interfaces.SrvCidade;

@Service
public class SrvCidadeImpl implements SrvCidade {

	private static final long serialVersionUID = 1L;

	private RepositoryCidade repositoryCidade;

}
