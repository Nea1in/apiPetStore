package tests;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.LoggerUtility;

public abstract class BaseTest {
    protected final LoggerUtility logger = new LoggerUtility(this.getClass());

    @BeforeMethod
    public void logTestStart(Object[] testArgs) {
        logger.info("===== STARTING TEST: {} =====", getTestName());
    }

    @AfterMethod
    public void logTestEnd() {
        logger.info("===== FINISHED TEST: {} =====", getTestName());
    }

    private String getTestName() {
        return Thread.currentThread().getStackTrace()[3].getMethodName();
    }
}
