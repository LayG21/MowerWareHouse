import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MowerWareHouse {
	
	private String storeName;					//Name of the mower store
	private ArrayList<Mower> mowers;		    // List of mowers in the warehouse
	private String outString;
	
	// Constructor
	public MowerWareHouse() {
		storeName = null;
		mowers = new ArrayList<Mower>();
		outString = "";
	}
	
	//Setters and getters
	
	public void setStoreName(String s) {
		this.storeName = s;
	}
	public String getStoreName() {
		return this.storeName;
	}
	
	public void setMowersList(ArrayList<Mower> m) {
		this.mowers = m;
	}
	
	public ArrayList<Mower> getMowersList(){
		return this.mowers;
	}
	
	public String getOutString() {
		return this.outString;
	}
	
	public void setOutString(String outcome) {
		this.outString = outcome;
	}
	
	//Methods for ArrayList
	
	//Returns number of items in mower list
	public int getNumMowers() {
		return this.mowers.size();
	}
	
	//Get mower at index in ArrayList
	public Mower getMower(int index) {
		return this.mowers.get(index);
	}
	
	//Store mower at location index in the mower ArrayList
	public void setMower (int index, Mower m) {
		this.mowers.set(index, m);
	}
	
	//Append Mower to mower list
	public void addMower(Mower m) {
		this.mowers.add(m);
	}
	
	//Remove and return the widget at index
	public Mower removeWidget(int index) {
		return this.mowers.remove(index);
	}
	
	// Read in input file
	public void readMowerData(String inputFileName) {
		try(Scanner scnr = new Scanner(new File(inputFileName))){
			storeName = scnr.nextLine();
			//System.out.println(storeName);
			
			while(scnr.hasNextLine()) {
				String mManufacturer = scnr.nextLine();
				int mYear = Integer.parseInt(scnr.nextLine());
				String mSNumber = scnr.nextLine();
				String subCat = scnr.nextLine();
				
				switch(subCat.charAt(0)) {
					case 'L':
						LawnTractor lt = new LawnTractor();
						
						
						String lManu = scnr.nextLine();
						double lHPower = Double.parseDouble(scnr.nextLine());
						int lCylinders = Integer.parseInt(scnr.nextLine());
						String lModel = scnr.nextLine();
						double lDeckWidth = Double.parseDouble(scnr.nextLine());
						
						lt.setManufacturer(mManufacturer);
						lt.setYear(mYear);
						lt.setSerialNumber(mSNumber);
						
						lt.setEngine(new Engine(lManu,lHPower,lCylinders));
						
						lt.setModel(lModel);
						lt.setDeckWidth(lDeckWidth);
						
						this.mowers.add(lt);
						//System.out.println(lt.toString());
						break;
					case 'C':
						CommercialMower cm = new CommercialMower();
						String cManu = scnr.nextLine();
						double cHPower = Double.parseDouble(scnr.nextLine());
						int cCylinders = Integer.parseInt(scnr.nextLine());
						
						String cModel = scnr.nextLine();
						double cDeckWidth = Double.parseDouble(scnr.nextLine());
						
						double cOperatingHours = Double.parseDouble(scnr.nextLine());
						boolean cZeroTurnRadius = Boolean.parseBoolean(scnr.nextLine());
						
						cm.setManufacturer(mManufacturer);
						cm.setYear(mYear);
						cm.setSerialNumber(mSNumber);
						
						cm.setEngine(new Engine(cManu,cHPower,cCylinders));
						
						cm.setModel(cModel);
						cm.setDeckWidth(cDeckWidth);
						
						cm.setOperatingHours(cOperatingHours);
						cm.setZeroTurnRadius(cZeroTurnRadius);
						
						this.mowers.add(cm);
						
						//System.out.println(cm.toString());
						break;
					case 'G':
						GasPoweredMower gw = new GasPoweredMower();
						
						double gCutWidth = Double.parseDouble(scnr.nextLine());
						double gWheelDiameter = Double.parseDouble(scnr.nextLine());
						
						String gManu = scnr.nextLine();
						double ghPower = Double.parseDouble(scnr.nextLine());
						int gCylinders = Integer.parseInt(scnr.nextLine());
						
						boolean gSelfPropelled = Boolean.parseBoolean(scnr.nextLine());
						
						gw.setManufacturer(mManufacturer);
						gw.setYear(mYear);
						gw.setSerialNumber(mSNumber);
						
						gw.setCutWidth(gCutWidth);
						gw.setWheelDiameter(gWheelDiameter);
						
						gw.setEngine(new Engine(gManu,ghPower,gCylinders));
						
						gw.setSelfPropelled(gSelfPropelled);
						
						this.mowers.add(gw);
						//System.out.println(gw.toString());
						break;
					case 'P':
						PushReelMower pm = new PushReelMower();
						
						double pCutWidth = Double.parseDouble(scnr.nextLine());
						double pWheelDiameter = Double.parseDouble(scnr.nextLine());
						
						int pNumWheels = Integer.parseInt(scnr.nextLine());
						
						pm.setManufacturer(mManufacturer);
						pm.setYear(mYear);
						pm.setSerialNumber(mSNumber);
						
						pm.setCutWidth(pCutWidth);
						pm.setWheelDiameter(pWheelDiameter);
						
						pm.setNumWheels(pNumWheels);
						
						this.mowers.add(pm);
						
						//System.out.println(pm.toString());
						break;
				}
			}
			setOutString("File Loaded!");
		}
		catch(Exception e) {
			setOutString("File Not Opened");
			System.err.println("Trouble reading file: " + e.getMessage());
			//e.printStackTrace();
		}
	}
	
	//Save mower data input file
	public void saveMowerData(String outputFileName) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
			writer.write(this.toString());
			setOutString("File Successfully Saved!");
			System.out.println("File saved successfully.");
			
		}
		catch(Exception e) {
			setOutString("File Not Saved");
			System.err.println("Trouble saving file:" + e.getMessage());
			//e.printStackTrace();
		}
	}
	
	
	//Output toString for mowers
	public String toString() {
		String out = getStoreName() + "\n";
		
		for(Mower mower : mowers) {
			out+= mower + "\n";
		}
		return out.trim();
	}
	

}
