package org.terracore.technomod;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.Container;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.StatCollector;
import net.minecraft.src.World;

@SideOnly(Side.CLIENT)
public class GuiElectricBench extends GuiContainer{

	public GuiElectricBench(InventoryPlayer inventory, World w, int x, int y, int z) {
		super(new ContainerElectricBench(inventory, w, x, y, z));
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2){
        this.fontRenderer.drawString("Electric Bench", 8, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		int GuiBackground = this.mc.renderEngine.getTexture(TechnoMod.assist.ElectricBenchGui);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(GuiBackground);
		int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
	}

}
