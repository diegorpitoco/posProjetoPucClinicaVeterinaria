package br.com.project.report.util;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

public class RelatorioUtil {
	private static final String DEFAULT_LOGO_IMAGE = "SuprimentoLogo.jpg";
	
	public enum FORMATO_RELATORIO{
		XLSX, PDF;
	}
	
	/**
	 * @return Arquivo de propriedades [application.properties]
	 * @throws IOException
	 */
	private static Properties loadProperties() throws IOException {
		Properties prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();           
		InputStream stream = loader.getResourceAsStream("resources/application.properties");
		prop.load(stream);
		return prop;
	}
	
	@SuppressWarnings("unused")
	private String gerarIdReport() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
		String id = simpleDateFormat.format(new Date());

		return id;
	}

	/**
	 * Retorna o caminho dos relatórios 
	 * lendo do arquivo [resources/application.properties]
	 * */
	public static String getReportSourcePath() {
		
		try {
			Properties prop = loadProperties();
			
			String reportSource = prop.getProperty("reportSource");
			return FacesContext.getCurrentInstance().getExternalContext().getRealPath(reportSource);
			
		}
		catch (IOException e) {
			//e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * @param siglaUnidade
	 * @return Caminho absoluto da logo da Unidade ou Logo padrão do sistema
	 */
	public static String getLogoSourcePath( String siglaUnidade ) {
		String retorno = null;
		try {
			Properties prop = loadProperties();
			prop = loadProperties();
			
			String logoSource = prop.getProperty("logoSource");
			String logoPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(logoSource);
			
			String logoFile = siglaUnidade + ".gif";
			
			File logoImage = new File( logoPath + File.separator + logoFile );
			if ( logoImage.exists() ) {
				retorno = logoImage.getAbsolutePath();
			}
			else {
				File defaultLogo = new File( logoPath + File.pathSeparator + DEFAULT_LOGO_IMAGE );
				retorno = defaultLogo.getAbsolutePath();
			}
		} 
		catch (IOException e) {
			//e.printStackTrace();
		}
		return retorno;
	}

	/**
	 * Retorna o caminho dos relatórios
	 * lendo do arquivo WEB.XML
	 * 
	 * <web-app>
	 * 		<context-param>
	 * 			<param-name>myconstantkey</param-name>
	 * 			<param-value>some string value</param-value>
	 * 		</context-param>
	 * </web-app>
	 * 
	 * */
	@SuppressWarnings("unused")
	private String reportFile() {
		return FacesContext.getCurrentInstance().getExternalContext().getInitParameter("reportDirectory");
	}

	/**
	 * Abrir Janela com Arquivo (PDF, XLS, TXT e etc)
	 * */
	public void abrirPoupUp(String fileName) {
		abrirPoupUp(fileName, null);
	}

	@SuppressWarnings("unused")
	public void abrirPoupUp(String fileName, String nomeJanela){
		HttpServletRequest req = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		String contextPath = req.getContextPath().replace("/", "");
		if (nomeJanela == null){
			nomeJanela = "Relatório";
		}

//		RequestContext.getCurrentInstance().execute("window.open(/" + contextPath + "/report/" + fileName	+ "",'"+nomeJanela+"','width=800px,height=800px')");
	}

	private byte[] gerarRelatorio(String relatorio, List<?> beans, Map<String, Object> params, FORMATO_RELATORIO formatoExportacao) {
		byte[] reportBytes = null;
		JasperPrint jasperPrint = null;
		try {
			String caminhoRelatorio = RelatorioUtil.getReportSourcePath();
			String arquivoRelatorio = relatorio.endsWith(".jasper") ? relatorio : (new StringBuilder()).append(relatorio).append(".jasper").toString();
			String arquivoJasper = caminhoRelatorio + File.separator + arquivoRelatorio;
			
			if (params == null)
				params = new HashMap<String, Object>();


			if (beans != null && beans.size() > 0) {
				JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(beans);
				jasperPrint = JasperFillManager.fillReport(arquivoJasper, params, beanCollectionDataSource);
			} 
			else {
				jasperPrint = JasperFillManager.fillReport(arquivoJasper, params, new JREmptyDataSource());
			}

			if (formatoExportacao.equals(FORMATO_RELATORIO.PDF)){
				reportBytes = gerarPDF(jasperPrint);
			}
			else if (formatoExportacao.equals(FORMATO_RELATORIO.XLSX)){
				reportBytes = gerarExcel(jasperPrint); 
			}
			
			/* gerando um arquivo temporário para o relatório
			File tempFile = File.createTempFile(arquivoRelatorio, fileSufix);
			FileUtils.writeByteArrayToFile(tempFile, reportBytes);
			*/
		} 
		catch (JRException e) {
			//e.printStackTrace();
			return null;
		}
		return reportBytes;
	}

	private byte[] gerarPDF(JasperPrint jasperPrint) {
		byte[] bytes = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		File testeFile = new File("testeArquivoPDF.pdf");	
		
		try {
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
//			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(testeFile));
			
			SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			exporter.setConfiguration(configuration);
			exporter.exportReport();
			
			bytes = baos.toByteArray();
		} 
		catch (JRException e) {
			//e.printStackTrace();
		}
		
		return bytes;
	}
	
	private byte[] gerarExcel(JasperPrint jasperPrint) {
		byte[] bytes = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
			
			SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
			configuration.setOnePagePerSheet(true);
			configuration.setDetectCellType(true);
			configuration.setCollapseRowSpan(false);
			exporter.setConfiguration(configuration);
			exporter.exportReport();
			
			bytes = baos.toByteArray();
		} 
		catch (JRException e) {
			//e.printStackTrace();
		}
		
		return bytes;
	}
	
	/**
	 * Gerar relatório no formato XLSX
	 */
	public byte[] gerarRelatorioXLSX(String relatorio, List<?> beans, Map<String, Object> params){
		return gerarRelatorio(relatorio, beans, params, FORMATO_RELATORIO.XLSX);
	}

	/**
	 * Gerar relatório no formato PDF 
	 * */
	public byte[] gerarRelatorioPDF(String relatorio, List<?> beans, Map<String, Object> params){
		return gerarRelatorio(relatorio, beans, params, FORMATO_RELATORIO.PDF);
	}

}