package org.terracore.technomod;

import net.minecraft.src.MapColor;
import net.minecraft.src.Material;

public class TechnoModMachineMaterial extends Material {

	public TechnoModMachineMaterial() {
		super(MapColor.ironColor);
		this.setNoHarvest();
		this.setImmovableMobility();
	}

}
