
public abstract class WalkBehindMower extends Mower{
private double cutWidth;		//Blade width of mower		
private double wheelDiameter;	//Diameter of the mower wheels


//Default Constructor
public WalkBehindMower() {
	this.cutWidth = 0.0;
	this.wheelDiameter = 0.0;
}


public void setCutWidth(double cw) {
	this.cutWidth = cw;
}

public double getCutWidth() {
	return this.cutWidth;
}

public void setWheelDiameter(double wd) {
	this.wheelDiameter = wd;
}

public double getWheelDiameter() {
	return this.wheelDiameter;
}


//label each property for user to confirm input (For UI)
@Override
	public String confirmString() {
		return super.confirmString() + "\n\n"
				+ "Walk Behind Mower Cut Width: " + getCutWidth() +"\n\n"
				+ "Walk Behind Mower Wheel Diameter: " + getWheelDiameter();
	}

//String for displaying all mowers of this type (For UI)
public String listDisplayString() {
	return 	super.listDisplayString() + "<br>"
			+ "Cut Width: " + getCutWidth() + "<br>"
			+ "Wheel Diameter: " + getWheelDiameter();
}

//ToString 
//Put each property on a separate line
@Override
public String toString() {
	return getCutWidth() + "\n" 
			+ getWheelDiameter();
}










}
