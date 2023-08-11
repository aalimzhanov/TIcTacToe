import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
/*
 * 2 player TicTacToe game
 * @author Adil Alimzhanov
 */
public class TicTacToe implements ActionListener{
    Random random;
    JFrame frame;
    JPanel title_panel;
    JPanel button_panel;
    JLabel textField;
    JButton[][] buttons;
    boolean xTurn;

    // constructor
    public TicTacToe(){
        setUpGUI();
        xTurn();
    }

    // set up all the elements of GUI
    private void setUpGUI(){
        random = new Random();
        frame = new JFrame();
        title_panel = new JPanel();
        button_panel = new JPanel();
        textField = new JLabel();
        buttons = new JButton[3][3];

        //setting up the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(Color.green);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        //setting up the textField
        textField.setBackground(Color.blue);
        textField.setForeground(Color.yellow);
        textField.setFont(new Font("Ink Free", Font.BOLD, 75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Tic-Tac-Toe");
        textField.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);
        
        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(Color.white);

        //creating buttons
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                buttons[i][j] = new JButton();
                button_panel.add(buttons[i][j]);
                buttons[i][j].setFont(new Font("MV Boli", Font.BOLD, 120));
                buttons[i][j].setFocusable(false);
                buttons[i][j].addActionListener(this);
            }
        }
        title_panel.add(textField);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

    }

    /*
     * change the color of the pressed button and change the text to X or O depending on the turn 
     * @param e pressed button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(e.getSource() == buttons[i][j]){
                    if(xTurn){
                        if(buttons[i][j].getText() == ""){
                            buttons[i][j].setForeground(Color.red);
                            buttons[i][j].setText("X");
                            xTurn = false;
                            textField.setText("O turn");
                            check();
                        }
                    }
                    else{
                        if(buttons[i][j].getText() == ""){
                            buttons[i][j].setForeground(Color.green);
                            buttons[i][j].setText("O");
                            xTurn = true;
                            textField.setText("X turn");
                            check();
                        }
                    }
                }
            }
        }
    }
    // randomly determine who's turn it is first
    public void xTurn(){
        if(random.nextInt(2) == 1){
            xTurn = true;
            textField.setText("X turn");
            return;
        }
        xTurn = false;
        textField.setText("O turn");
    }

    // check if anyone has won yet
    public void check(){
        int xCount;
        int oCount;
        //check horizontal lines
        for(int i = 0; i < 3; i ++){
            xCount = 0;
            oCount = 0;
            for(int j = 0; j < 3; j++){
                if(buttons[i][j].getText().equals("X")){
                    xCount++;
                }
                if(buttons[i][j].getText().equals("O")){
                    oCount++;
                }
            }

            if(xCount == 3 || oCount == 3){
                winner(i, 0, i, 1, i, 2);
                return;
            }
        }
        //check vertical lines
        for(int j = 0; j < 3; j ++){
            xCount = 0;
            oCount = 0;
            for(int i = 0; i < 3; i++){
                if(buttons[i][j].getText().equals("X")){
                    xCount++;
                }
                if(buttons[i][j].getText().equals("O")){
                    oCount++;
                }
            }
            if(xCount == 3 || oCount == 3){
                winner(0, j, 1, j, 2, j);
                return;
            }
        }

        //check both diagonal lines
        if(buttons[0][0].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[2][2].getText()) && !buttons[0][0].getText().equals("")){
            winner(0, 0, 1, 1, 2, 2);
            return;
        }
        if(buttons[0][2].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[2][0].getText()) & !buttons[0][2].getText().equals("")){
            winner(0, 2, 1, 1, 2, 0);
            return;
        }
    }

    /*
     * set the background of the winning buttons to color green and disable all the buttons
     * @param aI i-th coordinate of the first button
     * @param aJ j-th coordinate of the first button
     * @param bI i-th coordinate of the second button
     * @param bJ j-th coordinate of the second button
     * @param cI i-th coordinate of the third button
     * @param cJ j-th coordinate of the third button
     */
    public void winner(int aI, int aJ, int bI, int bJ, int cI, int cJ){

        buttons[aI][aJ].setBackground(Color.green);
        buttons[bI][bJ].setBackground(Color.green);
        buttons[cI][cJ].setBackground(Color.green);
        for (int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    buttons[i][j].setEnabled(false);
                }
            }
        String winner = buttons[aI][aJ].getText();
        textField.setText(winner + " wins");
    }
}
