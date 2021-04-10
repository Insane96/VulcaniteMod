package net.insane96mcp.vulcanite;

import java.util.Random;

import javax.security.auth.login.Configuration;

import net.insane96mcp.vulcanite.proxies.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Vulcanite.MOD_ID, name = Vulcanite.MOD_NAME, version = Vulcanite.VERSION, acceptedMinecraftVersions = Vulcanite.MINECRAFT_VERSIONS)
public class Vulcanite {
	
	public static final String MOD_ID = "vulcanite";
	public static final String MOD_NAME = "Vulcanite";
	public static final String VERSION = "1.5.3";
	public static final String RESOURCE_PREFIX = MOD_ID.toLowerCase() + ":";
	public static final String MINECRAFT_VERSIONS = "[1.12,1.12.2]";
	
	public static Configuration config;

	public static Random random = new Random();
	
	@Instance(MOD_ID)
	public static Vulcanite instance;
	
	@SidedProxy(clientSide = "net.insane96mcp.vulcanite.proxies.ClientProxy", serverSide = "net.insane96mcp.vulcanite.proxies.ServerProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event) {
		proxy.PreInit(event);
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event) {
		proxy.Init(event);
	}
	
	@EventHandler
	public void PostInit(FMLPostInitializationEvent event) {
		proxy.PostInit(event);
	}
}
