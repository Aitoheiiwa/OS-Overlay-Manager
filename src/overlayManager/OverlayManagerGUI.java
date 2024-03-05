package overlayManager;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OverlayManagerGUI {
	
	
	private final Path blueTeamImage =  Paths.get("./Displayed/blue_Team.png");
	private final Path redTeamImage =  Paths.get("./Displayed/red_Team.png");
	private final Path blueTeamScore =  Paths.get("./Displayed/blue_Team_Score.png");
	private final Path redTeamScore =  Paths.get("./Displayed/red_Team_Score.png");
	private final Path currentMatchImage =  Paths.get("./Displayed/current_Match.png");
	private Match currentMatch = new Match("","","");

	public OverlayManagerGUI(String [] teamName) {
		
		Arrays.sort(teamName);
		teamName[0] = "Unselected";
		
		// Initialize the window
		
		JFrame window = new JFrame("Overlay Manager");
		java.awt.Container content = window.getContentPane();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		content.setLayout(new java.awt.BorderLayout());
		
		// Team Selector
		
		JComboBox<String> blueTeamSelector = new JComboBox<>(teamName);
		JComboBox<String> redTeamSelector = new JComboBox<>(teamName);
		
		// Team selector Action
		
		blueTeamSelector.addActionListener(new ActionListener() {
			public Team team = currentMatch.getBlueTeamScore().getTeam();
			public Path teamImage = blueTeamImage;
			@Override
			public void actionPerformed(ActionEvent e) {
				teamSelectorAction(team, blueTeamSelector, teamImage);				
			}
		});

		redTeamSelector.addActionListener(new ActionListener() {
			public Team team = currentMatch.getRedTeamScore().getTeam();
			public Path teamImage = redTeamImage;
			@Override
			public void actionPerformed(ActionEvent e) {
				teamSelectorAction(team, redTeamSelector, teamImage);				
			}
		});
		
		// Blue and red team indicator 
		 JLabel blueTeamLabel = new JLabel("Blue team");
		 JLabel redTeamLabel = new JLabel("Red team");
		 
		// Score displayer
		 
		JLabel lBlueTeamScore = new JLabel("" + this.currentMatch.getBlueTeamScore().getScore());
		JLabel lRedTeamScore = new JLabel("" + this.currentMatch.getRedTeamScore().getScore());
		
		// Score increment button
		
		JButton bBlueINC = new JButton("+");
		JButton bRedINC = new JButton("+");
		
		bBlueINC.addActionListener(new ActionListener() {
			ScoreImage score = currentMatch.getBlueTeamScore();
			Path dest = blueTeamScore;
			JLabel displayer = lBlueTeamScore;
			@Override
			public void actionPerformed(ActionEvent e) {
				incAction(score,dest,displayer);
			}
		});
		
		bRedINC.addActionListener(new ActionListener() {
			ScoreImage score = currentMatch.getRedTeamScore();
			Path dest = redTeamScore;
			JLabel displayer = lRedTeamScore;
			@Override
			public void actionPerformed(ActionEvent e) {
				incAction(score,dest,displayer);
			}
		});
		
		// Reset button
		
		JButton bReset = new JButton("Reset");
		bReset.addActionListener(new ActionListener() {
			ScoreImage bScore = currentMatch.getBlueTeamScore();
			ScoreImage rScore = currentMatch.getRedTeamScore();
			JLabel bdisplayer = lBlueTeamScore;
			JLabel rdisplayer = lRedTeamScore;
			Path bdest = blueTeamScore;
			Path rdest = redTeamScore;
			@Override
			public void actionPerformed(ActionEvent e) {
				resetAction(bScore,rScore,bdest,rdest,bdisplayer,rdisplayer);
			}
		});
		
		// Match label
		
		JLabel matchLabel = new JLabel("Match");
		
		// Match selector
		
		JComboBox<String> matchSelector = new JComboBox<>(Match.MATCHLIST);
		matchSelector.addActionListener(new ActionListener() {
			public Match match = currentMatch;
			public Path matchImage = currentMatchImage;
			@Override
			public void actionPerformed(ActionEvent e) {
				matchSelectorAction(match, matchSelector, matchImage);				
			}
			
		});
		
		// Display
		
		JPanel scorePanel = new JPanel(new FlowLayout());
		content.add(scorePanel,BorderLayout.NORTH);
		scorePanel.add(blueTeamLabel);
		scorePanel.add(blueTeamSelector);
		scorePanel.add(lBlueTeamScore);
		scorePanel.add(bBlueINC);
		scorePanel.add(bReset);
		scorePanel.add(bRedINC);
		scorePanel.add(lRedTeamScore);
		scorePanel.add(redTeamSelector);
		scorePanel.add(redTeamLabel);
		
		JPanel matchPanel = new JPanel(new BorderLayout());
		JPanel matchPanelCenter = new JPanel(new FlowLayout());
		JPanel matchPanelSouth = new JPanel(new FlowLayout());
		
		
		content.add(matchPanel, BorderLayout.SOUTH);
		matchPanel.add(matchPanelCenter, BorderLayout.CENTER);
		matchPanel.add(matchPanelSouth, BorderLayout.SOUTH);
		matchPanelCenter.add(matchLabel);
		matchPanelSouth.add(matchSelector);
		
		bReset.doClick();
		
		
		window.setSize(700,135);
		window.setVisible(true);


	}
	
	public static void main(String[] args) {
		int nb_teams = args.length;
		String Teams []= new String[nb_teams + 1] ; 
		Teams[0] = "";
		System.arraycopy(args, 0, Teams, 1, nb_teams );

		new OverlayManagerGUI(Teams);
	}
	
	private static void teamSelectorAction(Team team, JComboBox<String> selector,Path dest) {
		
			team.setName(selector.getItemAt(selector.getSelectedIndex()));
			Path src = ScoreImage.teamPath(team);
			try {
				Files.copy(src,dest, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	private static void incAction(ScoreImage score, Path dest, JLabel displayer) {
		score.addScore();
		displayer.setText("" + score.getScore());
		Path src = score.getScorePath();
		try {
			Files.copy(src, dest,StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void resetAction(ScoreImage bScore, ScoreImage rScore,Path bDest,Path rDest,JLabel bdisplayer, JLabel rdisplayer) {
		bScore.resetScore();
		rScore.resetScore();
		bdisplayer.setText("" + bScore.getScore());
		rdisplayer.setText("" + rScore.getScore());
		Path bSrc = bScore.getScorePath();
		Path rSrc = rScore.getScorePath();
		System.out.println(""+ bScore.getScore());
		System.out.println(""+ rScore.getScore());
		try {
			Files.copy(bSrc, bDest,StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Files.copy(rSrc, rDest,StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void matchSelectorAction(Match match, JComboBox<String> selector,Path dest) {
		match.setName(selector.getItemAt(selector.getSelectedIndex()));
		Path src = match.getMatchPath();
		try {
			Files.copy(src,dest, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
