package com.github.margeobur.NoirAuction;

import org.bukkit.configuration.file.FileConfiguration;

public class AuctionConfig 
{
	NoirAuction Plugin;
	FileConfiguration config;
	
	public AuctionConfig(NoirAuction inst, FileConfiguration passedConfig)
	{
		Plugin = inst;
		InitConfig(passedConfig);
	}

	public void InitConfig(FileConfiguration passedConfig)
	{
		config = passedConfig;
		
		config.addDefault("itemList", "");
		config.addDefault("minimumBidRate", 0);
		config.addDefault("maximumBidRate", 0);
		config.addDefault("minimumReserve", 0);
		config.addDefault("maximumReserve", 0);
		config.addDefault("minimumStartingBid", 0);
		config.addDefault("maximumStartingBid", 0);
		config.addDefault("minimumBuyNow", 0);
		config.addDefault("maximumBuyNow", 0);
		config.addDefault("minimumDiffRatio", 0);
		config.addDefault("maximumDiffRatio", 0);
		
		config.options().copyDefaults(true);
		Plugin.saveConfig();
	}
	
}
