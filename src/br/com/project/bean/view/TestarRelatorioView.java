package br.com.project.bean.view;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.model.classes.Usuario;
import br.com.project.report.util.RelatorioUtil;

@Controller
@Scope(value = "request")
@ManagedBean(name = "testarRelatorioBeanView")
public class TestarRelatorioView extends BeanManagedViewAbstract{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private transient StreamedContent relatorioPreparadoDownload;
	

	

	public void imprimirRelatorio(){
		byte[] reportBytes;

		try {
			InputStream logo;
			ExternalContext stream = FacesContext.getCurrentInstance().getExternalContext();
			logo = stream.getResourceAsStream("/resources/img/logoVeteriaria.png");
			
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("logo", logo);
			params.put("parametro1", "ABC");
			params.put("parametro2", "ABC");
			params.put("parametro3", "ABC" );
			
			
			Usuario usuarioteste = new Usuario();
			usuarioteste.setUsu_codigo(123L);
			usuarioteste.setUsu_login("teste");
			
			List<Usuario> usuarioList = new ArrayList<Usuario>();
			usuarioList.add(usuarioteste);
			
			
			reportBytes = report("exemploreport", usuarioteste, params, usuarioList);
					
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> sessionMap = externalContext.getSessionMap();
			sessionMap.put("reportBytes", reportBytes);
			
			RequestContext.getCurrentInstance().execute( "window.open('../reportRedirect.xhtml');" );
		} 
		catch (Exception e) {

			e.printStackTrace();
		}
		
	}
	
	public StreamedContent downloadRelatorio(){
		byte[] reportBytes;

		try {
			InputStream logo;
			ExternalContext stream = FacesContext.getCurrentInstance().getExternalContext();
			logo = stream.getResourceAsStream("/resources/img/logoVeteriaria.png");
			
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("logo", logo);
			params.put("parametro1", "ABC");
			params.put("parametro2", "ABC");
			params.put("parametro3", "ABC" );
			
			
			Usuario usuarioteste = new Usuario();
			usuarioteste.setUsu_codigo(123L);
			usuarioteste.setUsu_login("teste");
			
			List<Usuario> usuarioList = new ArrayList<Usuario>();
			usuarioList.add(usuarioteste);
			
			
			
			
			
				reportBytes = report("exemploreport", usuarioteste, params, usuarioList);
				
				//byte[] arquivo_bytes = reportBytes;
				
				InputStream is = new ByteArrayInputStream(reportBytes);
	            String nomeArquivo =  "relatorio.pdf";
	            relatorioPreparadoDownload = new DefaultStreamedContent(is,"application/pdf",nomeArquivo);
	            
					
			
		} 
		catch (Exception e) {

			e.printStackTrace();
		}
		return relatorioPreparadoDownload;
		
	}
	
	
	public byte[] report(String nomerelatorio, Usuario usuario, Map<String, Object> params, List<Usuario> datasource) {
		byte[] reportBytes = null;
    	
    	String reportTitle = "TITULO " ; 
		String reportFile = nomerelatorio;
		
		params.put("REPORT_TITLE",  reportTitle );
			
			reportBytes = new RelatorioUtil().gerarRelatorioPDF(reportFile, datasource, params);
		

		
		return reportBytes;
	}


	@Override
	protected Class<?> getClassImplement() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected InterfaceCrud<?> getController() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public StreamedContent getRelatorioPreparadoDownload() {
		return relatorioPreparadoDownload;
	}

	public void setRelatorioPreparadoDownload(StreamedContent relatorioPreparadoDownload) {
		this.relatorioPreparadoDownload = relatorioPreparadoDownload;
	}
	
}
