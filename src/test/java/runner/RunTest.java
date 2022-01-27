package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"html:target/report.html", "json:target/cucumber-report/cucumber.json"},
        features = "src/test/resources/Funcionalidades",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = "passos",
        publish = true,
        tags = "@PostUsuarioConstructor")

public class RunTest {
    @AfterClass
    public static void report() throws IOException {
        Runtime.getRuntime().exec("cmd.exe /c mvn cluecumber-report:reporting");
    }

}
