package demo.runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"demo.steps"},
        plugin = {"pretty","junit:target/cucumber-reports/cucumber.xml"}
)
public class TestSuiteRunner {
}
