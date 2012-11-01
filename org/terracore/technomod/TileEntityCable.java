package org.terracore.technomod;

import com.google.common.io.ByteArrayDataInput;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.INetworkManager;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet132TileEntityData;
import net.minecraft.src.Packet250CustomPayload;
import net.minecraftforge.common.ForgeDirection;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class TileEntityCable extends TileEntity implements IConnectable, ICable {
	
	public boolean Up = false;
	public boolean Down = false;
	public boolean Left = false;
	public boolean Right = false;
	public boolean Back = false;
	public boolean Front = false;
	
	private IConnectable[] connected = {null, null, null, null, null, null};
	
	public static int heldPower = 0;
	
	public TileEntityCable(){
		TechnoMod.pn.addCable(this);
	}
	
	@Override
	public void updateEntity(){
		this.connected = PowerNetwork.updateConnected(worldObj, this.xCoord, this.yCoord, this.zCoord);
		//if(this.heldPower>1){
			for(IConnectable icb : this.connected){
				if(icb instanceof IPowerStorage){
					if(((IPowerStorage) icb).getRoom()>=this.heldPower){
						((IPowerStorage) icb).charge(this.heldPower);
						this.heldPower = 0;
					}
				}
				if(icb instanceof ICable){
					if(((ICable) icb).getPower() < 10){
						((ICable) icb).setPower(((ICable) icb).getPower()+1);
						this.heldPower -= 1;
					}
				}
			}
		//}
	}
	
	/*public void updatePowerFlow(){
		if(this.heldPower < 10){
			for(byte i = 0; i<this.connected.length; i++){
				IConnectable icb = this.connected[i];
				if(icb instanceof IPowerStorage && this.heldPower < 10){
					//if(((IPowerStorage)icb).hasCharge(10)){
					if( ((TileEntityChargeBox)icb).charge > 10){
						((IPowerStorage)icb).drain(10-heldPower);
						this.heldPower = 10;
					}
				}else if(icb instanceof TileEntityCable){
					if(((TileEntityCable)icb).heldPower > 10-heldPower){
						((TileEntityCable)icb).heldPower = 10-heldPower;
						this.heldPower = 10;
					}
				}
			}
		}
	}*/
	
	// Returns true because cables are connectable from any side
	@Override
	public boolean canConnectToSide(ForgeDirection dir) {
		return true;
	}

	@Override
	public boolean canOutputFrom(ForgeDirection dir) {
		return true;
	}

	@Override
	public boolean canInputTo(ForgeDirection dir) {
		return true;
	}

	@Override
	public IConnectable[] getConnections() {
		return this.connected;
	}

	@Override
	public void setPower(int i) {
		this.heldPower = i;
	}

	@Override
	public int getPower() {
		return this.heldPower;
	}

}
