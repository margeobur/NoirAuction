package com.github.margeobur.NoirAuction;

import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
// import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.ChatColor;

//represents an individual auction

public class Auction 
{
	private int id;
	int ownerID;
	
	int reserve;
	int bidrate;
	int currentbid;
	int buyout;
	int startingbid;
	
	private Sign auctionSign;
	private ItemStack itemtosell;
	
	public Auction(Sign theSign, String playername, int startBid, int thereserve, int thebidrate, int thebuyout)
	{
		auctionSign = theSign;
		auctionSign.setLine(0, ChatColor.DARK_BLUE + "[Auction]");
		auctionSign.setLine(3, playername);
		auctionSign.update(true);
		startingbid = startBid;
		reserve = thereserve;
		bidrate = thebidrate;
		buyout = thebuyout;
	}
	
	public Sign getSign()
	{
		return auctionSign;
	}
	public int getID()
	{
		return id;
	}
	public ItemStack getItemToSell()
	{
		if(itemtosell == null)
		{
			return null;
		}
		else
		return itemtosell;
	}
	public boolean setItemToSell(ItemStack newItemStack)
	{
		if(itemtosell != null)
			return false;
		
		itemtosell = newItemStack;
		
		return true;
	}
	public void clearItemToSell()
	{
		itemtosell = null;
	}
	
}
