package org.terracore.technomod;

import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.Slot;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class ContainerMachineBasic extends Container{

	public ContainerMachineBasic(InventoryPlayer inv, World world, int x, int y, int z) {
		
		TileEntity te;
		if(world.getBlockTileEntity(x, y, z) instanceof TileEntityBasicMachine)
			te = (TileEntityBasicMachine)world.getBlockTileEntity(x, y, z);
		else
			return;
		
		int slotnum = 0;
		for (int var3 = 0; var3 < 3; ++var3){
            for (int var4 = 0; var4 < 5; ++var4){
                this.addSlotToContainer(new Slot((IInventory) te, slotnum, var4 * 18 - 5, 14 + var3 * 18));
                slotnum++;
            }
        }
		
		
		for (int var3 = 0; var3 < 3; ++var3){
            for (int var4 = 0; var4 < 9; ++var4){
                this.addSlotToContainer(new Slot(inv, var4 + var3 * 9 + 9, 20 + var4 * 18, 82 + var3 * 18));
            }
        }

        for (int var3 = 0; var3 < 9; ++var3){
            this.addSlotToContainer(new Slot(inv, var3, 20 + var3 * 18, 140));
        }
	}
	
	@Override
	protected void retrySlotClick(int par1, int par2, boolean par3, EntityPlayer par4EntityPlayer){
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}

}
