package overlayManager;

import java.nio.file.Path;
import java.nio.file.Paths;

public  class  Match {

	private String name;
	private Path matchPath;
	private ScoreImage blueTeamScore;
	private ScoreImage redTeamScore;
	
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
	
	
	public Match(String name, String blueTeamName, String redTeamName) {
		this.name = name;
		this.matchPath = Paths.get(MATCHPATH + this.name + ".png");
		this.blueTeamScore = new ScoreImage(blueTeamName);
		this.redTeamScore = new ScoreImage(redTeamName);
	}
	
	public Path getMatchPath() {
		return this.matchPath;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
		this.matchPath = Paths.get(MATCHPATH +this.name + ".png");

	}
	protected ScoreImage getBlueTeamScore() {
		return this.blueTeamScore;
	}
	protected ScoreImage getRedTeamScore() {
		return this.redTeamScore;
	}

}
