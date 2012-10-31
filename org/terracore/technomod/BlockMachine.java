package org.terracore.technomod;

import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class BlockMachine extends TechniContainerBlock{

	protected BlockMachine(int itemid) {
		super(itemid, 12, TechnoMod.TechnoMachine);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityMachine();
	}

}
