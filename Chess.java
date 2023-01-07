// ICS3U Final Culminating Task: Chess
// Jacqueline Ho, Jonathan Feng
// Wednesday, January 27, 2020
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.BufferedImage;
import java.util.Scanner;

public class ISU extends JPanel implements MouseListener
{
	// Class Variables
	public static int pressX;
	public static int pressY;
	public static int releaseX;
	public static int releaseY;
	//x is column, y is row

	// detects the state of the game
	// can be one of six states: 
	// "start" --> indicates the opening screen is being displayed
	// "rules" --> indicates the rules screen is being displayed
	// "mode" --> indicates the game mode setting screen is being displayed
	// "play" --> indicates the main game screen is being displayed
	// "end" --> indicates the game is over and the ending screen is play displayed
	// "score" --> indicates the score board is being displayed
	
	public static String gameState = "start";
	
	// keeps track of the game mode/theme
	public static String gameMode = "dark";
	
	// checks who's turn it is
	public static boolean wTurn = true;
	
	// keeps track of the rules screen (1 = game overview screen; 2 = piece movements screen; 3 = general rules screen)
	public static String rulesScreen = "1";
	
	public static int [] countWins = new int [2];
	
	// Chess board array: first array is the row
	// p = pawn, r = rook, k = knight, b = bishop, Q = Queen, K = King
	// Last letter represents the piece color B = black, W = white
	String[][]boardPieces = {
			{"r1B", "k1B", "b1B","Q1B", "K1B", "b2B", "k2B", "r2B"},
			{"p1B", "p2B", "p3B", "p4B", "p5B", "p6B", "p7B", "p8B"},
			{"", "", "", "", "", "", "", ""},
			{"", "", "", "", "", "", "", ""},
			{"", "", "", "", "", "", "", ""},
			{"", "", "", "", "", "", "", ""},
			{"p1W", "p2W", "p3W", "p4W", "p5W", "p6W", "p7W", "p8W"},
			{"r1W", "k1W", "b1W","Q1W", "K1W", "b2W", "k2W", "r2W"}
	};

	// Coordinate array: stores the x and y coordinates of each square within the chess board. 
	// The first number at each index is the x coordinate and the second is the y coordinate
	public static int [][][] coord = {
			{{250, 100}, {300, 100}, {350, 100}, {400, 100}, {450, 100}, {500, 100}, {550, 100}, {600, 100}},
			{{250, 150}, {300, 150}, {350, 150}, {400, 150}, {450, 150}, {500, 150}, {550, 150}, {600, 150}},
			{{250, 200}, {300, 200}, {350, 200}, {400, 200}, {450, 200}, {500, 200}, {550, 200}, {600, 200}},
			{{250, 250}, {300, 250}, {350, 250}, {400, 250}, {450, 250}, {500, 250}, {550, 250}, {600, 250}},
			{{250, 300}, {300, 300}, {350, 300}, {400, 300}, {450, 300}, {500, 300}, {550, 300}, {600, 300}},
			{{250, 350}, {300, 350}, {350, 350}, {400, 350}, {450, 350}, {500, 350}, {550, 350}, {600, 350}},
			{{250, 400}, {300, 400}, {350, 400}, {400, 400}, {450, 400}, {500, 400}, {550, 400}, {600, 400}},
			{{250, 450}, {300, 450}, {350, 450}, {400, 450}, {450, 450}, {500, 450}, {550, 450}, {600, 450}},		
		};
	
	//castling variables
	public static boolean k1wMoved = false;
	public static boolean k1bMoved = false;
	public static boolean r1wMoved = false;
	public static boolean r2wMoved = false;
	public static boolean r1bMoved = false;
	public static boolean r2bMoved = false;
	
	// object constructor
	public ISU()
	{
		setPreferredSize(new Dimension(900, 550));
		addMouseListener(this);
	}

