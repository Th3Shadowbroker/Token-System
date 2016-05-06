package com.th3shadowbroker.TokenSystem.cC;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public final class CustomConfig {
	private final String fileName;
    private final Plugin plugin;
    private File configFile;
    private FileConfiguration fileConfiguration;
 
    public CustomConfig(Plugin plugin, String fileName) {
    	this.plugin = plugin;
    	this.fileName = fileName;
    }
 
    public void reloadConfig() {
        if (configFile == null) {
        	File dataFolder = plugin.getDataFolder();
        	configFile = new File(dataFolder, fileName);
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(configFile);
 
        //InputStream defConfigStream = plugin.getResource(fileName);
        
        //if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(configFile);
            fileConfiguration.setDefaults(defConfig);
        //}
    }
    public FileConfiguration getConfig() {
    	if (fileConfiguration == null) {
    		this.reloadConfig();
    	}
        return fileConfiguration;
    }
 
    public void saveConfig() {
        if (fileConfiguration == null || configFile == null) {
            return;
        } else {
            try {
                getConfig().save(configFile);
            } catch (IOException e) {
            	return;
            }
        }
    }
 
    public void saveDefaultConfig() {
        if (!configFile.exists()) {       
            this.plugin.saveResource(fileName, false);
        }
    }
}