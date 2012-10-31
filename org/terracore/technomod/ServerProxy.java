package org.terracore.technomod;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraft.src.WorldType;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class ServerProxy implements IGuiHandler{
	//public static BiomeGenBase chargedBiome = (new BiomeElectricCharged(70));

	public void loadTextureFiles(){}
	
	public static void registerRenderInformation(){
		//GameRegistry.addBiome(chargedBiome);
		//WorldType.DEFAULT.addNewBiome(new BiomeElectricCharged(70));
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		TileEntity te = world.getBlockTileEntity(x, y, z);
		
		if(ID == GuiIds.ELECTRIC_BENCH)
			return new ContainerElectricBench(player.inventory, world, x, y, z);
		if(ID == GuiIds.CHARGE_BOX)
			return new ContainerChargeBox(player.inventory, world, x, y, z);
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) {
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if(ID == GuiIds.DEBUGGERS_BOOK)return new GuiDebuggersBook(player, world);
		if(ID == GuiIds.ELECTRIC_BENCH)
			return new GuiElectricBench(player.inventory, world, x, y, z);
		if(ID == GuiIds.CHARGE_BOX)
			return new GuiChargeBox(player.inventory, world, x, y, z);
		if(ID == GuiIds.SOLAR_PANEL)
			return new GuiSolarPanel((TileEntityLowTechSolarPanel)world.getBlockTileEntity(x, y, z));
		return null;
	}

}
