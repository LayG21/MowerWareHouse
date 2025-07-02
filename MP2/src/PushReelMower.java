
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
 