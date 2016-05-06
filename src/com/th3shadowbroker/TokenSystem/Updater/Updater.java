package com.th3shadowbroker.TokenSystem.Updater;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import javax.xml.parsers.DocumentBuilderFactory;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.th3shadowbroker.TokenSystem.Main.tsmain;

public class Updater implements Listener{

	private tsmain plugin;
	private URL feed;
	
	private String version;
	private String link;
	
	public Updater(tsmain plugin,String url){
		this.plugin = plugin;
		
		try {
			this.feed = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}

	public boolean updateAvailable(){
		
		try {
			InputStream In = feed.openConnection().getInputStream();
			Document xmlDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(In);
			
			Node latestFile = xmlDoc.getElementsByTagName("item").item(0);
			NodeList children = latestFile.getChildNodes();
			
			version = children.item(1).getTextContent().replaceAll("[a-zA-Z ]", "");
			link = children.item(3).getTextContent();
			
			if(! (version.equals(plugin.getDescription().getVersion()) )){
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public String getDlVersion(){
		return version;
	}
	
	public String getDownloadURL(){
		return link;
	}

	public void sendOpUpdateInformation(Player p){
		if(updateAvailable() && p.isOp()){
			p.sendMessage(plugin.prefix + "§eUpdate found !");
			p.sendMessage(plugin.prefix + "§eType §b/ts update §efor autoupdate.");
		}
	}
	
	
	
	public void doAnUpdate(Player p){
		try{
			if(updateAvailable()){
				startUpdateLoading(p);
			}else{
				p.sendMessage(plugin.prefix + "§2 No update found :)");
			}
		}catch(Exception ex){
			p.sendMessage(plugin.prefix + "§4Error by updating §eTokenSystem §b[See console]");
		}
	}
	
	 public static File dataFolder = new File("plugins/");
	public void startUpdateLoading(Player p){
			
			try{
			String name = "TokenSystem";
			URL website = new URL(link);
			
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			
			File dataFile = new File(dataFolder.getPath() + File.separator + name + ".jar");
			@SuppressWarnings("resource")
			FileOutputStream fos = new FileOutputStream(dataFile);
			
		
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			
			System.out.print(plugin.cprefix + "Updated to version " + version);
			rbc.close();
			
			if(!(p == null)){
				p.sendMessage(plugin.prefix + "§2Update done !");
			}else{
				System.out.println(plugin.cspace + "Update done !");
			}
			
			}catch(Exception ex){
				//Nothing
			}
			
		}
}
