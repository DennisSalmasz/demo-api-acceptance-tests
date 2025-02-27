package demo.session;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.apache.commons.configuration.PropertiesConfiguration;
import demo.utils.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CoreSessionModel {

    private final Logger logger = LoggerFactory.getLogger(CoreSessionModel.class);
    private static CoreSessionModel instance;
    private static String envPropsFilePath = "environment.properties";
    private PropertiesConfiguration propertiesConfiguration;
    private Properties environmentProperties;
    private Properties serviceProperties;
    protected RequestSpecBuilder requestSpecBuilder;
    protected Response response;
    protected JSONObject requestJsonBody = new JSONObject();
    private Map<String,String> globalTestResources;

    public CoreSessionModel() {
    }

    public static synchronized CoreSessionModel getInstance() {
        if (instance == null) {
            instance = new CoreSessionModel();
            instance.cleanGlobalMap();
            try {
                instance.loadEnvironmentPropertiesFile(envPropsFilePath);
                instance.propertiesConfiguration = new PropertiesConfiguration(envPropsFilePath);
            } catch (Exception var1) {
                instance.logger.info("failed to open environment file");
            }
        }
        return instance;
    }

    protected void loadEnvironmentPropertiesFile(String filePath) throws FileNotFoundException {
        this.logger.info("loading environment properties from " + filePath);
        instance.environmentProperties = FileUtils.loadPropertiesFromFile(filePath);
    }

    public String getBaseUri(String serviceHost) throws Exception {
        return this.getProperty(this.environmentProperties, serviceHost.toLowerCase());
    }

    public String getBaseUri() throws Exception {
        return this.getProperty(this.environmentProperties, "");
    }

    protected String getProperty(Properties properties, String key) throws Exception {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new Exception("unable to find property: " + key);
        } else {
            if (StringUtils.startsWith(value, "${") && StringUtils.endsWith(value, "}")) {
                value = this.propertiesConfiguration.getString(key);
            }
            return value;
        }
    }

    public void loadServicePropertiesFile(String service) throws FileNotFoundException {
        String servicePath = "services/" + service + "/" + service + ".properties";
        instance.serviceProperties = FileUtils.loadPropertiesFromFile(servicePath);
    }

    public String getServiceBasePath(String endpointName) throws Exception {
        return this.getProperty(this.serviceProperties, endpointName);
    }

    public void createNewSpecBuilder(String hostEnvPropertyName) throws Exception {
        String uri;
        if (StringUtils.isNotBlank(hostEnvPropertyName)) {
            uri = instance.getBaseUri(hostEnvPropertyName);
        } else {
            uri = instance.getBaseUri();
        }
        this.requestSpecBuilder = (new RequestSpecBuilder()).setBaseUri(uri);
    }

    public RequestSpecBuilder getRequestSpecBuilder() {
        return instance.requestSpecBuilder;
    }

    public String getRequestBodyFilesPath() throws Exception {
        String service = this.getProperty(this.serviceProperties, "service.name");
        return this.checkEnvFile("services/" + service + "/requestBodyFiles/");
    }

    public void setJsonBody(JSONObject body) {
        instance.requestJsonBody = body;
    }

    public JSONObject getJsonBody() {
        return instance.requestJsonBody;
    }

    public String getResponseBodyFilesPath() throws Exception {
        String service = this.getProperty(this.serviceProperties, "service.name");
        return this.checkEnvFile("services/" + service + "/responseBodyFiles/");
    }

    public void setResponse(Response response) {
        instance.response = response;
    }

    public Response getResponse() {
        return instance.response;
    }

    public String checkEnvFile(String offset) {
        String envFile = "src/test/resources/" + offset +  "/";
        this.logger.info("environment offset checked is " + envFile);
        return Files.isDirectory(Paths.get(envFile)) ? offset + "/" : offset;
    }

    public void cleanGlobalMap() {
        instance.globalTestResources = new HashMap<>();
    }
}
