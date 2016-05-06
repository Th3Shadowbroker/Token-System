package com.th3shadowbroker.TokenSystem.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.th3shadowbroker.TokenSystem.PlayerLoot.PlayerLoot;
import com.th3shadowbroker.TokenSystem.Listeners.lkod;
import com.th3shadowbroker.TokenSystem.MobManager.mmkl;
import com.th3shadowbroker.TokenSystem.Updater.Updater;
import com.th3shadowbroker.TokenSystem.Updater.UpdaterMessage;
import com.th3shadowbroker.tokensystem.metrics.Metrics;

public class tsmain extends JavaPlugin{

	public String prefix = null;
	public String space = " ";
	public String cspace = "             " + space;
	public String cprefix = "[TokenSystem]" + space;
	public Economy eco = null;
	private File fLogFile = new File(getDataFolder(),"tokenlog.log");
	
	public void onEnable(){
		
		
		
		try {
	        Metrics metrics = new Metrics(this);
	        metrics.start();
	    } catch (IOException e) {
	        System.out.println(cprefix + "Metrics: Connection failed !");
	    }
		
		System.out.println(cprefix + "TokenSystem is loading");
		System.out.println("==================================");
		System.out.println(cspace + "Loading config...");
		genConfig();
		Player ep = null;
		prefix = convertVariables(this.getConfig().getString("Settings.mainSettings.prefix") + space, ep, ep);
		System.out.println(cspace + "Config loaded !");
		System.out.println(cspace + "Setup vault connection...");
		
		if(setupVaulConnection()){
			System.out.println(cspace + "Vault connected !");
		}else{
			System.out.println(cspace + "Vault disconnected !");
			this.getServer().getPluginManager().disablePlugin(this);
		}
		
		System.out.println(cspace + "Check for updates...");
		
		Updater upt = new Updater(this,"http://dev.bukkit.org/bukkit-plugins/token-system/files.rss"); 
				
		if(upt.updateAvailable()){
			System.out.println(cspace + "Update found !");
				try{
					if(!(this.getConfig().getBoolean("Settings.mainSettings.auto-updatedownload"))){
						System.out.println(cspace + "Auto update: DISABLED");
					}else{
						System.out.println("Updatesource set to " + upt.getDownloadURL());
						Player n = (Player) null;
						upt.startUpdateLoading(n);
						this.getServer().reload();
					}
				}catch(Exception ex){
					//Nothing
				}
		}else{
			System.out.println("No update found :)");
		}
		
		System.out.println("==================================");
		
		registerEvents();
		
		writeLogEntry("TokenSystem enabled !");
		
	}
	
	public void onDisable(){
		System.out.println(cprefix + "TokenSystem is unloading");
		System.out.println("==================================");
		System.out.println(cspace + "Saving config...");
		this.saveConfig();
		System.out.println(cspace + "Config saved !");
		System.out.println(cspace + "Vault disconnected !");
				
		writeLogEntry("TokenSystem disabled (server restart/reload ?) !");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		
		Player p = (Player)sender;
		
		if(sender instanceof Player){
			
			if(cmd.getName().equalsIgnoreCase("ts")){
				if(args.length == 0){
					p.sendMessage(prefix + ChatColor.RED + "Invalid arguments, use " + ChatColor.GOLD + "/ts <reload:info>" + ChatColor.RED + " !");
				}else
					if(args.length == 1){
						
							if(args[0].equalsIgnoreCase("reload")){
								if(p.hasPermission("tokensystem.admin")|p.isOp()){
									p.sendMessage(prefix + ChatColor.GREEN  + "Reloading...");
										try{
											this.saveConfig();
											this.reloadConfig();
											PlayerLoot.reloadLootingConfig();
											p.sendMessage(prefix + ChatColor.GREEN + "Reloaded !");
											writeLogEntry("Config reloaded by " + p.getName() + "(UUID: " + p.getUniqueId().toString() + ") !");
										}catch (Exception e){
											p.sendMessage(prefix + ChatColor.RED + "Something went wrong, please delete config and restart your server !");
											writeLogEntry("Config reloaded by " + p.getName() + "(UUID: " + p.getUniqueId().toString() + ") with this error \n" + e.getMessage());
										}
								}
							}else if(args[0].equalsIgnoreCase("info")){
								
								p.sendMessage(prefix + ChatColor.GOLD + "TokenSystem created by Th3Shadowbroker");
								p.sendMessage(cspace + ChatColor.AQUA + "Twitter: " + ChatColor.GOLD + "@FisJens");
								p.sendMessage(cspace + ChatColor.GOLD + "YouTube: http://" + ChatColor.RED + "You" + ChatColor.WHITE + "Tube" + ChatColor.GOLD + ".com/user/crafter629");
							
							}else if(args[0].equalsIgnoreCase("update")){
								
								Updater upt = new Updater(this, "http://dev.bukkit.org/bukkit-plugins/token-system/files.rss");
								upt.doAnUpdate(p);
								
							}else{
							p.sendMessage(prefix + ChatColor.RED + "Invalid arguments, use " + ChatColor.GOLD + "/ts <reload:info:update>" + ChatColor.RED + " !");
						}
						
				}else{
					p.sendMessage(prefix + ChatColor.RED + "Too many arguments !");
				}
			}
			
		}else{
			System.out.println("Use InGame commands !");
		}
		
		
		return false;
		
	}
	
