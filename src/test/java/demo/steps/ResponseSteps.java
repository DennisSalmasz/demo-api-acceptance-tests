package demo.steps;

import demo.session.CoreSessionModel;
import demo.actions.ResponseActions;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.Steps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseSteps {

    private final Logger logger = LoggerFactory.getLogger(ResponseSteps.class);

    private CoreSessionModel testSession = CoreSessionModel.getInstance();

    @Steps
    ResponseActions responseActions;

    @Then("^Status code is (.*)$")
    public void responseStatusStringIs(String rc) {
        logger.info("Check response code is " + rc);
        responseActions.responseCodeShouldBe(rc);
    }

    @Then("^I verify the response record matches (.*)$")
    public void verifyResponseRecordMatches(String fileName) throws Throwable {
        responseActions.verifyResponseRecordMatches(fileName);
    }
}
