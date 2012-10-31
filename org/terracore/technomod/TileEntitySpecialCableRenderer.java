package org.terracore.technomod;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntitySpecialRenderer;

public class TileEntitySpecialCableRenderer extends TileEntitySpecialRenderer {

	private ModelCable model = new ModelCable();
	
	
	public void renderAModelAt(TileEntityCable tile, double d, double d1, double d2, float f) {
		
		//System.out.println("updateRenderer");

		                bindTextureByName(TechnoMod.assist.CableTexture); //texture

		                GL11.glPushMatrix();
		                GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.5F, (float) d2 + 0.5F);
		                GL11.glScalef(1.0F, -1F, -1F);
		                if(tile.Up == true)model.renderUp();
		                if(tile.Down == true)model.renderDown();
		                if(tile.Left == true)model.renderLeft();
		                if(tile.Right == true)model.renderRight();
		                if(tile.Back == true)model.renderBack();
		                if(tile.Front == true)model.renderFront();
		                model.renderMiddle();
		                GL11.glPopMatrix();

		}

		public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) {
			renderAModelAt((TileEntityCable) tileentity, d, d1, d2, f); //where to render
		}

}