	// Mouse Listener Methods
	public void mouseClicked(MouseEvent e)
	{	
		// if user clicks on the screen while the start screen is being displayed
		if (gameState == "start")
		{
			//if user clicks within the parameters of the 'start' button
			if (e.getX() >= 391 && e.getX() <= 511 && e.getY() >= 304 && e.getY() <= 329)
			{
				gameState = "play";
				repaint();

			}
			
			// if user clicks within the parameters of the 'rules' button
			else if (e.getX() >= 391 && e.getX() <= 511 && e.getY() >= 356 && e.getY() <= 381)
			{
				gameState = "rules";
				repaint();
			}
			
			// if user clicks within the parameters of the 'mode' button
			else if (e.getX() >= 391 && e.getX() <= 511 && e.getY() >= 406 && e.getY() <= 433)
			{
				gameState = "mode";
				repaint();
			}
		}
		
		// if user clicks on the screen while the rules are being displayed
		else if (gameState == "rules")
		{
			
			// if user clicks within the parameters of the exit button of the rules screen
			if (e.getX() >= 828 && e.getX() <= 854 && e.getY() >= 99 && e.getY() <= 123)
			{
				gameState = "start";
				repaint();
			}
			// if user clicks within the parameters of the 'game overview' tab
			else if (e.getX() >= 94 && e.getX() <= 219 && e.getY() >= 141 && e.getY() <= 172)
			{
				rulesScreen = "1";
				repaint();
			}
			
			// if user clicks within the parameters of the 'piece movements' tab
			else if (e.getX() >= 236 && e.getX() <= 437 && e.getY() >= 137 && e.getY() <= 169)
			{
				rulesScreen = "2";
				repaint();
			}
			
			// if user clicks within the parameters of the 'rules' tab
			else if (e.getX() >= 461 && e.getX() <= 547 && e.getY() >= 139 && e.getY() <= 170)
			{
				rulesScreen = "3";
				repaint();
			}
		}
		
		// if user clicks on the screen while the 'mode' window is being displayed
		else if (gameState == "mode")
		{
			// if user clicks within the parameters of the 'dark mode' button
			if (e.getX() >= 400 && e.getX() <= 538 && e.getY() >= 245 && e.getY() <= 280)
			{
				gameMode = "dark";
				gameState = "start";
				repaint();
			}
			
			// if user clicks within the parameters of the 'royal mode' button
			else if (e.getX() >= 400 && e.getX() <= 538 && e.getY() >= 337 && e.getY() <= 369)
			{
				gameMode = "royal";
				gameState = "start";
				repaint();
			}
		}
		
		// if user clicks on the screen while the main game screen is being displayed
		else if (gameState == "play")
		{
			// if the user clicks on the 'exit game' button 
			if (e.getX() >= 28 && e.getX() <= 158 && e.getY() >= 28 && e.getY() <= 56)
			{
					
				gameState = "start";
				paintComponent(this.getGraphics());
			}
			
			// if the user clicks on the 'restart game' button
			else if (e.getX() >= 727 && e.getX() <= 861 && e.getY() >= 28 && e.getY() <= 56)
			{
				gameState = "reset";
				paintComponent(this.getGraphics());
			}
		}
		
		// if user clicks on the screen while the 'ending' screen is being displayed
		else if (gameState == "end")
		{
			
			// if the user clicks on the 'score' button
			if (e.getX() >= 380 && e.getX() <= 517 && e.getY() >= 398 && e.getY() <= 424)
			{
				gameState = "score";
				paintComponent(this.getGraphics());
			}
			
			// the user clicks on the 'play again' button
			else if (e.getX() >= 380 && e.getX() <= 517 && e.getY() >= 323 && e.getY() <= 349)
			{
				gameState = "start";
				paintComponent(this.getGraphics());
			}
		}
		
		
		else if (gameState == "score")
		{
			// if the user clicks on the exit button while the score board is being displayed
			if (e.getX() >= 603 && e.getX() <= 628 && e.getY() >= 49 && e.getY() <= 66)
			{
				gameState = "end";
				repaint();
			}
		}
	}

	public void mouseEntered(MouseEvent e)
	{

	}
	public void mouseExited(MouseEvent e)
	{

	}
		
