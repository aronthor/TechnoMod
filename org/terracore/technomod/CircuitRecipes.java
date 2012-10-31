package org.terracore.technomod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import net.minecraft.src.Block;
import net.minecraft.src.IRecipe;
import net.minecraft.src.InventoryCrafting;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ShapelessRecipes;
import net.minecraft.src.World;

public class CircuitRecipes {

	private static final CircuitRecipes instance = new CircuitRecipes();
	private List recipes = new ArrayList();
	
	private CircuitRecipes(){
		addRecipe(new ItemStack(TechnoItems.Circuits, 0, 1), new Object[]{Block.thinGlass, new ItemStack(Item.dyePowder, 2, 10)});
		
		
		Collections.sort(this.recipes, new RecipeSorter(this));
		System.out.println(this.recipes.size() + " Circuit Recipes Loaded");
	}
	
	
	public static CircuitRecipes getInstance(){
		return instance;
	}
	
	
	public void addRecipe(ItemStack par1ItemStack, Object ... par2ArrayOfObj)
    {
        ArrayList var3 = new ArrayList();
        Object[] var4 = par2ArrayOfObj;
        int var5 = par2ArrayOfObj.length;

        for (int var6 = 0; var6 < var5; ++var6)
        {
            Object var7 = var4[var6];

            if (var7 instanceof ItemStack)
            {
                var3.add(((ItemStack)var7).copy());
            }
            else if (var7 instanceof Item)
            {
                var3.add(new ItemStack((Item)var7));
            }
            else
            {
                if (!(var7 instanceof Block))
                {
                    throw new RuntimeException("Invalid Circuit Recipe!");
                }

                var3.add(new ItemStack((Block)var7));
            }
        }

        this.recipes.add(new CircuitRecipe(par1ItemStack, var3));
    }
	
	public ItemStack findMatchingRecipe(ElectricCrafting craftMatrix, World world)
    {
        int var2 = 0;
        ItemStack var3 = null;
        ItemStack var4 = null;

        
        // Check if player has Soldering Material Or Redstone for Powering the soldering iron
        if(craftMatrix.getStackInSlot(15) == null) // solder
        	return null;
        
        if(craftMatrix.getStackInSlot(16) == null) // power
        	return null;
        
        for (int var5 = 0; var5 < craftMatrix.getSizeInventory(); ++var5)
        {
            ItemStack var6 = craftMatrix.getStackInSlot(var5);

            if (var6 != null)
            {
                if (var2 == 0)
                {
                    var3 = var6;
                }

                if (var2 == 1)
                {
                    var4 = var6;
                }

                ++var2;
            }
        }

        if (var2 == 2 && var3.itemID == var4.itemID && var3.stackSize == 1 && var4.stackSize == 1 && Item.itemsList[var3.itemID].isRepairable())
        {
            Item var10 = Item.itemsList[var3.itemID];
            int var12 = var10.getMaxDamage() - var3.getItemDamageForDisplay();
            int var7 = var10.getMaxDamage() - var4.getItemDamageForDisplay();
            int var8 = var12 + var7 + var10.getMaxDamage() * 10 / 100;
            int var9 = var10.getMaxDamage() - var8;

            if (var9 < 0)
            {
                var9 = 0;
            }

            return new ItemStack(var3.itemID, 1, var9);
        }
        else
        {
            Iterator var11 = this.recipes.iterator();
            IRecipe var13;

            do
            {
                if (!var11.hasNext())
                {
                    return null;
                }

                var13 = (IRecipe)var11.next();
            }
            while (!var13.matches(craftMatrix, world));

            return var13.getCraftingResult(craftMatrix);
        }
    }
	
	
}
