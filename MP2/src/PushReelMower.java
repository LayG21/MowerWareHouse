
public class PushReelMower extends WalkBehindMower {
	private int numWheels; // Number of wheels on the mower

//Default constructor
	public PushReelMower() {
		this.numWheels = 0;
	}

//Getters and Setters
	public void setNumWheels(int numWheels) {
		this.numWheels = numWheels;
	}

	public int getNumWheels() {
		return numWheels;
	}

// label each property for user to confirm input (For UI)
	public String confirmString() {
		return super.confirmString() + "\n\n"
				+"Push Reel Mower Number of Wheels: " + getNumWheels();
	}
	
	//String for displaying all mowers of his type (For UI)
	@Override
	public String listDisplayString() {
		return "<html>"
				+super.listDisplayString() + "<br>"
			    + "Number of Wheels: " + getNumWheels()
			    + "</html>";
	}
//ToString
//put each property on a separate line
	@Override
	public String toString() {
		return getManufacturer() + "\n" 
	    + String.format("%04d",getYear()) + "\n"
	    + getSerialNumber()
	    + "\nP\n"
	    + super.toString() + "\n"
	    + getNumWheels();
	}

}
 