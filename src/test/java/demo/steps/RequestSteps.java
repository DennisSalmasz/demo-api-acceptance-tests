package demo.steps;

import demo.actions.HeaderActions;
import demo.actions.RequestActions;
import demo.session.CoreSessionModel;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestSteps {

    private final Logger logger = LoggerFactory.getLogger(RequestSteps.class);

    @Steps
    RequestActions requestActions;

    @Steps
    HeaderActions headerActions;

    @Given("^I create a new (.*) request$")
    public void iCreateANewRequest(String service) throws Exception {
        requestActions.createNewRequest(service, service+"-service");
        headerActions.setContentType("application/json");
    }

    @When("^I perform (.*) on (.*) resource$")
    public void iPerformRequest(String method, String resource) throws Exception {
        requestActions.performHttpRequest(method, resource);
    }

    @When("^I perform (.*) on (.*) resource with path parameters$")
    public void iPerformMethodWithPathParameters(String method, String resource, DataTable table) throws Throwable {
        requestActions.setListOfPathParameters(table);
        requestActions.performHttpRequest(method, resource);
    }
}
