package com.github.mcvier3ck.command;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.entity.Player;

import com.github.mcvier3ck.chatcontrol.ChatControlMain;

import net.md_5.bungee.api.ChatColor;

public class CommandList {

	public static void list(Player player, String[] args) {
		int page = Integer.valueOf(args[1]);
		
		//Try to get Table
		Statement st = null;
		ResultSet rs = null;
		try {
			st = ChatControlMain.mysql.getConnection().createStatement();
			rs = st.executeQuery("SELECT * FROM censor");			
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
	
		//Try to send word list
		try {
			int current = 0;
			rs.last();
			//Send Page Count
			player.sendMessage(ChatControlMain.Prefix + ChatColor.GOLD + "//////////Page " + page + "/" + (rs.getRow()+10)/10 + "\\\\\\\\\\\\\\\\\\\\");
			
			rs.first();
			if(page == 1) {
				player.sendMessage(ChatControlMain.Prefix + ChatColor.GOLD + rs.getString("word"));
				current=1;
			}
			//Send Words (10*page)
			while(rs.next()) {
				current++;
				if(current >=page*10-9 && current <=page*10) {
					player.sendMessage(ChatControlMain.Prefix + ChatColor.GOLD + rs.getString("word"));

				}
				
			}
		} catch (SQLException e) {
		
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void listBy(Player player, String[] args, String playername) {
		int page = Integer.valueOf(args[1]);
		
		//Try to get Table
		Statement st = null;
		ResultSet rs = null;
		try {
			st = ChatControlMain.mysql.getConnection().createStatement();
			rs = st.executeQuery("SELECT * FROM censor");			
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
	
		//Try to send word list
		try {
			int current = 0;
			rs.last();
			//Send Page Count
			player.sendMessage(ChatControlMain.Prefix + ChatColor.GOLD + "//////////Page " + page + "/" + (rs.getRow()+10)/10 + "\\\\\\\\\\\\\\\\\\\\");
			
			rs.first();
			if(page == 1) {
				if(rs.getString("addedBy").equalsIgnoreCase(playername)){
				player.sendMessage(ChatControlMain.Prefix + ChatColor.GOLD + rs.getString("word"));
				current=1;
				}
			}
			//Send Words (10*page)
			while(rs.next()) {
				current++;
				if(current >=page*10-9 && current <=page*10) {
					if(rs.getString("addedBy").equalsIgnoreCase(playername)
							){
					player.sendMessage(ChatControlMain.Prefix + ChatColor.GOLD + rs.getString("word"));
					}
				}
				
			}
		} catch (SQLException e) {
		
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void listForAll(Player player, String[] args, int forall) {
		int page = Integer.valueOf(args[1]);
		
		//Try to get Table
		Statement st = null;
		ResultSet rs = null;
		try {
			st = ChatControlMain.mysql.getConnection().createStatement();
			rs = st.executeQuery("SELECT * FROM censor");			
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
	
		//Try to send word list
		try {
			int current = 0;
			rs.last();
			//Send Page Count
			player.sendMessage(ChatControlMain.Prefix + ChatColor.GOLD + "//////////Page " + page + "/" + (rs.getRow()+10)/10 + "\\\\\\\\\\\\\\\\\\\\");
			
			rs.first();
			if(page == 1) {
				if(rs.getInt("forAll") == forall){
				player.sendMessage(ChatControlMain.Prefix + ChatColor.GOLD + rs.getString("word"));
				current=1;
				}
			}
			//Send Words (10*page)
			while(rs.next()) {
				current++;
				if(current >=page*10-9 && current <=page*10) {
					if(rs.getInt("forAll") == forall){
					player.sendMessage(ChatControlMain.Prefix + ChatColor.GOLD + rs.getString("word"));
					} 
				}
			}
		} catch (SQLException e) {
		
			System.out.println(e.getMessage());
		}
		
	}
	
}
