package com.th3shadowbroker.TokenSystem.Updater;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.th3shadowbroker.TokenSystem.Main.tsmain;

public class UpdaterMessage implements Listener{
	
	private tsmain plugin;

	public UpdaterMessage(tsmain plugin){
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerJoin(PlayerJoinEvent ev){
		if(plugin.getConfig().getBoolean("Settings.mainSettings.update") == true){
			if(!(plugin.getConfig().getBoolean("Settings.mainSettings.auto-updatedownload") == true)){
				Updater updater = new Updater(plugin, "http://dev.bukkit.org/bukkit-plugins/token-system/files.rss");
				updater.sendOpUpdateInformation(ev.getPlayer());
			}
		}
	}
}
