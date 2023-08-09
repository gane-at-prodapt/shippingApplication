package shippingApplication;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	static ArrayList<Ship> ships=new ArrayList<Ship>();
	static ArrayList<Port> ports=new ArrayList<Port>();
	static ArrayList<Container> containers = new ArrayList<Container>();
	
	static Port getPortById(int id) {
		for(Port p : ports) {
			if(p.id==id) {
				return p;
			}
		}
		return null;
	}
	
	static Ship getShipById(int id) {
		for(Ship s : ships) {
			if(s.id==id) {
				return s;
			}
		}
		return null;
	}
	
	static Container getContainerById(int id) {
		for(Container c : containers) {
			if(c.id==id) {
				return c;
			}
		}
		return null;
	}
	
	static void displayPorts(){
		for(Port p: ports) {
			System.out.println("port "+p.id);
			System.out.print("current ships: ");
			for(Ship s : p.current) {
				System.out.print(s.id+" ");
			}
			System.out.println();
			System.out.print("ship history: ");
			for(Ship s : p.history) {
				System.out.print(s.id+" ");
			}
			System.out.println();
			for(Container c : p.containers) {
				System.out.print(c.id+" ");
			}
			System.out.println();
		}
	
	}
	static void displayShips() {
		for(Ship s:ships) {
			System.out.println("Ship"+s.id);
			System.out.println("fuel: "+s.fuel);
			System.out.println("port: "+s.currentPort);
			for(Container c : s.containers) {
				System.out.print(c.id+" ");
			}
			System.out.println();
			
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		
		System.out.println("*********Welcome to shipping application*********");
		
		boolean flag=true;
		do{
			System.out.println("Menu:");
			System.out.println("1)Create a container");
			System.out.println("2)Create a ship");
			System.out.println("3)Create a port");
			System.out.println("4)Load container");
			System.out.println("5)Unload a container");
			System.out.println("6)Sail Ship to another port");
			System.out.println("7)Refuel Ship");
			System.out.println("8)Display Ports");
			System.out.println("9)Display Ships");
			System.out.println("10)Quit");
			System.out.println("Enter you choice: ");
			int n=sc.nextInt();
			switch(n)
			{
				case 1:{
					
					System.out.println("Enter the port ID: ");
					int port_ID=sc.nextInt();
					Port p = getPortById(port_ID);
					if(p==null) {
						System.out.println("Port "+port_ID+" not found!");
						break;
					}  
					System.out.println("Enter the weight for the container: ");
					int weight=sc.nextInt();
					int cont_ID;
					if(containers.size()==0) {
						cont_ID = containers.get(containers.size()-1).id+1;
					}else {
						cont_ID = 0;
					}
					
					if(weight<=3000) {
						containers.add(new BasicContainer(cont_ID,weight));
						p.containers.add(new BasicContainer(cont_ID,weight));
						System.out.println("Port successfully created.");
						
					}else {
						System.out.println("This is a heavy container, Enter its type(R - Refrigerated / L - Liquid)"
								+ " Press enter to continue as normal heavy container");
						String type = sc.next();
						if(type.isBlank()) {
							containers.add(new HeavyContainer(cont_ID,weight));
							p.containers.add(new HeavyContainer(cont_ID,weight));
						}else if(type.equalsIgnoreCase("R")) {
							containers.add(new RefrigeratedContainer(cont_ID,weight));
							p.containers.add(new RefrigeratedContainer(cont_ID,weight));
						}else if(type.equalsIgnoreCase("L")) {
							containers.add(new LiquidContainer(cont_ID,weight));
							p.containers.add(new LiquidContainer(cont_ID,weight));
						}else {
							System.out.println("Invalid response, try again!");
						}
					}
					break;
				}
				case 2:{
					int ship_ID=ships.get(ships.size()-1).id+1;
					System.out.println("Enter the port ID in which the ship is present:- ");
					int port_ID=sc.nextInt();
					Port p = getPortById(port_ID);
					if(p==null) {
						System.out.println("Port "+port_ID+" not found!");
						break;
					}  
					System.out.println("Enter the total weight capacity:- ");
					int totalWeightCapacity=sc.nextInt();
					System.out.println("Enter maximum number of containers:- ");
					int maxNumberOfAllContainers=sc.nextInt();
					System.out.println("Enter maximum number of heavy containers:- ");
					int maxNumberOfHeavyContainers=sc.nextInt();
					System.out.println("Enter maximum number of refrigerated containers:- ");
					int maximumNumberOfRefrigeratedContainers=sc.nextInt();
					System.out.println("Enter maximum number of liquid containers:- ");
					int maximumNumberOfLiquidContainers=sc.nextInt();
					System.out.println("Enter FuelConsumption per kilometer:- ");
					double fuelConsumptionPerKM=sc.nextDouble();
					ships.add(new Ship(ship_ID,ports.get(port_ID),totalWeightCapacity,maxNumberOfAllContainers,maxNumberOfHeavyContainers,maximumNumberOfRefrigeratedContainers,maximumNumberOfLiquidContainers,fuelConsumptionPerKM));
					System.out.println("Ship "+ship_ID+"created successfully");
					break;
				}
				case 3: {
					int port_ID=ports.get(ports.size()-1).id+1;
					System.out.println("Enter the latitude: ");
					double x=sc.nextDouble();
					System.out.println("Enter the longitude: ");
					double y=sc.nextDouble();
					ports.add(new Port(port_ID,x,y));
					System.out.println("Port "+port_ID+" added successfully");
					break;
				}
				case 4: {
					System.out.println("Enter the ship ID to load the container in it:- ");
					int ship_ID=sc.nextInt();
					Ship s = getShipById(ship_ID);
					if(s==null) {
						System.out.println("Ship "+ship_ID+" not found!");
						break;
					}
					System.out.println("Enter the container ID that needs to be loaded:- ");
					int cont_ID=sc.nextInt();
					Container c = getContainerById(cont_ID);
					if(c==null) {
						System.out.println("Container "+cont_ID+" not found!");
						break;
					}
					boolean isLoaded = s.load(c);
					if(isLoaded) {
						System.out.println("The container "+cont_ID+" is loaded into the ship "+ship_ID+" successfully");
					}else {
						System.out.println("Loading unsuccessful!");
					}
					
					break;
				}
				case 5: {
					System.out.println("Enter the ship ID to unload the container from it:- ");
					int ship_ID=sc.nextInt();
					Ship s = getShipById(ship_ID);
					if(s==null) {
						System.out.println("Ship "+ship_ID+" not found!");
						break;
					}
					System.out.println("Enter the container ID that needs to be unloaded:- ");
					int cont_ID=sc.nextInt();
					Container c = getContainerById(cont_ID);
					if(c==null) {
						System.out.println("Container "+cont_ID+" not found!");
						break;
					}
					boolean isUnLoaded = s.unLoad(c);
					if(isUnLoaded) {
						System.out.println("The container "+cont_ID+" is unloaded from the ship "+ship_ID+" successfully");
					}else {
						System.out.println("Unloading unsuccessful!");
					}
					
					break;
				}
				case 6: {
					System.out.println("Enter the ID of the ship that needed to visit another port:- ");
					int ship_ID=sc.nextInt();
					Ship s = getShipById(ship_ID);
					if(s==null) {
						System.out.println("Ship "+ship_ID+" not found!");
						break;
					}
					System.out.println("Enter the ID of the port which is the next destination of the ship:- ");
					int port_ID=sc.nextInt();
					Port p = getPortById(port_ID);
					if(p==null) {
						System.out.println("Port "+port_ID+" not found!");
						break;
					}  
					boolean isSailed = s.sailTo(p);
					if(isSailed) {
						System.out.println("Ship "+ship_ID+" departed to the other port "+port_ID);
					}else {
						System.out.println("Sailing unsucessful!");
					}
					break;
				}
				case 7: {
					System.out.println("Enter the shipID to refuel:- ");
					int ship_ID=sc.nextInt();
					Ship s = getShipById(ship_ID);
					if(s==null) {
						System.out.println("Ship "+ship_ID+" not found!");
						break;
					}
					System.out.println("Enter the quantity of fuel:- ");
					double fuel=sc.nextDouble();
					s.reFuel(fuel);
					System.out.println("The ship "+ship_ID+" refueled successfully");
					break;
				}
				case 8: {
					if(ports.size()==0) {
						System.out.println("No ports available");
					}
					else {
					displayPorts();
					}
					break;
				}
				case 9:{
					if(ships.size()==0) {
						System.out.println("No ships available");
					}
					else {
					displayShips();
					}
					break;
				}
				case 10:{
					flag = false;
					break;
				}
				default:{
					System.out.println("Invalid operation");
				}
			}
		}while(flag);
		sc.close();
	}
}