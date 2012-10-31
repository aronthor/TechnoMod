package org.terracore.technomod;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class TechniPowerAssistant {

	public static int getPowerValue(Object obj){
		if(obj instanceof Item){
			return getPowerValue((Item)obj);
		}else if(obj instanceof Block){
			return getPowerValue((Block)obj);
		}else if(obj instanceof ItemStack){
			return getPowerValue(((ItemStack) obj).getItem());
		}
		return 0;
	}
	
	public static int getPowerValue(Item item){
		if(item.shiftedIndex == Item.redstone.shiftedIndex)
			return 50;
		return 0;
	}
	
	public static int getPowerValue(Block block){
		return 0;
	}
	
	public static boolean isValidPowerItem(Object obj){
		return getPowerValue(obj) > 0;
	}
	
}
