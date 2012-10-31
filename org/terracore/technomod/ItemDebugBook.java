package org.terracore.technomod;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class ItemDebugBook extends TechniItem{

	protected ItemDebugBook(int itemid) {
		super(itemid);
		//this.setCreativeTab(CreativeTabs.tabMisc);
		this.setCreativeTab(TechnoMod.ctab);
		this.setIconCoord(11, 3);
		this.setHasEffect(true);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack is, World w, EntityPlayer ep){
		ep.openGui(TechnoMod.instance, GuiIds.DEBUGGERS_BOOK, w, (int)ep.posX, (int)ep.posY, (int)ep.posZ);
		return is;
    }

}