	public void mousePressed(MouseEvent e)
	{
		// detects the position on the chess board the user presses down on to (indicating which piece they have chosen to move)
		if (e.getX() > 250 && e.getX() < 650 && e.getY() > 100 && e.getY() < 500 && gameState == "play")
		{
			pressX = (e.getX() - 250) / 50;
			pressY = (e.getY() - 100) / 50;
			drawSquare(this.getGraphics());
		}
	}
	public void mouseReleased(MouseEvent e)
	{
		// detects the designated square on the chess board the user moves the piece to
		if (e.getX() > 250 && e.getX() < 650 && e.getY() > 100 && e.getY() < 500 && gameState == "play")
		{
			releaseX = (e.getX() - 250) / 50;
			releaseY = (e.getY() - 100) / 50;
			paintComponent(this.getGraphics());
		}
	}

// once a user's move is deemed valid, this method changes the boardPieces array and changes the player turn
public static boolean pieceMove(String[][]boardPieces)
{
	boardPieces[releaseY][releaseX] = boardPieces[pressY][pressX];
	boardPieces[pressY][pressX] = "";
	
	// changing the player's turn
	if (wTurn == true)
	{
		wTurn = false;
	}
	else 
	{
		wTurn = true;
	}
	return wTurn;
}
	
// this method checks that the space that the user attempts to move a piece to is empty or contains an enemy colored piece
public static boolean rightCapColour(String[][]boardPieces, int releaseY,int releaseX,int pressY, int pressX)
{
	boolean valid = true;
		
	if (wTurn == true)
	{
		if ((boardPieces[releaseY][releaseX].equals("") || boardPieces[releaseY][releaseX].charAt(2) == 'B') == false)
		{
			valid = false;
		}
	}
	else
	{
		if ((boardPieces[releaseY][releaseX].equals("") || boardPieces[releaseY][releaseX].charAt(2) == 'W') == false)
		{
			valid = false;
		}
	}
	return valid;
}

// this method checks the validity of an attempted bishop move
public static boolean bishopMove(String[][]boardPieces, int releaseY, int releaseX, int pressY, int pressX)
{	
	boolean valid = true;
	valid = rightCapColour(boardPieces, releaseY, releaseX, pressY, pressX);
	
	//makes sure the attempted move is diagonal
	if (Math.abs(releaseY - pressY) != Math.abs(releaseX - pressX))
	{
		valid = false;
	}
	
	int bPathVertical = 0;
	int bPathHorizontal = 0;
	
	if (pressX < releaseX)
	{
		bPathHorizontal = 1;
	}
	else
	{
		bPathHorizontal = -1;
	}
	
	if (pressY < releaseY)
	{
		bPathVertical = 1;
	}
	else
	{
		bPathVertical = -1;
	}
	
	//checks that there are no pieces between the start and end
	if (valid == true)
	{
		for (int i = 1; i < Math.abs(releaseY - pressY); i++)
		{
			if (boardPieces[pressY + i * bPathVertical][pressX + i * bPathHorizontal].equals("") == false)
			{
				valid = false;
			}
		}
	}
	return valid;
}

//this method checks the validity of an attempted knight move
public static boolean knightMove(String[][]boardPieces, int releaseY,int releaseX,int pressY, int pressX)
{
	boolean valid = false;
	if (rightCapColour(boardPieces, releaseY, releaseX, pressY, pressX) == false)
	{
		return false;
	}
	
	//up and down L movements
	if ((releaseY - pressY == 2 || releaseY - pressY == -2) && (releaseX - pressX == 1 || releaseX - pressX == -1))
	{
		valid = true;	
	}
	
	//right and left L movements
	else if ((releaseX - pressX == 2 || releaseX - pressX == -2) && (releaseY - pressY == 1 || releaseY - pressY == -1))
	{
		valid = true;
	}
	return valid;
}

//this method checks the validity of an attempted rook move
public static boolean rookMove(String[][]boardPieces, int releaseY,int releaseX,int pressY, int pressX)
{
	boolean valid = true;
	valid = rightCapColour(boardPieces, releaseY, releaseX, pressY, pressX);
	
	int rPathVertical = 0;
	int rPathHorizontal = 0;
	
	if ((releaseY == pressY || releaseX == pressX) == false)
	{
	return false;
	}
	
	if (releaseY > pressY)
	{
		rPathVertical = 1;
	}
	else if (releaseY < pressY)
	{
		rPathVertical = -1;
	}
	else{
	}
	
	if (releaseX > pressX)
	{
		rPathHorizontal = 1;
	}
	else if (releaseX < pressX)
	{
		rPathHorizontal = -1;
	}
	else{
	}
	
	//checks that there are no pieces between the start and end
	for (int i = 1; i < Math.abs(releaseY - pressY + releaseX - pressX); i++)
	{
		if (boardPieces[pressY + i * rPathVertical][pressX + i * rPathHorizontal].equals("") == false)
		{
			valid = false;
		}
	}
	
	return valid;
}

//this method checks the validity of an attempted pawn move
public static boolean pawnMove(String[][]boardPieces, int releaseY,int releaseX,int pressY, int pressX)
{
	boolean valid = false;
	
	if (boardPieces[pressY][pressX].charAt(2) == ('W'))
	{
		if (pressY == 6 && releaseY == 4 && releaseX == pressX && boardPieces[releaseY][releaseX].equals(""))
		{
			valid = true;
		}
		else if (releaseY == pressY - 1 && releaseX == pressX && boardPieces[releaseY][releaseX].equals(""))
		{
			valid = true;
		}
		else if (boardPieces[releaseY][releaseX].equals("") == false && releaseY == pressY - 1 && (releaseX == pressX - 1 || releaseX == pressX + 1))
		{
			if (boardPieces[releaseY][releaseX].charAt(2) == 'B')
			{
				valid = true;
			}
		}
	}
	else
	{
		if (pressY == 1 && releaseY == 3 && releaseX == pressX && boardPieces[releaseY][releaseX].equals(""))
		{
			valid = true;
		}
		else if (releaseY == pressY + 1 && releaseX == pressX && boardPieces[releaseY][releaseX].equals(""))
		{
			valid = true;
		}
		else if (boardPieces[releaseY][releaseX].equals("") == false && releaseY == pressY + 1 && (releaseX == pressX - 1 || releaseX == pressX + 1))
		{
			if (boardPieces[releaseY][releaseX].charAt(2) == 'W')
			{
				valid = true;
			}
		}
	}
	
	return valid;
}

//this method checks the validity of an attempted knight move
public static boolean kingMove(String[][]boardPieces, int releaseY,int releaseX,int pressY, int pressX)
{
	boolean valid = true;

	valid = rightCapColour(boardPieces, releaseY, releaseX, pressY, pressX);
	if ((Math.abs(releaseY - pressY) < 2 && Math.abs(releaseX - pressX) < 2 ) == false)
	{
		valid = false;
	}	
	
	return valid;
}


//this method checks if the player that is going is in check
public static boolean checkStatus(String[][]boardPieces)
{
	char enemyColour = 'W';
	char yourColour = 'B';
	if (wTurn == true)
	{
		enemyColour = 'B';
		yourColour = 'W';
	}
	//finds the position of the current player's king
	int kingX = 0;
	int kingY = 0;
	
	for (int i = 0; i < 8; i++)
	{
		for (int j = 0; j < 8; j++)
		{
			if (boardPieces[i][j].equals("K1"+yourColour))
			{
				kingY = i;
				kingX = j;
				break;
			}
		}
	}
	
	//checks all enemy pieces to see if they can capture the king
	boolean check = false;
	
	//this for loop scans all the squares within the chess board and detects if an enemy piece is within the square
	//if an enemy piece is detected, it checks if this piece can capture the friendly king. If this is true, the check = true
	for (int i = 0; i < 8; i++)
	{
		for (int j = 0; j < 8; j++)
		{
			if (boardPieces[i][j].equals("") == false)
			{
				if (boardPieces[i][j].charAt(2) == enemyColour)
				{
					if (boardPieces[i][j].charAt(0) == 'b')
					{
						if (bishopMove(boardPieces, i, j, kingY, kingX) == true)
						{
							check = true;
						}
					}
					else if (boardPieces[i][j].charAt(0) == 'k')
					{
						if (knightMove(boardPieces, i, j, kingY, kingX) == true)
						{
							check = true;	
						}
					}
					else if (boardPieces[i][j].charAt(0) == 'r')
					{
						if (rookMove(boardPieces, i, j, kingY, kingX) == true)
						{
							check = true;
						}
					}
					else if (boardPieces[i][j].charAt(0) == 'Q')
					{
						if (rookMove(boardPieces, i, j, kingY, kingX) == true)
						{
							check = true;
						}
						else if (bishopMove(boardPieces, i, j, kingY, kingX) == true)
						{
							check = true;
						}
					}
					else if (boardPieces[i][j].charAt(0) == 'p')
					{
						if (pawnMove(boardPieces, i, j, kingY, kingX) == true)
						{
							check = true;
						}
					}
					else if (boardPieces[i][j].charAt(0) == 'K')
					{
						if (kingMove(boardPieces, i, j, kingY, kingX) == true)
						{
							check = true;
						}
					}
				}
			}
		}
	}
	
	return check;
}

// this method detects is a player is in check mate
public static int checkmateStatus(String[][]boardPieces)
{
	int possibleMoves = 0;
	boolean valid = false;
	
	char enemyColour = 'W';
	char yourColour = 'B';
	if (wTurn == true)
	{
		enemyColour = 'B';
		yourColour = 'W';
	}
	
	//this for loop goes through all the squares within the chess board and detects all
	//the squares within the chess board a friendly piece can move to
	//if a move is valid, it checks if the result of that move is safe from check. 
	//if true, then that move is considered a possible move
	//as well, a counter in the for loop tracks how many possible moves the player can make. 
	//if there are zero possible moves, the player is in check mate.
	for (int i = 0; i < 8; i++)
	{
		for (int j = 0; j < 8; j++)
		{
			if (boardPieces[i][j].equals("") == false)
			{
				if (boardPieces[i][j].charAt(2) == yourColour)
				{
					//goes through all the possible moves of that piece
					for (int k = 0; k < 8; k++)
					{
						for (int l = 0; l < 8; l++)
						{
							valid = false;
							if (boardPieces[i][j].charAt(0) == 'b')
							{
								if (bishopMove(boardPieces, k, l, i, j) == true)
								{
									valid = true;
								}
							}
							if (boardPieces[i][j].charAt(0) == 'k')
							{
								if (knightMove(boardPieces, k, l, i, j) == true)
								{
									valid = true;
								}
							}
							if (boardPieces[i][j].charAt(0) == 'r')
							{
								if (rookMove(boardPieces, k, l, i, j) == true)
								{
									valid = true;
								}
							}
							if (boardPieces[i][j].charAt(0) == 'Q')
							{
								if (bishopMove(boardPieces, k, l, i, j) == true)
								{
									valid = true;
								}
								else if (rookMove(boardPieces, k, l, i, j) == true)
								{
									valid = true;
								}
							}
							if (boardPieces[i][j].charAt(0) == 'p')
							{
								if (pawnMove(boardPieces, k, l, i, j) == true)
								{
									valid = true;
								}
							}
							
							if (boardPieces[i][j].charAt(0) == 'K')
							{
								if (kingMove(boardPieces, k, l, i, j) == true)
								{
									valid = true;
								}
							}
							
							//checks if any of the possible moves can take the player out of check
							if (valid == true)
							{
								String storedOldPiece = boardPieces[k][l];
								
								boardPieces[k][l] = boardPieces[i][j];
								boardPieces[i][j] = "";
								if (checkStatus(boardPieces) == false)
								{
									possibleMoves++;
								}
								boardPieces[i][j] = boardPieces[k][l];
								boardPieces[k][l] = storedOldPiece;
							}							
						}
					}
				}
			}
		}
	}
	return possibleMoves;
} 
	
