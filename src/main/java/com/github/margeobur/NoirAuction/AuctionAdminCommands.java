package com.github.margeobur.NoirAuction;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;

public class AuctionAdminCommands implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if(command.getName().equalsIgnoreCase("auctionadmin") == false)
			return false;
		
		if(!(sender.isOp()))
		{
			sender.sendMessage("You must be an admin to use /auctionadmin commands!");
			return true;
		}
		
		if (args.length < 1) 
		{
	        sender.sendMessage("Not enough arguments!");
	        return false;
	    }
		
		if(args[0].equalsIgnoreCase("terminate"))
		{
			sender.sendMessage("Will terminate the targetted auction after confirming");
			return true;
		}
		else if(args[0].equalsIgnoreCase("reload"))
		{
			sender.sendMessage("Will reload the plugin");
			return true;
		}
		else if(args[0].equalsIgnoreCase("adjust"))
		{
			sender.sendMessage("Will adjust certain values");
			return true;
		}
			
		return false;
	}
	
}
