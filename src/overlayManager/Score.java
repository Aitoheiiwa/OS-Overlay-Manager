package overlayManager;

public class Score {
	
	private int score;
	private Team team;
	
	
	public Score(String teamName) {
		this.score = 0;
		this.team = new Team(teamName);
	}
	
	public void addScore(int x) {
		this.score += x;
	}
	
	public void addScore() {
		this.addScore(1);
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void resetScore() {
		this.score = 0;
	}
	
	public void setTeam(Team team) {
		this.team = team;
	}
	
	public String getTeamName() {
		return this.team.getName();
	}
	protected Team getTeam() {
		return this.team;
	}

}
