package shippingApplication;

public class RefrigeratedContainer extends HeavyContainer{

	RefrigeratedContainer(int id, int weight) {
		super(id, weight);
	}
	
	@Override
	double consumption() {
		return 5.0*this.weight;
	}
	
	@Override
	String type() {
		return "refrigerated";	
	}


}
