package com.github.margeobur.NoirAuction;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.inventory.ItemStack;
import com.github.margeobur.NoirAuction.NoirAuction;
import com.github.margeobur.NoirAuction.Auction;
import java.util.ArrayList;
import org.bukkit.entity.Player;
import java.util.logging.Level;

//Manages all the auctions

public class AuctionManager 
{
	NoirAuction plugin;
	ArrayList<Auction> Auctions = new ArrayList<Auction>();
	
	public AuctionManager(NoirAuction inst)
	{
		plugin = inst;
	}
	
	public void addAuction(Sign sign, String playername, int startBid, int reserve, int bidrate, int buyout)
	{
		sign.setLine(3, playername);
		Auctions.add(new Auction(sign, playername, startBid, reserve, bidrate, buyout));
	}
	
	public boolean putItemInAuction(Sign sign, Player player)
	{
		ItemStack item = player.getItemInHand();
		ItemStack newItem;
		
		Auction targetAuction = findAuctionOfSign(sign);
		if(targetAuction == null)
		{
			plugin.getLogger().log(Level.INFO, "putItemInAuction returning false!");
			return false; 
		}
		
		if(targetAuction.setItemToSell(item) == false)
		{
			player.sendMessage("There already is an item in that auction sign!");
			return true;
		}
		
		newItem = new ItemStack(Material.AIR);
		player.setItemInHand(newItem);
		
		return true;
	}
	public boolean takeItemFromAuction(Sign sign, Player player)
	{
		ItemStack item;
		
		Auction targetAuction = findAuctionOfSign(sign);
		if(targetAuction == null)
		{
			
			return false;
		}
		
		if(targetAuction.getItemToSell() == null)
		{
			player.sendMessage("No item in sign");
			return true;
		}
		
		item = targetAuction.getItemToSell();
		player.setItemInHand(item);
		
		targetAuction.clearItemToSell();
		return true;
	}
	
	public Auction findAuctionOfSign(Sign sign)
	{
		for(int i = 0; i < Auctions.size(); i++)
		{
			if(Auctions.get(i).getSign().getLocation().equals(sign.getLocation()) == true)
			{
				return Auctions.get(i);
			}
		}
		return null;
	}
	
	public boolean removeAuction(Sign sign, Player player)
	{
		Auction targetAuction = findAuctionOfSign(sign);
		ItemStack item;
		
		if(targetAuction == null)
			return false;
		
		item = targetAuction.getItemToSell();
		if(!(item == null))
			player.setItemInHand(item);
		
		targetAuction.clearItemToSell();
		
		Auctions.remove(targetAuction);
		
		return true;
	}
	
}
