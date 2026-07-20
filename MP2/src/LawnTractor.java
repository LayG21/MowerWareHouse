public class LawnTractor extends Mower{
	private Engine engine;						// Mower Engine
	private String model;					    // Model of Lawn Tractor
	private double deckwidth;					// Width of Mower Deck
	
	//mower has a engine
	
	// Default Constructor
	public LawnTractor() {
		this.engine = new Engine();
		this.model = "None";
		this.deckwidth = 0.0;
	}
	
	//Setters and Getters
	public Engine getEngine() { 
		return engine;
	}


	public void setEngine(Engine engine) {
		this.engine = engine;
	} 

	
	

	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}

	
	

	public double getDeckWidth() {
		return deckwidth;
	}


	public void setDeckWidth(double deckwidth) {
		this.deckwidth = deckwidth;
	}
	
	// label each property for user to confirm input (For UI)
	public String confirmString() {
		return super.confirmString() + "\n\n"
				+ getEngine().confirmString() + "\n\n"
				+ "Lawn Tractor Model: " + getModel() + "\n\n"
				+ "Lawn Tractor Deck Width: " + getDeckWidth();
	}
	
	//String for displaying all mowers of his type (For UI)
	@Override
	public String listDisplayString() {
		return "<html>"
				+ super.listDisplayString() + "<br>"
				+ getEngine().listDisplayString() + "<br>"
				+ "Lawn Tractor Model: " + getModel() + "<br>"
				+ "Lawn Tractor Deck Width: " + getDeckWidth()
				+ "</html>";
	}
	//Put each property on a separate line
	@Override
	public String toString() {
		return super.toString() 
				+ "\nL\n" 
				+ getEngine().toString() + "\n" 
				+ getModel() + "\n" 
				+ getDeckWidth();
		
	}
	
}