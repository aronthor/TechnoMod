package org.terracore.technomod;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;

public class TechnoBlocks {
	
	public static Block ElectricBench, ChargedStone, ChargeBox, SiliconOre,
	LvCable, LTSolar, Machine;
	
	public static void InitModBlocks(){
		initBlocks();
		addNames();
		addBlockRecipes();
		registerBlocks();
		registerBlockTiles();
	}

	private static void registerBlockTiles() {
		GameRegistry.registerTileEntity(TileEntityElectricBench.class, "ElectricBench");
		GameRegistry.registerTileEntity(TileEntityChargeBox.class, "ChargeBox");
		GameRegistry.registerTileEntity(TileEntityLowTechSolarPanel.class, "LTSolarPanel");
		GameRegistry.registerTileEntity(TileEntityBasicMachine.class, "Machine");
	}

	private static void registerBlocks() {
		GameRegistry.registerBlock(ElectricBench);
		GameRegistry.registerBlock(ChargedStone);
		GameRegistry.registerBlock(ChargeBox);
		GameRegistry.registerBlock(SiliconOre);
		GameRegistry.registerBlock(LvCable);
		GameRegistry.registerBlock(LTSolar);
		GameRegistry.registerBlock(Machine);
	}

	private static void addBlockRecipes() {
		GameRegistry.addRecipe(new ItemStack(ElectricBench), new Object[]{ // Electric Bench
			"XDX", "ORO", "XDX", 
			Character.valueOf('X'), Item.ingotIron,		// Iron Ingot, Diamond, Iron Ingot
			Character.valueOf('D'), Item.diamond,		// Stone, Redstone, Stone
			Character.valueOf('R'), Item.redstone,		// Iron Ingot, Diamond, Iron Ingot
			Character.valueOf('O'), Block.stone
		});
	}

	private static void addNames() {
		LanguageRegistry.addName(ChargedStone, "Charged Stone");
		LanguageRegistry.addName(ChargeBox, "Charge Box");
		LanguageRegistry.addName(SiliconOre, "Silicon Ore");
		LanguageRegistry.addName(LvCable, "LV Cable");
		LanguageRegistry.addName(LTSolar, "Low Tech Solar Panel");
		LanguageRegistry.addName(Machine, "Machine Casing");
		LanguageRegistry.addName(ElectricBench, "Electric Bench");
	}

	private static void initBlocks() {
		ElectricBench = new BlockElectricBench(TechnoMod.configurator.getBlockId(0), 0, Material.rock).setBlockName("ElectricBench");
		ChargedStone = new TechniBlock(TechnoMod.configurator.getBlockId(1), 3, Material.rock).setBlockName("ChargedStone");
		SiliconOre = new TechniBlock(TechnoMod.configurator.getBlockId(3), 7, Material.rock).setBlockName("SiliconOre");
		ChargeBox = new BlockChargeBox(TechnoMod.configurator.getBlockId(2)).setBlockName("ChargeBox");
		LvCable = new BlockCable(TechnoMod.configurator.getBlockId(4), 8, Material.grass).setBlockName("LvCable");
		LTSolar = new BlockSolarPanel(TechnoMod.configurator.getBlockId(5), 9, 0).setBlockName("LTSolar");
		Machine = new BlockMachine(TechnoMod.configurator.getBlockId(6)).setBlockName("Machine");
	}
	
}
