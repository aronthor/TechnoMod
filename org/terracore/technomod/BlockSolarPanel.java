package org.terracore.technomod;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class BlockSolarPanel extends TechniContainerBlock{
	
	private int tier = 0;

	public BlockSolarPanel(int itemid, int textureindex, int type) {
		super(itemid, textureindex, Material.wood);
		this.setCreativeTab(TechnoMod.ctab);
		tier = type;
	}

	@Override
	public int getBlockTextureFromSide(int side){
		if(tier == 0){
			switch(side){
				case 0: return 10;
				case 1: return 11;
				default: return 9;
			}
		}
		return 0;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9){
        if(player.isSneaking() || world.getBlockTileEntity(x, y, z) == null)
        	return false;
        
        player.openGui(TechnoMod.instance, 4, world, x, y, z);
		return true;
    }

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityLowTechSolarPanel(world);
	}
}