	// Object Method
	// where the graphics will be drawn onto the JPanel
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		// resets the value of key variables required to start a new game if the game is not in play
		// (a.k.a has either been restarted or has been exited out to display another screen sucha s the start screen)
		if (gameState.equals("start") || gameState.equals("reset") || gameState.equals("rules") || gameState.equals("mode")) //if the game ends, it redraws the board to normal when they restart
		{
			// resets the boardPieces array back to it's initial arrangement by storing all of the 
			// pieces back to the starting positions of a chess game
			String [][] resetPieces = {
				{"r1B", "k1B", "b1B","Q1B", "K1B", "b2B", "k2B", "r2B"},
				{"p1B", "p2B", "p3B", "p4B", "p5B", "p6B", "p7B", "p8B"},
				{"", "", "", "", "", "", "", ""},
				{"", "", "", "", "", "", "", ""},
				{"", "", "", "", "", "", "", ""},
				{"", "", "", "", "", "", "", ""},
				{"p1W", "p2W", "p3W", "p4W", "p5W", "p6W", "p7W", "p8W"},
				{"r1W", "k1W", "b1W","Q1W", "K1W", "b2W", "k2W", "r2W"}
			};
				
			boardPieces = resetPieces;
			
			// resets the main class variables back to the initial value needed for a new game
			wTurn = true;
				
			pressX = 0;
			pressY = 0;
			releaseX =  0;
			releaseY = 0;
			
			k1wMoved = false;
			k1bMoved = false;
			r1wMoved = false;
			r2wMoved = false;
			r1bMoved = false;
			r2bMoved = false;
			
			// displays the main game screen if user chose to reset the game
			// otherwise, brings the user back to the start screen 
			if (gameState.equals("reset"))
			{
				gameState = "play";
				paintComponent(this.getGraphics());
			}
			else
			{
				drawStartScreen(g);
			}
		}
						
		// displays the end screens once the game is over
		else if (gameState.equals("end") || gameState.equals("score"))
		{
			drawEndScreen(g);
			String whiteWins = Integer.toString(countWins[1]);
			String blackWins = Integer.toString(countWins[0]);
			
			// displays the score board if the user clicks on the 'score' button
			if (gameState.equals("score"))
			{
				Image scoreBoard = Toolkit.getDefaultToolkit().getImage("images/scoreBoard.png");
				g.drawImage(scoreBoard, 0, 0, this);
				g.setColor(Color.GRAY);
				
				g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
				g.drawString(whiteWins, 550, 225);
				g.drawString(blackWins, 550, 375);	
			}	
		}
		
