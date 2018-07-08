package com.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class GeneratePDFReport {

	public static void main(String[] args) {
		String filePath = "C://Jasper//page//";
		System.out.println("Jasper file path : " + filePath);

		GeneratePDFReport report = new GeneratePDFReport();

		System.out.println("Compiling the JRXML file to Jasper files....");
		report.compileReports(filePath);

		CountryList countryList = new CountryList();
		ArrayList<Country> dataList = countryList.getCountryList();

		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);

		Map<String, Object> reportParams = new HashMap<String, Object>();
		String mainReport = filePath + "CountryMainReport.jasper";
		//reportParams.put("countryList", beanColDataSource);

		try {
			System.out.println("Filling the report....");
			JasperPrint reportPrint = JasperFillManager.fillReport(mainReport, reportParams, beanColDataSource);

			List<JRPrintPage> pages = reportPrint.getPages();

			System.out.println("Number of Pages :" + pages.size());

			List<JasperPrint> jasperPrint = new ArrayList<JasperPrint>();

			jasperPrint.add(reportPrint);

			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("C://countryReport.pdf"));
			exporter.exportReport();

			// JasperExportManager.exportReportToPdfFile(print,
			// "H://report.pdf");
			System.out.println("Exported the PDF file successfully....");
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	private void compileReports(String filePath) {
		String mainReport = filePath + "CountryMainReport.jrxml";
		String subreport = filePath + "StatesReport.jrxml";

		try {
			JasperCompileManager.compileReportToFile(mainReport);
			System.out.println("Successfully generated " + mainReport + ".jasper file");
			JasperCompileManager.compileReportToFile(subreport);
			System.out.println("Successfully generated " + subreport + ".jasper file");
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

}
