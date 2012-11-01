package org.terracore.technomod;

public interface IPowerStorage extends IConnectable {

	public void charge(int volt);
	public void drain(int volt);
	public boolean hasCharge(int volt);
	public int getCharge();
	public int getMaxCharge();
	public int getRoom();
	
}