		// if the game is in play in which the main screen is displayed
		else if (gameState.equals("play"))
		{
			drawBoard(g, checkStatus(boardPieces));
			if(true)
			{
				if (boardPieces[pressY][pressX] != "")
				{
					if ((boardPieces[pressY][pressX].charAt(2) == 'W' && wTurn == true) || (boardPieces[pressY][pressX].charAt(2) == 'B' && wTurn == false))
					{
//pawn 
						boolean valid = false;
						if (boardPieces[pressY][pressX].charAt(0) == 'p')
						{
							valid = pawnMove(boardPieces, releaseY, releaseX, pressY, pressX);
						}		
//bishop
						else if (boardPieces[pressY][pressX].charAt(0) == 'b')
						{
							valid = bishopMove(boardPieces, releaseY, releaseX, pressY, pressX);
						}
//knight
						else if (boardPieces[pressY][pressX].charAt(0) == 'k')
						{
							valid = knightMove(boardPieces, releaseY, releaseX, pressY, pressX);	
						}
//rook
						else if (boardPieces[pressY][pressX].charAt(0) == 'r')
						{
							valid = rookMove(boardPieces, releaseY, releaseX, pressY, pressX);	
						}
//queen
						else if (boardPieces[pressY][pressX].charAt(0) == 'Q')
						{
							valid = false;
							if (releaseY == pressY || releaseX == pressX)
							{
								valid = rookMove(boardPieces, releaseY, releaseX, pressY, pressX);
							}
							if (Math.abs(releaseY - pressY) == Math.abs(releaseX - pressX))
							{
								valid = bishopMove(boardPieces, releaseY, releaseX, pressY, pressX);
							}
						}
//King
						else if (boardPieces[pressY][pressX].charAt(0) == 'K')
						{
							valid = false;
							valid = kingMove(boardPieces, releaseY, releaseX, pressY, pressX);
							
							if (valid != true)
							{
								char yourColour = 'B';
								if (wTurn == true)
								{
									yourColour = 'W';
								}
								
								if (boardPieces[pressY][pressX].equals("") == false)
								{
									if (boardPieces[pressY][pressX].charAt(2) == yourColour)
									{	
										if (yourColour == 'W' && k1wMoved == false)
										{
											//white right castle
											if (releaseY == 7 && releaseX == 6 && r1wMoved == false && boardPieces[7][5].equals("") && boardPieces[7][6].equals(""))
											{
												valid = true;
												
												//tests if moved spaces are not in check
												if (checkStatus(boardPieces) == true)
												{
													valid = false;
												}
												
												boardPieces[7][4] = "";
												boardPieces[7][5] = "K1W";
												if (checkStatus(boardPieces) == true)
												{
													valid = false;
												}
												boardPieces[7][4] = "K1W";
												boardPieces[7][5] = "";
												
												boardPieces[7][4] = "";
												boardPieces[7][5] = "K1W";
												if (checkStatus(boardPieces) == true)
												{
													valid = false;
												}
												boardPieces[7][4] = "K1W";
												boardPieces[7][5] = "";
												
												boardPieces[7][4] = "";
												boardPieces[7][6] = "K1W";
												if (checkStatus(boardPieces) == true)
												{
													valid = false;
												}
												boardPieces[7][4] = "K1W";
												boardPieces[7][6] = "";
												
												//moves r1W
												if (valid == true)
												{
													boardPieces[7][7] = "";
													boardPieces[7][5] = "r1W";
												}
											}
											
											//white left castle
											else if (releaseY == 7 && releaseX == 2 && r2wMoved == false && boardPieces[7][3].equals("") && boardPieces[7][2].equals("") && boardPieces[7][1].equals(""))
											{
												valid = true;
												
												if (checkStatus(boardPieces) == true)
												{
													valid = false;
												}
												
												boardPieces[7][4] = "";
												boardPieces[7][3] = "K1W";
												if (checkStatus(boardPieces) == true)
												{
													valid = false;
												}
												boardPieces[7][4] = "K1W";
												boardPieces[7][3] = "";
												
												boardPieces[7][4] = "";
												boardPieces[7][2] = "K1W";
												if (checkStatus(boardPieces) == true)
												{
													valid = false;
												}
												boardPieces[7][4] = "K1W";
												boardPieces[7][2] = "";
												
												boardPieces[7][4] = "";
												boardPieces[7][1] = "K1W";
												if (checkStatus(boardPieces) == true)
												{
													valid = false;
												}
												boardPieces[7][4] = "K1W";
												boardPieces[7][1] = "";
												
												//moves r2W
												if (valid == true)
												{
													boardPieces[7][0] = "";
													boardPieces[7][3] = "r2W";
												}
											}
										}
										else if (yourColour == 'B' && k1bMoved == false)
										{
											//black right castle
											if (releaseY == 0 && releaseX == 6 && r1bMoved == false && boardPieces[0][5].equals("") && boardPieces[0][6].equals(""))
											{
												valid = true;
												
												//tests if moved spaces are not in check
												if (checkStatus(boardPieces) == true)
												{
													valid = false;
												}
												
												boardPieces[0][4] = "";
												boardPieces[0][5] = "K1B";
												if (checkStatus(boardPieces) == true)
												{
													valid = false;
												}
												boardPieces[0][4] = "K1B";
												boardPieces[0][5] = "";
												
												boardPieces[0][4] = "";
												boardPieces[0][6] = "K1B";
												if (checkStatus(boardPieces) == true)
												{
													valid = false;
												}
												boardPieces[0][4] = "K1B";
												boardPieces[0][6] = "";
												
												//moves r1B
												if (valid == true)
												{
													boardPieces[0][7] = "";
													boardPieces[0][5] = "r1B";
												}
											}
											//black left castle
											else if (releaseY == 0 && releaseX == 2 && r2bMoved == false && boardPieces[0][3].equals("") && boardPieces[0][2].equals("") && boardPieces[0][1].equals(""))
											{
												valid = true;
												
												if (checkStatus(boardPieces) == true)
												{
													valid = false;
												}
												
												boardPieces[0][4] = "";
												boardPieces[0][3] = "K1B";
												if (checkStatus(boardPieces) == true)
												{
													valid = false;
												}
												boardPieces[0][4] = "K1B";
												boardPieces[0][3] = "";
												
												boardPieces[0][4] = "";
												boardPieces[0][2] = "K1B";
												if (checkStatus(boardPieces) == true)
												{
													valid = false;
												}
												boardPieces[0][4] = "K1B";
												boardPieces[0][2] = "";
												
												boardPieces[0][4] = "";
												boardPieces[0][1] = "K1B";
												if (checkStatus(boardPieces) == true)
												{
													valid = false;
												}
												boardPieces[0][4] = "K1B";
												boardPieces[0][1] = "";
												
												//moves r2W
												if (valid == true)
												{
													boardPieces[0][0] = "";
													boardPieces[0][3] = "r2B";
												}
											}
										}
									}
								}
							}
						}
//check if the check status is true as another parameter for making the move valid
						String storedOldPiece = boardPieces[releaseY][releaseX];
						if (valid == true)
						{
							boardPieces[releaseY][releaseX] = boardPieces[pressY][pressX];
							boardPieces[pressY][pressX] = "";
							if (checkStatus(boardPieces) == true)
							{
								valid = false;
							}
							boardPieces[pressY][pressX] = boardPieces[releaseY][releaseX];
							boardPieces[releaseY][releaseX] = storedOldPiece;
						}
						
						// move the pieces in the array
						if (valid == true)
						{
							wTurn = pieceMove(boardPieces);		
							//detect movement for castle pieces
							//castling variables
							if (boardPieces[releaseY][releaseX].equals("r1W"))
							{
								r1wMoved = true;
							}
							else if (boardPieces[releaseY][releaseX].equals("r1B"))
							{
								r1bMoved = true;
							}
							else if (boardPieces[releaseY][releaseX].equals("r2W"))
							{
								r2wMoved = true;
							}
							else if (boardPieces[releaseY][releaseX].equals("r2B"))
							{
								r2bMoved = true;
							}
							else if (boardPieces[releaseY][releaseX].equals("K1W"))
							{
								k1wMoved = true;
							}
							else if (boardPieces[releaseY][releaseX].equals("K1B"))
							{
								k1bMoved = true;
							}
						}
						//changes castling variables
						drawBoard(g, checkStatus(boardPieces));
						
						//checks if it is in check mate
						if (checkmateStatus(boardPieces) == 0)
						{
							try {
								saveWins();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							gameState = "end";
							repaint();
						}
					}
				}
			}				
		}
	}
	
