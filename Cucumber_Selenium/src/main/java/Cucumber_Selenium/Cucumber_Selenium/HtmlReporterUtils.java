package Cucumber_Selenium.Cucumber_Selenium;

import java.io.File;
import java.util.Arrays;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class HtmlReporterUtils {
	
	private HtmlReporterUtils() {}
	
	/**
	 * Méthode qui paramètre le rapport en début d'exécution du scénario
	 * 
	 * @param location chemin vers les fichiers en local
	 * @param documentTitle le titre affché dans l'onglet (&lt;title&gt; html)
	 * @param reportName Le titre/sujet du rapport
	 * @return l'objet "report"
	 */
	public static ExtentReports setupReport(String location, String documentTitle, String reportName) {
		
		createMissingFolders(location);
		
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(location);
		htmlReporter.config().setDocumentTitle(documentTitle);
		htmlReporter.config().setReportName(reportName);
//		htmlReporter.config().setEncoding("UTF-8"); // default UTF-8
		htmlReporter.config().setTimeStampFormat("dd/MM/yyyy HH:mm:ss");
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.setAppendExisting(true);
		
		ExtentReports report	= new ExtentReports();
		report.attachReporter(htmlReporter);
		
		return report;
	}
	
	/**
	 * Permet de filtrer une exception en n'affichant que le message d'entête, la première ligne du stack et les lignes
	 * contenant 'fr.sacem'
	 * 
	 * @param ae l'objet de type Throwable à formatter
	 * @return : the filtered trace
	 */
	public static String filteredStackTrace(Throwable ae) {

		StackTraceElement[] arrAe = ae.getStackTrace();
				
		StringBuilder filteredTrace = new StringBuilder();
		if (ae.getMessage() != null) {
			filteredTrace.append("<font color='red'>" + ae.getMessage() + "</font><br>");
		}		
		
		for (StackTraceElement elmt : arrAe) {
			if (elmt.toString().matches(".*fr.sacem.*") || Arrays.asList(arrAe).indexOf(elmt) == 0) {
				String[] splitted = elmt.toString().split("\\(");
				filteredTrace.append("<font color='red'>" + splitted[0] + "</font><font color='blue'> (" + splitted[1] + "</font><br>");
			}
		}

		return filteredTrace.toString();
	}
	
	/**
	 * Permet de filtrer une exception en affichant le message et les lignes de la stack
	 * 
	 * @param ae l'objet de type Throwable à formatter
	 * @return : the filtered trace
	 */
	public static String stackTrace(Throwable ae) {

		StackTraceElement[] arrAe = ae.getStackTrace();
				
		StringBuilder filteredTrace = new StringBuilder();
		if (ae.getMessage() != null) {
			filteredTrace.append("<font color='red'>" + ae.getMessage() + "</font><br>");
		}		
		
		for (StackTraceElement elmt : arrAe) {
			if (Arrays.asList(arrAe).indexOf(elmt) == 0) {
				String[] splitted = elmt.toString().split("\\(");
				filteredTrace.append("<font color='red'>" + splitted[0] + "</font><font color='blue'> (" + splitted[1] + "</font><br>");
			}
		}

		return filteredTrace.toString();
	}
	
	/**
	 * Permet de créer automatiquement un dossier contenant les rapports de test s'il n'existe pas
	 * 
	 * @param filePath
	 */
	private static void createMissingFolders(String filePath) {
		String folder;
		File file;
		if (filePath.contains(".html")) {
			folder	= filePath.substring(0, filePath.lastIndexOf('\\'));
			file	= new File(folder);
		} else {
			throw new NullPointerException("L'argument fournit n'est pas un fichier au format attendu ; i.e. .html");
		}
		
		
		file.mkdir();
	}
}
