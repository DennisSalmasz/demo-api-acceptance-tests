package demo.actions;

import demo.session.CoreSessionModel;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import demo.utils.JsonObjectUtils;

import static org.junit.Assert.assertTrue;

public class ResponseActions {

    private final Logger logger = LoggerFactory.getLogger(ResponseActions.class);
    protected CoreSessionModel testSession = CoreSessionModel.getInstance();

    public void responseCodeShouldBe(String rc) {
        Integer sc = testSession.getResponse().statusCode();
        assertTrue("Response code is incorrect", rc.contentEquals(sc.toString()));
    }

    public void verifyResponseRecordMatches(String fileName) throws Exception {
        JSONObject ref = getResponseJsonObjectFromFile(fileName);
        JSONObject jsonResp = new JSONObject(testSession.getResponse().getBody().asString());
        assertTrue("Response record and Expected JSON Record pattern are not the same",
                JsonObjectUtils.areEqual(ref, jsonResp));
    }

    public JSONObject getResponseJsonObjectFromFile(String fileName) throws Exception {
        String filePath = testSession.getResponseBodyFilesPath() + fileName;
        logger.info("Checking response for file in " + fileName);
        return JsonObjectUtils.jsonFromFile(filePath);
    }
}
