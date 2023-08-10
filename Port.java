package shippingApplication;
import java.util.ArrayList;
import java.lang.Math;

public class Port implements IPort{
	
	int id;
	double x,y;
	ArrayList<Container> containers = new ArrayList<Container>();
	ArrayList<Ship> history = new ArrayList<Ship>();
	ArrayList<Ship> current = new ArrayList<Ship>();
	
	@Override
	public void incomingShip(Ship s) {
		
		current.add(s);
		
	}
	
	@Override
	public void outgoingShip(Ship s) {
		current.remove(s);
		
		for(Ship i: history) {
			if(s.equals(i)) {
				history.remove(i);
				break;
			}
		}
		history.add(s);
	}
	
	Port(int id, double x, double y){

		this.id = id;
		this.x = x;
		this.y =y;
	}
	
	static double degreesToRadians(double degrees) {

		return degrees * Math.PI / 180.0;
	}
	
	double getDistance(Port other) {

		
		double earthRadiusKm = 6371;
		
		double lat1 = this.x, lon1 = this.y, lat2 = other.x, lon2 = other.y;
		double dLat = degreesToRadians(lat2-lat1);
		double dLon = degreesToRadians(lon2-lon1);

		lat1 = degreesToRadians(lat1);
		lat2 = degreesToRadians(lat2);

		double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
		          Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2); 
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		
		return earthRadiusKm * c;
	}
	
	
	
	
	

}
