package api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {        // the reason for ConfigManager is to read from properties file. Use chirags method I leant before

    private static ConfigManager manager;
    private static final Properties prop = new Properties();

    private ConfigManager() throws IOException {

        InputStream inputStream = ConfigManager.class.getResourceAsStream("../resource/config.properties");
        prop.load(inputStream);

    }

    // Making thread safe
    public static ConfigManager getInstance(){
        if (manager == null){
            synchronized (ConfigManager.class){
                try {
                    manager = new ConfigManager();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return manager;
    }

    public String getString(String key){
        return System.getProperty(key, prop.getProperty(key));
    }




}
