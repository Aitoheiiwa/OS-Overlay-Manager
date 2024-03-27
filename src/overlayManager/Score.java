package overlayManager;

public class Score {
	
	private int score;
	
	
	public Score() {
		this.score = 0;
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

}
