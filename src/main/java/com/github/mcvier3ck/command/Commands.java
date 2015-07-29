package com.github.mcvier3ck.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.mcvier3ck.chatfilter.ChatFilterMain;

import net.md_5.bungee.api.ChatColor;

public class Commands implements CommandExecutor{

	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cs instanceof Player) {
			Player player = (Player)cs;
			
			if(cmd.getLabel().equalsIgnoreCase("chatfilter")) {
				//Return all subcommands
				if(args.length == 0) {
					
					player.sendMessage(ChatFilterMain.Prefix + ChatColor.GREEN + "/////////////Commands\\\\\\\\\\\\\\");
					player.sendMessage(ChatFilterMain.Prefix + ChatColor.GREEN + "/chatfilter add <word> <forAll>");
					player.sendMessage(ChatFilterMain.Prefix + ChatColor.GREEN + "/chatfilter remove <word>");
					player.sendMessage(ChatFilterMain.Prefix + ChatColor.GREEN + "/chatfilter list <page>");
					player.sendMessage(ChatFilterMain.Prefix + ChatColor.GREEN + "/chatfilter list <page> <by>");
					player.sendMessage(ChatFilterMain.Prefix + ChatColor.GREEN + "/chatfilter list <page> <forall>");
				
				//Add word
				} else if(args[0].equalsIgnoreCase("add")) {
					
					//Check if has all parameters
					if(args.length ==3) {
						
						//Check if paramaters right
						if(args[2].toLowerCase().equalsIgnoreCase("true") || args[2].toLowerCase().equalsIgnoreCase("false") ) {
							
							if(cs.hasPermission("chatfilter.add"))  {
								//Call method add for cleaner code
								CommandAdd.add(player, args);
							}
						} else {
							cs.sendMessage(ChatFilterMain.Prefix + ChatColor.GREEN + "/chatfilter add <word> <forAll true/false>");
						}
					} else {
						cs.sendMessage(ChatFilterMain.Prefix + ChatColor.GREEN + "/chatfilter add <word> <forAll>");
					}
					
				//Remove word
				} else if(args[0].equalsIgnoreCase("remove")) {
					if(cs.hasPermission("chatfilter.remove")) {
				
						
						
					}
					
				//List word	
				} else if(args[0].equalsIgnoreCase("list")) {
					if(cs.hasPermission("chatfilter.list")) {
						//Check if has select page
						if(args.length ==2) {
							//Call method list for cleaner code
							CommandList.list(player, args);
						} else {
							cs.sendMessage(ChatFilterMain.Prefix + ChatColor.GREEN + "/chatfilter list <page>");
						}
							
						
					}
				}
			}
		}
		
		
		return false;
	}
	

	

}
