public class Engine{
	
	private String manufacturer;							// Engine manufacturer
	private double horsePower;							    // Horse power of engine
	private int cylinders;									// Number of cylinders
	
	// Default constructor
	public Engine() {
		this.manufacturer = "None";
		this.horsePower = 0.0;
		this.cylinders = 0;
	}
	
	 
	// Constructor 
	public Engine(String mn, double hp, int cl) {
		this.manufacturer = mn;
		this.horsePower = hp;
		this.cylinders = cl;
		
	}
	 
	
	
	//Setters and Getters

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	
	
	public double getHorsePower() {
		return horsePower;
	}

	public void setHorsePower(double horsePower) {
		this.horsePower = horsePower;
	}

	
	
	public int getCylinders() {
		return cylinders;
	}

	public void setCylinders(int cylinders) {
		this.cylinders = cylinders;
	}
	
	
	//Put every property on separate line
	@Override
	public String toString() {
		return manufacturer + "\n" + horsePower + "\n" + cylinders;
	}
	
}

