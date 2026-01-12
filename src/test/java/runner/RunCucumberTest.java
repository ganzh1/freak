package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features/facebook_signup.feature",
        glue = {"steps"},
        plugin = {
                "pretty",                           // Console only (no file)
                "html:target/facebook-report.html", // HTML
                "json:target/facebook-report.json"  // JSON
        },
        monochrome = true
)
public class RunCucumberTest {
}
