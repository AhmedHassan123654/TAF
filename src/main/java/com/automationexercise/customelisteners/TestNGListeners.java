package com.automationexercise.customelisteners;

import com.automationexercise.FileUtils;
import com.automationexercise.drivers.WebDriverProvider;
import com.automationexercise.media.ScreenRecordManager;
import com.automationexercise.media.ScreenShotsManager;
import com.automationexercise.utils.dataReader.PropertyReader;
import com.automationexercise.utils.logs.LogsManager;
import com.automationexercise.utils.report.AllureAttachmentManger;
import com.automationexercise.utils.report.AllureConstants;
import com.automationexercise.utils.report.AllureEnviromentManger;
import com.automationexercise.utils.report.AllureReportGenerator;
import com.automationexercise.validations.Validation;
import org.apache.commons.logging.Log;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;

public class TestNGListeners implements IExecutionListener, IInvokedMethodListener, ITestListener {
    public void onExecutionStart() {

        LogsManager.info("======== Starting Test Execution ========");
        org.apache.logging.log4j.LogManager.shutdown();
        cleantestdirs();
        LogsManager.info("✅ Test Directories Cleaned Successfully");
        createtestdirs();
        LogsManager.info("✅ Test Directories Created Successfully");
        PropertyReader.loadProperties();
        LogsManager.info("✅ Test Properties Loaded Successfully");
        AllureEnviromentManger.setEnvironmentVariables();
        LogsManager.info("✅ Allure Environment Variables Set Successfully");
    }

    /**
     * Invoked once all the suites have been run.
     */
    public void onExecutionFinish() {
        AllureReportGenerator.generateReports(false);
        AllureReportGenerator.copyHistory();
        AllureReportGenerator.generateReports(true);
        AllureReportGenerator.openReport(AllureReportGenerator.renameReport());
        LogsManager.info("✅ Allure Report Generated Successfully");
        LogsManager.info("======== Test Execution Finished ========");

    }


    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            ScreenRecordManager.startRecording();
            LogsManager.info("======== Starting Test: " + testResult.getMethod().getMethodName() + " ========");
        }

    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        WebDriver driver = null;
        if (method.isTestMethod()) {
            ScreenRecordManager.stopRecording(testResult.getMethod().getMethodName());
            Validation.assertAll(testResult);
            if (testResult.getInstance() instanceof WebDriverProvider provider)
                driver = provider.getDriver();
            switch (testResult.getStatus()) {
                case ITestResult.SUCCESS ->
                        ScreenShotsManager.takeFullPageScreenshot(driver, "passed-" + testResult.getName());
                case ITestResult.FAILURE ->
                        ScreenShotsManager.takeFullPageScreenshot(driver, "failed-" + testResult.getName());
                case ITestResult.SKIP ->
                        ScreenShotsManager.takeFullPageScreenshot(driver, "skipped-" + testResult.getName());
            }
            AllureAttachmentManger.attachLogs();
            AllureAttachmentManger.attachRecords(testResult.getName());
        }

    }


    public void onTestStart(ITestResult result) {
        // not implemented
    }

    /**
     * Invoked each time a test succeeds.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SUCCESS
     */
    public void onTestSuccess(ITestResult result) {
        LogsManager.info("======== Test: " + result.getMethod().getMethodName() + " Finished Successfully ========");
    }

    /**
     * Invoked each time a test fails.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#FAILURE
     */
    public void onTestFailure(ITestResult result) {
        LogsManager.error("======== Test: " + result.getMethod().getMethodName() + " Failed ========");
    }

    /**
     * Invoked each time a test is skipped.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SKIP
     */
    public void onTestSkipped(ITestResult result) {
        LogsManager.warn("======== Test: " + result.getMethod().getMethodName() + " Skipped ========");
    }


    //cleanning and creating dirs

    private void cleantestdirs() {
        FileUtils.cleanDirectory(AllureConstants.RESULTS_FOLDER.toFile());
        FileUtils.cleanDirectory(new File(ScreenShotsManager.SCREENSHOTS_PATH));
        FileUtils.cleanDirectory(new File(ScreenRecordManager.RECORDINGS_PATH));
        FileUtils.deleteLogFile(LogsManager.LOGS_PATH + File.separator + "logs.log");

    }

    private void createtestdirs() {
        FileUtils.createDirectory(ScreenShotsManager.SCREENSHOTS_PATH);
        FileUtils.createDirectory(ScreenRecordManager.RECORDINGS_PATH);
    }


}
