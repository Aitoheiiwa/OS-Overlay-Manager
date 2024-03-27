package overlayManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class BestOfX {

	public static final Path blueTeamImage =  Paths.get("../Displayed/blue_Team.png");
	public static final Path redTeamImage =  Paths.get("../Displayed/red_Team.png");
	public static final Path blueScoreImage =  Paths.get("../Displayed/blue_Team_Score.png");
	public static final Path redScoreImage =  Paths.get("../Displayed/red_Team_Score.png");
	public static final Path currentMatchImage =  Paths.get("../Displayed/current_Match.png");
	private Match match ;
	private TeamImage blueTeam;
	private TeamImage redTeam;
	private ScoreImage blueScore;
	private ScoreImage redScore;
	
	
	public BestOfX(Match match, TeamImage blueTeam, TeamImage redTeam) {
		this.match = match;
		this.blueTeam = blueTeam;
		this.blueScore = new ScoreImage();
		this.redTeam = blueTeam;
		this.redScore = new ScoreImage();	
		}
	
	public Match getMatch() {
		return this.match;
	}
	
	public Team getBlueTeam() {
		return this.blueTeam;
	}
	
	public Team getRedTeam() {
		return this.redTeam;
	}
	
	public Score getBlueScore() {
		return this.blueScore;
	}
	
	public Score getRedScore() {
		return this.redScore;
	}
	
	public void updateMatch() {
		try {
			Files.copy(this.match.getPath(),currentMatchImage,StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) { System.out.println("Can not find " + this.match.getPath());}
	}
	public void updateBlueTeam() {
		try {
			Files.copy(this.blueTeam.getPath(),blueTeamImage,StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) { System.out.println("Can not find " + this.blueTeam.getPath());}
	}
	
	public void updateRedTeam() {
		try {
			Files.copy(this.redTeam.getPath(),redTeamImage,StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) { System.out.println("Can not find " + this.redTeam.getPath());}
	}
	
	public void updateBlueScore() {
		try {
			Files.copy(this.blueScore.getPath(),blueScoreImage,StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) { System.out.println("Can not find " + this.blueScore.getPath());}
	}
	
	public void updateRedScore() {
		try {
			Files.copy(this.redScore.getPath(),redScoreImage,StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) { System.out.println("Can not find " + this.redScore.getPath());}
	}
	
}
