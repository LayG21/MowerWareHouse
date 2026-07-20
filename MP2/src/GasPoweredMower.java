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
	
	// label each property for user to confirm input (For UI)
	public String confirmString() {
		return super.confirmString() + "\n\n"
				+ getEngine().confirmString() + "\n\n"
				+ "Is Self Propelled?: " + getSelfPropelled();
	}
	
	//String for displaying all mowers of his type (For UI)
	@Override
	public String listDisplayString() {
		return "<html>"
				+ super.listDisplayString() + "<br>"
				+ getEngine().listDisplayString() + "<br>"
				+ "Is Self Propelled?: " + getSelfPropelled()
				+ "</html>";
	}
//toString
//put each property on a separate line
	@Override
	public String toString() {
		return getManufacturer() + "\n" 
		+ String.format("%04d",getYear()) + "\n" 
		+ getSerialNumber() 
		+ "\nG\n" 
		+ super.toString() + "\n" 
		+ getEngine().toString() + "\n" 
		+ getSelfPropelled();
	}

}
