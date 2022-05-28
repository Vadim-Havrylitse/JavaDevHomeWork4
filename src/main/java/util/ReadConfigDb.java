package util;

import com.google.gson.Gson;
import dbenum.DBEnum;
import lombok.Getter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

@Getter
public final class ReadConfigDb <T> {
    private final String nameOfFileConfig;

    public ReadConfigDb(DBEnum db) {
        this.nameOfFileConfig = db.getNameOfFileConfig();
    }

    public Object getObjectFromConfigFile(T element){
        String fileStringJson = readFileAndReturnString(nameOfFileConfig);
        Gson gson = new Gson();
        return gson.fromJson(fileStringJson, element.getClass());
    }

    private String readFileAndReturnString(String fileName){
        URL resource = ReadConfigDb.class.getClassLoader().getResource(fileName);
        String fileStringJson = null;
        try {
            if (resource != null) {
                fileStringJson = Files.readString(Path.of(resource.toURI()));
            }
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
        }
        return fileStringJson;
    }

}
