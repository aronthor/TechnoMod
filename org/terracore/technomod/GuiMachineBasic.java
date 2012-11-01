package org.terracore.technomod;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.Container;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class GuiMachineBasic extends GuiContainer{
	
	TileEntityBasicMachine machine;

	public GuiMachineBasic(InventoryPlayer inventory, World world, int x,int y, int z) {
		super(new ContainerMachineBasic(inventory, world, x, y, z));
		if(world.getBlockTileEntity(x, y, z) instanceof TileEntityBasicMachine)
			machine = (TileEntityBasicMachine)world.getBlockTileEntity(x, y, z);
		else
			machine = null;
	}
	
	@Override
	public void initGui(){
		
		super.initGui();
		
		int guiWidth = 203;
		int guiHeight = 167;
		
		int baseX = (this.width-guiWidth)/2;
		int baseY = (this.height-guiHeight)/2;
		
		GuiButton btn = new GuiButton(101, baseX+100, baseY+57, 80, 20, "Build!");
		
		if(machine != null && machine.getUsedComponents() > 3)btn.enabled = false;
		
		this.controlList.add(btn);
	}
	
	@Override
	public void updateScreen(){
		this.initGui();
	}

	@Override
	public void actionPerformed(GuiButton btn){
		if(btn.id == 101 && btn.enabled == true){
			this.mc.thePlayer.closeScreen();
			machine.build();
		}
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		
		int guiWidth = 203;
		int guiHeight = 167;
		
		int baseX = (this.width-guiWidth)/2;
		int baseY = (this.height-guiHeight)/2;
		
		int bg = mc.renderEngine.getTexture(Assistant.BasicMachineGui);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(bg);
		this.drawTexturedModalRect(baseX, baseY, 0, 0, guiWidth, guiWidth);
		
		// Inventory Stuff
		this.drawString(fontRenderer, "Basic Machine Build", baseX + 4, baseY+4, 0xffffff);
		this.drawString(fontRenderer, "Inventory", baseX + 4, baseY+70, 0xffffff);
		
		// Stats
		if(machine != null){
			this.drawString(fontRenderer, "Components Used", baseX+100, baseY+17, 0xffffff);
			this.drawString(fontRenderer, ""+machine.getUsedComponents(), baseX+110, baseY+27, 0xffffff);
			this.drawString(fontRenderer, "Max Components", baseX+100, baseY+37, 0xffffff);
			this.drawString(fontRenderer, "3", baseX+110, baseY+47, 0xffffff);
		}
	}

}
