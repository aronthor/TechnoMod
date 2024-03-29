package org.terracore.technomod;

import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class TechnoItems {
	
	// Mod Items !!
	public static Item DebugBook, Circuits, ScrewDriver;
	
	public static void InitTechnoItems(){
		initItems();
		addNames();
	}

	private static void addNames() {
		if(TechnoMod.assist.DebugMode){ // Debug Items
			LanguageRegistry.addName(DebugBook, "Debuggers Book");
		}
		// Circuits
		LanguageRegistry.addName(new ItemStack(Circuits, 0, 1), "Basic Circuit");
		
		LanguageRegistry.addName(ScrewDriver, "Screw Driver");
		
	}

	private static void initItems() {
		if(TechnoMod.assist.DebugMode)DebugBook = new ItemDebugBook(TechnoMod.configurator.getItemId(0)).setItemName("DebuggersBook");
		
		// Circuits
		Circuits = new ItemCircuit(TechnoMod.configurator.getItemId(1)).setItemName("Circuits");
		// Tools
		ScrewDriver = new ItemScrewDriver(TechnoMod.configurator.getItemId(2)).setItemName("ScrewDriver");
	}
	
}
