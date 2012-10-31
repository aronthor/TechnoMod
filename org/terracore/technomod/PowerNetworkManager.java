package org.terracore.technomod;

import java.util.EnumSet;

import cpw.mods.fml.common.TickType;
import net.minecraft.src.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class PowerNetworkManager {

	public static PowerNetworkManager instance = new PowerNetworkManager();
	
	public boolean checkIfConnectable(TileEntity te) {
		if(te instanceof IConnectable){
			IConnectable con = (IConnectable)te;
			if(con.canConnectToSide(ForgeDirection.UNKNOWN))
				return true;
			return false;
		}
		return false;
	}

	public void tick(EnumSet<TickType> type, Object[] tickData) {
		// TODO Auto-generated method stub
		System.out.println("Ticker");
	}

}
