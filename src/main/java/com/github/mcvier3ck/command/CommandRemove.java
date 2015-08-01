package com.github.mcvier3ck.command;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.entity.Player;

import com.github.mcvier3ck.chatcontrol.ChatControlMain;

import net.md_5.bungee.api.ChatColor;

public class CommandRemove {
	
	public static void remove(Player player, String[] args) {
		
		//Try to get Table
		Statement st = null;
		ResultSet rs = null;
		try {
			st = ChatControlMain.mysql.getConnection().createStatement();
			rs = st.executeQuery("SELECT * FROM censor");			
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		
		
		boolean listed = false;
		
		//Ceck if allready listed
		try {
			while(rs.next()) {
				if(rs.getString("word").equalsIgnoreCase(args[1])) {
					listed = true;
				}
			}
		} catch (SQLException e) {
		
			System.out.println(e.getMessage());
		}
		
		if(listed) {
			ChatControlMain.mysql.queryUpdate("DELETE FROM censor WHERE word = '" + args[1]  + "'");
			player.sendMessage(ChatControlMain.Prefix + ChatColor.GOLD + args[1] + " is no longer Blocked");
		} else {
			player.sendMessage(ChatControlMain.Prefix + ChatColor.GOLD + args[1] + " is didn't listed");
		}
	
	
	}
}
