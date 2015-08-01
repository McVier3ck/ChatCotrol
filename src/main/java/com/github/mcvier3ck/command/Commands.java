package com.github.mcvier3ck.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.mcvier3ck.chatcontrol.ChatControlMain;

import net.md_5.bungee.api.ChatColor;

public class Commands implements CommandExecutor{

	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cs instanceof Player) {
			Player player = (Player)cs;
			
			if(cmd.getLabel().equalsIgnoreCase("chatfilter")) {
				//Return all subcommands
				if(args.length == 0) {
					
					player.sendMessage(ChatControlMain.Prefix + ChatColor.GREEN + "//////////Commands\\\\\\\\\\\\\\\\\\\\");
					player.sendMessage(ChatControlMain.Prefix + ChatColor.GREEN + "/chatfilter add <word> <forAll>");
					player.sendMessage(ChatControlMain.Prefix + ChatColor.GREEN + "/chatfilter remove <word>");
					player.sendMessage(ChatControlMain.Prefix + ChatColor.GREEN + "/chatfilter list <page>");
					player.sendMessage(ChatControlMain.Prefix + ChatColor.GREEN + "/chatfilter list <page> <by>");
					player.sendMessage(ChatControlMain.Prefix + ChatColor.GREEN + "/chatfilter list <page> <forall>");
				
				//Add word
				} else if(args[0].equalsIgnoreCase("add")) {
					
					//Check if has all parameters
					if(args.length ==3) {
						
						//Check if paramaters right
						if(args[2].toLowerCase().equalsIgnoreCase("true") || args[2].toLowerCase().equalsIgnoreCase("false") ) {
							
							//Check if haspermisson 
							if(cs.hasPermission("chatfilter.add"))  {
								//Call method add for cleaner code
								CommandAdd.add(player, args);
							} else {
								player.sendMessage(ChatControlMain.Prefix + ChatColor.RED + "You have no permissons for this command");
							}
						} else {
							//give message if command needs more
							cs.sendMessage(ChatControlMain.Prefix + ChatColor.GREEN + "/chatfilter add <word> <forAll true/false>");
						}
					} else {
						//give message if command needs more
						cs.sendMessage(ChatControlMain.Prefix + ChatColor.GREEN + "/chatfilter add <word> <forAll>");
					}
					
				//Remove word
				} else if(args[0].equalsIgnoreCase("remove")) {
					//Check if haspermisson
					if(cs.hasPermission("chatfilter.remove")) {
						//Check if has all parameters
						if(args.length == 2) {
							//Call method add for cleaner code
							CommandRemove.remove(player, args);
						} else {
							//give message if command needs more
							player.sendMessage(ChatControlMain.Prefix + ChatColor.GREEN + "/chatfilter remove <word>");
						}
						
					} else {
						player.sendMessage(ChatControlMain.Prefix + ChatColor.RED + "You have no permissons for this command");
					}
					
				//List word	
				} else if(args[0].equalsIgnoreCase("list")) {
					//Check if haspermisson
					if(cs.hasPermission("chatfilter.list")) {
						//Check if has select page
						if(args.length ==2) {
							
								//Call method list for cleaner code
								CommandList.list(player, args);
								
						//Check if has more parameters
						} else if(args.length ==3){
						if(args[2].equalsIgnoreCase("true") || args[2].equalsIgnoreCase("false")) {
							
							//Check if true or false;
							int forall = 0;
							if(args[2].equalsIgnoreCase("true")) forall=1;
							if(args[2].equalsIgnoreCase("false")) forall=0;
							
							//Call method listForAll for cleaner code
							CommandList.listForAll(player, args, forall);
							
						} else {
							
							//Call method listBy for cleaner code
							CommandList.listBy(player, args, args[2]);
						}
							
						} else {
							//give message if command needs more
							player.sendMessage(ChatControlMain.Prefix + ChatColor.GREEN + "/chatfilter list <page>");
							player.sendMessage(ChatControlMain.Prefix + ChatColor.GREEN + "/chatfilter list <page> <by>");
							player.sendMessage(ChatControlMain.Prefix + ChatColor.GREEN + "/chatfilter list <page> <forall>");

						}
							
				      } else {
							player.sendMessage(ChatControlMain.Prefix + ChatColor.RED + "You have no permissons for this command");
				      }
					} else {
						//Return all subcommands
						player.sendMessage(ChatControlMain.Prefix + ChatColor.GREEN + "//////////Commands\\\\\\\\\\\\\\\\\\\\");
						player.sendMessage(ChatControlMain.Prefix + ChatColor.GREEN + "/chatfilter add <word> <forAll>");
						player.sendMessage(ChatControlMain.Prefix + ChatColor.GREEN + "/chatfilter remove <word>");
						player.sendMessage(ChatControlMain.Prefix + ChatColor.GREEN + "/chatfilter list <page>");
						player.sendMessage(ChatControlMain.Prefix + ChatColor.GREEN + "/chatfilter list <page> <by>");
						player.sendMessage(ChatControlMain.Prefix + ChatColor.GREEN + "/chatfilter list <page> <forall>");
					
					}
				}
			}
		
		return false;
	}
	

	

}