	//method that draws the start Screen and all its contents (i.e rules, mode window)
	public void drawStartScreen (Graphics g) 
	{	
		Image openScreen;
		// if the game is in 'dark mode'
		if (gameMode == "dark")
		{
			openScreen = Toolkit.getDefaultToolkit().getImage("images/openScreen.png");
		}
		// if the game is in 'royal mode'
		else
		{
			openScreen = Toolkit.getDefaultToolkit().getImage("images/openScreen2.png");
		}
		
		g.drawImage(openScreen, 0, 0, this);
		
		// displays the rules window if the user clicked on the 'rules' button
		if (gameState == "rules")
		{
			Image rules1 = Toolkit.getDefaultToolkit().getImage("images/rules1.png");
			Image rules2 = Toolkit.getDefaultToolkit().getImage("images/rules2.png");
			Image rules3 = Toolkit.getDefaultToolkit().getImage("images/rules3.png");
			
			// displays the game overview
			g.drawImage(rules1, 0, 0, this);
			
			// displays the piece movements 
			if (rulesScreen.equals("2"))
			{
				g.drawImage(rules2, 0, 0, this);
			}
			// displays the general rules of the game
			else if (rulesScreen.equals("3"))
			{
				g.drawImage(rules3, 0, 0, this);
			}
		}
		// displays the game mode window if the user clicked on the 'mode' button
		else if (gameState == "mode")
		{
			Image modeScreen = Toolkit.getDefaultToolkit().getImage("images/gameModeScreen.png");
			g.drawImage(modeScreen, 39, 0, this);
		}
	}
	
	// method that draws the end screen and all its components
	public void drawEndScreen (Graphics g)
	{
		Image endScreen;
		
		// if the game is in 'dark mode' 
		if (gameMode == "dark")
		{
			if (wTurn)
			{
				endScreen = Toolkit.getDefaultToolkit().getImage("images/blackWins.png");
			}
			
			else
			{
				endScreen = Toolkit.getDefaultToolkit().getImage("images/whiteWins.png");
			}
		}
		
		// if the game is in 'royal mode'
		else
		{
			if (wTurn)
			{
				endScreen = Toolkit.getDefaultToolkit().getImage("images/blackWins2.jpeg");
			}
			
			else
			{
				endScreen = Toolkit.getDefaultToolkit().getImage("images/whiteWins2.jpeg");
			}
		}
		g.drawImage(endScreen, 0, 0, this);	
	}
	
