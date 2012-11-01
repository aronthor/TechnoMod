package org.terracore.technomod;

public interface IPowerGenerator extends IConnectable {

	public int stored = 0;
	public int maxStored = 0;
	
	public void drain(int volts);

	public int getStored();

	public void updateConnections();
	
}
