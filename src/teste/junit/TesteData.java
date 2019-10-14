package teste.junit;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import br.com.project.report.util.DateUtils;

public class TesteData {

	@Test
	public void testData() {
		try {

			assertEquals("14102019", DateUtils.getDateAtualReportName());
			
			assertEquals("'14-10-2019'", DateUtils.formatDateSql(Calendar.getInstance().getTime()));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
