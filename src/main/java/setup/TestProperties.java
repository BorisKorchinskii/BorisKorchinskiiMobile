package setup;

import enums.PropertiesSelect;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

class TestProperties {

    private Properties currentProps = new Properties();

    Properties getCurrentProps() throws IOException {
        FileInputStream in = new FileInputStream(String.valueOf(PropertiesSelect.NATIVE));
        currentProps.load(in);
        in.close();
        return currentProps;
    }

    String getProp(String propKey) throws IOException {
        if(!currentProps.containsKey(propKey)) currentProps = getCurrentProps();
        return currentProps.getProperty(propKey, null);
    }
}
