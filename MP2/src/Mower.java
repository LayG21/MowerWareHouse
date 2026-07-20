public abstract class Mower {
	private String manufacturer; 						// Mower manufacturer
	private int year; 									// Year of manufacture
	private String serialNumber; 						// Serial number of mower
	
	//Default Constructor
	public Mower() {
		this.manufacturer = "None";
		this.year = 0;
		this.serialNumber = "None";
	}
	 
	
	//Constructor
	public Mower(String mf, int y, String sNumber) {
		this.manufacturer = mf;
		this.year = y;
		this.serialNumber = sNumber;
	}
 
	
	//Setters and Getters
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer; 
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	
	
	public void setYear(int year) {
		this.year = year;
	}

	public int getYear() {
		return this.year;
	}

	
	
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getSerialNumber() {
		return this.serialNumber;
	}
	
	
	// label each property for user to confirm input (For UI)
	public String confirmString() {
		return "Mower Manufacturer: " + getManufacturer() + "\n\n"
				+ "Mower Year: " + String.format("%04d",getYear()) + "\n\n" 
				+ "Mower Serial Number: " + getSerialNumber();
	}
	
	//String for displaying all mowers of his type (For UI)
	public String listDisplayString() {
		return "Mower Manufacturer: " + getManufacturer() + "<br>"
				+ "Mower Year: " + String.format("%04d",getYear()) + "<br>" 
				+ "Mower Serial Number: " + getSerialNumber();
	}
	
	//ToString
	// Put each property on a separate line
	@Override
	public String toString() {
		return getManufacturer() + "\n" 
				+ String.format("%04d",getYear()) + "\n" 
				+ getSerialNumber(); 
	}




}
