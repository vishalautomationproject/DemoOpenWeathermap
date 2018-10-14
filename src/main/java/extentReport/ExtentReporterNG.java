package extentReport;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReporterNG implements IReporter {
	
	private ExtentReports extent;

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
			String outputDirectory) {
		extent = new ExtentReports(outputDirectory + File.separator
				+ "Extent.html", true);

		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();

				buildTestNodes(context.getPassedTests(), LogStatus.PASS);
				buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
				buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
			}
		}

		extent.flush();
		extent.close();
	}

	private void buildTestNodes(IResultMap tests, LogStatus status) {
		ExtentTest OpenWeatherMap;

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				OpenWeatherMap = extent.startTest(result.getMethod().getMethodName());

				OpenWeatherMap.setStartedTime(getTime(result.getStartMillis()));
				OpenWeatherMap.setEndedTime(getTime(result.getEndMillis()));

				for (String group : result.getMethod().getGroups())
					OpenWeatherMap.assignCategory(group);

				if (result.getThrowable() != null) {
					OpenWeatherMap.log(status, result.getThrowable());
				} else {
					OpenWeatherMap.log(status, "Open Weather Map Application Test Automation Test Suite " + status.toString().toLowerCase()
							+ "ed");
				}

				extent.endTest(OpenWeatherMap);
			}
		}
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
}