package runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

/**
 * Test Runner principal para ejecutar todas las pruebas de Sauce Demo
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = 
    "pretty," +
    "html:target/cucumber-reports/cucumber.html," +
    "json:target/cucumber-reports/cucumber.json," +
    "junit:target/cucumber-reports/cucumber.xml")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "stepDefinitions")
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "src/test/resources/features")
@ConfigurationParameter(key = PLUGIN_PUBLISH_ENABLED_PROPERTY_NAME, value = "false")
public class TestRunner {
}
