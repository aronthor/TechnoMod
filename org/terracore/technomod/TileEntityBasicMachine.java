package org.terracore.technomod;

import java.util.Random;

import net.minecraft.src.BlockContainer;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntity;

public class TileEntityBasicMachine extends TileEntity implements IInventory{

	public ItemStack[] inventory = new ItemStack[15];
	
	@Override
	public int getSizeInventory() {
		return 15;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2) {
		if (this.inventory[par1] != null){
            ItemStack var3;

            if (this.inventory[par1].stackSize <= par2){
                var3 = this.inventory[par1];
                this.inventory[par1] = null;
                return var3;
            }else{
                var3 = this.inventory[par1].splitStack(par2);

                if (this.inventory[par1].stackSize == 0){
                    this.inventory[par1] = null;
                }
                return var3;
            }
        }else{
            return null;
        }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (this.inventory[slot] != null){
            ItemStack var2 = this.inventory[slot];
            this.inventory[slot] = null;
            return var2;
        }else{
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		this.inventory[slot] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }
	}

	@Override
	public String getInvName() {
		return "BasicMachine";
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		return true;
	}

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}

	public int getUsedComponents() {
		int used = 0;
		
		for(int i = 0; i < inventory.length; i++){
			if(inventory[i] != null){
				used += inventory[i].stackSize;
			}
		}
		
		return used;
	}
	
	public void build(){
		BlockContainer block = (BlockContainer) TechnoBlocks.LTSolar;
		this.worldObj.setBlockWithNotify(xCoord, yCoord, zCoord, 0);
		//this.worldObj.setBlockWithNotify(xCoord, yCoord, zCoord, block.blockID);
		this.worldObj.removeBlockTileEntity(xCoord, yCoord, zCoord);
		//this.worldObj.setBlockTileEntity(xCoord, yCoord, zCoord, block.createNewTileEntity(worldObj));
		//this.worldObj.markBlockNeedsUpdate(xCoord, yCoord, zCoord);
		this.worldObj.setBlockWithNotify(xCoord, yCoord, zCoord, block.blockID);
		
		// TODO replace block with the newly built block
		
		Random rand = new Random(); // Fancy smoke effect when machine is built
		for(int i = 0;i < 100; i++){
			double xRand = (rand.nextDouble())*1.0,
				   yRand = (rand.nextDouble())*1.0,
				   zRand = (rand.nextDouble())*1.0;
			double xVel = 0.1-(rand.nextDouble()*0.2),
				   yVel = 0.1-(rand.nextDouble()*0.2),
				   zVel = 0.1-(rand.nextDouble()*0.2);
			worldObj.spawnParticle("reddust", this.xCoord+xRand, yCoord+yRand, zCoord+zRand, xVel, yVel, zVel);
		}
	}

}
