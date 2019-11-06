package AI.dtapijava.Enums;

public enum ConfigType {

    tableSize("tableSize");

    public static final String TAB_SIZE = "tableSize";

    private String configType;


    ConfigType(final String configType) {
        this.configType = configType;
    }

}
