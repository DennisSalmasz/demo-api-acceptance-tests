package demo.steps;

import demo.actions.BodyActions;
import demo.session.CoreSessionModel;
import io.cucumber.java.en.And;
import net.thucydides.core.annotations.Steps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BodySteps {

    private final Logger logger = LoggerFactory.getLogger(BodySteps.class);
    private CoreSessionModel testSession = CoreSessionModel.getInstance();

    @Steps
    BodyActions bodyActions;

    @And("^I load the body from (.*)$")
    public void iLoadBodyToJsonString(String resourceFile) throws Exception {
        bodyActions.getRequestBodyFile(resourceFile);
        bodyActions.loadRequestFromJsonObject();
    }
}
