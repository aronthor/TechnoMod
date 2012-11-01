package org.terracore.technomod;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.StatCollector;
import net.minecraft.src.World;

@SideOnly(Side.CLIENT)
public class GuiChargeBox extends GuiContainer{

	private TileEntityChargeBox te;
	
	public GuiChargeBox(InventoryPlayer inventory, World world, int x, int y, int z) {
		super(new ContainerChargeBox(inventory, world, x, y, z));
		
		if(world.getBlockTileEntity(x, y, z) != null)
			te = (TileEntityChargeBox)world.getBlockTileEntity(x, y, z);
	}
	
	@Override
	public void updateScreen(){
        super.updateScreen();
    }

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2){
        this.fontRenderer.drawString("Charge Box", 60, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
        
        this.fontRenderer.drawString(this.te.getCharge()+" V", 54, 36, 0x000000);
    }
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		int gui = this.mc.renderEngine.getTexture(TechnoMod.assist.ChargeBoxGui);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(gui);
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
        //this.fontRenderer.drawString("Charge: "+this.te.power.getCharge(), 60, 16, 4210752);
        
        //int progress = ((int)(this.te.charge == 0 ? 1 : this.te.charge) / this.te.max) * 74;
        int progress = (te.charge == 0 ? 1 : te.charge) * 74 / te.max;
        this.drawTexturedModalRect(var5 + 51, var6 + 28, 176, 0, progress, 22);
	}

}
