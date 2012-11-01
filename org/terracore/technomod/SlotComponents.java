package org.terracore.technomod;

import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;

public class SlotComponents extends Slot{

	public SlotComponents(IInventory par1iInventory, int par2, int par3, int par4) {
		super(par1iInventory, par2, par3, par4);
	}
	
	@Override
	public boolean isItemValid(ItemStack is){
		return CraftHelper.instance.checkForValidMachineComponent(is);
	}

}
