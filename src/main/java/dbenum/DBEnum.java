package dbenum;

import lombok.Getter;

@Getter
public enum DBEnum {
    H2 ("h2_config.json"),
    MYSQL("mySql_config.json");

    private final String nameOfFileConfig;

    DBEnum(String nameOfFileConfig) {
        this.nameOfFileConfig = nameOfFileConfig;
    }
}
