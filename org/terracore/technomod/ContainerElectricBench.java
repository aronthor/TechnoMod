package org.terracore.technomod;

import java.util.Iterator;

import net.minecraft.src.Container;
import net.minecraft.src.CraftingManager;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ICrafting;
import net.minecraft.src.IInventory;
import net.minecraft.src.InventoryCraftResult;
import net.minecraft.src.InventoryCrafting;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;
import net.minecraft.src.SlotCrafting;
import net.minecraft.src.World;

public class ContainerElectricBench extends Container {
	
	public ElectricCrafting craftMatrix = new ElectricCrafting(this, 5, 3);
	public IInventory craftResult = new ElectricCraftResault();
	private TileEntityElectricBench bench;
	private ElectricCraftResault craftResault;
	private SlotElectricCrafting craftingSlot;
	private World worldObj;
	private int posX;
    private int posY;
    private int posZ;

	public ContainerElectricBench(InventoryPlayer inv, World world, int par3, int par4, int par5){
		this.bench = (TileEntityElectricBench)world.getBlockTileEntity(par3, par4, par5);
		
		this.worldObj = world;
		this.posX = par3;
        this.posY = par4;
        this.posZ = par5;
		int slotid = 0;
		int var3;
        
		for(var3 = 0; var3 < 3; ++var3){
			for(int var5=0;var5 < 5; ++var5){
				this.addSlotToContainer(new Slot(this.craftMatrix, slotid, 8+var5*18, 17+var3*18));
				++slotid;
			}
		}
		
		for (var3 = 0; var3 < 3; ++var3){
            for (int var4 = 0; var4 < 9; ++var4){
                this.addSlotToContainer(new Slot(inv, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3){
            this.addSlotToContainer(new Slot(inv, var3, 8 + var3 * 18, 142));
        }
        
        this.addSlotToContainer(new SlotSolder(this.craftMatrix, 15, 108, 17)); // Solder
        this.addSlotToContainer(new SlotPower(this.craftMatrix, 16, 108, 17+36)); // Power
        
        this.addSlotToContainer(new SlotElectricCrafting(inv.player, this.craftMatrix, this.craftResult, 0, 152, 35));
        
        this.onCraftMatrixChanged(this.craftMatrix);
	}
	
	@Override
	protected void retrySlotClick(int par1, int par2, boolean par3, EntityPlayer par4EntityPlayer){
	}
	
	@Override
	public void onCraftMatrixChanged(IInventory par1IInventory){
		this.craftResult.setInventorySlotContents(0, CircuitRecipes.getInstance().findMatchingRecipe(this.craftMatrix, this.worldObj));
    }
	
	@Override
	public void onCraftGuiClosed(EntityPlayer par1EntityPlayer)
    {
        super.onCraftGuiClosed(par1EntityPlayer);

        if (!this.worldObj.isRemote){
            for (int var2 = 0; var2 < this.craftMatrix.getSizeInventory(); ++var2)
            {
                ItemStack var3 = this.craftMatrix.getStackInSlotOnClosing(var2);

                if (var3 != null)
                {
                    par1EntityPlayer.dropPlayerItem(var3);
                }
            }
        }
    }
	
	public ItemStack transferStackInSlot(int par1){
		return null;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}

}
