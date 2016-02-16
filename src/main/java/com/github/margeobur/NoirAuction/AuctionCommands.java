package com.github.margeobur.NoirAuction;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;


public class AuctionCommands implements CommandExecutor
{
	
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		Player thePlayer;

		if(command.getName().equalsIgnoreCase("auction") == false)
			return false;
		
		//*****Safety tests*****
		
		if(!(sender instanceof Player))
		{
			sender.sendMessage("This command set is only useful for players!");
			return true;
		}
		else
			thePlayer = (Player) sender;
		
		if(!(thePlayer.isOnline()))
			return false;
		
		if (args.length < 1) 
		{
	        sender.sendMessage("Not enough arguments!");
	        return false;
	    }
		//**********************
		
		if(args[0].equalsIgnoreCase("display"))
		{
			sender.sendMessage("Command will show info about an auction here after clicking on desired auction sign.");
			//more code to come
			return true;
		}
		else if(args[0].equalsIgnoreCase("start"))
		{
			sender.sendMessage("Command will start the auction after checking that everything is set \n and checking that the player is happy");
			//more code to come
			return true;
		}
		
		return false;
	}
}
