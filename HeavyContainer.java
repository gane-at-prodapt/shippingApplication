package shippingApplication;

public class HeavyContainer extends Container{

	HeavyContainer(int id, int weight,Port currentPort) {
		super(id, weight,currentPort);
		
	}

	@Override
	double consumption() {
		
		return 3.0*this.weight;
	}
	
	@Override
	String type() {
		return "heavy";	
	}

}
