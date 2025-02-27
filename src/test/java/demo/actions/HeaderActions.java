package demo.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import demo.session.CoreSessionModel;

public class HeaderActions {

    private final Logger logger = LoggerFactory.getLogger(RequestActions.class);
    protected CoreSessionModel testSession = CoreSessionModel.getInstance();

    public void setContentType(String value) {
        this.testSession.getRequestSpecBuilder().setContentType(value);
    }
}
