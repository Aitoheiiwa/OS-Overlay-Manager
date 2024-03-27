package overlayManager;

import java.nio.file.Path;
import java.nio.file.Paths;

public  class  Match {

	private String name;
	private Path matchPath;
	
	private static final  String MATCHPATH = "../Assets/Match/";
	
	public static final String[] MATCHLIST = {"Unselected", 
											  "Grand Final",
											  "Winners Final",
											  "Losers Final",
											  "Winners Semi-final",
											  "Losers Semi-Final", 
											  "Winners Quarter-final",
											  "Losers Quarter-final",
											  "Group A",
											  "Group B",
											  "Group C",
											  "Group D"};
	
	
	public Match(String name) {
		this.name = name;
		this.matchPath = Paths.get(MATCHPATH + this.name + ".png");
	}
	
	public Path getPath() {
		return this.matchPath;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
		this.matchPath = Paths.get(MATCHPATH +this.name + ".png");

	}

}
