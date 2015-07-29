package com.github.mcvier3ck.command;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.entity.Player;

import com.github.mcvier3ck.chatfilter.ChatFilterMain;

import net.md_5.bungee.api.ChatColor;

public class CommandList {

	public static void list(Player player, String[] args) {
		int page = Integer.valueOf(args[1]);
		
		//Try to get Table
		Statement st = null;
		ResultSet rs = null;
		try {
			st = ChatFilterMain.mysql.getConnection().createStatement();
			rs = st.executeQuery("SELECT * FROM censor");			
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
	
		//Try to send word list
		try {
			int current = 0;
			rs.last();
			//Send Page Count
			player.sendMessage(ChatFilterMain.Prefix + ChatColor.GOLD + "//////////Page " + page + "/" + 10/rs.getRow() + "\\\\\\\\\\");
			rs.first();
			
			//Send Words (10*page)
			while(rs.next()) {
				current++;
				if(current >=page*10-9) {
					player.sendMessage(ChatFilterMain.Prefix + ChatColor.GOLD + rs.getString("word"));

				}
	
			}
		} catch (SQLException e) {
		
			System.out.println(e.getMessage());
		}
		
	}
	
	
}
