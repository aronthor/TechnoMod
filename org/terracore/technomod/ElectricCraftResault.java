package org.terracore.technomod;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;

public class ElectricCraftResault implements IInventory {
	
	private ItemStack[] resault = new ItemStack[1];

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return resault[slot];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.resault[par1] != null)
        {
            ItemStack var3 = this.resault[par1];
            this.resault[par1] = null;
            return var3;
        }
        else
        {
            return null;
        }
    }
	
	@Override
	public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.resault[par1] != null)
        {
            ItemStack var2 = this.resault[par1];
            this.resault[par1] = null;
            return var2;
        }
        else
        {
            return null;
        }
    }

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.resault[par1] = par2ItemStack;
    }

	@Override
	public String getInvName() {
		return "ElectricResault";
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void onInventoryChanged() {
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		return true;
	}

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}

}
