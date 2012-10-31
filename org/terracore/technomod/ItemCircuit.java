package org.terracore.technomod;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.MathHelper;

public class ItemCircuit extends TechniItem{
	
	// Circuit List
	private static final String[] names = new String[]{
		"BasicCircuit"
	};
	private static final int[] ids = new int[]{
		0
	};

	protected ItemCircuit(int itemid) {
		super(itemid);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(TechnoMod.ctab);
		//this.setCreativeTab(CreativeTabs.tabMisc);
		this.setTextureFile(TechnoMod.assist.CircuitTextures);
		this.setMaxStackSize(64);
	}
	
	@Override
	public int getIconFromDamage(int metadata){
        switch(metadata){
        	case 0:return 0;
        	
        	default: return 0;
        }
    }

}