	public void drawSquare (Graphics g)
	{
		// draws a black border to highlight which square within the chess board the user has pressed on
	    g.drawRect(pressX*50 + 250, pressY*50 + 100, 50, 50);
	}
	
	// method that draws the chess board, game announcements, and all the pieces
	public void drawBoard (Graphics g, boolean checkStatus) 
	{
		Image background;
		Image chessBoard;
		Image whiteMessage;
		Image blackMessage;
		Image check;
		
		// if the game is in 'dark mode'
		if (gameMode == "dark")
		{
			background = Toolkit.getDefaultToolkit().getImage("images/mainBg.png");
			chessBoard = Toolkit.getDefaultToolkit().getImage("images/chessBoard.png");
			whiteMessage = Toolkit.getDefaultToolkit().getImage("images/whitesturn.png");
			blackMessage = Toolkit.getDefaultToolkit().getImage("images/blacksturn.png");
			check = Toolkit.getDefaultToolkit().getImage("images/check2.png");
		}
		
		// if the game is in 'royal mode'
		else
		{
			background = Toolkit.getDefaultToolkit().getImage("images/mainBg2.png");
			chessBoard = Toolkit.getDefaultToolkit().getImage("images/chessBoard2.png");
			whiteMessage = Toolkit.getDefaultToolkit().getImage("images/whitesTurn2.png");
			blackMessage = Toolkit.getDefaultToolkit().getImage("images/blacksTurn2.png");
			check = Toolkit.getDefaultToolkit().getImage("images/check.png");
		}
		
		// image variables: first letter --> indicates the colour of the piece (b = black, w = white)
		// 					second letter --> the first letter of the piece type (R = rook, K = knight etc)
		
		Image bR = Toolkit.getDefaultToolkit().getImage("images/bR1.png");
		Image bK = Toolkit.getDefaultToolkit().getImage("images/bK1.png");
		Image bB = Toolkit.getDefaultToolkit().getImage("images/bB1.png");
		Image bQ = Toolkit.getDefaultToolkit().getImage("images/bQ.png");
		Image bKing = Toolkit.getDefaultToolkit().getImage("images/bKing.png");
		Image bP = Toolkit.getDefaultToolkit().getImage("images/bP1.png");
		
		Image wR = Toolkit.getDefaultToolkit().getImage("images/wR1.png");
		Image wK = Toolkit.getDefaultToolkit().getImage("images/wK1.png");
		Image wB = Toolkit.getDefaultToolkit().getImage("images/wB1.png");
		Image wQ = Toolkit.getDefaultToolkit().getImage("images/wQ.png");
		Image wKing = Toolkit.getDefaultToolkit().getImage("images/wKing.png");
		Image wP = Toolkit.getDefaultToolkit().getImage("images/wP1.png");
		
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this);
		g.drawImage(chessBoard, 250, 100, this);
		
		// if the game is in check, displays an announcement on the screen indicating the game is in check
		if (checkStatus)
		{
			g.drawImage(check, 300, -15, this);
		}
		
		// if the game is not in check, displays an announcement on the screen indicating the player's turn 
		else
		{
			if (wTurn)
			{
				g.drawImage(whiteMessage, 300, -15, this);
			}
			else
			{
				g.drawImage(blackMessage, 300, -15, this);
			}
		}
		
		// counters which keep track of the number of each piece within the chess board that is still in play 
		// detects how many of each of the pieces have been captured
		int bPcount = 0; // black pawns (begins with 8 on the chess board)
		int wPcount = 0; // white pawns (begins with 8 on the chess board)
		int wKcount = 0; // white knights (begins with 2 on the chess board)
		int bKcount = 0; // black knights (begins with 2 on the chess board)
		int wBcount = 0; // white bishops (begins with 2 on the chess board)
		int bBcount = 0; // black bishops (begins with 2 on the chess board)
		int wQcount = 0; // white queens (begins with 1 on the chess board)
		int bQcount = 0; // black queens (begins with 1 on the chess board)
		int wRcount = 0; // white rooks (begins with 2 on the chess board)
		int bRcount = 0; // black rooks (begins with 2 on the chess board)
		
