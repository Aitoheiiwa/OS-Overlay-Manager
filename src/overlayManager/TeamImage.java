package overlayManager;

import java.nio.file.Path;
import java.nio.file.Paths;

public class TeamImage extends Team {
	
	private Path teamPath;

	
	TeamImage(String name) {
		super(name);
	}
	
	@Override
	public void setName(String name) {
		super.setName(name);
		this.teamPath = teamPath(name);
	}
	
	public Path getPath() {
		return this.teamPath;
	}
	
	public static Path teamPath(String teamName) {
		return Paths.get("../Assets/Teams/" + teamName + ".png");
	}

}
