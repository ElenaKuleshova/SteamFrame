package framework;

import java.io.IOException;
import java.util.Properties;

public class LanguagePropertiesReader {
    protected Properties properties;

    public LanguagePropertiesReader(String steamLanguage){
        properties = new Properties ();
        try{
            properties.load(getClass().getClassLoader().getResourceAsStream("localization/"+steamLanguage+".properties"));
        } catch (IOException e){
            e.printStackTrace();}
    }
    public String getLangProperty(String key){
        return properties.getProperty(key);
    }
}