		// for loop that counts how many of each piece is still within the game and has not been captured
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if (boardPieces[i][j].equals("") == false)
				{
					if (boardPieces[i][j].charAt(2) == 'W')
					{
						if (boardPieces[i][j].charAt(0) == 'r')
						{
							g.drawImage(wR, coord[i][j][0], coord[i][j][1], this);
							wRcount++;
						}
						else if (boardPieces[i][j].charAt(0) == 'b')
						{
							g.drawImage(wB, coord[i][j][0], coord[i][j][1], this);
							wBcount++;
						}
						else if (boardPieces[i][j].charAt(0) == 'k')
						{
							g.drawImage(wK, coord[i][j][0], coord[i][j][1], this);
							wKcount++;
						}
						else if (boardPieces[i][j].charAt(0) == 'Q')
						{
							g.drawImage(wQ, coord[i][j][0], coord[i][j][1], this);
							wQcount++;
						}
						else if (boardPieces[i][j].charAt(0) == 'K')
						{
							g.drawImage(wKing, coord[i][j][0], coord[i][j][1], this);
						}
						else if (boardPieces[i][j].charAt(0) == 'p')
						{
							g.drawImage(wP, coord[i][j][0], coord[i][j][1], this);
							wPcount++;
						}
					}
					
					else if (boardPieces[i][j].charAt(2) == 'B')
					{
						if (boardPieces[i][j].charAt(0) == 'r')
						{
							g.drawImage(bR, coord[i][j][0], coord[i][j][1], this);
							bRcount++;
						}
						else if (boardPieces[i][j].charAt(0) == 'b')
						{
							g.drawImage(bB, coord[i][j][0], coord[i][j][1], this);
							bBcount++;
						}
						else if (boardPieces[i][j].charAt(0) == 'k')
						{
							g.drawImage(bK, coord[i][j][0], coord[i][j][1], this);
							bKcount++;
						}
						else if (boardPieces[i][j].charAt(0) == 'Q')
						{
							g.drawImage(bQ, coord[i][j][0], coord[i][j][1], this);
							bQcount++;
						}
						else if (boardPieces[i][j].charAt(0) == 'K')
						{
							g.drawImage(bKing, coord[i][j][0], coord[i][j][1], this);
						}
						else if (boardPieces[i][j].charAt(0) == 'p')
						{
							g.drawImage(bP, coord[i][j][0], coord[i][j][1], this);
							bPcount++;
						}
					}
				}
			}
		}
		
		// draws all the captured pieces on to the side of the chess board 
		// as they will be out of play for the remainder of the game
		if (wRcount != 2)
		{
			for (int i = (2 - wRcount); i > 0; i--)
			{
				g.drawImage(wR, 30 + (50*i), 130, this);
			}
		}
		
		if (wKcount !=2)
		{
			for (int i = (2 - wKcount); i > 0; i--)
			{
				g.drawImage(wK, 30 + (50*i), 180, this);
			}
		}
		
		if (wBcount != 2)
		{
			for (int i = (2 - wBcount); i > 0; i--)
			{
				g.drawImage(wB, 30 + (50*i), 230, this);
			}
		}
		
		if (wQcount !=1)
		{
			g.drawImage(wQ, 80, 280, this);
			
		}
		
		if (wPcount != 8)
		{
			for (int i = (8 - wPcount); i > 0; i--)
			{
				if (i < 3)
				{
					if (i%2 == 0)
					{
						g.drawImage(wP, 120, 330, this);
					}
					else
					{
						g.drawImage(wP, 80, 330, this);
					}
				}
				else if ( i < 5)
				{
					if (i%2 == 0)
					{
						g.drawImage(wP, 120, 380, this);
					}
					else
					{
						g.drawImage(wP, 80, 380, this);
					}
				}
				else if (i < 7)
				{
					if (i%2 == 0)
					{
						g.drawImage(wP, 120, 430, this);
					}
					else
					{
						g.drawImage(wP, 80, 430, this);
					}
				}
				else
				{
					if (i%2 == 0)
					{
						g.drawImage(wP, 160, 330, this);
					}
					else
					{
						g.drawImage(wP, 160, 380, this);
					}
				}
			}
		}
		
		if (bRcount != 2)
		{
			for (int i = (2 - bRcount); i > 0; i--)
			{
				g.drawImage(bR, 650 + (50*i), 130, this);
			}
		}
		
		if (bKcount !=2)
		{
			for (int i = (2 - bKcount); i > 0; i--)
			{
				g.drawImage(bK, 650 + (50*i), 180, this);
			}
		}
		
		if (bBcount != 2)
		{
			for (int i = (2 - bBcount); i > 0; i--)
			{
				g.drawImage(bB, 650 + (50*i), 230, this);
			}
		}
		
		if (bQcount !=1)
		{
			g.drawImage(bQ, 700, 280, this);
			
		}
		
		if (bPcount != 8)
		{

			for (int i = (8 - bPcount); i > 0; i--)
			{
				if (i < 3)
				{
					if (i%2 == 0)
					{
						g.drawImage(bP, 740, 330, this);
					}
					else
					{
						g.drawImage(bP, 700, 330, this);
					}
				}
				else if ( i < 5)
				{
					if (i%2 == 0)
					{
						g.drawImage(bP, 740, 380, this);
					}
					else
					{
						g.drawImage(bP, 700, 380, this);
					}
				}
				else if (i < 7)
				{
					if (i%2 == 0)
					{
						g.drawImage(bP, 740, 430, this);
					}
					else
					{
						g.drawImage(bP, 700, 430, this);
					}
				}
				else
				{
					if (i%2 == 0)
					{
						g.drawImage(bP, 780, 330, this);
					}
					else
					{
						g.drawImage(bP, 780, 380, this);
					}
				}
			}
		}
		
		// draws a black border to highlight which square within the chess board the piece was moved to
		g.drawRect(releaseX*50 + 250, releaseY*50 + 100, 50, 50);
	}

	// text file streaming for keeping track of the game scores
	public static int[] saveWins() throws IOException
	{
		Scanner inputFile = new Scanner (new File("chessWinSave.txt"));
		
		int blackWins = inputFile.nextInt();
		int whiteWins = inputFile.nextInt();
		
		if (wTurn == true)
		{
			blackWins ++;
		}
		else
		{
			whiteWins ++;
		}
		
		PrintWriter outputFile = new PrintWriter(new FileWriter("chessWinSave.txt",false));
		
		outputFile.println(blackWins);
		outputFile.println(whiteWins);
		
		countWins[0] = blackWins;
		countWins[1] = whiteWins;
		inputFile.close();
		outputFile.close();
		
		return countWins;
	}

	public static void main (String[] args) 
	{
		JFrame myFrame = new JFrame("Chess");
		ISU myPanel = new ISU();
		myFrame.add(myPanel);
		myFrame.pack();
		myFrame.setVisible(true);
		myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

}
