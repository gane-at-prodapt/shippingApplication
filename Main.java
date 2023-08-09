package shippingApplication;
import java.util.*;
import shipsandports.containers.Container;
public class Main {
public static void main(String[] args)
{
	Scanner sc=new Scanner(System.in);
	ArrayList<Container> conts=new ArrayList<Container>();
	ArrayList<Ship> ships=new ArrayList<Ship>();
	ArrayList<Port> ports=new ArrayList<Port>();
	boolean flag=true;
	do{
		System.out.println("Menu:-");
		System.out.println("1)Create a container");
		System.out.println("2)Create a ship");
		System.out.println("3)Create a port");
		System.out.println("4)Load container");
		System.out.println("5)Unload a container");
		System.out.println("6)Sail Ship to another port");
		System.out.println("7)Refuel Ship");
		System.out.println("8)Quit");
		System.out.println("Enter you choice");
		int n=sc.nextInt();
		switch(n)
		{
		case 1:{
			System.out.println("Enter the container ID:- ");
			int cont_ID=sc.nextInt();
			System.out.println("Enter the port ID:- ");
			int port_ID=sc.nextInt();
			System.out.println("Enter the weight for the conatainer:- ");
			int weight=sc.nextInt();
			Container cont;			
		}
		case 2:{
			System.out.println("Enter the ship ID:- ");
			int ship_ID=sc.nextInt();
			System.out.println("Enter the port ID in which the ship is present:- ");
			int port_ID=sc.nextInt();
			System.out.println("Enter the total weight capacity:- ");
			int totalWeightCapacity=sc.nextInt();
			System.out.println("Enter maximum number of containers:- ");
			int maxNumberOfAllContainers=sc.nextInt();
			System.out.println("Enter maximum number of heavy containers:- ");
			int maxNumberOfHeavyContainers=sc.nextInt();
			System.out.println("Enter maximum number of referigerated containers:- ");
			int maximumNumberOfReferigeratedContainers=sc.nextInt();
			System.out.println("Enter maximum number of liquid containers:- ");
			int maximumNumberOfLiquidContainers=sc.nextInt();
			System.out.println("Enter FuelConsumption per kilometer:- ");
			double fuelConsumptionPerKM=sc.nextDouble();
			ships.add(new Ship(ship_ID,ports.get(port_ID),totalWeightCapacity,maxNumberOfAllContainers,maxNumberOfHeavyContainers,maximumNumberOfReferigeratedContainers,maximumNumberOfLiquidContainers,fuelConsumptionPerKM));
			System.out.println("Ship "+ship_ID+"created successfully");
			break;}
		case 3: {
			int port_ID=ports.size();
			System.out.println("Enter the latitude:- ");
			double x=sc.nextDouble();
			System.out.println("Enter the longitude:- ");
			double y=sc.nextDouble();
			ports.add(new Port(port_ID,x,y));
			System.out.println("Port "+port_ID+" added successfully");
			break;
		}
		case 4: {
			System.out.println("Enter the ship ID to load the container in it:- ");
			int ship_ID=sc.nextInt();
			System.out.println("Enter the container ID that needs to be loaded:- ");
			int cont_ID=sc.nextInt();
			//ships.get(ship_ID).load(conts.get(cont_ID));
			System.out.println("The container "+cont_ID+" is loaded into the ship "+ship_ID+" successfully");
			break;
		}
		case 5: {
			System.out.println("Enter the ship ID to unload the container from it:- ");
			int ship_ID=sc.nextInt();
			System.out.println("Enter the container ID that needs to be unloaded:- ");
			int cont_ID=sc.nextInt();
			//ships.get(ship_ID).unLoad(conts.get(cont_ID));
			System.out.println("The container "+cont_ID+" is unloaded from the ship "+ship_ID+" successfully");
			break;
		}
		case 6: {
			System.out.println("Enter the ID of the ship that needed to visit another port:- ");
			int ship_ID=sc.nextInt();
			System.out.println("Enter the ID of the port which is the next destination of the ship:- ");
			int port_ID=sc.nextInt();
			ships.get(ship_ID).sailTo(ports.get(port_ID));
			System.out.println("Ship "+ship_ID+" departed to the other port "+port_ID);
			break;
		}
		case 7: {
			System.out.println("Enter the shipID to refuel:- ");
			int ship_ID=sc.nextInt();
			System.out.println("Enter the quantity of fuel:- ");
			double fuel=sc.nextDouble();
			//ships.get(ship_ID).refuel(fuel);
			System.out.println("The ship "+ship_ID+" refueled successfully");
			break;
		}
		case 8:
			flag = false;
			break;
		default:
		{
			System.out.println("Invalid operation");
		}
		}
	}while(flag);
}
}