package runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

/**
 * Runner específico para ejecutar solo los tests de smoke
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@smoke")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = 
    "pretty," +
    "html:target/cucumber-reports/smoke-tests.html," +
    "json:target/cucumber-reports/smoke-tests.json")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "stepDefinitions")
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "src/test/resources/features")
@ConfigurationParameter(key = PLUGIN_PUBLISH_ENABLED_PROPERTY_NAME, value = "false")
public class SmokeTestRunner {
}
