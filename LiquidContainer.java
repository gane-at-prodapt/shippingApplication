package shippingApplication;

public class LiquidContainer extends HeavyContainer{

	LiquidContainer(int id, int weight,Port currentPort) {
		super(id, weight, currentPort);
	}
	
	@Override
	double consumption() {
		
		return 4.0*this.weight;
	}
	
	@Override
	String type() {
		return "liquid";	
	}


}
