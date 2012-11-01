package org.terracore.technomod;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.INetworkManager;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet132TileEntityData;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityChargeBox extends TileEntity implements IInventory, IPowerStorage{
	
	public ItemStack[] inventory = new ItemStack[2];
	public int charge = 0;
	public int max = 40000;
	
	private IConnectable[] connected = {null, null, null, null, null, null};
	
	public TileEntityChargeBox(){
		TechnoMod.pn.addChargeBox(this);
	}
	
	public TileEntityChargeBox(World world){
		this.worldObj = world;
	}
	
	public ItemStack[] getInventory(){
		return inventory;
	}
	
	public void updateEntity() {
		
		this.connected = PowerNetwork.updateConnected(worldObj, this.xCoord, this.yCoord, this.zCoord);
		
		if(TechniPowerAssistant.isValidPowerItem(this.inventory[0]) && charge+TechniPowerAssistant.getPowerValue(this.inventory[0])<=max){
			charge(TechniPowerAssistant.getPowerValue(this.inventory[0]));
			if(this.inventory[0].stackSize > 1)
				this.inventory[0] = new ItemStack(Item.redstone, this.inventory[0].stackSize-1);
			else
				this.inventory[0] = null;
		}
		
		if(this.charge>1){
			for(IConnectable icb : this.connected){
				if(icb instanceof ICable){
					if(((ICable) icb).getPower() < 10){
						((ICable) icb).setPower(((ICable) icb).getPower()+1);
						this.charge -= 1;
					}
				}
			}
		}
			
		
		/*if(isConnectedToPower()){
			TileEntityCable cable = findFirstCable();
			if(this.charge < this.max - 10){
				this.charge(cable.heldPower);
				cable.heldPower = 0;
			}
		}*/
	}

	private TileEntityCable findFirstCable() {
		if(this.worldObj.getBlockTileEntity(xCoord+1, yCoord, zCoord) instanceof TileEntityCable)
			return (TileEntityCable)this.worldObj.getBlockTileEntity(xCoord+1, yCoord, zCoord);
		if(this.worldObj.getBlockTileEntity(xCoord-1, yCoord, zCoord) instanceof TileEntityCable)
			return (TileEntityCable)this.worldObj.getBlockTileEntity(xCoord-1, yCoord, zCoord);
		if(this.worldObj.getBlockTileEntity(xCoord, yCoord+1, zCoord) instanceof TileEntityCable)
			return (TileEntityCable)this.worldObj.getBlockTileEntity(xCoord, yCoord+1, zCoord);
		if(this.worldObj.getBlockTileEntity(xCoord, yCoord-1, zCoord) instanceof TileEntityCable)
			return (TileEntityCable)this.worldObj.getBlockTileEntity(xCoord, yCoord-1, zCoord);
		if(this.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord+1) instanceof TileEntityCable)
			return (TileEntityCable)this.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord+1);
		if(this.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord-1) instanceof TileEntityCable)
			return (TileEntityCable)this.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord-1);
		return null;
	}

	private boolean isConnectedToPower() {
		if(this.worldObj.getBlockTileEntity(xCoord+1, yCoord, zCoord) instanceof TileEntityCable)
			return true;
		if(this.worldObj.getBlockTileEntity(xCoord-1, yCoord, zCoord) instanceof TileEntityCable)
			return true;
		if(this.worldObj.getBlockTileEntity(xCoord, yCoord+1, zCoord) instanceof TileEntityCable)
			return true;
		if(this.worldObj.getBlockTileEntity(xCoord, yCoord-1, zCoord) instanceof TileEntityCable)
			return true;
		if(this.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord+1) instanceof TileEntityCable)
			return true;
		if(this.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord-1) instanceof TileEntityCable)
			return true;
		return false;
	}

	@Override
	public Packet getDescriptionPacket(){
		NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, tag);
    }
	
	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData packet){
		NBTTagCompound tag = packet.customParam1;
        charge = tag.getInteger("Charge");
    }
	
	@Override
	public int getSizeInventory() {
		return 2;
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
		return "Charge Box";
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}
	
	@Override
    public void readFromNBT(NBTTagCompound data) {
            super.readFromNBT(data);
            
            charge = data.getInteger("Charge");
            
            NBTTagList tagList = data.getTagList("ChargeBox");
            for (int i = 0; i < tagList.tagCount(); i++) {
                    NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
                    byte slot = tag.getByte("Slot");
                    if (slot >= 0 && slot < inventory.length) {
                    	inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
                    }
            }
    }

    @Override
    public void writeToNBT(NBTTagCompound data) {
            super.writeToNBT(data);
            data.setInteger("Charge", charge);
            
            NBTTagList itemList = new NBTTagList();
            for (int i = 0; i < inventory.length; i++) {
                    ItemStack stack = inventory[i];
                    if (stack != null) {
                            NBTTagCompound tag = new NBTTagCompound();
                            tag.setByte("Slot", (byte) i);
                            stack.writeToNBT(tag);
                            itemList.appendTag(tag);
                    }
            }
            data.setTag("ChargeBox", itemList);
    }

	@Override
	public boolean canConnectToSide(ForgeDirection dir) {
		return true;
	}

	@Override
	public void charge(int volt) {
		if(this.charge + volt <= this.max)
			this.charge += volt;
	}

	@Override
	public void drain(int volt) {
		if(hasCharge(volt))
			this.charge -= volt;
	}

	@Override
	public boolean hasCharge(int volt) {
		return this.charge >= volt;
	}

	@Override
	public int getCharge() {
		return this.charge;
	}

	@Override
	public boolean canOutputFrom(ForgeDirection dir) {
		if(dir == ForgeDirection.UP || dir == ForgeDirection.DOWN)
			return true;
		return false;
	}

	@Override
	public boolean canInputTo(ForgeDirection dir) {
		if(dir ==  ForgeDirection.EAST)
			return true;
		if(dir ==  ForgeDirection.WEST)
			return true;
		if(dir ==  ForgeDirection.NORTH)
			return true;
		if(dir ==  ForgeDirection.SOUTH)
			return true;
		return false;
	}

	@Override
	public int getMaxCharge() {
		return this.max;
	}

	@Override
	public IConnectable[] getConnections() {
		return this.connected;
	}
	
	@Override
	public int getRoom(){
		return this.max-this.charge;
	}
	
}
