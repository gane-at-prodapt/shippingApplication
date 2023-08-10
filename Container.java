package shippingApplication;

abstract class Container {
	int id;
	int weight;
	double bill=0.0;
	Container(int id,int weight){
		this.id = id;
		this.weight = weight;
	}
	
	abstract double consumption();
	abstract String type();
	boolean equals(Container other) {
		if(other.id==this.id) {
			return true;
		}
		return false;
	}
	
}