package org.terracore.technomod;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.GuiScreen;
import net.minecraft.src.NBTTagString;
import net.minecraft.src.StatCollector;

public class GuiSolarPanel extends GuiScreen{
	
	private TileEntityLowTechSolarPanel solar;
	
	public GuiSolarPanel(TileEntityLowTechSolarPanel te){
		this.solar = te;
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3){
		this.drawDefaultBackground();
        int var4 = this.mc.renderEngine.getTexture(TechnoMod.assist.SolarPanelGui);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(var4);
        int var5 = (this.width - 93) / 2;
        int var6 = (this.height - 52) / 3;
        this.drawTexturedModalRect(var5, var6, 0, 0, 93, 52);
        this.drawCenteredString(fontRenderer, "Solar Panel", var5+(92/2), var6+5, 0xffffff);
        
        if(solar.isSunlight())
        	this.drawCenteredString(fontRenderer, "Clear Sunlight", var5+(92/2), var6+25, 0x00ff00);
        else
        	this.drawCenteredString(fontRenderer, "Blocked Sunlight", var5+(92/2), var6+25, 0xff0000);
    }
	
}
