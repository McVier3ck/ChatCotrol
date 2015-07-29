package com.github.mcvier3ck.utils;

import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.github.mcvier3ck.chatfilter.ChatFilterMain;

import net.milkbowl.vault.chat.Chat;


public class PermissonsInteract {

	public static String getPrefix(Player player) {
		RegisteredServiceProvider<Chat> chatProvider = ChatFilterMain.getInstance().getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
		
		return chatProvider.getProvider().getPlayerPrefix(player);
	}
	
	public static String getSuffix(Player player) {
		RegisteredServiceProvider<Chat> chatProvider = ChatFilterMain.getInstance().getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
		
		return chatProvider.getProvider().getPlayerSuffix(player);
	}
	
	
}
