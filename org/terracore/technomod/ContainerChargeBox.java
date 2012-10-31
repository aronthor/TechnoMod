package org.terracore.technomod;

import java.util.Iterator;

import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ICrafting;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;
import net.minecraft.src.World;

public class ContainerChargeBox extends Container{
	
	private TileEntityChargeBox te;

	public ContainerChargeBox(InventoryPlayer inventory, World world, int x, int y, int z) {
		if(world.getBlockTileEntity(x, y, z) != null)
			te = (TileEntityChargeBox)world.getBlockTileEntity(x, y, z);
		
		this.addSlotToContainer(new SlotPower(te, 0, 26, 31));
		this.addSlotToContainer(new Slot(te, 1, 134, 31));
		
		int var3;
		for (var3 = 0; var3 < 3; ++var3){
            for (int var4 = 0; var4 < 9; ++var4){
                this.addSlotToContainer(new Slot(inventory, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3){
            this.addSlotToContainer(new Slot(inventory, var3, 8 + var3 * 18, 142));
        }
	}
	
	public ItemStack transferStackInSlot(int par1){
        return null;
    }
	
	/*@Override
	public void updateCraftingResults(){
        if(this.te.inventory[0] != null && TechniPowerAssistant.isValidPowerItem(this.te.inventory[0])){
        	this.te.addMineVolts(TechniPowerAssistant.getPowerValue(te.inventory[0]));
        	//this.te.decrStackSize(te.inventory[1], 1);
        }
    }*/

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}
	
	@Override
	protected void retrySlotClick(int par1, int par2, boolean par3, EntityPlayer par4EntityPlayer){
	}

}
