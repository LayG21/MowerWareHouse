
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

// label each property for user to confirm input
	public String confirmString() {
		return super.confirmString() + "\n"
				+"Number of Wheels: " + getNumWheels();
	}
//ToString
//put each property on a separate line
	@Override
	public String toString() {
		return getManufacturer() + "\n" 
	    + getYear() + "\n"
	    + getSerialNumber()
	    + "\nP\n"
	    + super.toString() + "\n"
	    + numWheels;
	}

}
 