package demo.actions;

import demo.session.CoreSessionModel;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class RequestActions {

    private final Logger logger = LoggerFactory.getLogger(RequestActions.class);
    protected CoreSessionModel testSession = CoreSessionModel.getInstance();

    public void createNewRequest(String service, String nodeParam) throws Exception {
        testSession.loadServicePropertiesFile(service);
        testSession.createNewSpecBuilder(nodeParam);
    }

    public void setListOfPathParameters(DataTable paramTable) {
        List<Map<String,String>> data = paramTable.asMaps(String.class, String.class);
        setListOfPathParameters(data);
    }

    public void setListOfPathParameters(List<Map<String,String>> pathParams) {
        for (Map<String, String> pathParam : pathParams) {
            setPathParameter(pathParam.get("key"), pathParam.get("value"));
        }
    }

    public void setPathParameter(String key, String value) {
        if (value == null) {
            value = "";
        }
        testSession.getRequestSpecBuilder().addPathParam(key,value);
    }

    public void performHttpRequest(String method, String resource) throws Exception {
        testSession.getRequestSpecBuilder().setBasePath(this.testSession.getServiceBasePath(resource));
        callHttpRequest(method);
    }

    private void callHttpRequest(String method) throws Exception {
        RequestSpecification requestSpec = testSession.getRequestSpecBuilder().build();
        Response response;
        switch (method) {
            case "GET":
                response = SerenityRest.given(requestSpec).get();
                break;
            case "PATCH":
                response = SerenityRest.given(requestSpec).patch();
                break;
            case "POST":
                response = SerenityRest.given(requestSpec).post();
                break;
            case "PUT":
                response = SerenityRest.given(requestSpec).put();
                break;
            case "DELETE":
                response = SerenityRest.given(requestSpec).delete();
                break;
            default:
                throw new Exception("Unsupported request method" + method);
        }
        testSession.setResponse(response);
        response.prettyPeek();
    }
}
