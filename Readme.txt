Read Me Text File - Jacqueline Ho, Jonathan Feng					Wednesday Jan 27, 2020

Partner Responsibilities:

Jonathan: 
- coded the mouse listener methods
- coded all the piece movement methods
- coded the checkmate methods
- coded the paintComponent 
- coded the saveWin method and text file streaming for the scoreboard

Jacqueline:
- coded the check methods
- coded the pieceMove method
- coded the rightCapColour method
- designed the game screens/graphics
- coded the graphics methods (drawBoard, drawEndScreen, drawStartScreen)


Additional Functionalities:

- The option of a 'game mode' setting in which the user can choose between a 'dark mode' or 'royal mode' and change the theme of the graphics accordingly. We decided to add this option as we wanted to include three buttons on our open screen but didn't know what the third button could be; thus, we decided to create a 'mode' button for a more customizable game experience.

- The addition of castling in which a player's king and either of its rooks can both be moved within the same turn (however, this only occurs if the king has been untouched, all of the spaces between the pieces are empty, if the king is not in check, and the squares that the king passes over to is not under attack). We decided to add this option because Mr. Chow told us to and as well, we wanted to extend our skills of programming.

- The ability to drag a piece, as opposed to using a two-part series of mouse clicks, was added to the game to allow the user to move their pieces more efficiently. As we originally had the idea of moving each chess piece by having the user make two mouse clicks (in which the first click initialized the piece they wanted to move and the second click initialized the square they wanted to move it to), we then realized dragging the piece and releasing it to the designated square would be more efficient for both the user and our code. 

- The addition of a score board that keeps track of the number of wins of each coloured player including the previous scores. We implemented a score board as we wanted to utilize text file streaming within our code.

- The drawing of captured pieces on the sides of the chess board. We decided it would be aesthetically pleasing to the eyes if the players could see which pieces were captured and out of play throughout the game.


Missing Functionalities:

- no missing functionalities


Known bugs / errors:

- Castling will still occur if the rook has been moved but will not move if the king has been moved :/ 



