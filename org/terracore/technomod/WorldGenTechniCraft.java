package org.terracore.technomod;

import java.util.Random;

import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenTechniCraft implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.dimensionId){
			case 0: this.genSurface(random, chunkX*16, chunkZ*16, world);
			case -1: this.genNether(random, chunkX*16, chunkZ*16, world);
			default: this.genOther(random, chunkX*16, chunkZ*16, world);
		}
	}

	private void genOther(Random random, int i, int j, World world) {
		// TODO Auto-generated method stub
		
	}

	private void genNether(Random random, int i, int j, World world) {
		// TODO Auto-generated method stub
		
	}

	private void genSurface(Random random, int i, int j, World world) {
		int blockX = i + random.nextInt(16),
			blockY = random.nextInt(64),
			blockZ = j + random.nextInt(16);
		
		(new WorldGenMinable(TechnoBlocks.SiliconOre.blockID, 10)).generate(world, random, blockX, blockY, blockZ);
	}

}
