package game.wh;

import kuusisto.tinysound.Music;
import kuusisto.tinysound.TinySound;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;


public class GameFrame extends JFrame {

	public GameFrame(String title) throws HeadlessException{
		super(title);
	}

	static boolean isIntroFinished = false;

	public static void main(String[] args) throws InterruptedException {

		BufferedImage logo = null;

		// Getting Intro Logo
		try {
			logo = ImageIO.read(new FileImageInputStream(new File("src/wh/game/resource/images/logo.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		GameFrame screen = new GameFrame("World's Hardest Game: Javavengers Edition");

		int currentScreen=-1;
		int menuScreen=1, creditsScreen=1, pause, gameProcess=1, introProcess=1, mngProcess=1, gameEnd, howToPlayScreen=1;



		screen.setResizable(false);
		screen.setFocusable(false);

		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MainMenu menu = new MainMenu();
		Game game = new Game();
		Minigame minigame = new Minigame();
		Credits credits = new Credits();
		HowToPlay howToPlay = new HowToPlay();
		Intro intro = new Intro(logo);

		intro.setFadeListener(new FadeListener() {
			@Override
			public void fadeStarted(Intro i) {

			}

			@Override
			public void fadeCompleted(Intro i) {
				isIntroFinished = true;
			}
		});

		//Music Initializer
		TinySound.init();
		Music bgmusic = TinySound.loadMusic(ClassLoader.getSystemResource(
				"wh/game/sounds/music.wav"));
		bgmusic.play(true,0.9f);
		bgmusic.pause();

		// GAME LOOP
		while (true) {

			// INTRO SCREEN
			if(currentScreen == -1){
				screen.add(intro);
				screen.pack();
				screen.setLocationRelativeTo(null); // Centering the JFrame with respect to computer screen.
				screen.setVisible(true);

				//Intro Music
				TinySound.init();
				Music introMusic = TinySound.loadMusic(ClassLoader.getSystemResource(
						"wh/game/sounds/introLogo.wav"));
				introMusic.play(false,0.3f);

				//Intro fade in&out
				intro.fadeIn();
				Thread.sleep(4000); // Sleeping the code for 4 seconds -> Waiting for fadeIn to complete
				intro.fadeOut();

				while(introProcess == 1) {

					//Move to menu screen
					if (isIntroFinished) {
						currentScreen = 0;
						screen.remove(intro);
						screen.setVisible(false);
						introProcess++;
					}
					System.out.println("Intro Playing Ssssh!");
				}
			}

			//MENU
			if(currentScreen == 0) {

				//Music pause or not ?
				if(menu.musicButtonCounter % 2 == 1) {
					bgmusic.pause();
				}
				else{
					bgmusic.resume();
				}

				menu.requestFocus();

				screen.add(menu.play);
				screen.add(menu.quit);
				screen.add(menu.credits);
				screen.add(menu.howToPlay);
				screen.add(menu.music);
				screen.add(menu);

				screen.pack();

				screen.setVisible(true);



				// Menu Screen Loop waiting for command
				while (menuScreen == 1) {

					//Music pause or not ?
					if(menu.musicButtonCounter % 2 == 1) {
						bgmusic.pause();
					}
					else{
						bgmusic.resume();
					}

					//Clicking play button
					if (menu.playButton) {
						currentScreen = 3;
						game.setStartTime(System.nanoTime());
						screen.remove(menu.play);
						screen.remove(menu.quit);
						screen.remove(menu.credits);
						screen.remove(menu.howToPlay);
						screen.remove(menu.music);
						screen.remove(menu);
						screen.setVisible(false);
						gameProcess=1;
						menuScreen++;
						game.goToMenu = false;
					}

					//Clicking credits button
					if (menu.creditsBool) {
						currentScreen = 1;
						screen.remove(menu.play);
						screen.remove(menu.quit);
						screen.remove(menu.credits);
						screen.remove(menu.howToPlay);
						screen.remove(menu.music);
						screen.remove(menu);
						screen.setVisible(false);
						menuScreen++;
						creditsScreen=1;
					}

					//Clicking How To Play button
					if (menu.howToPlayBool) {
						currentScreen = 2;
						screen.remove(menu.play);
						screen.remove(menu.quit);
						screen.remove(menu.credits);
						screen.remove(menu.howToPlay);
						screen.remove(menu.music);
						screen.remove(menu);
						screen.setVisible(false);
						menuScreen++;
						howToPlayScreen=1;
					}
					System.out.println("Menu is ON!");
				}
			}

			//CREDITS
			if(currentScreen == 1) {

				credits.requestFocus();

				screen.add(credits.backButton);
				screen.add(credits);
				screen.pack();

				screen.setVisible(true);

				while (creditsScreen == 1) {

					//Waiting for closing command
					if (credits.closeCredits) {
						currentScreen = 0;
						screen.remove(credits.backButton);
						screen.remove(credits);
						screen.setVisible(false);
						creditsScreen++;
						menuScreen=1;
						menu.creditsBool = false;
						credits.closeCredits = false;
					}
					System.out.println("Credits is ON!");
				}
			}

			//HOWTOPLAY
			if(currentScreen == 2) {

				howToPlay.requestFocus();

				screen.add(howToPlay.backButton);
				screen.add(howToPlay);
				screen.pack();

				screen.setVisible(true);

				while (howToPlayScreen == 1) {

					//Waiting for closing command
					if (howToPlay.closeHowToPlay) {
						currentScreen = 0;
						screen.remove(howToPlay.backButton);
						screen.remove(howToPlay);
						screen.setVisible(false);
						howToPlayScreen++;
						menuScreen=1;
						menu.howToPlayBool = false;
						howToPlay.closeHowToPlay = false;
					}
					System.out.println("How To Play is ON!");
				}
			}

			//GAME ON
			if(currentScreen == 3) {

				game.requestFocus();

				game.setFocusable(true);
				game.setFocusTraversalKeysEnabled(false);

				screen.add(game.pause);
				screen.add(game);
				screen.pack();
				screen.setVisible(true);


				while(gameProcess == 1){

					//Refreshing the minigame after Level 1 is passed
					if(game.player.getLevel1().isPassed && !game.player.getLevel2().isPassed){
						minigame.setPassed(false);
						minigame.refresh();
					}

					//Minigame is passed or not
					if (!game.player.getLevel1().isPassed && minigame.isPassed || !game.player.getLevel3().isPassed && minigame.isPassed) {
						game.player.minigame1 = false;
						game.player.minigame3 = false;
						minigame.getSetOfPressedKeys().remove(KeyEvent.VK_SPACE);
						minigame.getSetOfPressedKeys().remove(KeyEvent.VK_LEFT);
						minigame.getSetOfPressedKeys().remove(KeyEvent.VK_RIGHT);
						minigame.getSetOfPressedKeys().remove(KeyEvent.VK_DOWN);
						minigame.getSetOfPressedKeys().remove(KeyEvent.VK_UP);
					}

					//Entering minigame
					if (game.minigameOn && !minigame.isPassed){
						screen.remove(game.pause);
						screen.remove(game);
						screen.setVisible(false);
						currentScreen = 4;
						mngProcess=1;
						gameProcess++;
					}

					// PAUSE SCREEN
					if(game.isPaused) {
						screen.add(game.ok);
						screen.add(game.leave);
						pause = 1;
						game.continueGame=false;

						//Waiting for command
						while (pause == 1) {

							// Leaving the game
							if (game.goToMenu) {
								currentScreen = 0;
								game.getTimes().add(System.nanoTime()-game.getStartTime());
								screen.remove(game.pause);
								screen.remove(game.ok);
								screen.remove(game.leave);
								screen.remove(game);
								screen.setVisible(false);
								menuScreen = 1;
								menu.playButton = false;
								game.isPaused = false;
								pause++;
								gameProcess++;
							}

							//Continue the game
							if (game.continueGame){
								screen.remove(game.ok);
								screen.remove(game.leave);
								pause++;
							}
							System.out.println("Game Paused!?");
						}

					}

					//GAME END SCREEN
					if(game.gameFinished) {
						game.add(game.backToMenu);
						game.setStopTime(System.nanoTime());
						gameEnd = 1;

						//Waiting for command & refreshing the game
						while (gameEnd == 1) {
							if (game.goToMenu) {

								currentScreen = 0;
								screen.remove(game.pause);
								game.remove(game.backToMenu);
								screen.remove(game);
								screen.setVisible(false);

								//Refresh Game
								game.player.getLevel3().setPassed(false);
								game.player.getLevel2().setPassed(false);
								game.player.getLevel1().setPassed(false);
								game.player.setOnceMng3(true);
								game.player.setDeathCounter(0);
								game.player.setStepCounter(0);
								game.player.x=20;
								game.player.y=480;
								game.player.gameFinished = false;
								game.gameFinished = false;
								game.coinRestart();

								//Refresh Minigame
								game.player.minigame3=false;
								game.player.minigame1=false;
								game.minigameOn=false;
								minigame.setPassed(false);
								minigame.refresh();

								menuScreen = 1;
								menu.playButton = false;
								gameEnd++;
								gameProcess++;
							}
							System.out.println("Well Done!");
						}
					}
					System.out.println("Game ON!");
				}

			}

			//MINIGAME
			if (currentScreen == 4){

				minigame.requestFocus();

				minigame.setFocusable(true);
				minigame.setFocusTraversalKeysEnabled(false);

				screen.add(minigame);
				screen.pack();

				screen.setVisible(true);

				while (mngProcess==1){

					//Waiting for the minigame finishing
					if (minigame.isPassed){
						currentScreen = 3;
						screen.remove(minigame);
						screen.setVisible(false);
						mngProcess++;
						gameProcess = 1;
						game.minigameOn = false;
					}
					System.out.println("Minigame is ON!");
				}

			}

		}

	}

}
