public class GasPoweredMower extends WalkBehindMower {
	private Engine engine; // Mower engine and will be made as a type of gasPowered mower
	private boolean selfPropelled; // Is the mower self-propelled

	//Default Constructor
	public GasPoweredMower() {
		this.engine = null;
		this.selfPropelled = false;	
	}
	
	//Getters and Setters
	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setSelfPropelled(boolean selfPropelled) {
		this.selfPropelled = selfPropelled;
	}

	public boolean getSelfPropelled() {
		return selfPropelled;
	}

//toString
//put each property on a separate line
	@Override
	public String toString() {
		return getManufacturer() + "\n" 
		+ getYear() + "\n" 
		+ getSerialNumber() 
		+ "\nG\n" 
		+ super.toString() + "\n" 
		+ engine.toString() + "\n" 
		+ selfPropelled;
	}

}
