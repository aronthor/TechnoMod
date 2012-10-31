package org.terracore.technomod;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

public class CreativeTechniTab extends CreativeTabs{

	public CreativeTechniTab(int par1, String par2Str) {
		super(par1, par2Str);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getTabIconItemIndex() {
		return Item.cake.shiftedIndex;
	}
	
}
