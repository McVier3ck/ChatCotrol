package com.github.mcvier3ck.command;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.entity.Player;

import com.github.mcvier3ck.chatcontrol.ChatControlMain;

import net.md_5.bungee.api.ChatColor;

public class CommandAdd {

public static void add(Player player, String[] args) {
		
		//Try to get Table
		Statement st = null;
		ResultSet rs = null;
		try {
			st = ChatControlMain.mysql.getConnection().createStatement();
			rs = st.executeQuery("SELECT * FROM censor");			
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		
		//Ceck if allready listed
		try {
			while(rs.next()) {
				if(rs.getString("word").equalsIgnoreCase(args[1])) {
					player.sendMessage(ChatControlMain.Prefix + ChatColor.GOLD + "Word already listed");
					return;
				}
			}
		} catch (SQLException e) {
		
			System.out.println(e.getMessage());
		}
		
		//Chech if for all
		int forAll = 0;
		
		if(args[2].toLowerCase().equalsIgnoreCase("true")) forAll =1;
		if(args[2].toLowerCase().equalsIgnoreCase("false")) forAll =0;
		
		//Add to table
		ChatControlMain.mysql.queryUpdate("INSERT INTO censor (word, addedBy, forall) VALUES ('" + args[1].toLowerCase() + "', '" + player.getName() + "', '" + forAll +"')");
		
		//send mesagges to player
		if(forAll == 1) player.sendMessage(ChatControlMain.Prefix + ChatColor.GOLD + "Block word " + args[1].toLowerCase() + " for All");
		if(forAll == 0) player.sendMessage(ChatControlMain.Prefix + ChatColor.GOLD + "Block word " + args[1].toLowerCase() + " for non Op");
	}
	
}
