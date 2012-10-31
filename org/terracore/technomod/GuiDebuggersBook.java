package org.terracore.technomod;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.Block;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.ItemStack;
import net.minecraft.src.StatCollector;
import net.minecraft.src.World;

@SideOnly(Side.CLIENT)
public class GuiDebuggersBook extends GuiScreen{

	private EntityPlayer player;
	private World world;
	
	public GuiDebuggersBook(EntityPlayer ep, World world) {
		this.player = ep;
		this.world = world;
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		int bid = button.id;
		if(bid == 101){
			player.inventory.addItemStackToInventory(new ItemStack(Block.oreIron));
		}
	}
	
	@Override
	public void initGui() {
		controlList.add(new GuiButton(101, 30, 30, 100, 20, "Ores !"));
	}
	
	@Override
	public void drawDefaultBackground(){
        this.drawWorldBackground(0);
        this.drawCenteredString(fontRenderer, "Debuggers Book", 10, 10, 0xffffff);
    }
}
