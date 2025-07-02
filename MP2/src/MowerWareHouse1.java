import java.io.*; 
import java.util.*;

public class MowerWareHouse1 {
private String storeName;
private ArrayList<Mower> mowers;

public MowerWareHouse1() {
	storeName = "None";
	mowers = new ArrayList<Mower>();
} 

//Get and set for store name
public void setStoreName(String storeName) {
	this.storeName = storeName;
}
public String getStoreName() {
	return storeName;
}


//Get and set for ArrayList
public void setMowers(ArrayList<Mower> mowers) {
	this.mowers = mowers;
}

public ArrayList<Mower> getMowers() {
	return mowers;
}




//Handling ArrayList
public int getNumMowers() {
	return mowers.size();
}

public Mower getMower(int index) {
	return mowers.get(index);
}

public void setMower(int index, Mower element) {
	this.mowers.set(index, element);
}

public void add(Mower e) {
	this.mowers.add(e);
}

public Mower remove(int index) {
	return mowers.remove(index);
}

//read in from file
public void readMowerData(String inputFile) throws IOException {
	Mower m = null; // added on 3 17
 	Scanner scnr = new Scanner(new File(inputFile));
	storeName = scnr.nextLine();
	
	
	String manufacturer;				//name of manufacturer
	int year; 							// the year
	String serial;						// serial number
	String sub;							//To find the subclass that needs to be
										//accessed
	
	//System.out.println(getStoreName());
	while(scnr.hasNext()) {
	manufacturer = scnr.nextLine();
	year = Integer.parseInt(scnr.nextLine());
	serial = scnr.nextLine();
	
	sub = scnr.nextLine();
	switch(sub.charAt(0)) {
	case 'L': 
		LawnTractor lt = new LawnTractor();
		lt.setManufacturer(manufacturer);
	
		lt.setYear(year);
		lt.setSerialNumber(serial);
		
		Engine e = new Engine();
		e.setManufacturer(scnr.nextLine());
		e.setHorsePower(scnr.nextDouble());
		scnr.nextLine();
		e.setCylinders(scnr.nextInt());
		scnr.nextLine();
		
		lt.setEngine(e);
		
		lt.setModel(scnr.nextLine()); 
		lt.setDeckWidth(scnr.nextDouble());			//Model is not being stored
		scnr.nextLine();
		
		System.out.println(lt);					//Does not print out the other L, 
		m = lt;	//Added on 3 17					//but when I print out each part it is being stored
		break;
		
	case 'C':
		CommercialMower cm = new CommercialMower();
		
		cm.setManufacturer(manufacturer);
		cm.setYear(year);
		cm.setSerialNumber(serial);
		
		Engine cE = new Engine();
		cE.setManufacturer(scnr.nextLine());
		cE.setHorsePower(scnr.nextDouble());
		scnr.nextLine();
		cE.setCylinders(scnr.nextInt());
		scnr.nextLine();
		
		cm.setEngine(cE);
		
		cm.setModel(scnr.nextLine());
		cm.setDeckWidth(scnr.nextDouble());
		
		cm.setOperatingHours(scnr.nextDouble());
		cm.setZeroTurnRadius(scnr.nextBoolean());
		scnr.nextLine();
		
		//System.out.println(cm);
		m = cm; //added on 3 17
		break;
	case 'G':
		GasPoweredMower gp = new GasPoweredMower();
		gp.setManufacturer(manufacturer);
		gp.setYear(year);
		gp.setSerialNumber(serial);
		
		
		gp.setCutWidth(scnr.nextDouble());
		gp.setWheelDiameter(scnr.nextDouble());
		scnr.nextLine();
		
		Engine gE = new Engine();
		
		gE.setManufacturer(scnr.nextLine());
		gE.setHorsePower(scnr.nextDouble());
		gE.setCylinders(scnr.nextInt());
		gp.setEngine(gE);
		
		//System.out.println(scnr.nextLine());
		gp.setSelfPropelled(scnr.nextBoolean());
		scnr.nextLine();
		//System.out.println(gp);
		m = gp; //ADDED ON 3 17
		break;
	case 'P':
		PushReelMower pr = new PushReelMower();
		pr.setManufacturer(manufacturer);
		pr.setYear(year);
		pr.setSerialNumber(serial);
		
		pr.setCutWidth(scnr.nextDouble());
		pr.setWheelDiameter(scnr.nextDouble());
		pr.setNumWheels(scnr.nextInt());
		scnr.nextLine();
		//System.out.println(pr);
		m = pr; //added on 3 17
		break;
	}
	mowers.add(m);//added on 3 17
	}
	//Issue with reading in the model 
		
	}
	

//save data
public void saveMowerData(String outputFile) throws IOException {
	FileOutputStream fo = new FileOutputStream(outputFile);
	PrintWriter p = new PrintWriter(fo);
	p.println(this.toString());
	p.close();
}


//toString
//put each property on a separate line;
@Override
public String toString() {
	String out = getStoreName() + "\n";
	for(int i = 0; i < getNumMowers(); i++ ) {
		out += mowers.get(i);
	}
	return out;
}



}
