package shippingApplication;

import java.util.ArrayList;

public class Ship implements IShip{
	
	int id;
	String name;
	double fuel=0;
	Port currentPort;
	
	int totalWeightCapacity;
	int maxNumberOfAllContainers;
	int maxNumberOfHeavyContainers;
	int maxNumberOfRefrigeratedContainers;
	int maxNumberOfLiquidContainers;
	double fuelConsumptionPerKM;
	ArrayList<Container> containers = new ArrayList<Container>();
	
	private double fuelRequired(double distance) {

		double fuelReq = 0;
		for(Container c : containers) {
			fuelReq+=c.consumption();
		}
		
		return (distance*fuelReq)*this.fuelConsumptionPerKM;
		
	}
	
	
	@Override
	public boolean sailTo(Port p) {
		if(this.id==p.id) {
			System.out.println("Point of departure and destination should not be same");
			return false;
		}
		double fuelReq = this.fuelRequired(this.currentPort.getDistance(p));
		if(this.fuel>=fuelReq) {
			this.currentPort.outgoingShip(this);
			p.incomingShip(this);
			for(Container c : this.containers) {
				c.bill+=fuel*((double)c.weight/this.totalWeightCapacity);
			}
			this.fuel-=fuelReq;
			this.currentPort=p;
			return true;
		}else {
			System.out.println("Not enough fuel available, Refuel "+String.format("%.2f",(fuelReq-this.fuel))+" amount of fuel");
			return false;
		}
	}
		
	@Override
	public boolean load(Container cont) {
		boolean containerFound = false;
		for(Container c : this.currentPort.containers) {
			if(cont.equals(c)) {
				containerFound=true;
				break;
			}
		}
		if(containerFound) {
			if(cont.weight>this.totalWeightCapacity) {
				System.out.println("Ship's weight limit reached, available space: "+this.totalWeightCapacity);
				return false;
			}else {
				if(cont.type()=="basic") {
					if(this.maxNumberOfAllContainers>0) {
						for(int i=0;i<this.currentPort.containers.size();i++) {
							Container c = this.currentPort.containers.get(i);
							if(c.equals(cont)) {
								this.currentPort.containers.remove(i);
								break;
							}
						}
						this.containers.add(cont); 
						this.maxNumberOfAllContainers--;
						this.totalWeightCapacity-=cont.weight;
						cont.currentPort=null;
						cont.currentShip=this;
						return true;
					}else {
						System.out.println("Maximum number of containers loaded");
						return false;
					}
				}else if(cont.type()=="heavy") {
					if(this.maxNumberOfAllContainers>0 && this.maxNumberOfHeavyContainers>0) {
						for(int i=0;i<this.currentPort.containers.size();i++) {
							Container c = this.currentPort.containers.get(i);
							if(c.equals(cont)) {
								this.currentPort.containers.remove(i);
								break;
							}
						}
						this.containers.add(cont);
						this.maxNumberOfAllContainers--;
						this.maxNumberOfHeavyContainers--;
						this.totalWeightCapacity-=cont.weight;
						cont.currentPort=null;
						cont.currentShip=this;
						return true;
					}else {
						System.out.println("Maximum number of heavy containers loaded");
						return false;
					}
					
				}else if(cont.type()=="refrigerated") {
					if(this.maxNumberOfAllContainers>0 && this.maxNumberOfHeavyContainers>0 && this.maxNumberOfRefrigeratedContainers>0) {
						for(int i=0;i<this.currentPort.containers.size();i++) {
							Container c = this.currentPort.containers.get(i);
							if(c.equals(cont)) {
								this.currentPort.containers.remove(i);
								break;
							}
						}
						this.containers.add(cont);
						this.maxNumberOfAllContainers--;
						this.maxNumberOfHeavyContainers--;
						this.maxNumberOfRefrigeratedContainers--;
						this.totalWeightCapacity-=cont.weight;
						cont.currentPort=null;
						cont.currentShip=this;
						return true;
					}else {
						System.out.println("Maximum number of refrigerated containers loaded");
						return false;
					}
				}else {
					if(this.maxNumberOfAllContainers>0 && this.maxNumberOfHeavyContainers>0 && this.maxNumberOfLiquidContainers>0) {
						for(int i=0;i<this.currentPort.containers.size();i++) {
							Container c = this.currentPort.containers.get(i);
							if(c.equals(cont)) {
								this.currentPort.containers.remove(i);
								break;
							}
						}
						this.containers.add(cont);
						this.maxNumberOfAllContainers--;
						this.maxNumberOfHeavyContainers--;
						this.maxNumberOfLiquidContainers--;
						this.totalWeightCapacity-=cont.weight;
						cont.currentPort=null;
						cont.currentShip=this;
						return true;
					}else {
						System.out.println("Maximum number of liquid containers loaded");
						return false;
					}
				}
			}
		}else {
			System.out.println("Container not found in port "+this.currentPort.name);
			return false;
		}
		
	}
	
