package ExtentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

	public static final ExtentReports extentReports = new ExtentReports();
    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./extent-reports/extent-report.html");
        reporter.config().setReportName("Technical Task - Execution Report");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("TechTask", " MB.io");
        extentReports.setSystemInfo("Candidate", "Manoj Thomas");
        return extentReports;
    }
	
}
