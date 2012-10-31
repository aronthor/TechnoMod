package org.terracore.technomod;

import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityLowTechSolarPanel extends TileEntity implements IPowerGenerator {

	private int stored = 0;
	
	private IConnectable[] con;
	
	public TileEntityLowTechSolarPanel(){
		TechnoMod.pn.addGenerator(this);
	}
	
	public TileEntityLowTechSolarPanel(World world) {
		
	}
	
	@Override
	public void updateEntity(){
		this.con = PowerNetwork.updateConnected(worldObj, xCoord, yCoord, zCoord);
		
		if(isSunlight() && this.stored < 20){
			this.stored += 1;
		}
	}
	
	public boolean isSunlight() {
		/*if(!this.worldObj.isDaytime())
			return false;*/
		
		if(this.worldObj.isRaining())
			return false;
		
		if(this.worldObj.getWorldTime() > 12500)
			return false;
		
		boolean sunlight = true;
		for(int i = this.yCoord+1; i < worldObj.getActualHeight(); i++){
			if(worldObj.getBlockId(xCoord, i, zCoord) != 0){
				sunlight = false;
			}
		}
		return sunlight;
	}

	@Override
	public void drain(int volts) {
		this.stored -= volts;
	}

	@Override
	public boolean canConnectToSide(ForgeDirection dir) {
		if(dir == ForgeDirection.UP)
			return false;
		return true;
	}

	@Override
	public boolean canOutputFrom(ForgeDirection dir) {
		if(dir == ForgeDirection.UP)
			return false;
		return true;
	}

	@Override
	public boolean canInputTo(ForgeDirection dir) {
		return false;
	}

	@Override
	public IConnectable[] getConnections() {
		return this.con;
	}

}
