package com.github.margeobur.NoirAuction;

import com.github.margeobur.NoirAuction.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

public class NoirAuction extends JavaPlugin
{
	AuctionManager auctionManager;
	AuctionConfig auctionConfig;
	
	@Override
    public void onEnable() 
	{
		auctionConfig = new AuctionConfig(this, this.getConfig());
		auctionManager = new AuctionManager(this);
		
		getServer().getPluginManager().registerEvents(new PlayerListener(this, auctionManager), this);
		this.getCommand("auction").setExecutor(new AuctionCommands());
		this.getCommand("auctionadmin").setExecutor(new AuctionAdminCommands());
    }
 
    @Override
    public void onDisable()
    {
    	
    }         
    
}
