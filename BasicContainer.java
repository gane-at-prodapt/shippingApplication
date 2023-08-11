package shippingApplication;

public class BasicContainer extends Container{

	BasicContainer(int id, int weight,Port currentPort) {
		super(id, weight,currentPort);
	}

	@Override
	double consumption() {
		return 2.50*this.weight;
	}

	@Override
	String type() {
		return "basic";	
	}

}
