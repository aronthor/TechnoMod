package org.terracore.technomod;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraftforge.common.ForgeDirection;

public class PowerNetwork implements ITickHandler{
	
	public List<ICable> cables = new ArrayList<ICable>();
	public List<IPowerStorage> storage = new ArrayList<IPowerStorage>();
	public List<IPowerGenerator> gens = new ArrayList<IPowerGenerator>();
	
	public PowerNetwork(){
		TechnoMod.echo("TechnoMod PowerNetwork Created !!");
		//this.addCable(cable);
	}
	
	public void addCable(ICable cable){
		this.cables.add(cable);
	}
	
	public void addChargeBox(IPowerStorage box){
		this.storage.add(box);
	}
	
	public void addGenerator(IPowerGenerator gen){
		this.gens.add(gen);
	}
	
	public void updateFlow(){
		
		/*for(int i = 0; i < gens.size(); i++){
			IPowerGenerator gen = gens.get(i);
			for(IConnectable icb : gen.getConnections()){
				if(icb instanceof IPowerStorage){
					if(((IPowerStorage) icb).getRoom() >= 10 && gen.getStored() >= 10){
						TechnoMod.echo("Storage:"+((IPowerStorage)icb).getCharge()+", Gen:"+gen.getStored());
						((IPowerStorage) icb).charge(10);
						gen.drain(10);
					}
				}
			}
			gen.updateConnections();
		}*/
		
	}
	
	public static IConnectable[] updateConnected(World worldObj, int xCoord, int yCoord, int zCoord){
		IConnectable[] connected = {null, null, null, null, null, null};
		
		if(worldObj.getBlockTileEntity(xCoord, yCoord-1, zCoord) instanceof IConnectable)
			connected[ForgeDirection.DOWN.ordinal()] = (IConnectable)worldObj.getBlockTileEntity(xCoord, yCoord-1, zCoord);
		if(worldObj.getBlockTileEntity(xCoord, yCoord+1, zCoord) instanceof IConnectable)
			connected[ForgeDirection.UP.ordinal()] = (IConnectable)worldObj.getBlockTileEntity(xCoord, yCoord+1, zCoord);
		
		if(worldObj.getBlockTileEntity(xCoord, yCoord, zCoord-1) instanceof IConnectable)
			connected[ForgeDirection.NORTH.ordinal()] = (IConnectable)worldObj.getBlockTileEntity(xCoord, yCoord, zCoord-1);
		if(worldObj.getBlockTileEntity(xCoord, yCoord, zCoord+1) instanceof IConnectable)
			connected[ForgeDirection.SOUTH.ordinal()] = (IConnectable)worldObj.getBlockTileEntity(xCoord, yCoord, zCoord+1);
		
		if(worldObj.getBlockTileEntity(xCoord-1, yCoord, zCoord) instanceof IConnectable)
			connected[ForgeDirection.WEST.ordinal()] = (IConnectable)worldObj.getBlockTileEntity(xCoord-1, yCoord, zCoord);
		if(worldObj.getBlockTileEntity(xCoord+1, yCoord, zCoord) instanceof IConnectable)
			connected[ForgeDirection.EAST.ordinal()] = (IConnectable)worldObj.getBlockTileEntity(xCoord+1, yCoord, zCoord);
	
		return connected;
		
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {}

	@Override
	public EnumSet<TickType> ticks() {
		updateFlow();
		return null;
	}

	@Override
	public String getLabel() {
		return "TechniCraft Power Network";
	}
}