	@Override
	public void reFuel(double newFuel) {
		System.out.println("Current fuel level: "+this.fuel);
		System.out.println("Fueling the ship now!!!");
		this.fuel+=newFuel;
	}
	
	@Override
	public boolean unLoad(Container cont) {

		boolean containerFound = false;
		for(Container c : this.containers) {
			if(cont.equals(c)) {
				containerFound=true;
				break;
			}
		}
		if(containerFound) {
			
			if(cont.type()=="basic") {
				this.containers.remove(cont);
				this.currentPort.containers.add(cont);
				this.maxNumberOfAllContainers++;
				this.totalWeightCapacity+=cont.weight;
				cont.currentPort=this.currentPort;
				cont.currentShip=null;
				return true;
				
			}else if(cont.type()=="heavy") {
				
				this.containers.remove(cont);
				this.currentPort.containers.add(cont);
				this.maxNumberOfAllContainers++;
				this.maxNumberOfHeavyContainers++;
				this.totalWeightCapacity+=cont.weight;
				
				cont.currentPort=this.currentPort;
				cont.currentShip=null;
	
				return true;
				
			}else if(cont.type()=="refrigerated") {
				
				this.containers.remove(cont);
				this.currentPort.containers.add(cont);
				this.maxNumberOfAllContainers++;
				this.maxNumberOfHeavyContainers++;
				this.maxNumberOfRefrigeratedContainers++;
				this.totalWeightCapacity+=cont.weight;
				
				cont.currentPort=this.currentPort;
				cont.currentShip=null;
	
				return true;
				
			}else {
				this.containers.remove(cont);
				this.currentPort.containers.add(cont);
				this.maxNumberOfAllContainers++;
				this.maxNumberOfHeavyContainers++;
				this.maxNumberOfLiquidContainers++;
				this.totalWeightCapacity+=cont.weight;
				
				cont.currentPort=this.currentPort;
				cont.currentShip=null;

				return true;
			}
		}else {
			System.out.println("Container not found in ship "+this.name);
			return false;
		}
	}
	
	public Ship(int id,
			String name,
			Port p,
			int totalWeightCapacity,
			int maxNumberOfAllContainers,
			int maxNumberOfHeavyContainers,
			int maxNumberOfRefrigeratedContainers,
			int maxNumberOfLiquidContainers,
			double fuelConsumptionPerKM) {
		
		this.id = id;
		this.currentPort = p;
		this.name = name;
		
		this.currentPort.current.add(this);
		
		this.totalWeightCapacity = totalWeightCapacity;
		this.maxNumberOfAllContainers = maxNumberOfAllContainers;
		this.maxNumberOfHeavyContainers = maxNumberOfHeavyContainers;
		this.maxNumberOfRefrigeratedContainers = maxNumberOfRefrigeratedContainers;
		this.maxNumberOfLiquidContainers = maxNumberOfLiquidContainers;
		this.fuelConsumptionPerKM = fuelConsumptionPerKM;
		
		

	}
	
	ArrayList<Container> getCurrentContainers() {
		return this.containers;
	}
	

}
