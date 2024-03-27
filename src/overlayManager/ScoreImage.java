package overlayManager;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ScoreImage extends Score {

	private Path scorePath;	
	public ScoreImage() {
		super();
		scorePath = scorePath(0);
	}
	

	public Path getPath() {
		return this.scorePath;
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
	public void resetScore() {
		super.resetScore();
		this.scorePath = scorePath(0);
	}
	
	public static Path scorePath(int score) {
		return Paths.get("../Assets/Score/_" + score + "_.png");
	}
	
	

}
