package hooks.globalHooks;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import util.Logger;
import util.configurations.TestConfiguration;
import util.helpers.SeleniumExtender;

public class BeforeHooks {

    @Before(order = 1)
    public void initExecutor() {
        TestConfiguration.initialise();
        Logger.init();
    }

    @Before(order = 0)
    public void initReporter(Scenario newScenario) {
        SeleniumExtender.SeleniumReporter.init(newScenario);
    }
}
