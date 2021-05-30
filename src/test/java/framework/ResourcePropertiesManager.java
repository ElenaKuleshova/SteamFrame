package framework;

import java.io.IOException;
import java.util.Properties;

public class ResourcePropertiesManager {
    protected Properties properties;
    public ResourcePropertiesManager(){
        properties = new Properties();
    }

    public ResourcePropertiesManager(String ResourceFileName){
        properties = new Properties ();
        try{
        properties.load(getClass().getClassLoader().getResourceAsStream(ResourceFileName));
    } catch (IOException e){
        e.printStackTrace();}
    }

    public String getProperty(String key){return properties.getProperty(key);}

}
