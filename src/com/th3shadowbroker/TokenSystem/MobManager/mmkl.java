package com.th3shadowbroker.TokenSystem.MobManager;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.th3shadowbroker.TokenSystem.Main.tsmain;
import com.th3shadowbroker.TokenSystem.cC.CustomConfig;

public class mmkl implements Listener{

	private tsmain plugin;
	
	private static CustomConfig cfg = new CustomConfig(Bukkit.getPluginManager().getPlugin("TokenSystem"), "loot.yml");

	public mmkl(tsmain plugin){
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerKillMobs(EntityDeathEvent ev){
	
		try{
			LivingEntity m = ev.getEntity();
			Player p = ev.getEntity().getKiller();
			String msg = plugin.getConfig().getString("Settings.options.killMobMsg");
			
			if(plugin.getConfig().getBoolean("Settings.options.killMob.enableMobkills") == true){
				
				if(msg.contains("&")){
					msg = msg.replaceAll("&", "§");
				}
				if(msg.contains("#")){
					msg = msg.replaceAll("#", "§f");
				}
				if(m.getType() == EntityType.BAT){
					addReward(p, plugin.getConfig().getDouble("Mobs." + m.getType().name()));
					/////////////////////////////////////////////////////////////////////////
					addLoot(m);
					/////////////////////////////////////////////////////////////////////////
					if(msg.contains("&")){
						msg = msg.replaceAll("&", "§");
					}
					if(msg.contains("#")){
						msg = msg.replaceAll("#", "§f");
					}
					if(msg.contains("%kmw%")){
						String sendThis = msg.replaceAll("%kmw%", plugin.getConfig().getString("Mobs." + m.getType().name()) + " " +
						plugin.getConfig().getString("Settings.tokenSettings.tokenName"));
						p.sendMessage(plugin.prefix + sendThis);
					}else{
						p.sendMessage(plugin.prefix + msg);
					}
				}else if(m.getType() == EntityType.BLAZE){
					addReward(p, plugin.getConfig().getDouble("Mobs." + m.getType().name()));
					/////////////////////////////////////////////////////////////////////////
					addLoot(m);
					/////////////////////////////////////////////////////////////////////////
					if(msg.contains("&")){
						msg = msg.replaceAll("&", "§");
					}
					if(msg.contains("#")){
						msg = msg.replaceAll("#", "§f");
					}
					if(msg.contains("%kmw%")){
						String sendThis = msg.replaceAll("%kmw%", plugin.getConfig().getString("Mobs." + m.getType().name()) + " " +
						plugin.getConfig().getString("Settings.tokenSettings.tokenName"));
						p.sendMessage(plugin.prefix + sendThis);
					}else{
						p.sendMessage(plugin.prefix + msg);
					}
				}else if(m.getType() == EntityType.CAVE_SPIDER){
					addReward(p, plugin.getConfig().getDouble("Mobs." + m.getType().name()));
					/////////////////////////////////////////////////////////////////////////
					addLoot(m);
					/////////////////////////////////////////////////////////////////////////
					if(msg.contains("&")){
						msg = msg.replaceAll("&", "§");
					}
					if(msg.contains("#")){
						msg = msg.replaceAll("#", "§f");
					}
					if(msg.contains("%kmw%")){
						String sendThis = msg.replaceAll("%kmw%", plugin.getConfig().getString("Mobs." + m.getType().name()) + " " +
						plugin.getConfig().getString("Settings.tokenSettings.tokenName"));
						p.sendMessage(plugin.prefix + sendThis);
					}else{
						p.sendMessage(plugin.prefix + msg);
					}
				}else if(m.getType() == EntityType.CHICKEN){
					addReward(p, plugin.getConfig().getDouble("Mobs." + m.getType().name()));
					/////////////////////////////////////////////////////////////////////////
					addLoot(m);
					/////////////////////////////////////////////////////////////////////////
					if(msg.contains("&")){
						msg = msg.replaceAll("&", "§");
					}
					if(msg.contains("#")){
						msg = msg.replaceAll("#", "§f");
					}
					if(msg.contains("%kmw%")){
						String sendThis = msg.replaceAll("%kmw%", plugin.getConfig().getString("Mobs." + m.getType().name()) + " " +
						plugin.getConfig().getString("Settings.tokenSettings.tokenName"));
						p.sendMessage(plugin.prefix + sendThis);
					}else{
						p.sendMessage(plugin.prefix + msg);
					}
				}else if(m.getType() == EntityType.COW){
					addReward(p, plugin.getConfig().getDouble("Mobs." + m.getType().name()));
					/////////////////////////////////////////////////////////////////////////
					addLoot(m);
					/////////////////////////////////////////////////////////////////////////
					if(msg.contains("&")){
						msg = msg.replaceAll("&", "§");
					}
					if(msg.contains("#")){
						msg = msg.replaceAll("#", "§f");
					}
					if(msg.contains("%kmw%")){
						String sendThis = msg.replaceAll("%kmw%", plugin.getConfig().getString("Mobs." + m.getType().name()) + " " +
						plugin.getConfig().getString("Settings.tokenSettings.tokenName"));
						p.sendMessage(plugin.prefix + sendThis);
					}else{
						p.sendMessage(plugin.prefix + msg);
					}
				}else if(m.getType() == EntityType.CREEPER){
					addReward(p, plugin.getConfig().getDouble("Mobs." + m.getType().name()));
					/////////////////////////////////////////////////////////////////////////
					addLoot(m);
					/////////////////////////////////////////////////////////////////////////
					if(msg.contains("&")){
						msg = msg.replaceAll("&", "§");
					}
					if(msg.contains("#")){
						msg = msg.replaceAll("#", "§f");
					}
					if(msg.contains("%kmw%")){
						String sendThis = msg.replaceAll("%kmw%", plugin.getConfig().getString("Mobs." + m.getType().name()) + " " +
						plugin.getConfig().getString("Settings.tokenSettings.tokenName"));
						p.sendMessage(plugin.prefix + sendThis);
					}else{
						p.sendMessage(plugin.prefix + msg);
					}
				}else if(m.getType() == EntityType.ZOMBIE){
					addReward(p, plugin.getConfig().getDouble("Mobs." + m.getType().name()));
					/////////////////////////////////////////////////////////////////////////
					addLoot(m);
					/////////////////////////////////////////////////////////////////////////
					if(msg.contains("&")){
						msg = msg.replaceAll("&", "§");
					}
					if(msg.contains("#")){
						msg = msg.replaceAll("#", "§f");
					}
					if(msg.contains("%kmw%")){
						String sendThis = msg.replaceAll("%kmw%", plugin.getConfig().getString("Mobs." + m.getType().name()) + " " +
						plugin.getConfig().getString("Settings.tokenSettings.tokenName"));
						p.sendMessage(plugin.prefix + sendThis);
					}else{
						p.sendMessage(plugin.prefix + msg);
					}
				}else if(m.getType() == EntityType.ENDERMAN){
					addReward(p, plugin.getConfig().getDouble("Mobs." + m.getType().name()));
					/////////////////////////////////////////////////////////////////////////
					addLoot(m);
					/////////////////////////////////////////////////////////////////////////
					if(msg.contains("&")){
						msg = msg.replaceAll("&", "§");
					}
					if(msg.contains("#")){
						msg = msg.replaceAll("#", "§f");
					}
					if(msg.contains("%kmw%")){
						String sendThis = msg.replaceAll("%kmw%", plugin.getConfig().getString("Mobs." + m.getType().name()) + " " +
						plugin.getConfig().getString("Settings.tokenSettings.tokenName"));
						p.sendMessage(plugin.prefix + sendThis);
					}else{
						p.sendMessage(plugin.prefix + msg);
					}
				}else if(m.getType() == EntityType.GHAST){
					addReward(p, plugin.getConfig().getDouble("Mobs." + m.getType().name()));
					/////////////////////////////////////////////////////////////////////////
					addLoot(m);
					/////////////////////////////////////////////////////////////////////////
					if(msg.contains("&")){
						msg = msg.replaceAll("&", "§");
					}
					if(msg.contains("#")){
						msg = msg.replaceAll("#", "§f");
					}
					if(msg.contains("%kmw%")){
						String sendThis = msg.replaceAll("%kmw%", plugin.getConfig().getString("Mobs." + m.getType().name()) + " " +
						plugin.getConfig().getString("Settings.tokenSettings.tokenName"));
						p.sendMessage(plugin.prefix + sendThis);
					}else{
						p.sendMessage(plugin.prefix + msg);
					}
				}else if(m.getType() == EntityType.GIANT){
					addReward(p, plugin.getConfig().getDouble("Mobs." + m.getType().name()));
					/////////////////////////////////////////////////////////////////////////
					addLoot(m);
					/////////////////////////////////////////////////////////////////////////
					if(msg.contains("&")){
						msg = msg.replaceAll("&", "§");
					}
					if(msg.contains("#")){
						msg = msg.replaceAll("#", "§f");
					}
					if(msg.contains("%kmw%")){
						String sendThis = msg.replaceAll("%kmw%", plugin.getConfig().getString("Mobs." + m.getType().name()) + " " +
						plugin.getConfig().getString("Settings.tokenSettings.tokenName"));
						p.sendMessage(plugin.prefix + sendThis);
					}else{
						p.sendMessage(plugin.prefix + msg);
					}
				}else if(m.getType() == EntityType.HORSE){
					addReward(p, plugin.getConfig().getDouble("Mobs." + m.getType().name()));
					/////////////////////////////////////////////////////////////////////////
					addLoot(m);
					/////////////////////////////////////////////////////////////////////////
					if(msg.contains("&")){
						msg = msg.replaceAll("&", "§");
					}
					if(msg.contains("#")){
						msg = msg.replaceAll("#", "§f");
					}
					if(msg.contains("%kmw%")){
						String sendThis = msg.replaceAll("%kmw%", plugin.getConfig().getString("Mobs." + m.getType().name()) + " " +
						plugin.getConfig().getString("Settings.tokenSettings.tokenName"));
						p.sendMessage(plugin.prefix + sendThis);
					}else{
						p.sendMessage(plugin.prefix + msg);
					}
				}else if(m.getType() == EntityType.IRON_GOLEM){
					addReward(p, plugin.getConfig().getDouble("Mobs." + m.getType().name()));
					/////////////////////////////////////////////////////////////////////////
					addLoot(m);
					/////////////////////////////////////////////////////////////////////////
					if(msg.contains("&")){
						msg = msg.replaceAll("&", "§");
					}
					if(msg.contains("#")){
						msg = msg.replaceAll("#", "§f");
					}
					if(msg.contains("%kmw%")){
						String sendThis = msg.replaceAll("%kmw%", plugin.getConfig().getString("Mobs." + m.getType().name()) + " " +
						plugin.getConfig().getString("Settings.tokenSettings.tokenName"));
						p.sendMessage(plugin.prefix + sendThis);
					}else{
						p.sendMessage(plugin.prefix + msg);
					}
				}else if(m.getType() == EntityType.MAGMA_CUBE){
					addReward(p, plugin.getConfig().getDouble("Mobs." + m.getType().name()));
					/////////////////////////////////////////////////////////////////////////
					addLoot(m);
					/////////////////////////////////////////////////////////////////////////
					if(msg.contains("&")){
						msg = msg.replaceAll("&", "§");
					}
					if(msg.contains("#")){
						msg = msg.replaceAll("#", "§f");
					}
					if(msg.contains("%kmw%")){
						String sendThis = msg.replaceAll("%kmw%", plugin.getConfig().getString("Mobs." + m.getType().name()) + " " +
						plugin.getConfig().getString("Settings.tokenSettings.tokenName"));
						p.sendMessage(plugin.prefix + sendThis);
					}else{
						p.sendMessage(plugin.prefix + msg);
					}
				}else if(m.getType() == EntityType.OCELOT){
					addReward(p, plugin.getConfig().getDouble("Mobs." + m.getType().name()));
					/////////////////////////////////////////////////////////////////////////
					addLoot(m);
					/////////////////////////////////////////////////////////////////////////
					if(msg.contains("&")){
						msg = msg.replaceAll("&", "§");
					}
					if(msg.contains("#")){
						msg = msg.replaceAll("#", "§f");
					}
					if(msg.contains("%kmw%")){
						String sendThis = msg.replaceAll("%kmw%", plugin.getConfig().getString("Mobs." + m.getType().name()) + " " +
						plugin.getConfig().getString("Settings.tokenSettings.tokenName"));
						p.sendMessage(plugin.prefix + sendThis);
					}else{
						p.sendMessage(plugin.prefix + msg);
					}
				}else if(m.getType() == EntityType.PIG){
					addReward(p, plugin.getConfig().getDouble("Mobs." + m.getType().name()));
					/////////////////////////////////////////////////////////////////////////
					addLoot(m);
					/////////////////////////////////////////////////////////////////////////
					if(msg.contains("&")){
						msg = msg.replaceAll("&", "§");
					}
					if(msg.contains("#")){
						msg = msg.replaceAll("#", "§f");
					}
					if(msg.contains("%kmw%")){
						String sendThis = msg.replaceAll("%kmw%", plugin.getConfig().getString("Mobs." + m.getType().name()) + " " +
						plugin.getConfig().getString("Settings.tokenSettings.tokenName"));
						p.sendMessage(plugin.prefix + sendThis);
					}else{
						p.sendMessage(plugin.prefix + msg);
					}
				}else if(m.getType() == EntityType.PIG_ZOMBIE){
					addReward(p, plugin.getConfig().getDouble("Mobs." + m.getType().name()));
					/////////////////////////////////////////////////////////////////////////
					addLoot(m);
					/////////////////////////////////////////////////////////////////////////
					if(msg.contains("&")){
						msg = msg.replaceAll("&", "§");
					}
					if(msg.contains("#")){
						msg = msg.replaceAll("#", "§f");
					}
					if(msg.contains("%kmw%")){
						String sendThis = msg.replaceAll("%kmw%", plugin.getConfig().getString("Mobs." + m.getType().name()) + " " +
						plugin.getConfig().getString("Settings.tokenSettings.tokenName"));
						p.sendMessage(plugin.prefix + sendThis);
					}else{
						p.sendMessage(plugin.prefix + msg);
					}
				}else if(m.getType() == EntityType.SHEEP){
					addReward(p, plugin.getConfig().getDouble("Mobs." + m.getType().name()));
					/////////////////////////////////////////////////////////////////////////
					addLoot(m);
					/////////////////////////////////////////////////////////////////////////
					if(msg.contains("&")){
						msg = msg.replaceAll("&", "§");
					}
					if(msg.contains("#")){
						msg = msg.replaceAll("#", "§f");
					}
					if(msg.contains("%kmw%")){
						String sendThis = msg.replaceAll("%kmw%", plugin.getConfig().getString("Mobs." + m.getType().name()) + " " +
						plugin.getConfig().getString("Settings.tokenSettings.tokenName"));
						p.sendMessage(plugin.prefix + sendThis);
					}else{
						p.sendMessage(plugin.prefix + msg);
					}
				}else if(m.getType() == EntityType.SILVERFISH){
					addReward(p, plugin.getConfig().getDouble("Mobs." + m.getType().name()));
					/////////////////////////////////////////////////////////////////////////
					addLoot(m);
					/////////////////////////////////////////////////////////////////////////
					if(msg.contains("&")){
						msg = msg.replaceAll("&", "§");
					}
					if(msg.contains("#")){
						msg = msg.replaceAll("#", "§f");
					}
					if(msg.contains("%kmw%")){
						String sendThis = msg.replaceAll("%kmw%", plugin.getConfig().getString("Mobs." + m.getType().name()) + " " +
						plugin.getConfig().getString("Settings.tokenSettings.tokenName"));
						p.sendMessage(plugin.prefix + sendThis);
					}else{
						p.sendMessage(plugin.prefix + msg);
					}
				}else if(m.getType() == EntityType.SKELETON){
					addReward(p, plugin.getConfig().getDouble("Mobs." + m.getType().name()));
					/////////////////////////////////////////////////////////////////////////
					addLoot(m);
					/////////////////////////////////////////////////////////////////////////
					if(msg.contains("&")){
						msg = msg.replaceAll("&", "§");
					}
					if(msg.contains("#")){
						msg = msg.replaceAll("#", "§f");
					}
					if(msg.contains("%kmw%")){
						String sendThis = msg.replaceAll("%kmw%", plugin.getConfig().getString("Mobs." + m.getType().name()) + " " +
						plugin.getConfig().getString("Settings.tokenSettings.tokenName"));
						p.sendMessage(plugin.prefix + sendThis);
					}else{
						p.sendMessage(plugin.prefix + msg);
					}
				}else if(m.getType() == EntityType.SLIME){
					addReward(p, plugin.getConfig().getDouble("Mobs." + m.getType().name()));
					/////////////////////////////////////////////////////////////////////////
					addLoot(m);
					/////////////////////////////////////////////////////////////////////////
					if(msg.contains("&")){
						msg = msg.replaceAll("&", "§");
					}
					if(msg.contains("#")){
						msg = msg.replaceAll("#", "§f");
					}
					if(msg.contains("%kmw%")){
						String sendThis = msg.replaceAll("%kmw%", plugin.getConfig().getString("Mobs." + m.getType().name()) + " " +
						plugin.getConfig().getString("Settings.tokenSettings.tokenName"));
						p.sendMessage(plugin.prefix + sendThis);
					}else{
						p.sendMessage(plugin.prefix + msg);
					}
				}else if(m.getType() == EntityType.SNOWMAN){
					addReward(p, plugin.getConfig().getDouble("Mobs." + m.getType().name()));
					/////////////////////////////////////////////////////////////////////////
					addLoot(m);
					/////////////////////////////////////////////////////////////////////////
					if(msg.contains("&")){
						msg = msg.replaceAll("&", "§");
					}
					if(msg.contains("#")){
						msg = msg.replaceAll("#", "§f");
					}
					if(msg.contains("%kmw%")){
						String sendThis = msg.replaceAll("%kmw%", plugin.getConfig().getString("Mobs." + m.getType().name()) + " " +
						plugin.getConfig().getString("Settings.tokenSettings.tokenName"));
						p.sendMessage(plugin.prefix + sendThis);
					}else{
						p.sendMessage(plugin.prefix + msg);
					}
				}else if(m.getType() == EntityType.SPIDER){
					addReward(p, plugin.getConfig().getDouble("Mobs." + m.getType().name()));
					/////////////////////////////////////////////////////////////////////////
					addLoot(m);
					/////////////////////////////////////////////////////////////////////////
					if(msg.contains("&")){
						msg = msg.replaceAll("&", "§");
					}
					if(msg.contains("#")){
						msg = msg.replaceAll("#", "§f");
					}
					if(msg.contains("%kmw%")){
						String sendThis = msg.replaceAll("%kmw%", plugin.getConfig().getString("Mobs." + m.getType().name()) + " " +
						plugin.getConfig().getString("Settings.tokenSettings.tokenName"));
						p.sendMessage(plugin.prefix + sendThis);
					}else{
						p.sendMessage(plugin.prefix + msg);
					}
				}else if(m.getType() == EntityType.SQUID){
					addReward(p, plugin.getConfig().getDouble("Mobs." + m.getType().name()));
					/////////////////////////////////////////////////////////////////////////
					addLoot(m);
					/////////////////////////////////////////////////////////////////////////
					if(msg.contains("&")){
						msg = msg.replaceAll("&", "§");
					}
					if(msg.contains("#")){
						msg = msg.replaceAll("#", "§f");
					}
					if(msg.contains("%kmw%")){
						String sendThis = msg.replaceAll("%kmw%", plugin.getConfig().getString("Mobs." + m.getType().name()) + " " +
						plugin.getConfig().getString("Settings.tokenSettings.tokenName"));
						p.sendMessage(plugin.prefix + sendThis);
					}else{
						p.sendMessage(plugin.prefix + msg);
					}
				}else if(m.getType() == EntityType.WITCH){
					addReward(p, plugin.getConfig().getDouble("Mobs." + m.getType().name()));
					/////////////////////////////////////////////////////////////////////////
					addLoot(m);
					/////////////////////////////////////////////////////////////////////////
					if(msg.contains("&")){
						msg = msg.replaceAll("&", "§");
					}
					if(msg.contains("#")){
						msg = msg.replaceAll("#", "§f");
					}
					if(msg.contains("%kmw%")){
						String sendThis = msg.replaceAll("%kmw%", plugin.getConfig().getString("Mobs." + m.getType().name()) + " " +
						plugin.getConfig().getString("Settings.tokenSettings.tokenName"));
						p.sendMessage(plugin.prefix + sendThis);
					}else{
						p.sendMessage(plugin.prefix + msg);
					}
				}else if(m.getType() == EntityType.VILLAGER){
					addReward(p, plugin.getConfig().getDouble("Mobs." + m.getType().name()));
					/////////////////////////////////////////////////////////////////////////
					addLoot(m);
					/////////////////////////////////////////////////////////////////////////
					if(msg.contains("&")){
						msg = msg.replaceAll("&", "§");
					}
					if(msg.contains("#")){
						msg = msg.replaceAll("#", "§f");
					}
					if(msg.contains("%kmw%")){
						String sendThis = msg.replaceAll("%kmw%", plugin.getConfig().getString("Mobs." + m.getType().name()) + " " +
						plugin.getConfig().getString("Settings.tokenSettings.tokenName"));
						p.sendMessage(plugin.prefix + sendThis);
					}else{
						p.sendMessage(plugin.prefix + msg);
					}
				}else if(m.getType() == EntityType.WOLF){
					addReward(p, plugin.getConfig().getDouble("Mobs." + m.getType().name()));
					/////////////////////////////////////////////////////////////////////////
					addLoot(m);
					/////////////////////////////////////////////////////////////////////////
					if(msg.contains("&")){
						msg = msg.replaceAll("&", "§");
					}
					if(msg.contains("#")){
						msg = msg.replaceAll("#", "§f");
					}
					if(msg.contains("%kmw%")){
						String sendThis = msg.replaceAll("%kmw%", plugin.getConfig().getString("Mobs." + m.getType().name()) + " " +
						plugin.getConfig().getString("Settings.tokenSettings.tokenName"));
						p.sendMessage(plugin.prefix + sendThis);
					}else{
						p.sendMessage(plugin.prefix + msg);
					}
				}else if(m.getType() == EntityType.WITHER){
					addReward(p, plugin.getConfig().getDouble("Mobs." + m.getType().name()));
					/////////////////////////////////////////////////////////////////////////
					addLoot(m);
					/////////////////////////////////////////////////////////////////////////
					if(msg.contains("&")){
						msg = msg.replaceAll("&", "§");
					}
					if(msg.contains("#")){
						msg = msg.replaceAll("#", "§f");
					}
					if(msg.contains("%kmw%")){
						String sendThis = msg.replaceAll("%kmw%", plugin.getConfig().getString("Mobs." + m.getType().name()) + " " +
						plugin.getConfig().getString("Settings.tokenSettings.tokenName"));
						p.sendMessage(plugin.prefix + sendThis);
					}else{
						p.sendMessage(plugin.prefix + msg);
					}
				}else{
					//nothing
				}
				
			
				
			}
		}catch(NullPointerException ex){
			//Nothing
		}
		
	}
	
	public void addReward(Player p, double amount){
		plugin.eco.depositPlayer(p, amount);
	}

	public void addLoot(LivingEntity le){
		try{
		
				if(cfg.getConfig().getBoolean("MobLoot.enabled") == true){
					LivingEntity p = le;
					if(!(cfg.getConfig().getBoolean("Mobs." + le.getType().toString().toUpperCase() + ".Enabled") == false)){
						ItemStack is = new ItemStack(cfg.getConfig().getItemStack("Mobs." + le.getType().toString().toUpperCase() + ".LootMaterial"));
						ItemMeta im = is.getItemMeta();
						im.setDisplayName(cfg.getConfig().getString("Mobs." + le.getType().toString().toUpperCase() + ".LootName").replaceAll("&", "§"));
						int amount = cfg.getConfig().getInt("Mobs." + le.getType().toString().toUpperCase() + ".Amount");
						is.setItemMeta(im);
						
						for(int i = 0; i != amount; i++){
							World w = p.getWorld();
							w.dropItemNaturally(p.getLocation(), is);
						}
					}
				}
			
			
		}catch(NullPointerException ex){
			//Nothing
		}
		
	}

	public static void genLootingConfig(){
		
		cfg.getConfig().addDefault("MobLoot.enabled", true);
		
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
		
		for(int i = 0; i != max; i++){
			name = mobList.get(i);
			cfg.getConfig().addDefault("Mobs." + name.toUpperCase() + ".Enabled", false);
			cfg.getConfig().addDefault("Mobs." + name.toUpperCase() + ".LootName", "DefaultLoot");
			cfg.getConfig().addDefault("Mobs." + name.toUpperCase() + ".LootMaterial", new ItemStack(Material.REDSTONE));
			cfg.getConfig().addDefault("Mobs." + name.toUpperCase() + ".Amount", 1);
		}
		
		cfg.getConfig().options().copyDefaults(true);
		cfg.getConfig().options().header("See wiki-page for more informations.  http://dev.bukkit.org/bukkit-plugins/token-system/pages/mob-loot-system-setup/");
		cfg.saveConfig();
	}
}
