package overlayManager;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ScoreImage extends Score {

	private Path scorePath;
	private Path teamPath;
	
	public ScoreImage(String teamName) {
		super(teamName);
		scorePath = scorePath(0);
		teamPath = teamPath(teamName);
	}
	

	public Path getScorePath() {
		return this.scorePath;
	}
	
	public Path getTeamPath() {
		return this.teamPath;
	}
	
	@Override
	public void addScore(int x) {
		super.addScore(x);
		this.scorePath = scorePath(this.getScore());
	}
	@Override
	public void addScore() {
		this.addScore(1);
	}
	
	
	@Override
	public void setTeam(Team team) {
		super.setTeam(team);
		this.teamPath = teamPath(team);
	}
	@Override
	public void resetScore() {
		super.resetScore();
		this.scorePath = scorePath(0);
	}
	
	public static Path scorePath(int score) {
		return Paths.get("../Assets/Score/_" + score + "_.png");
	}
	
	public static Path teamPath(Team team) {
		return Paths.get("../Assets/Teams/" + team.getName() + ".png");
	}
	
	public static Path teamPath(String teamName) {
		return Paths.get("../Assets/Teams/" + teamName + ".png");
	}

}