	public void registerEvents(){
		
		try{
			this.getServer().getPluginManager().registerEvents(new lkod(this), this);
				if(this.getConfig().getBoolean("Settings.mainSettings.debug") == true){
					System.out.println("[TS@Debugger] LR: lkod >> Done !");
				}
			this.getServer().getPluginManager().registerEvents(new mmkl(this), this);
				if(this.getConfig().getBoolean("Settings.mainSettings.debug") == true){
					System.out.println("[TS@Debugger] LR: mmkl >> Done !");
				}
			this.getServer().getPluginManager().registerEvents(new PlayerLoot(this), this);
				if(this.getConfig().getBoolean("Settings.mainSettings.debug") == true){
					System.out.println("[TS@Debugger] LR: PlayerLoot >> Done !");
				}
			this.getServer().getPluginManager().registerEvents(new UpdaterMessage(this), this);
				if(this.getConfig().getBoolean("Settings.mainSettings.debug") == true){
					System.out.println("[TS@Debugger] LR: UpdaterMessage >> Done !");
				}
		}catch(Exception ex){
			System.out.println("[EX@REGISTER_EVENTS] Loading exception !");
			this.getServer().getPluginManager().disablePlugin(this);
		}
		
	}

	public void writeLogEntry(String what){
		if(this.getConfig().getBoolean("Settings.mainSettings.logging") == true){
			try {

				SimpleDateFormat formatter = new SimpleDateFormat ("dd.mm.yyyy 'at' HH:mm:ss");
				Date currentTime = new Date();
				FileWriter fw = new FileWriter(fLogFile);
				FileReader fr = new FileReader(fLogFile);
				BufferedReader br = new BufferedReader(fr);
				
				String fl;
	            String ofl = null;

				
				while ((fl = br.readLine()) != null) {
					for (int i = 0; i < fl.length(); i++) {
						ofl = ofl + fl;
					}
				}

				
				fw.write(fl + "<" + formatter.format(currentTime) + "> " + what + "\n");
				fr.close();
				fw.flush();
				fw.close();
			} catch (IOException e) {
				System.out.println(cprefix + "Couldn't create log-entry !");
			}
		}
	}
	
	private boolean setupVaulConnection(){
		
		if(this.getServer().getPluginManager().getPlugin("Vault") == null){
			return false;
		}
		
		RegisteredServiceProvider<Economy> rsps = this.getServer().getServicesManager().getRegistration(Economy.class);
		
		if(rsps == null){
			return false;
		}
		
		eco = rsps.getProvider();
				
		return (eco != null);
}
	
