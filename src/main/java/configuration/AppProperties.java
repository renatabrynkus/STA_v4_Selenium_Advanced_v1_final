package configuration;

import models.Browser;
import models.Environment;

import java.util.Map;

public class AppProperties {
    YamlReader yamlReader = new YamlReader();
    private Browser browser;
    private Environment environment;

    public AppProperties() {
        setBrowserProperties();
        setEnvironmentProperties();
    }

    public static AppProperties getInstance() {
        return AppProperties.AppPropertiesSingleton.INSTANCE;
    }

    private void setBrowserProperties() {
        browser = yamlReader.getConfig().getBrowser();
        Map<String, Object> browserProperties = browser.getBrowserProperties();
        for (Map.Entry entry : browserProperties.entrySet()) {
            System.setProperty(entry.getKey().toString(), entry.getValue().toString());
        }
    }

    private void setEnvironmentProperties() {
        environment = yamlReader.getConfig().getEnvironment();
        Map<String, Object> environmentProperties = environment.getEnvironmentProperties();
        for (Map.Entry entry : environmentProperties.entrySet()) {
            System.setProperty(entry.getKey().toString(), entry.getValue().toString());
        }
    }

    private static class AppPropertiesSingleton {
        private static final AppProperties INSTANCE = new AppProperties();
    }
}
