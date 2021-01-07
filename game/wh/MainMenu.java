package game.wh;

//      Create the main menu
//      MainMenu mainMenu = new MainMenu();
//		mainMenu.setVisible(true);
//this is added to the main class

    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import javax.swing.*;


public class MainMenu extends JFrame {

        private static final int gameStart = 1;

        // The background color (can be used)
        private static final Color bgColor = new Color(155, 62, 151);

        // The name of the nexus
        private final JTextField TheWorldsHardestGame;

        public MainMenu() {
            super("The Worlds Hardest Game");

            // Configure the JFrame
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBackground(bgColor); //can be used

            // Create the header for the MainMenu
            JPanel headerPanel = new JPanel();
            headerPanel.setLayout(new FlowLayout());
            headerPanel.setBackground(Color.WHITE);

            JLabel topIcon = new JLabel(""); //left empty but can be used
            topIcon.setIcon(new ImageIcon("resource/buttons/javaaveng.png"));
            headerPanel.add(topIcon);
            add(headerPanel, BorderLayout.NORTH);

            // Create the panel
            JPanel stretchPanel = new JPanel();
            stretchPanel.setLayout(new BorderLayout());

            // Create the panel containing the controls
            JPanel buttonsPanel = new JPanel();
            GridLayout buttonsLayout = new GridLayout(0,1);
            buttonsLayout.setVgap(5);
            buttonsPanel.setLayout(buttonsLayout);
            buttonsPanel.setBackground(Color.WHITE);
            stretchPanel.add(buttonsPanel, BorderLayout.NORTH);

            // Create the text field which contains the name
            TheWorldsHardestGame = new JTextField("WELCOME TO THE Main Menu of The Worlds' Hardest Game");
            TheWorldsHardestGame.setDisabledTextColor(Color.BLACK);
            TheWorldsHardestGame.setSelectedTextColor(Color.WHITE);
            TheWorldsHardestGame.setBackground(Color.WHITE);
            TheWorldsHardestGame.setForeground(Color.WHITE);
            TheWorldsHardestGame.setBorder(null);
            TheWorldsHardestGame.setHorizontalAlignment(JTextField.CENTER);
//            TheWorldsHardestGame.setPreferredSize(new Dimension(50,50));
            buttonsPanel.add(TheWorldsHardestGame);

            // Create the play game label
            JLabel playLabel = new JLabel("Play the Game!");
            playLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
            playLabel.setHorizontalAlignment(JLabel.CENTER);
            playLabel.setForeground(Color.WHITE);
            buttonsPanel.add(playLabel);

            // Create the play game button
            JButton playTheGame = new JButton("Start playing");
            playTheGame.setBackground(Color.GREEN);
            //adding image
            Icon iconPlay = new ImageIcon("resource/buttons/newplay.PNG");
            playTheGame = new JButton("Start Playing",iconPlay);
            buttonsPanel.add(playTheGame);
            playTheGame.addActionListener(new PlayGameListener(gameStart));

            JButton settingTheGame = new JButton("Settings");
//            settingTheGame.addActionListener(new PlayGameListener(//TODO);
            settingTheGame.setBackground(Color.CYAN);
            Icon iconSetting = new ImageIcon("resource/buttons/newsetting.PNG");
            settingTheGame = new JButton("Settings       ",iconSetting);
            buttonsPanel.add(settingTheGame);


            JButton gameCredits = new JButton("Credits");
//            gameCredits.addActionListener(new PlayGameListener(//TODO);
            gameCredits.setBackground(Color.ORANGE);
            //adding button image
            Icon iconCredits = new ImageIcon("resource/buttons/newabout.PNG");
            gameCredits = new JButton("Credits       ",iconCredits);
            buttonsPanel.add(gameCredits);

            // Create the padding below the play game buttons
            JPanel basePadding = new JPanel();
            basePadding.setBackground(Color.WHITE);
            basePadding.setPreferredSize(new Dimension(970 , 470));
            stretchPanel.add(basePadding, BorderLayout.CENTER);

            add(stretchPanel, BorderLayout.CENTER);

            // Create the quit button
            JButton quit;
            Icon quitIcon = new ImageIcon("resource/buttons/exit.PNG");
            quit = new JButton("Quit Game",quitIcon);
            quit.addActionListener(e -> System.exit(0)
            );
            quit.setBackground(Color.WHITE);
            add(quit, BorderLayout.SOUTH);

            pack();
            setLocationRelativeTo(buttonsPanel);
            // thy this if it doesn't fit the screen: setLocationRelativeTo(null);
        }


        private class PlayGameListener implements ActionListener {

            private double action;

            public PlayGameListener(double number) {
                this.action = number;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                // Hide this window
                setVisible(false);

                // Create the game's window and display it
//                new OyunEkrani(null);
//                        setVisible(true);
            }

        }


}
