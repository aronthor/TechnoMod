package org.terracore.technomod;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class BlockMachine extends TechniContainerBlock{

	public BlockMachine(int itemid) {
		super(itemid, 12, TechnoMod.TechnoMachine);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9){
		Item equipped = player.getCurrentEquippedItem() != null ? player.getCurrentEquippedItem().getItem() : null;
		if(equipped instanceof ItemScrewDriver)
			player.openGui(TechnoMod.instance, GuiIds.MachineBasic, world, x, y, z);
		
		return false;
    }
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityBasicMachine();
	}

}
