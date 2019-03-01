package net.insane96mcp.vulcanite;

import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.insane96mcp.vulcanite.init.ModConfig;
import net.insane96mcp.vulcanite.init.Strings.Names;
import net.insane96mcp.vulcanite.item.ItemFlintAndVulcanite;
import net.insane96mcp.vulcanite.item.ItemVulcaniteArmor;
import net.insane96mcp.vulcanite.item.ItemVulcaniteAxe;
import net.insane96mcp.vulcanite.item.ItemVulcanitePickaxe;
import net.insane96mcp.vulcanite.item.ItemVulcaniteShovel;
import net.insane96mcp.vulcanite.item.ItemVulcaniteSword;
import net.insane96mcp.vulcanite.item.materials.ModMaterial;
import net.insane96mcp.vulcanite.network.PacketBlockBreak;
import net.minecraft.block.Block;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemHoe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

@Mod(Vulcanite.MOD_ID)
public class Vulcanite {
	
	public static final String MOD_ID = "vulcanite";
	public static final String RESOURCE_PREFIX = MOD_ID + ":";
	
	public static SimpleChannel channel = NetworkRegistry.newSimpleChannel(new ResourceLocation(MOD_ID, MOD_ID), () -> "1.0", "1.0"::equals, "1.0"::equals);
	
	// Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public Vulcanite() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
    	int discriminator = 0;
    	
       	channel.registerMessage(discriminator++, PacketBlockBreak.class, PacketBlockBreak::encode, PacketBlockBreak::decode, PacketBlockBreak::onMessage);
       	
       	ModLoadingContext.get().registerConfig(Type.COMMON, ModConfig.SPEC);
       	ModConfig.Init(Paths.get("config", MOD_ID + ".toml"));
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
        }
        
        @SubscribeEvent
        public static void OnItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
        	
        	itemRegistryEvent.getRegistry().registerAll(
        		new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName(new ResourceLocation(Vulcanite.MOD_ID, Names.VULCANITE_INGOT)),
        		new ItemVulcanitePickaxe(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_PICKAXE),
        		new ItemVulcaniteAxe(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_AXE),
        		new ItemVulcaniteShovel(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_SHOVEL),
        		new ItemHoe(ModMaterial.TOOL_VULCANITE, -0.5f, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName(new ResourceLocation(Vulcanite.MOD_ID, Names.VULCANITE_HOE)),
        		new ItemFlintAndVulcanite(Vulcanite.RESOURCE_PREFIX + Names.FLINT_AND_VULCANITE),
        		new ItemVulcaniteSword(Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_SWORD),

        		new ItemVulcaniteArmor(EntityEquipmentSlot.HEAD, Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_HELMET),
        		new ItemVulcaniteArmor(EntityEquipmentSlot.CHEST, Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_CHESTPLATE),
        		new ItemVulcaniteArmor(EntityEquipmentSlot.LEGS, Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_LEGGINGS),
        		new ItemVulcaniteArmor(EntityEquipmentSlot.FEET, Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_BOOTS)
        	);
        }
        
        
    }
}
