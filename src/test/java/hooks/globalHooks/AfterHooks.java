package hooks.globalHooks;

import cucumber.api.java.After;
import util.SeleniumExecutor;
import util.configurations.DataSource;
import util.configurations.DataSourceORM;
import util.helpers.SeleniumExtender;

public class AfterHooks {

    @After(order = 0)
    public void afterScenario() {
        SeleniumExecutor.stop();

        DataSource.closeConnection();
        DataSourceORM.closeConnection();
    }

    @After(order = 99999)
    public void closeReporter() {
        SeleniumExtender.SeleniumReporter.embedScreenshotIfScenarioFailed();
        SeleniumExtender.SeleniumReporter.close();
    }
}
