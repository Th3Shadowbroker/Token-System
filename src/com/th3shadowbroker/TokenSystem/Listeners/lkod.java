package com.th3shadowbroker.TokenSystem.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import com.th3shadowbroker.TokenSystem.Main.tsmain;

public class lkod implements Listener{

	private tsmain plugin;

	public lkod(tsmain plugin){
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerKilles(PlayerDeathEvent ev){
		try{
			Player p = ev.getEntity();
			Player k = ev.getEntity().getKiller();
			String pre = plugin.prefix;
			String dlm = plugin.convertVariables(plugin.getConfig().getString("Settings.options.killrewardMsg"),p,k);
			String kwm = plugin.convertVariables(plugin.getConfig().getString("Settings.options.deathlossMsg"),p,k);
			String broad = plugin.convertVariables(plugin.getConfig().getString("Settings.options.killBroadcast"),p,k);
			
			if(!(plugin.eco.hasAccount(p))){
				plugin.eco.createPlayerAccount(p);
			}
			
			if(!(plugin.eco.hasAccount(k))){
				plugin.eco.createPlayerAccount(k);
			}
			
			p.sendMessage(pre + kwm);
			k.sendMessage(pre + dlm);
			
			plugin.eco.depositPlayer(k, plugin.getConfig().getDouble("Settings.options.killreward"));
			plugin.eco.withdrawPlayer(p, plugin.getConfig().getDouble("Settings.options.deathloss"));
			
			ev.setDeathMessage(pre + broad);
			
			
		}catch(NullPointerException ex){
			System.out.println("NULLPOINTER-EXCEPTION");
		}
	}

}
