package overlayManager;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OverlayManagerGUI {
	
	private BestOfX BoX = new BestOfX(new Match(""), new TeamImage(""), new TeamImage(""));
	private MutableBoolean orientation = new MutableBoolean();

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
		blueTeamSelector.addActionListener(evt -> {
			String blueOrient = orientation.get() ? "blue_" : "";
			BoX.getBlueTeam().setName(blueOrient + blueTeamSelector.getItemAt(blueTeamSelector.getSelectedIndex()));
			BoX.updateBlueTeam();
		});

		
		
		
		redTeamSelector.addActionListener(evt -> {
			String redOrient = orientation.get() ? "red_" : "";
			BoX.getRedTeam().setName(redOrient + redTeamSelector.getItemAt(redTeamSelector.getSelectedIndex()));
			BoX.updateBlueTeam();
		});
		
		// Blue and red team indicator 
		 JLabel blueTeamLabel = new JLabel("Blue team");
		 JLabel redTeamLabel = new JLabel("Red team");
		 
		// Score displayer
		 
		JLabel lBlueScore = new JLabel("" + BoX.getBlueScore());
		JLabel lRedScore = new JLabel("" + BoX.getRedScore());
		
		// Score increment button
		
		JButton bBlueINC = new JButton("+");
		JButton bRedINC = new JButton("+");
		
		bBlueINC.addActionListener(evt -> {
			BoX.getBlueScore().addScore();
			BoX.updateBlueScore();
			lBlueScore.setText("" + BoX.getBlueScore().getScore());
		});
		
		
		bRedINC.addActionListener(evt -> {
			BoX.getRedScore().addScore();
			BoX.updateRedScore();
			lRedScore.setText("" + BoX.getRedScore().getScore());
		});
		
		
		// Reset button
		
		JButton bReset = new JButton("Reset");
		
		bReset.addActionListener(evt -> {
			BoX.getBlueScore().resetScore();
			BoX.getRedScore().resetScore();
			BoX.updateRedScore();
			BoX.updateBlueScore();
			lBlueScore.setText("" + BoX.getBlueScore().getScore());
			lRedScore.setText("" + BoX.getRedScore().getScore());
		});
		
		// Match label 
		
		JLabel matchLabel = new JLabel("Match");

		
		// Match selector
		
		JComboBox<String> matchSelector = new JComboBox<>(Match.MATCHLIST);
		
		matchSelector.addActionListener(evt -> {
			BoX.getMatch().setName(matchSelector.getItemAt(matchSelector.getSelectedIndex()));
			BoX.updateMatch();
		});
		
		
		// Orientation button and panel
		
		JLabel lOrientation = new JLabel("Orientation");
		JButton bOrientation = new JButton("Off");
		bOrientation.addActionListener(e -> {
			orientation.flip();
			String onOff = orientation.get() ? "On" : "Off";
			bOrientation.setText(onOff);

		});
		
		// Display
		JPanel orientationPanel = new JPanel(new FlowLayout());
		content.add(orientationPanel, BorderLayout.NORTH);
		orientationPanel.add(lOrientation);
		orientationPanel.add(bOrientation);
		
		
		JPanel scorePanel = new JPanel(new FlowLayout());
		content.add(scorePanel,BorderLayout.CENTER);
		scorePanel.add(blueTeamLabel);
		scorePanel.add(blueTeamSelector);
		scorePanel.add(lBlueScore);
		scorePanel.add(bBlueINC);
		scorePanel.add(bReset);
		scorePanel.add(bRedINC);
		scorePanel.add(lRedScore);
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
		
		
		window.pack();
		window.setVisible(true);


	}
	
	public static void main(String[] args) {
		int nb_teams = args.length;
		String Teams []= new String[nb_teams + 1] ; 
		Teams[0] = "";
		System.arraycopy(args, 0, Teams, 1, nb_teams );

		new OverlayManagerGUI(Teams);
	}
	
}