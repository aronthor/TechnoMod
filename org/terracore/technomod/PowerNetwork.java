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
		
		// TODO ChargeBox to Machine
		// TODO Cable to Machine
		for(int i = 0; i < this.storage.size(); i++){ // Loop through Storage Units
			IPowerStorage box = storage.get(i);
			for(IConnectable icb : storage.get(i).getConnections()){
				
				if(icb instanceof ICable){ // Storage Unit To Cable
					if(box.hasCharge(10)){
						((ICable) icb).setPower(10);
						box.drain(10);
					}
				}
				
				if(icb instanceof IPowerStorage){ // Storage Unit To Storage Unit
					if(box.hasCharge(1)){
						((IPowerStorage) icb).charge(1);
						box.drain(1);
					}
				}
			}
		}
		
		for(int i = 0; i < this.cables.size(); i++){ // Loop through cables
			ICable cable =  this.cables.get(i);
			for(IConnectable icb : cable.getConnections()){
				
				if(icb instanceof ICable){ // Cable to Cable
					if(cable.getPower() < 10 && ((ICable)icb).getPower() == 10){
						cable.setPower(10);
						((ICable) icb).setPower(10-cable.getPower());
					}
				}
				
				if(icb instanceof IPowerStorage){ // Cable to Storage Unit
					if(cable.getPower() > 0){
						((IPowerStorage) icb).charge(cable.getPower());
						cable.setPower(0);
					}
				}
			}
		}
		/*
		for(int i = 0; i< this.gens.size(); i++){ // Loop through Power Gens
			IPowerGenerator gen = this.gens.get(i);
			for(IConnectable icb : gen.getConnections()){
				
				if(icb instanceof ICable){ // Power Generator to Cable
					if(gen.stored >= 10){
						((ICable) icb).setPower(gen.stored);
						gen.drain(gen.stored);
					}
				}
				
				if(icb instanceof IPowerStorage){ // Power Generator to Storage Unit
					if(gen.stored >= 10){
						((IPowerStorage) icb).charge(gen.stored);
						gen.drain(gen.stored);
					}
				}
			}
		}
		*/
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
