package org.terracore.technomod;

import net.minecraftforge.common.ForgeDirection;

public interface IConnectable {

	public boolean canConnectToSide(ForgeDirection dir);
	public IConnectable[] getConnections();
	public boolean canOutputFrom(ForgeDirection dir);
	public boolean canInputTo(ForgeDirection dir);
	
}
