package setup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

class TestProperties {

    private static Properties currentProps;

    Properties getCurrentProps() throws IOException {
        return (currentProps != null) ? currentProps : readPropertiesFromFile();
    }

    public String getProp(String propKey) throws IOException {
        return getCurrentProps().getProperty(propKey, null);
    }

    private Properties readPropertiesFromFile() throws IOException {

        FileInputStream in = new FileInputStream(System.getProperty("property"));
        this.currentProps = new Properties();
        this.currentProps.load(in);
        in.close();
        return this.currentProps;
    }
}
