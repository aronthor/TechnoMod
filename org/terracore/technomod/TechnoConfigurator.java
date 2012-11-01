package org.terracore.technomod;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;

import cpw.mods.fml.common.FMLLog;

public class TechnoConfigurator {
	
	// Item and Block IDs
	public static int[] ItemIds, BlockIds, RenderIds;
	
	// Booleans
	public static boolean onlineVersionCheck = true;
	
	private File configfile;
	
	public TechnoConfigurator(File file){
		this.configfile = file;
	}
	
	public void loadConfigs(){
		ItemIds = new int[30];
		BlockIds = new int[30];
		
		Configuration Configs = new Configuration(this.configfile);
		
		try{ // Get the configs
			Configs.load();
			
			// Mod Settings
			onlineVersionCheck = Configs.get(Configs.CATEGORY_GENERAL, "Online Version Check", true).getBoolean(true);
			
			// Item Ids
			ItemIds[0] = Configs.get(Configs.CATEGORY_ITEM, "Debuggers Book", 1500).getInt(); // Debuggers Book
			ItemIds[1] = Configs.get(Configs.CATEGORY_ITEM, "Circuits", 1501).getInt(); // Circuits (Uses meta data)
			ItemIds[2] = Configs.get(Configs.CATEGORY_ITEM, "Screw Driver", 1502).getInt(); // Screw Driver
			
			// Block Ids
			BlockIds[0] = Configs.get(Configs.CATEGORY_BLOCK, "Electric Bench", 600).getInt(); // Electric Bench
			BlockIds[1] = Configs.get(Configs.CATEGORY_BLOCK, "Charged Stone", 601).getInt(); // Charged Stone
			BlockIds[2] = Configs.get(Configs.CATEGORY_BLOCK, "Charge Box", 602).getInt(); // Charge Box
			BlockIds[3] = Configs.get(Configs.CATEGORY_BLOCK, "Silicon Ore", 603).getInt(); // Silicon Ore
			BlockIds[4] = Configs.get(Configs.CATEGORY_BLOCK, "Low Voltage Cables", 604).getInt(); // Low Voltage Cables
			BlockIds[5] = Configs.get(Configs.CATEGORY_BLOCK, "LowTechSolarPanel", 605).getInt(); // Low Tech Solar Panel
			BlockIds[6] = Configs.get(Configs.CATEGORY_BLOCK, "Machine Blocks[MultiBlock]", 606).getInt(); // Machine Blocks [MultiBlock]
			
			// Render Ids
			//RenderIds[1] = RenderingRegistry.getNextAvailableRenderId();
			
		}catch(Exception err){
			FMLLog.log(Level.SEVERE, err, "TechnoMod could not load Configs");
		}finally{
			Configs.save();
		}
	}
	
	public int getBlockId(int id){
		return BlockIds[id];
	}
	
	public int getItemId(int id){
		return ItemIds[id];
	}
	
}
