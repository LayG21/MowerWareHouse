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
		return manufacturer;
	}

	
	
	public void setYear(int year) {
		this.year = year;
	}

	public int getYear() {
		return year;
	}

	
	
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	
	// Put each property on a separate line
	@Override
	public String toString() {
		return manufacturer + "\n" + year + "\n" + serialNumber; 
	}

}
