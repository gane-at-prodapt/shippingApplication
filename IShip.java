package shippingApplication;

interface IShip {
	
	boolean sailTo(Port p); //returns true if a ship successfully sailed to the destination port 
	
	boolean load(Container cont); //returns true if a container was successfully loaded to a ship 
	
	void reFuel(double newFuel); //adds fuel to a ship 
	
	boolean unLoad(Container cont); //returns true if a container was successfully unloaded from a ship 3. 
	
}
