package enums;

public enum PropertiesSelect {

    NATIVE("src/native.properties"), WEB("src/web.properties");

    public String name;

    PropertiesSelect(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
