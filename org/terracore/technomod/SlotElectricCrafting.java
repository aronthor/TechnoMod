package org.terracore.technomod;

import net.minecraft.src.AchievementList;
import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class SlotElectricCrafting extends Slot
{
    /** The craft matrix inventory linked to this result slot. */
    private final IInventory craftMatrix;

    /** The player that is using the GUI where this slot resides. */
    private EntityPlayer thePlayer;

    /**
     * The number of items that have been crafted so far. Gets passed to ItemStack.onCrafting before being reset.
     */
    private int amountCrafted;

    public SlotElectricCrafting(EntityPlayer par1EntityPlayer, IInventory par2IInventory, IInventory par3IInventory, int par4, int par5, int par6)
    {
        super(par3IInventory, par4, par5, par6);
        this.thePlayer = par1EntityPlayer;
        this.craftMatrix = par2IInventory;
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    @Override
    public boolean isItemValid(ItemStack par1ItemStack)
    {
        return false;
    }

    /**
     * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new
     * stack.
     */
    @Override
    public ItemStack decrStackSize(int par1)
    {
        if (this.getHasStack())
        {
            this.amountCrafted += Math.min(par1, this.getStack().stackSize);
        }

        return super.decrStackSize(par1);
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood. Typically increases an
     * internal count then calls onCrafting(item).
     */
    @Override
    protected void onCrafting(ItemStack par1ItemStack, int par2)
    {
        this.amountCrafted += par2;
        this.onCrafting(par1ItemStack);
    }

    /**
     * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood.
     */
    @Override
    protected void onCrafting(ItemStack par1ItemStack)
    {
        par1ItemStack.onCrafting(this.thePlayer.worldObj, this.thePlayer, this.amountCrafted);
        this.amountCrafted = 0;
    }

    /**
     * Called when the player picks up an item from an inventory slot
     */
    @Override
    public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack)
    {
        GameRegistry.onItemCrafted(par1EntityPlayer, par2ItemStack, craftMatrix);
        this.onCrafting(par2ItemStack);

        for (int var3 = 0; var3 < this.craftMatrix.getSizeInventory(); ++var3)
        {
            ItemStack var4 = this.craftMatrix.getStackInSlot(var3);

            if (var4 != null)
            {
                this.craftMatrix.decrStackSize(var3, 1);

                if (var4.getItem().hasContainerItem())
                {
                    ItemStack var5 = var4.getItem().getContainerItemStack(var4);
                    
                    if (var5.isItemStackDamageable() && var5.getItemDamage() > var5.getMaxDamage())
                    {
                        MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(thePlayer, var5));
                        var4 = null;
                    }

                    if (var5 != null && (!var4.getItem().doesContainerItemLeaveCraftingGrid(var4) || !this.thePlayer.inventory.addItemStackToInventory(var5)))
                    {
                        if (this.craftMatrix.getStackInSlot(var3) == null)
                        {
                            this.craftMatrix.setInventorySlotContents(var3, var5);
                        }
                        else
                        {
                            this.thePlayer.dropPlayerItem(var5);
                        }
                    }
                }
            }
        }
    }
}