	public void genConfig(){
		
		
		
		String name = null;
		ArrayList<String> mobList = new ArrayList<String>();
		
		mobList.add("Bat");
		mobList.add("Blaze");
		mobList.add("Cave_Spider");
		mobList.add("Chicken");
		mobList.add("Cow");
		mobList.add("Creeper");
		mobList.add("Zombie");
		mobList.add("Enderman");
		mobList.add("Ghast");
		mobList.add("Giant");
		mobList.add("Horse");
		mobList.add("Iron_Golem");
		mobList.add("Magma_Cube");
		mobList.add("Ocelot");
		mobList.add("Pig");
		mobList.add("Pig_Zombie");
		mobList.add("Sheep");
		mobList.add("Silverfish");
		mobList.add("Skeleton");
		mobList.add("Slime");
		mobList.add("Snowman");
		mobList.add("Spider");
		mobList.add("Squid");
		mobList.add("Witch");
		mobList.add("Villager");
		mobList.add("Wolf");
		mobList.add("Wither");
		
		int max = mobList.size();
		
		
		
		this.getConfig().options().header("Variables: Killreward = %kw%  Deathloss = %dl%  Tokenname = %tn%  Playername = %p%  Killername = %k%  Mob-Killreward = %kmw% \n" + 
										  " Prefix-colorcodes: http://ess.khhq.net/mc/ \n" +
										  "IMPORTANT: Use # to reset all colorcodes to white !\n" +
										  "Logging isn't available for this version!");
		
		this.getConfig().addDefault("Settings.mainSettings.enabled", true);
		this.getConfig().addDefault("Settings.mainSettings.logging", false);
		this.getConfig().addDefault("Settings.mainSettings.debug", false);
		this.getConfig().addDefault("Settings.mainSettings.update", true);
		this.getConfig().addDefault("Settings.mainSettings.auto-updatedownload", false);
		this.getConfig().addDefault("Settings.mainSettings.prefix", "&e[TokenSystem]#");
		
		this.getConfig().addDefault("Settings.tokenSettings.tokenName", "Tokens");
		this.getConfig().addDefault("Settings.tokenSettings.startWith", 100.0);
		
		this.getConfig().addDefault("Settings.options.killreward", 5.0);
		this.getConfig().addDefault("Settings.options.deathloss", 10.0);
		this.getConfig().addDefault("Settings.options.killrewardMsg", "&2You get %kw% %tn% !");
		this.getConfig().addDefault("Settings.options.deathlossMsg", "&cYou lost %dl% %tn% !");
		this.getConfig().addDefault("Settings.options.killBroadcast", "#%k%&c killed #%p% &c!");
		this.getConfig().addDefault("Settings.options.killMob.enableMobkills", true);
		this.getConfig().addDefault("Settings.options.killMobMsg", "&2You get %kmw% !");
		
		for(int i = 0; i != max; i++){
			name = mobList.get(i);
			this.getConfig().addDefault("Mobs." + name.toUpperCase(), 5.0);
		}
		
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		
		
		mmkl.genLootingConfig();
		PlayerLoot.genLootingConfig();
		
	}
	
	public String convertVariables(String input, Player p, Player k){
		String tmp = input;
		
		try{
			if(tmp.contains("&")){
				tmp = tmp.replaceAll("&","§");
			}
			
			if(tmp.contains("#")){
				tmp = tmp.replaceAll("#", "§f");
			}
			
			if(tmp.contains("%kw%")){
				tmp = tmp.replaceAll("%kw%",this.getConfig().getString("Settings.options.killreward"));
			}
			
			if(tmp.contains("%dl%")){
				tmp = tmp.replaceAll("%dl%", this.getConfig().getString("Settings.options.deathloss"));
			}
			
			if(tmp.contains("%tn%")){
				tmp = tmp.replaceAll("%tn%",this.getConfig().getString("Settings.tokenSettings.tokenName"));
			}
			
			if(tmp.contains("%p%")){
				tmp = tmp.replaceAll("%p%",	p.getName());
			}
			
			if(tmp.contains("%k%")){
				tmp = tmp.replaceAll("%k%", k.getName());
			}
		}catch(NullPointerException ex){
			System.out.println("[TokenSystem-Core] Variable exception !");
	    }
		
		return tmp;
	}
	
	public boolean isConfigPathNull(String configPath){
		if(this.getConfig().getString(configPath) == null){
			return true;
		}else{
			return false;
		}
	}
	
	
}
