package org.terracore.technomod;

import net.minecraft.src.BlockContainer;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class BlockChargeBox extends TechniContainerBlock{

	protected BlockChargeBox(int id) {
		super(id, 4, Material.wood);
		//this.setCreativeTab(CreativeTabs.tabBlock);
		this.setCreativeTab(TechnoMod.ctab);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundStoneFootstep);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9){
        if(player.isSneaking() || world.getBlockTileEntity(x, y, z) == null)
        	return false;
        
        player.openGui(TechnoMod.instance, 3, world, x, y, z);
		return true;
    }
	
	@Override
	public int getBlockTextureFromSide(int side){
        switch(side){
        	case 0:return 6;
        	case 1:return 5;
        	default: return 4;
        }
    }

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityChargeBox(var1);
	}

}
