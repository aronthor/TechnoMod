package org.terracore.technomod;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.src.ItemStack;
import net.minecraft.src.Item;
import net.minecraft.src.Block;

public class CraftHelper {
	
	public static CraftHelper instance = new CraftHelper();
	
	private List solder = new ArrayList();
	private List power = new ArrayList();
	
	public CraftHelper(){
		TechnoMod.echo("CraftHelper loaded !!");
	}
	
	public void addSolderMaterial(Object obj){
		solder.add(obj);
	}
	
	public void addPowerMaterial(Object obj){
		power.add(obj);
	}
	
	public boolean checkForValidSolderMaterial(ItemStack item){
		for(int i=0;i<solder.size();i++){
			if(solder.get(i) == item.getItem())
				return true;
		}
		return false;
	}
	
	public boolean checkForValidPowerMaterial(ItemStack item) {
		for(int i=0;i<power.size();i++){
			if(power.get(i) == item.getItem())
				return true;
		}
		return false;
	}

	public boolean checkForValidMachineComponent(ItemStack is) {
		if(Item.itemsList[is.itemID] instanceof ItemCircuit)
			return true;
		return false;
	}
	
	/*
	
	public static boolean isValidSolderMaterial(ItemStack item) {
		for(int i=0;i<solder.length;i++){
			if(solder[i] == item.getItem())
				return true;
		}
		return false;
	}

	public static boolean getValidPowerSource(ItemStack item) {
		for(int i=0;i<power.length;i++){
			if(power[i] == item.getItem())
				return true;
		}
		return false;
	}
	 */
	
}
