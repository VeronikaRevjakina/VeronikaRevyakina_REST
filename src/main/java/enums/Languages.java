package enums;

public enum Languages {
    EN("en"),
    RU("ru"),
    UK("uk");

    private final String lang;

    Languages(String lang) {
        this.lang = lang;
    }

    public String getLang() {
        return lang;
    }
}
