package com.monefy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigReader {
	
	private static Properties props = null;
    private static ConfigReader instance = null;
    private static final String DEFAULT_PROPERTIES = "config.properties";
    private static InputStream input = null;
    //private static final String CONFIG_PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator +"java" + File.separator + "resources" + File.separator;
    private static final String CONFIG_PATH = System.getProperty("user.dir") + File.separator;

    private ConfigReader() throws Exception {
        props = new Properties();
        try {
            if (Files.isReadable(Paths.get(CONFIG_PATH + DEFAULT_PROPERTIES))) {
            	String pathOfAbsolute = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
                String propertiesFilePath = CONFIG_PATH+DEFAULT_PROPERTIES;
                //propertiesFilePath = propertiesFilePath.replace("file:/", "").replace("/", "\\");
                Paths.get(new URI(pathOfAbsolute));
                input =  ClassLoader.getSystemResourceAsStream(propertiesFilePath);
               input = new FileInputStream(propertiesFilePath);
               props.load( input );
            } 
            else 
            {
                throw new IOException("Properties file does not exist");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static synchronized ConfigReader getInstance() throws Exception {
        if (instance == null) instance = new ConfigReader();
        return instance;
    }

    public String getValue(String key) {
        if (System.getProperty(key) != null) {
            return System.getProperty(key);
        } else if (props.containsKey(key)) {
            return props.getProperty(key);
        }
        return null;
    }


}

