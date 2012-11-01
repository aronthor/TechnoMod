package org.terracore.technomod;

import java.util.logging.Level;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MapColor;
import net.minecraft.src.Material;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;

@Mod(modid = "TechnoMod", name="TechnoMod", version="0.0.1")
@NetworkMod(channels = {"TechnoMod"}, clientSideRequired = true, serverSideRequired = false, packetHandler=PacketHandler.class)
public class TechnoMod {
	
	public static Assistant assist = new Assistant();
	
	@Instance("TechnoMod")
	public static TechnoMod instance;
	
	@SidedProxy(clientSide = "org.terracore.technomod.ClientProxy", serverSide = "org.terracore.technomod.ServerProxy")
	public static ServerProxy serverproxy;
	
	// TechnoMod Creative Tab
	public static CreativeTabs ctab = new CreativeTechniTab(CreativeTabs.getNextID(), "TechnoMod");
	
	// TechnoMachineMaterial
	public static Material TechnoMachine = new TechnoModMachineMaterial();
	
	// TechnoMod Power Network
	public static PowerNetwork pn = new PowerNetwork();

	public static TechnoConfigurator configurator;
	
	@PreInit
	public void PreInit(FMLPreInitializationEvent e){
		if(Assistant.DebugMode)this.echo("TechnoMod is in debug mode!");
		
		configurator = new TechnoConfigurator(e.getSuggestedConfigurationFile());
		configurator.loadConfigs();
		
		// Version Check
		if(configurator.onlineVersionCheck == true){
			System.out.println("Online Version Check is On! Unimplemented at the moment!");
			// TODO Make mod check with http://technicraft.terracore.org/version.txt for if it is the latest version!
		}
		
	}
	
	@Init
	public void Init(FMLInitializationEvent e){
		serverproxy.loadTextureFiles();
		
		NetworkRegistry.instance().registerGuiHandler(this, serverproxy);
		//TickRegistry.registerTickHandler(pn, Side.SERVER);
		
		TechnoBlocks.InitModBlocks();
		TechnoItems.InitTechnoItems();
		
		GameRegistry.registerWorldGenerator(new WorldGenTechniCraft());
		
		CraftHelper.instance.addSolderMaterial(Item.ingotIron);
		CraftHelper.instance.addPowerMaterial(Item.redstone);
	}

	public static void echo(String s){
		System.out.println("[TechnoMod] "+s);
	}	
}
