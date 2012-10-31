package org.terracore.technomod;

import java.util.Random;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class BlockCable extends TechniContainerBlock{

	public BlockCable(int itemid, int textureindex, Material material) {
		super(itemid, textureindex, material);
		//this.setCreativeTab(CreativeTabs.tabMisc);
		this.setCreativeTab(TechnoMod.ctab);
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4){
		this.setBlockBounds(0.3F, 0.3F, 0.3F, 0.7F, 0.7F, 0.7F);
	}
	
	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		updateRender(par1World, par2, par3, par4); //For When The World Is Loaded
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5){
		updateRender(par1World, par2, par3, par4); //used For Cables Already There
	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		updateRender(par1World, par2, par3, par4); //Used For Blocks Just Added
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}

	private void updateRender(World par1World, int par2, int par3, int par4){
		
         //int block = TechniCraft.LvCable.blockID; //Int To Check Agains
         TileEntityCable TE = (TileEntityCable)par1World.getBlockTileEntity(par2, par3, par4); //Get The TileEntity Of A SINGLE Block
         
         TE.Up = canCableConnectTo(par1World, par2, par3+1, par4);
         TE.Down = canCableConnectTo(par1World, par2, par3-1, par4);
         TE.Left = canCableConnectTo(par1World, par2+1, par3, par4);
         TE.Right = canCableConnectTo(par1World, par2-1, par3, par4);
         TE.Front = canCableConnectTo(par1World, par2, par3, par4+1);
         TE.Back = canCableConnectTo(par1World, par2, par3, par4-1);
	}
	
	private boolean canCableConnectTo(World world, int x, int y, int z) {
		if(PowerNetworkManager.instance.checkIfConnectable(world.getBlockTileEntity(x, y, z)))return true;
		return false;
	}

	@Override
	public boolean isOpaqueCube(){
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock(){
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityCable();
	}

}
