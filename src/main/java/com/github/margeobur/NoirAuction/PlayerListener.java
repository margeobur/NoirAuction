package com.github.margeobur.NoirAuction;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.block.BlockBreakEvent;

import org.bukkit.Material;
import org.bukkit.entity.Player;
// import org.bukkit.inventory.ItemStack;
import org.bukkit.block.Sign;
import org.bukkit.ChatColor;

import com.github.margeobur.NoirAuction.AuctionManager;
import com.github.margeobur.NoirAuction.NoirAuction;
import com.github.margeobur.NoirAuction.Auction;

public class PlayerListener implements Listener
{
	NoirAuction plugin;
	AuctionManager auctionManager;
	
	public PlayerListener(NoirAuction thePlugin, AuctionManager theAuctionManager)
	{
		plugin = thePlugin;
		auctionManager = theAuctionManager;
	}
	
	@EventHandler
	public void onSignPlace(SignChangeEvent event)
	{	
		Sign aSign;
		
		int reserve;
		int bidrate;
		int buyout;
		int startingbid;
		
		boolean isNumber = true;
		int tempIndex;
		String tempString;
		String lines[];
		
		if(event.getLine(0).equalsIgnoreCase("[auction]") == false)
		{
			return;
		}
		
		aSign = (Sign) event.getBlock().getState();
		
		lines = event.getLines();
		
		if(lines[1].contains(":") && lines[1].indexOf(':') == lines[1].lastIndexOf(':'))
		{			
			tempString = lines[1].substring(0, lines[1].indexOf(':'));
			for(int i = 0; i < tempString.length(); i++)
			{
				if(Character.isDigit(tempString.charAt(i)) == false)
				{
					isNumber = false;
					break;
				}
			}
			if(isNumber)
			{
				startingbid = Integer.valueOf(tempString);
			}
			else
			{
				event.getPlayer().sendMessage("Error on line 1. There's no number to the left of the colon!");
				return;
			}
			
			tempString = lines[1].substring(lines[1].indexOf(':') + 1);
			for(int i = 0; i < tempString.length(); i++)
			{
				if(Character.isDigit(tempString.charAt(i)) == false)
				{
					isNumber = false;
					break;
				}
			}
			if(isNumber)
			{
				reserve = Integer.valueOf(tempString);
			}
			else
			{
				event.getPlayer().sendMessage("Error on line 1. There's no number to the right of the colon!");
				return;
			}
		}
		else
		{
			event.getPlayer().sendMessage("Error on line 1. You either didn't type a colon or there is more than one!");
			return;
		}
		
		if(lines[2].contains(":") && lines[2].indexOf(':') == lines[2].lastIndexOf(':'))
		{			
			tempString = lines[2].substring(0, lines[2].indexOf(':'));
			for(int i = 0; i < tempString.length(); i++)
			{
				if(Character.isDigit(tempString.charAt(i)) == false)
				{
					isNumber = false;
					break;
				}
			}
			if(isNumber)
			{
				bidrate = Integer.valueOf(tempString);
			}
			else
			{
				event.getPlayer().sendMessage("Error on line 2. There's no number to the left of the colon!");
				return;
			}
			
			tempString = lines[2].substring(lines[2].indexOf(':') + 1);
			for(int i = 0; i < tempString.length(); i++)
			{
				if(Character.isDigit(tempString.charAt(i)) == false)
				{
					isNumber = false;
					break;
				}
			}
			if(isNumber)
			{
				buyout = Integer.valueOf(tempString);
			}
			else
			{
				event.getPlayer().sendMessage("Error on line 2. There's no number to the right of the colon!");
				return;
			}
		}
		else
		{
			event.getPlayer().sendMessage("Error on line 2. You either didn't type a colon or there is more than one!");
			return;
		}
		
		
		
		event.setCancelled(true);
			
		auctionManager.addAuction(aSign, event.getPlayer().getName(), startingbid, reserve, bidrate, buyout);

		return;
		
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		Sign sign;
		Player thePlayer = event.getPlayer();
		
		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && isValidSign(event))
		{
			if(thePlayer.getItemInHand().getAmount() == 0)
			{
				thePlayer.sendMessage("The item comes out of the sign.");
				sign = (Sign) event.getClickedBlock().getState();
				if(!(auctionManager.takeItemFromAuction(sign, thePlayer)))
				{
					event.getPlayer().sendMessage("Something went wrong! D:");
				}
				return;
			}	
			else if(isValidItem(thePlayer.getItemInHand().getType()))
			{
				thePlayer.sendMessage("The item goes into the sign.");
				sign = (Sign) event.getClickedBlock().getState();
				if(!(auctionManager.putItemInAuction(sign, thePlayer)))
				{
					event.getPlayer().sendMessage("Something went wrong! D:");
				}
				return;
			}	
		}
	}
	
	@EventHandler
	public void onSignBreak(BlockBreakEvent event)
	{
		Sign sign;
		if(isValidSign(event))
		{
			sign = (Sign) event.getBlock().getState();
			auctionManager.removeAuction(sign, event.getPlayer());
		}
	}
	
    public boolean isValidItem(Material mat)
    {
    	switch(mat)
    	{
    	case DIAMOND_SWORD:
    		return true;
    	case DIAMOND_CHESTPLATE:
    		return true;
 
    	default:
    		break;
    		
    	}
    	
    	return false;
    }

    public boolean isValidSign(PlayerInteractEvent event)
    {
    	Sign sign;
    	
		if(event.getClickedBlock().getType().equals(Material.SIGN_POST) == true
				|| event.getClickedBlock().getType().equals(Material.WALL_SIGN) == true)
		{
			sign = (Sign) event.getClickedBlock().getState();
		
			if(ChatColor.stripColor(sign.getLine(0)).equals("[Auction]"))
			{
				return true;
			}
			else
				return false;
		}
		
		else
			return false;
    }
    public boolean isValidSign(BlockBreakEvent event)
    {
    	Sign sign;
    	
		if(event.getBlock().getType().equals(Material.SIGN_POST) == true
				|| event.getBlock().getType().equals(Material.WALL_SIGN) == true)
		{
			sign = (Sign) event.getBlock().getState();
		
			if(ChatColor.stripColor(sign.getLine(0)).equals("[Auction]"))
			{
				return true;
			}
			else
				return false;
		}
		
		else
			return false;
    }
}
