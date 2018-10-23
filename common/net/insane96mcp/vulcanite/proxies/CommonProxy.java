package net.insane96mcp.vulcanite.proxies;

import net.insane96mcp.vulcanite.init.ModBlocks;
import net.insane96mcp.vulcanite.init.ModItems;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	public void PreInit(FMLPreInitializationEvent event) {		
		ModItems.PreInit();
		ModBlocks.PreInit();
	}
	
	public void Init(FMLInitializationEvent event) {
		ModItems.Init();
		ModBlocks.Init();
	}
	
	public void PostInit(FMLPostInitializationEvent event) {
	
	}
}
