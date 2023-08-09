package shippingApplication;

import java.util.ArrayList;
import java.util.List;

public class Ship implements IShip{
	
	int id;
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
		
		return (distance+fuelReq)*this.fuelConsumptionPerKM;
		
	}
	
	
	@Override
	public boolean sailTo(Port p) {
		double fuelReq = this.fuelRequired(this.currentPort.getDistance(p));
		if(fuelReq>=this.fuel) {
			this.currentPort.outgoingShip(this);
			p.incomingShip(this);
			this.fuel-=fuelReq;
			this.currentPort=p;
			return true;
		}else {
			System.out.println("Not enough fuel available");
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
				System.out.println("Container weight is very high");
				return false;
			}else {
				if(cont.type()=="basic") {
					if(this.maxNumberOfAllContainers>0) {
						this.containers.add(cont); 
						this.maxNumberOfAllContainers--;
						this.totalWeightCapacity-=cont.weight;
						return true;
					}else {
						System.out.println("Invalid maximum number of containers");
						return false;
					}
				}else if(cont.type()=="heavy") {
					if(this.maxNumberOfHeavyContainers>0) {
						this.containers.add(cont);
						this.maxNumberOfAllContainers--;
						this.maxNumberOfHeavyContainers--;
						this.totalWeightCapacity-=cont.weight;
						return true;
					}else {
						System.out.println("Invalid maximum number of heavy containers");
						return false;
					}
					
				}else if(cont.type()=="refrigerated") {
					if(this.maxNumberOfRefrigeratedContainers>0) {
						this.containers.add(cont);
						this.maxNumberOfAllContainers--;
						this.maxNumberOfHeavyContainers--;
						this.maxNumberOfRefrigeratedContainers--;
						this.totalWeightCapacity-=cont.weight;
						return true;
					}else {
						System.out.println("Invalid maximum number of refrigerated containers");
						return false;
					}
				}else {
					if(this.maxNumberOfLiquidContainers>0) {
						this.containers.add(cont);
						this.maxNumberOfAllContainers--;
						this.maxNumberOfHeavyContainers--;
						this.maxNumberOfLiquidContainers--;
						this.totalWeightCapacity-=cont.weight;
						return true;
					}else {
						System.out.println("Invalid maximum number of liquid containers");
						return false;
					}
				}
			}
		}else {
			System.out.println("Container not found");
			return false;
		}
		
	}
	
	@Override
	public void reFuel(double newFuel) {
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
				this.maxNumberOfAllContainers++;
				this.totalWeightCapacity+=cont.weight;
				return true;
				
			}else if(cont.type()=="heavy") {
				
				this.containers.remove(cont);
				this.maxNumberOfAllContainers++;
				this.maxNumberOfHeavyContainers++;
				this.totalWeightCapacity+=cont.weight;
				return true;
				
			}else if(cont.type()=="refrigerated") {
				
				this.containers.remove(cont);
				this.maxNumberOfAllContainers++;
				this.maxNumberOfHeavyContainers++;
				this.maxNumberOfRefrigeratedContainers++;
				this.totalWeightCapacity+=cont.weight;
				return true;
				
			}else {
				this.containers.add(cont);
				this.maxNumberOfAllContainers++;
				this.maxNumberOfHeavyContainers++;
				this.maxNumberOfLiquidContainers++;
				this.totalWeightCapacity+=cont.weight;
				return true;
			}
		}else {
			System.out.println("Container not found");
			return false;
		}
	}
	
	public Ship(int id,
			Port p,
			int totalWeightCapacity,
			int maxNumberOfAllContainers,
			int maxNumberOfHeavyContainers,
			int maxNumberOfRefrigeratedContainers,
			int maxNumberOfLiquidContainers,
			double fuelConsumptionPerKM) {
		
		this.id = id;
		this.currentPort = p;
		this.totalWeightCapacity = totalWeightCapacity;
		this.maxNumberOfHeavyContainers = maxNumberOfHeavyContainers;
		this.maxNumberOfRefrigeratedContainers = maxNumberOfRefrigeratedContainers;
		this.maxNumberOfLiquidContainers = maxNumberOfLiquidContainers;
		this.fuelConsumptionPerKM = fuelConsumptionPerKM;

	}
	
	ArrayList<Container> getCurrentContainers() {
		return this.containers;
	}
	

}
