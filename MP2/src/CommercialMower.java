// new updated code. do not include in count:
public class CommercialMower extends LawnTractor{
	private double operatingHours;
	private boolean zeroTurnRadius;
	
	
	//Default Constructor
	public CommercialMower() {
		this.operatingHours = 0.0;
		this.zeroTurnRadius = false;
	}
	
	//Setters and Getters
	public void setOperatingHours(double o) {
		this.operatingHours = o;
	} 
	
	public double getOperatingHours() {
		return operatingHours;
	}
	
	
	
	public void setZeroTurnRadius(boolean z) {
		this.zeroTurnRadius = z;
	}
	public boolean getZeroTurnRadius() {
		return zeroTurnRadius;
	}
	
	 
	//Put each property on a separate line 
	@Override
	public String toString() {
		return getManufacturer() + "\n" 
	    + getYear() + "\n" 
		+ getSerialNumber() 
		+ "\nC\n" 
		+ getEngine().toString() + "\n" 
		+ getModel() + "\n" 
		+ getDeckWidth() + "\n" 
		+ operatingHours + "\n" 
		+ zeroTurnRadius;
	}
	
}

