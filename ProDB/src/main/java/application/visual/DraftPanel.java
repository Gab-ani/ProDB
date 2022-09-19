package application.visual;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.springframework.stereotype.Service;

import application.MatchAnalyser;
import application.data.Hero;

public class DraftPanel {
	
	private MatchAnalyser analyser;

	private JFrame draftPanel;
	
	private SetupMode mode;
	
	private JLabel[] heroIcons;
	private int iconSize = 100;
	
	private JTextField inputArea;
	private Rectangle inputBounds = new Rectangle(10, 120, 500, 20);
	
	private ArrayList<String> direNicknames;
	private ArrayList<String> radiantNicknames;
	private String[] addOrder;
	private int addIndex;
	
	private void init() {
		
		setupMainWindow();
		setupHeroLabels();
		setupInputArea();
		setupOutputArea();
		setupDraftArrays();
		draftPanel.setVisible(true);
	}
	





	public DraftPanel(SetupMode mode, MatchAnalyser model) {
		this.mode = mode;
		this.analyser = model;
		init();
	}
	
	private void setupOutputArea() {
		// TODO Auto-generated method stub
		
	}	

	private void setupDraftArrays() {
		addIndex = 0;
		if(mode == SetupMode.LIVE) {
			addOrder = new String[]{"radiant", "radiant", "dire", "dire", "radiant", "radiant", "dire", "dire", "radiant", "dire"};
		} else if(mode == SetupMode.AFTERMATH) {
			addOrder = new String[]{"radiant", "radiant", "radiant", "radiant", "radiant", "dire", "dire", "dire", "dire", "dire"};
		}
		direNicknames = new ArrayList<>();
		radiantNicknames = new ArrayList<>();
	}

	private void setupInputArea() {
		inputArea = new JTextField("героев вводить сюда");
		inputArea.setBounds(inputBounds);
		inputArea.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String[] nicknames = inputArea.getText().split(" ");
					for(String nickname : nicknames) {
						addNickname(nickname);
					}
					inputArea.setText("");
				}
			}
		});
		draftPanel.add(inputArea);
	}
	
	private void setupMainWindow() {
		draftPanel = new JFrame("predictions");
		draftPanel.setLayout(null);
		draftPanel.setResizable(false);
		draftPanel.setSize(1130, 300);
		draftPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		draftPanel.setVisible(true);
	}

	private void setupHeroLabels() {
		heroIcons = new JLabel[10];
		for(int i = 0; i < 10; i++) {
			heroIcons[i] = new JLabel("fdsf");
			heroIcons[i].setBorder(BorderFactory.createBevelBorder(1));
			heroIcons[i].setBounds(10 + i * (iconSize + 10), 10, iconSize, iconSize);
			draftPanel.add(heroIcons[i]);
		}
	}
	
	private void addNickname(String nickname) {
		if(addOrder[addIndex].equals("dire")) {
			direNicknames.add(nickname);
		} else {
			radiantNicknames.add(nickname);
		}
		addIndex += 1;
		if(addIndex == 10) {
			sendInfo();
		}
	}

	private void sendInfo() {
		Hero[] radiant = analyser.createTeam(radiantNicknames);
		Hero[] dire = analyser.createTeam(direNicknames);
		
		System.out.println(analyser.predict(radiant, dire));
		
	}

	public enum SetupMode {
		LIVE, AFTERMATH
	}
	
}