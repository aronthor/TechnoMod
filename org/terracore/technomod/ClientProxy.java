package org.terracore.technomod;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends ServerProxy{

	@Override
	public void loadTextureFiles(){
		MinecraftForgeClient.preloadTexture(TechnoMod.assist.BlockTextures);
		MinecraftForgeClient.preloadTexture(TechnoMod.assist.CircuitTextures);
	}
	
}
