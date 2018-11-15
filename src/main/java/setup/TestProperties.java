package setup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
    Properties currentProps = new Properties();
    String currentPropertyFile;
    String propLocation = "./src/test/resources/";

    protected void setPropertyFile(SelectProperties propertyFile) {
        currentPropertyFile = propertyFile.getFileName();
    }

    Properties getCurrentProps() throws IOException {
        //FileInputStream in = new FileInputStream(System.getProperty(propLocation) + propName);
        FileInputStream in = new FileInputStream(propLocation + currentPropertyFile);
        currentProps.load(in);
        in.close();
        return currentProps;
    }

    protected String getProp(String propKey) throws IOException {
        if (!currentProps.containsKey(propKey)) currentProps = getCurrentProps();
        return currentProps.getProperty(propKey, null);
    }
}
