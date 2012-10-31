package org.terracore.technomod;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class TechniItem extends Item{
	
	private boolean hasEffect = false;

	protected TechniItem(int itemid) {
		super(itemid);
		this.setCreativeTab(TechnoMod.ctab);
	}
	
	public void setHasEffect(boolean haseffect){
		this.hasEffect = haseffect;
	}

	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack){
        return this.hasEffect;
    }
	
}
