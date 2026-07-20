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
	
	// label each property for user to confirm input (For UI)
	public String confirmString() {
		return super.confirmString() + "\n\n"
				+ "Commercial Operating Hours: " + getOperatingHours() + "\n\n"
				+ "Is Zero Turn Radius?: " + getZeroTurnRadius();
	}
	
	//String for displaying all mowers of his type (For UI)
	@Override
	public String listDisplayString() {
		return "<html>"
				+ super.listDisplayString().replace("<html>", "").replace("</html>", "") + "<br>"
				+ "Commercial Operating Hours: " + getOperatingHours() + "<br>"
				+ "Is Zero Turn Radius?: " + getZeroTurnRadius()
				+ "</html>";
	}
	
	//Put each property on a separate line 
	@Override
	public String toString() {
		return getManufacturer() + "\n" 
	    + String.format("%04d",getYear())+ "\n" 
		+ getSerialNumber() 
		+ "\nC\n" 
		+ getEngine().toString() + "\n" 
		+ getModel() + "\n" 
		+ getDeckWidth() + "\n" 
		+ getOperatingHours() + "\n" 
		+ getZeroTurnRadius();
	}
	
}

