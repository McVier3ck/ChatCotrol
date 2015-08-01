package com.github.mcvier3ck.listener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.github.mcvier3ck.chatcontrol.ChatControlMain;
import com.github.mcvier3ck.utils.PermissonsInteract;

public class ChatListener implements Listener{

	@EventHandler
	public void onUseChat(AsyncPlayerChatEvent e) throws SQLException {
		Player player = e.getPlayer();
		String message = e.getMessage().toLowerCase();
		
		ResultSet rs = null;
		Statement st = null;
			
			//Get entrys from table
			st = ChatControlMain.mysql.getConnection().createStatement();
			rs = st.executeQuery("SELECT * FROM censor");
		
			
			while(rs.next()) {
				//Check if word blocked
				if(message.contains(rs.getString("word"))) {
					//Check if word blocked for all
					if(rs.getInt("forAll") == 0) {
						//Check when not blocked for all if player can use
						if(player.hasPermission("chatfilter.morewords")){
							return;
						}
					} 
					//Replace word with xxxx
					message = message.replaceAll(rs.getString("word"), "xxxx");
					
				}
			}
			message = ChatControlMain.getInstance().getConfig().getString("chat.color") + message;
			
		//give back prefix and suffix + message
		String format = ChatControlMain.getInstance().getConfig().getString("chat.format");
		format = format.replace("{player}", player.getName());
		format = format.replace("{suffix}", PermissonsInteract.getSuffix(player));
		format = format.replace("{prefix}", PermissonsInteract.getPrefix(player));
		format = format.replace("{msg}", message);
		format = format.replace("&", "§");
		e.setFormat(format);
		
		
	}
	
	
	
}
