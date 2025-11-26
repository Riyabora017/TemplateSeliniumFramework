package V.TemplateSelFramewrk.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import V.TemplateSelFramewrk.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
  ExtentTest test;
	ExtentReports extent =	ExtentReporterNG.getReportObject();
	ThreadLocal <ExtentTest> extentTest = new ThreadLocal();  //Thread safe
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	extentTest.set(test); //unique thread id(ErrorValidationTest)->test
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		extentTest.get().log(Status.PASS,"Test Passed");
	}
	
	
	@Override
	public void onTestFailure(ITestResult result) {
		//test.log(Status.FAIL,"Test Fail");
		extentTest.get().fail(result.getThrowable());
	    
	    try {
	        driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
	    } catch (Exception e1) {
	        e1.printStackTrace();
	    }

		//screenshot,attach to report
				String filePath = null;
				try {
					filePath = getScreenshot(result.getMethod().getMethodName(), driver);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
				
			}
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
