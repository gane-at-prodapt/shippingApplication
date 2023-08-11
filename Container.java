package shippingApplication;

abstract class Container {
	int id;
	int weight;
	double bill=0.0;
	
	Port currentPort = null;
	Ship currentShip = null;
	
	Container(int id,int weight,Port currentPort){
		this.id = id;
		this.weight = weight;
		this.currentPort = currentPort;
	}
	
	abstract double consumption();
	abstract String type();
	
	String trackContainer() {
		if(currentPort==null) {
			return "Container C-"+this.id+"-"+this.type()+" is in ship "+currentShip.name+" at port "+currentShip.currentPort.name;
		}else {
			return "Container C-"+this.id+"-"+this.type()+" is at port "+currentPort.name;
		}
	}
	
	boolean equals(Container other) {
		if(other.id==this.id) {
			return true;
		}
		return false;
	}
	
}