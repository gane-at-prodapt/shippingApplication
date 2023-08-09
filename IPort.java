package shippingApplication;

interface IPort {
	/*
	 Note that there should not be any duplicates in the current and history ArrayLists 
	 (i.e. do not add the same ship to history if it has already visited that port before)  
	 */
	
	void incomingShip(Ship s); //should add this ship to current ArrayList.
	
	void outgoingShip(Ship s); //should add this ship to history ArrayList. 
	
}
