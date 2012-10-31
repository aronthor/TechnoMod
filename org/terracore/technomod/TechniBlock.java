package org.terracore.technomod;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;

public class TechniBlock extends Block{

	public TechniBlock(int itemid, int textureindex, Material material) {
		super(itemid, textureindex, material);
		//this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setCreativeTab(TechnoMod.ctab);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundStoneFootstep);
		this.setTextureFile(TechnoMod.assist.BlockTextures);
	}
}
