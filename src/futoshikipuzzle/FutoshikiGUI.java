

package futoshikipuzzle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 * The FutoshikiGUI class is the main class responsible for the creation,
 * maintenance and displaying of the futoshiki puzzle graphic user interface
 * 
 * @author James Goodman 
 * @version 1.0 (05/05/2016)
 */
public class FutoshikiGUI {  
    
    private FutoshikiPuzzle futoshikiPuzzle;
    
    private boolean isPlaying = false;
    private boolean pencilToggle = false;
    private Color backgroundColor = new Color(220, 210, 180, 255);

    private JButton[][] squareButtons;
    
    /**
     * Responsible for creating the main menu that first greets the player upon 
     * starting the game
     * 
     * @return JFrame the window containing the main menu
     */
    private JFrame createMainMenu() {
        final JFrame menuFrame = new JFrame();
        menuFrame.setLayout(new BorderLayout());
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel menuTop = new JPanel(new BorderLayout());
        menuTop.setBackground(backgroundColor);
        menuTop.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        menuTop.setPreferredSize(new Dimension(400, 250));
        
        JLabel header = new JLabel ("Futoshiki Puzzle");
        header.setFont(new Font("Serif", Font.ITALIC, 36));
        header.setHorizontalAlignment(0);
        menuTop.add(header, BorderLayout.NORTH);
        
        JPanel menuBottom = new JPanel(new FlowLayout());
        menuBottom.setBackground(backgroundColor);
        menuBottom.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JButton puzzleSelect = new JButton("Puzzle Select");        
        puzzleSelect.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPlaying) {
                    createPuzzleSelect();
                    isPlaying = true;
                    menuFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(menuFrame, "Close the current puzzle before starting a new one.");
                }
            }                    
        });
        menuBottom.add(puzzleSelect);
        
        JButton quit = new JButton("Quit Game");
        menuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        quit.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {             
                System.exit(0);
            }                    
        });
        menuBottom.add(quit);    
        
        menuFrame.add(menuTop, BorderLayout.NORTH);
        menuFrame.add(menuBottom, BorderLayout.SOUTH);
        
        menuFrame.setTitle("Menu");
        menuFrame.setResizable(false);
        menuFrame.pack();
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setVisible(true);
        return menuFrame;

    }
    
    /**
     * Responsible for creating the in game menu that allows the player to check
     * for legality and solve the puzzle
     * 
     * @param JFrame the window containing the game menu
     */
    private JFrame createGameMenu() {
        final JFrame menuFrame = new JFrame();
        
        JPanel menu = new JPanel();
        menu.setBackground(backgroundColor);
        menu.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        menu.setLayout(new GridLayout(3, 1, 0, 20));
        menu.setPreferredSize(new Dimension(200, 300));
        
        JToggleButton togglePencil = new JToggleButton("Toggle Pencil Markings");        
        togglePencil.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isPlaying) {
                    pencilToggle = !pencilToggle;
                }
            }                    
        });
        menu.add(togglePencil);        
        
        JButton legalCheck = new JButton("Check Legal");        
        legalCheck.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {  
                if (isPlaying) {
                    if (futoshikiPuzzle.isLegal()){
                        JOptionPane.showMessageDialog(menuFrame, "There is nothing wrong with this puzzle.");
                    } else {
                        JOptionPane.showMessageDialog(menuFrame, "This puzzle is not legal." + futoshikiPuzzle.getProblems());
                    }
                } else {
                    JOptionPane.showMessageDialog(menuFrame, "No active puzzle to check.");
                }
            }                    
        });
        menu.add(legalCheck); 
        
        JButton solve = new JButton("Solve");
        solve.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {                
                if (isPlaying) {
                    if (futoshikiPuzzle.solve(futoshikiPuzzle)) {
                        updateUISquares(); 
                        JOptionPane.showMessageDialog(menuFrame, "The puzzle has been solved.");                        
                    } else {
                        JOptionPane.showMessageDialog(menuFrame, "This puzzle can't be solved in it's current state.");
                    }
                } else {
                    JOptionPane.showMessageDialog(menuFrame, "No active puzzle to solve.");
                }
            }                    
        });
        menu.add(solve);   
        
        menuFrame.add(menu);       
        
        menuFrame.setTitle("Menu");
        menuFrame.setResizable(false);
        menuFrame.pack();
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setVisible(true);
        return menuFrame;
    }
    
    /**
     * Responsible for creating the puzzle selection screen that allows the 
     * player to choose which of a variety of puzzles to play     * 
     */
    private void createPuzzleSelect () {
        final JFrame selectFrame = new JFrame();
        JPanel selectPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        selectPanel.setBackground(backgroundColor);
        selectPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        selectFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                isPlaying = false;
                createMainMenu();
            }
        });
        
        JButton selectButtonOne = new JButton("Easy - First Time Futoshiki");
        selectButtonOne.setPreferredSize(new Dimension(250, 50));
        selectButtonOne.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {
                createBoard(1);
                selectFrame.dispose();
            }                    
        });
        selectPanel.add(selectButtonOne);
        
        JButton selectButtonTwo = new JButton("Intermediate - Casual Player");
        selectButtonTwo.setPreferredSize(new Dimension(250, 50));
        selectButtonTwo.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {
                createBoard(2);
                selectFrame.dispose();
            }                    
        });
        selectPanel.add(selectButtonTwo);
        
        JButton selectButtonThree = new JButton("Hard - Futoshiki Master");
        selectButtonThree.setPreferredSize(new Dimension(250, 50));
        selectButtonThree.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {
                createBoard(3);
                selectFrame.dispose();
            }                    
        });
        selectPanel.add(selectButtonThree);
        
        JButton selectButtonFour = new JButton("Random Puzzle of Unknown Size");
        selectButtonFour.setPreferredSize(new Dimension(250, 50));
        selectButtonFour.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {
                createBoard(4);
                selectFrame.dispose();
            }                    
        });
        selectPanel.add(selectButtonFour);
        
        selectFrame.add(selectPanel);
        selectFrame.setTitle("Puzzle Select");
        selectFrame.setResizable(false);
        selectFrame.pack();
        selectFrame.setLocationRelativeTo(null);        
        selectFrame.setVisible(true); 
    }
    
    /**
     * Responsible for creating the puzzle board that represents the current 
     * futoshikiPuzzle configuration
     * 
     * @param preset the index of the puzzle to be played
     * 
     */
    private void createBoard(int preset) {
        final JFrame boardFrame = new JFrame();
        final JFrame menuFrame = createGameMenu(); 
        
        boardFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                isPlaying = false;
                pencilToggle = false;
                menuFrame.dispose();
                createMainMenu();
            }
        });
       
        switch (preset) {
            case 1:
                puzzlePreset1();                
                break;
            case 2:
                puzzlePreset2();                
                break;
            case 3:
                puzzlePreset3();                
                break;
            case 4:
                futoshikiPuzzle = new FutoshikiPuzzle(5);
                futoshikiPuzzle = futoshikiPuzzle.fillPuzzle(3, 7);
                //System.out.println(futoshikiPuzzle);
                break;
            
        }

        JPanel boardPanel = generateFutoshikiPanel(futoshikiPuzzle);
        boardPanel.setBackground(backgroundColor);
        boardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        boardFrame.add(boardPanel);
        boardFrame.setTitle("Board");
        boardFrame.setResizable(false);
        boardFrame.pack();
        boardFrame.setLocationRelativeTo(null);
        boardFrame.setVisible(true); 
        
        menuFrame.setLocation(boardFrame.getX() + boardFrame.getWidth() + 15, boardFrame.getY());
                
    }
    
    /**
     * Updates the UI buttons that hold the futoshiki square text fields to their 
     * current corresponding futoshiki square vales
     * 
     */
    private void updateUISquares() {
        for (int i = 0; i < futoshikiPuzzle.getGridSize(); i++) {
            for (int j = 0; j < futoshikiPuzzle.getGridSize(); j++) {
                JButton button = squareButtons[i][j];
                button.setText(futoshikiPuzzle.getSquare(i, j).getValueAsString());  
                button.repaint();
                button.revalidate();
            }
        }        
    }    
    
    /**
     * Setup for the first and easiest puzzle preset
     * 
     */
    private void puzzlePreset1 () {
        futoshikiPuzzle = new FutoshikiPuzzle(3);        
        
        futoshikiPuzzle.setSquare(2, 1, 3);
        
        futoshikiPuzzle.setRowConstraint(0, 2, new MoreThan(futoshikiPuzzle.getSquare(0, 2), futoshikiPuzzle.getSquare(1, 2)));
        
    }
    
    /**
     * Setup for the second intermediate puzzle preset
     * 
     */
    private void puzzlePreset2 () {
        futoshikiPuzzle = new FutoshikiPuzzle(5);
        
        futoshikiPuzzle.setSquare(4, 0, 5);
        futoshikiPuzzle.setSquare(1, 1, 3);
        futoshikiPuzzle.setSquare(3, 2, 2);
        
        futoshikiPuzzle.setRowConstraint(1, 1, new MoreThan(futoshikiPuzzle.getSquare(1, 1), futoshikiPuzzle.getSquare(2, 1)));
        futoshikiPuzzle.setRowConstraint(0, 3, new LessThan(futoshikiPuzzle.getSquare(0, 3), futoshikiPuzzle.getSquare(1, 3)));
        
        futoshikiPuzzle.setColumnConstraint(3, 2, new MoreThan(futoshikiPuzzle.getSquare(3, 2), futoshikiPuzzle.getSquare(3, 3)));                
    }

    /**
     * Setup for the third and hardest puzzle preset
     * 
     */
    private void puzzlePreset3 () {
        futoshikiPuzzle = new FutoshikiPuzzle(7);      
    
        futoshikiPuzzle.setSquare(3, 2, 6);
        futoshikiPuzzle.setSquare(5, 1, 3);
        futoshikiPuzzle.setSquare(4, 4, 2);
        
        futoshikiPuzzle.setRowConstraint(1, 1, new MoreThan(futoshikiPuzzle.getSquare(1, 1), futoshikiPuzzle.getSquare(2, 1)));
        futoshikiPuzzle.setRowConstraint(0, 3, new LessThan(futoshikiPuzzle.getSquare(0, 3), futoshikiPuzzle.getSquare(1, 3)));
        
        futoshikiPuzzle.setColumnConstraint(6, 4, new MoreThan(futoshikiPuzzle.getSquare(6, 4), futoshikiPuzzle.getSquare(6, 5)));        
        futoshikiPuzzle.setColumnConstraint(3, 2, new MoreThan(futoshikiPuzzle.getSquare(3, 2), futoshikiPuzzle.getSquare(3, 3)));
        
    }
    
    /**
     * Given a position and FutoshikiSquare create a futoshiki button that displays 
     * the value of the corresponding futoshiki square, add this button to the grid
     * of button objects and return it
     * 
     * @param y the row or y cooridnate of the square
     * @param x the column or x cooridnate of the square
     * @param gridSize the size of the grid being used
     * @param futoSquare the FutoshikiSquare object to be used
     * 
     * @return JButton the button that has been created using the parameters provided
     * 
     */
    private JButton createGridButton (int y, int x, final int gridSize, final FutoshikiSquare futoSquare) {
        final JButton futoButton = new JButton();
        final JLabel futoLabel = new JLabel();
        futoButton.setMargin(new Insets(3, 3, 3, 3));
        futoButton.setLayout(new BorderLayout());
        futoButton.setPreferredSize(new Dimension(50, 50));
        
        futoButton.setText(futoSquare.getValueAsString());
        if (futoSquare.getValue() == 0) {                    
            futoButton.addActionListener(new ActionListener() {   
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (pencilToggle) {
                        futoSquare.setPencilMark((futoSquare.getPencilMark()+ 1) % (gridSize + 1));
                        futoLabel.setText(futoSquare.getPencilMarkAsString());
                    } else {
                        futoSquare.setValue((futoSquare.getValue() + 1) % (gridSize + 1));
                        futoButton.setText(futoSquare.getValueAsString());     
                    }  
                }                    
            });
        } else {
            futoButton.setEnabled(false);
        }

        squareButtons[x][y] = futoButton;
        futoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        futoLabel.setForeground(Color.red);                
        futoButton.add(futoLabel, BorderLayout.NORTH);
        
        return futoButton;

        
    }
    
    /**
     * Create the full game board based on the current configuration of the futoshiki
     * puzzle object, this includes all value buttons and label displaying the constraints
     * between them
     * 
     * @param futo the futoshiki puzzle being used
     * 
     * @return JPanel the panel containing the final board
     * 
     */
    private JPanel generateFutoshikiPanel(final FutoshikiPuzzle futo) {
        JPanel futoPanel = new JPanel(new GridLayout((futo.getGridSize() * 2) - 1, (futo.getGridSize() * 2) - 1));  
        
        squareButtons = new JButton[futo.getGridSize()][futo.getGridSize()];

        for (int y = 0; y < futo.getGridSize(); y++) {
            for (int x = 0; x < futo.getGridSize(); x++) {      
                futoPanel.add(createGridButton(y, x, futo.getGridSize(), futo.getSquare(x, y)));     
                
                if (x < futo.getGridSize() - 1) {
                    Constraint futoRowCon = futo.getRowConstraint(x, y);
                    JLabel futoConLabel = new JLabel();

                    if (futoRowCon != null) {
                        futoConLabel.setText(futoRowCon.toString());
                        futoConLabel.setHorizontalAlignment(0);
                    } else {
                        futoConLabel.setText("");
                    }
                    
                    futoPanel.add(futoConLabel);
                }
                
            }
            
            if (y < futo.getGridSize() - 1) {
                for (int x = 0; x < futo.getGridSize(); x++) {   
                    Constraint futoColCon = futo.getColumnConstraint(x, y);
                    JLabel futoConLabel = new JLabel();

                    if (futoColCon != null) {
                        futoConLabel.setText(futoColCon.toString());
                        futoConLabel.setHorizontalAlignment(0);
                    } else {
                        futoConLabel.setText("");
                    }

                    futoPanel.add(futoConLabel);
                    
                    if (x < futo.getGridSize() - 1) {
                        JLabel futoLabel = new JLabel("");
                        futoPanel.add(futoLabel);
                    }

                }
            }
            
        }

        return futoPanel;
    }
    
    /**
     * The main method that creates GUI object and the main menu 
     * 
     */
    public static void main (String[] args) {
        FutoshikiGUI futoGUI = new FutoshikiGUI();
        futoGUI.createMainMenu();
        
        
    }
    
    
}















