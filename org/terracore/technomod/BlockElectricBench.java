package org.terracore.technomod;

import java.util.Random;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class BlockElectricBench extends TechniContainerBlock{

	protected BlockElectricBench(int itemid, int textureindex, Material material) {
		super(itemid, textureindex, material);
		//this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setCreativeTab(TechnoMod.ctab);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundStoneFootstep);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9){
        if(player.isSneaking() || world.getBlockTileEntity(x, y, z) == null)
        	return false;
        
        player.openGui(TechnoMod.instance, 2, world, x, y, z);
		return true;
    }
	
	@Override
	public int getBlockTextureFromSide(int side){
        switch(side){
        	case 0:return 2;
        	case 1:return 0;
        	default: return 1;
        }
    }
	
	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityElectricBench();
	}
	
	

}
