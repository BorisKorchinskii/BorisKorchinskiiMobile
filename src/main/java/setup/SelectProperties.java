package setup;

public enum SelectProperties {

    NATIVE("native"),
    WEB("web");

    private String currentAppType;

    SelectProperties(String current) {
        this.currentAppType = current;
    }

    public String getFileName() {
        return currentAppType + ".properties";
    }
}
