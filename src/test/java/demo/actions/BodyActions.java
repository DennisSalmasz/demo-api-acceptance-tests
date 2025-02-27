package demo.actions;

import demo.session.CoreSessionModel;
import demo.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BodyActions {

    private final Logger logger = LoggerFactory.getLogger(BodyActions.class);
    protected CoreSessionModel testSession = CoreSessionModel.getInstance();

    public void getRequestBodyFile(String fileName) throws Exception {
        String filePath = testSession.getRequestBodyFilesPath() + fileName;
        testSession.setJsonBody(FileUtils.loadJsonFromFile(filePath));
    }

    public void loadRequestFromJsonObject() {
        testSession.getRequestSpecBuilder().setBody(testSession.getJsonBody().toString());
    }
}
