package org.terracore.technomod;

import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityLowTechSolarPanel extends TileEntity implements IPowerGenerator {

	public static int stored = 0;
	
	private int lastUpdate = 0;
	
	private IConnectable[] con = {null, null, null, null, null, null};
	
	public TileEntityLowTechSolarPanel(){
		TechnoMod.pn.addGenerator(this);
	}
	
	public TileEntityLowTechSolarPanel(World world) {
		
	}
	
	public void updateConnections(){
		if(lastUpdate > 10){
			this.con = PowerNetwork.updateConnected(worldObj, xCoord, yCoord, zCoord);
			lastUpdate = 0;
		}
		lastUpdate++;
	}
	
	public int getStored(){
		return stored;
	}
	
	@Override
	public void updateEntity(){
		this.con = PowerNetwork.updateConnected(worldObj, xCoord, yCoord, zCoord);
		
		if(isSunlight()){
			for(IConnectable icb : this.con){
				if(icb instanceof IPowerStorage){
					if(((IPowerStorage) icb).getRoom()>=1){
						((IPowerStorage) icb).charge(1);
					}
				}
				if(icb instanceof ICable){
					if(((ICable) icb).getPower() < 10){
						((ICable) icb).setPower(((ICable) icb).getPower()+1);
					}
				}
			}
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
