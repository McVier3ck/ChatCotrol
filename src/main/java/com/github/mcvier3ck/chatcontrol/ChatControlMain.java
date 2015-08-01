package com.github.mcvier3ck.chatcontrol;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.mcvier3ck.command.Commands;
import com.github.mcvier3ck.listener.ChatListener;
import com.github.mcvier3ck.utils.MySQL;

import net.md_5.bungee.api.ChatColor;


public class ChatControlMain extends JavaPlugin{

	public static ChatControlMain chatfilter;
	public static MySQL mysql = null;
	public static String Prefix = ChatColor.DARK_AQUA + "[ChatFilter] ";
	
	
	
	@Override
	public void onDisable() {
		//Close MySQL Connection because not more need
		mysql.closeConnection();
	}
	
	
	
	@Override
	public void onEnable() {
		//Creates config if need
		Creatconfig();
		
		//Load Config and create MySQL Instance
		Config();
		//Set the Instace to this class
		chatfilter = this;
		
		//Init Events
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new ChatListener(), this);
		
		//Init Commands
		this.getCommand("chatcontrol").setExecutor(new Commands());
		
		
		//Creates MySQL Table if need
		mysql.queryUpdate("CREATE TABLE IF NOT EXISTS censor (word VARCHAR(120) NOT NULL ,forAll SMALLINT (1) NOT NULL DEFAULT 0 ,addedBy VARCHAR(90) NOT NULL,PRIMARY KEY (word))ENGINE = InnoDB");
	}
	
	
	
	public static ChatControlMain getInstance() {
		return chatfilter;
	}
	
	
	
	public void Config() {
		//Get connection information for mysql
		String host = this.getConfig().getString("mysql.host");
		int port = this.getConfig().getInt("mysql.port");
		String user = this.getConfig().getString("mysql.user");
		String password = this.getConfig().getString("mysql.password");
		String database = this.getConfig().getString("mysql.database");
		
		//Connect to MySQL Server
		mysql = new MySQL(host, port, user, password, database);
		
	}
	
	
	public void Creatconfig() {
		//adding dafault if empty
		this.getConfig().addDefault("mysql.host", "");
		this.getConfig().addDefault("mysql.port", "");
		this.getConfig().addDefault("mysql.user", "");
		this.getConfig().addDefault("mysql.password", "");
		this.getConfig().addDefault("mysql.database", "");
		
		this.getConfig().addDefault("chat.format", "{prefix} {player} {suffix}: {msg}");
		this.getConfig().addDefault("chat.color", "&f");
		
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
	
}
