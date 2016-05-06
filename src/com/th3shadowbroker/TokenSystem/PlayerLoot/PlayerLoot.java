package com.th3shadowbroker.TokenSystem.PlayerLoot;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.th3shadowbroker.TokenSystem.Main.tsmain;
import com.th3shadowbroker.TokenSystem.cC.CustomConfig;

public class PlayerLoot implements Listener{
	
	private tsmain plugin;
	
	private static CustomConfig cfg = new CustomConfig(Bukkit.getPluginManager().getPlugin("TokenSystem"),"playerLoot.yml");
	private static CustomConfig tmp = new CustomConfig(Bukkit.getPluginManager().getPlugin("TokenSystem"),"tmp.yml");

	public PlayerLoot(tsmain plugin){
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerGetLoot(PlayerDeathEvent ev){
		if(!(cfg.getConfig().getBoolean("PlayerLooting.Enabled") == false)){
			try{
				Player p = (Player) ev.getEntity();
				Player k = (Player) p.getKiller();
//				
//				if(!(tmp.getConfig().getLong(k.getUniqueId().toString() + ".cdt." + p.getUniqueId().toString()) >=
//					System.currentTimeMillis())){
					
					if(!(cfg.getConfig().getInt("PlayerLooting.MaxLootPerPlayer") == 0)){

						
						if(tmp.getConfig().getString(k.getUniqueId().toString() + ".kills." + p.getUniqueId().toString()) == null){
							
							tmp.getConfig().set(k.getUniqueId().toString() + ".kills." + p.getUniqueId(), 1);
							addLoot(p);
							if(cfg.getConfig().getBoolean("PlayerLooting.Chat.LootSpawned.Enabled") == true){
								String in = cfg.getConfig().getString("PlayerLooting.Chat.LootSpawned.Msg");
								String tmp = in;
								tmp = tmp.replaceAll("%l%", cfg.getConfig().getString("PlayerLooting.Loot.ItemName"));
								tmp = tmp.replaceAll("&", "§");
								tmp = tmp.replaceAll("%p%",p.getName());
								k.sendMessage(plugin.prefix + tmp);
							}
							
						}else{
							
							if(!(tmp.getConfig().getInt(k.getUniqueId().toString() + ".kills." + p.getUniqueId().toString()) >=
								 cfg.getConfig().getInt("PlayerLooting.MaxLootPerPlayer")
								 )){
								
								addLoot(p);
								
								int before = tmp.getConfig().getInt(k.getUniqueId().toString() + ".kills." + p.getUniqueId().toString());
								tmp.getConfig().set(k.getUniqueId().toString() + ".kills." + p.getUniqueId().toString(), before + 1);
								tmp.saveConfig();
								
								
								setLootCooldown(k,p.getUniqueId(), cfg.getConfig().getInt(k.getUniqueId().toString() + ".kills." + p.getUniqueId().toString()), 24);
								
								
								if(cfg.getConfig().getBoolean("PlayerLooting.Chat.LootSpawned.Enabled") == true){
									String in = cfg.getConfig().getString("PlayerLooting.Chat.LootSpawned.Msg");
									String tmp = in;
									tmp = tmp.replaceAll("%l%", cfg.getConfig().getString("PlayerLooting.Loot.ItemName"));
									tmp = tmp.replaceAll("&", "§");
									tmp = tmp.replaceAll("%p%",p.getName());
									k.sendMessage(plugin.prefix + tmp);
								}
								
							}else{
								if(cfg.getConfig().getBoolean("PlayerLooting.Chat.MaxKills.Enabled") == true){
									String tmp = cfg.getConfig().getString("PlayerLooting.Chat.MaxKills.Msg");
									tmp = tmp.replaceAll("&", "§");
									tmp = tmp.replaceAll("%p%",p.getName());
									k.sendMessage(plugin.prefix + tmp);
								}
							}
						}
						
					}else{
						
						addLoot(p);
						if(cfg.getConfig().getBoolean("PlayerLooting.Chat.LootSpawned.Enabled") == true){
							String in = cfg.getConfig().getString("PlayerLooting.Chat.LootSpawned.Msg");
							String tmp = in;
							tmp = tmp.replaceAll("%l%", cfg.getConfig().getString("PlayerLooting.Loot.ItemName"));
							tmp = tmp.replaceAll("&", "§");
							tmp = tmp.replaceAll("%p%",p.getName());
							k.sendMessage(plugin.prefix + tmp);
						}
						
					}
					
						
					tmp.saveConfig();
					
//				}else{
//					
//					if(cfg.getConfig().getBoolean("PlayerLooting.Chat.ResetMsg.Enabled") == true){
//						String in = cfg.getConfig().getString("PlayerLooting.Chat.ResetMsg.Msg");
//						String tmp = in;
//						tmp = tmp.replaceAll("&", "§");
//						tmp = tmp.replaceAll("%p%",p.getName());
//						k.sendMessage(plugin.prefix + tmp);
//					}
//					
//				}
				
			}catch(NullPointerException ex){
				//Nothing
			}
		}
	}

	private void addLoot(Player p){
		ItemStack i = cfg.getConfig().getItemStack("PlayerLooting.Loot.ItemMaterial");
		ItemMeta m = i.getItemMeta();
		int maxDrops = cfg.getConfig().getInt("PlayerLooting.Loot.Amount");
		
		m.setDisplayName(cfg.getConfig().getString("PlayerLooting.Loot.ItemName"));
		
		i.setItemMeta(m);
		
		for(int x = 0; x != maxDrops; x++){

			p.getWorld().dropItemNaturally(p.getLocation(), i);
		}
		
	}

	public static void genLootingConfig(){
		cfg.getConfig().addDefault("PlayerLooting.Enabled", false);
		cfg.getConfig().addDefault("PlayerLooting.Chat.MaxKills.Enabled", true);
		cfg.getConfig().addDefault("PlayerLooting.Chat.MaxKills.Msg", "&4You have reached the player-kill limit for&e %p% &4!");
		cfg.getConfig().addDefault("PlayerLooting.Chat.LootSpawned.Enabled", true);
		cfg.getConfig().addDefault("PlayerLooting.Chat.LootSpawned.Msg", "&2%p% dropped %l% !");
		cfg.getConfig().addDefault("PlayerLooting.Chat.ResetMsg.Enabled", false);
		cfg.getConfig().addDefault("PlayerLooting.Chat.ResetMsg.Msg", "&4Daily limit reached, please come back later ;)");
		cfg.getConfig().addDefault("PlayerLooting.MaxLootPerPlayer", 3);
		cfg.getConfig().addDefault("PlayerLooting.Loot.ResetAfter", 24);
		cfg.getConfig().addDefault("PlayerLooting.Loot.ItemName", "DefaultLoot");
		cfg.getConfig().addDefault("PlayerLooting.Loot.ItemMaterial", new ItemStack(Material.REDSTONE));
		cfg.getConfig().addDefault("PlayerLooting.Loot.Amount", 1);
		
		cfg.getConfig().options().header("Use essentials color-codes: http://ess.khhq.net/mc/ \n" +
										 "if MaxLootPerPlayer is 0 = no limit");
		cfg.getConfig().options().copyDefaults(true);
		cfg.saveConfig();
		
	}

	public static void reloadLootingConfig(){
		tmp.saveConfig();
		tmp.reloadConfig();
		cfg.saveConfig();
		cfg.reloadConfig();
	}

	public static void setLootCooldown(Player p,UUID uuidOfTarget,int Qvalue,int miliseconds){
		if(Qvalue == cfg.getConfig().getInt("PlayerLooting.MaxLootPerPlayer")){
			try{
				tmp.getConfig().set(getPlayerProfile(p,uuidOfTarget, DataType.COOLDOWNTIME), System.currentTimeMillis());
				tmp.saveConfig();
			}catch(NullPointerException ex){
				System.out.println("[TokenSystemCore] Coolddown-Exception");
			}
		}
	}

	
	private static String getPlayerProfile(Player p,UUID uuid, DataType RAW){
		if(RAW == DataType.RAW){
			return tmp.getConfig().getString(p.getUniqueId().toString());
		}else if(RAW == DataType.KILLS){
			return tmp.getConfig().getString(p.getUniqueId().toString() + ".kills." + uuid.toString());
		}else if(RAW == DataType.COOLDOWNTIME){
			return tmp.getConfig().getString(p.getUniqueId().toString() + ".cdt." + uuid.toString());
		}else{
			return "[TokenSystemCore] Invalid DataType !";
		}
	}

	public static String getCooldownTime(){
		return cfg.getConfig().getString("PlayerLooting.Loot.ResetAfter");
	}
}
