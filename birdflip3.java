import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
//Hi I am Hussein, I declared many things globally and this is a lot of text, but i wont write as much later on so don't worry
public class birdflip3 extends Applet implements ActionListener
{
    Panel p_card, grid, grid2, grid3, grid4; //p_card is the panel that holds all of the card layouts and grids are panels that hold gridlayouts for each level of the 4 levels
    JLabel highscores[], highscores2[], highscores3[]; //high scores that are printed on each game screen
    String aboutimages[] = new String [6]; //string array that holds the names of the image files implemented in the about page
    String aboutsentences[] = new String [6]; //string array that holds the sentences and text implemented in the about page
    JLabel aboutpic, abouttext, endimage, endtext; //aboutpic and abouttext are JLabels using the above strings for setText if a button is clicked. endimage and endtextare for the ending screen
    Panel highscoredisplay, highscoredisplay2, highscoredisplay3; //displays highscores using the highscores[] arrays
    Panel menucard, aboutcard, gamecard, game1card, game2card, easygame1card, hardgame1card, easygame2card, hardgame2card, endcard; //all of the panels for the screens used in the cdLayout
    JProgressBar gameprogress, easygame1progress, hardgame1progress; //JProgressBars used in the menu page, and game1 easy and hard levels
    int easy1progresscount, hard1progresscount, easy2progresscount, hard2progresscount, nextnum = 0; //counters increased after being done to increase the JProgressbar value if a level is done
    int gameprogressamt, easygame1progressamt, hardgame1progressamt = 0; //amt:amount, these are the values of the JProgrressbars
    JLabel pscoreeasy1, phighscoreeasy1, pscorehard1, phighscorehard1, pscoreeasy2, phighscoreeasy2, pscorehard2, phighscorehard2; //print the score and highscore of every level
    int scoreeasy1, highscoreeasy1, scorehard1, highscorehard1, scoreeasy2, highscoreeasy2, scorehard2, highscorehard2 = 0; //the score and highscore of every level
    JLabel peasymovesused, peasymovesleft, phardmovesused, phardmovesleft; //moves used and moves left JLabels for the easy and hard levels in game2/smart free
    int easymovesleft = 24; //declaring the integers of them
    int hardmovesleft = 20;
    int easymovesused, hardmovesused = 0;
    CardLayout cdLayout = new CardLayout (); //main and only card layout used in the game for storing screens
    JButton d[], d2[], d3[], d4[], end; //jbutton arrays for the grids on each level. end is the button for the ending screen activation
    int c = 0; //a counter used in sorting the grids of the levels, can be changed
    int ranpos[], ranpos2[], ranpos3[], ranpos4[]; //ranpos:random position, an array used to randomly position pictures in the grid of each level, there are 4 again
    int ranorg, ranorg2, ranorg3, ranorg4 = 0; //ranorg:random organization, an int that uses Math.random to get a random number and position it randomly for each grid
    int check = 0; //checking counter used in the grid of each level
    String ac1, ac2, acnum1, acnum2 = ""; //ac:action command, used to store the action commands of the grids in each level, acnum stored the ac's number
    int num1, num2, imnum1, imnum2 = 0; //these are also used in the action performed method of the grid, imnum:image number, the different images i have for matching have a specific number to them
    int vischeckeasy1, vischeckhard1 = 0; //vischeck:visibility check, used in the action performed of game1/full free
    Color purple = new Color (154, 140, 227); //color i declared to match the color scheme
    AudioClip music; //music variable

    public void init ()
    {
	resize (800, 600);
	setBackground (Color.black);

	p_card = new Panel (); //initializing and adding the panel and layout holding the screens
	p_card.setLayout (cdLayout);
	menu (); //all of the screens' void methods
	aboutpage ();
	endpage ();
	gamepage ();
	game1page ();
	easygame1page ();
	hardgame1page ();
	game2page ();
	easygame2page ();
	hardgame2page ();
	setLayout (new BorderLayout ());
	add ("Center", p_card);
    }


    public void menu ()
    {
	playaudio ("birdflipmusic.wav"); //playing audio,theme song of tomb raider: https://www.youtube.com/watch?v=x_76AaWVQXE
	playaudio ("birdsounds.wav"); //playing bird songs/sounds to loop with the music, it sounds pretty cool: https://www.youtube.com/watch?v=d_gF8A3tqVc
	menucard = new Panel (); //declaring menu panel
	menucard.setBackground (Color.black);

	Panel menubuttons = new Panel (new GridLayout (1, 3)); //buttons of the menu

	JLabel birdpic = new JLabel (createImageIcon ("birdpark.jpg"));

	JButton play = new JButton ("PLAY"); //takes you to the game
	play.setFont (new Font ("Algerian", Font.PLAIN, 50));
	play.setBackground (Color.black);
	play.setForeground (purple);
	play.addActionListener (this);
	play.setActionCommand ("play");
	menubuttons.add (play);

	end = new JButton ("ENDING"); //shows if you complete each level, only visible after you do so
	end.setFont (new Font ("Algerian", Font.PLAIN, 50));
	end.setBackground (Color.black);
	end.setForeground (purple);
	end.setVisible (false);
	end.addActionListener (this);
	end.setActionCommand ("end");
	menubuttons.add (end);

	JButton about = new JButton ("ABOUT"); // has backstory
	about.setFont (new Font ("Algerian", Font.PLAIN, 50));
	about.setBackground (Color.black);
	about.setForeground (purple);
	about.addActionListener (this);
	about.setActionCommand ("about");
	menubuttons.add (about);

	gameprogress = new JProgressBar (0, 100);
	gameprogress.setValue (gameprogressamt);
	gameprogress.setStringPainted (true);
	gameprogress.setString ("Game is " + gameprogressamt + "% completed");
	gameprogress.setFont (new Font ("Algerian", Font.PLAIN, 14));
	gameprogress.setPreferredSize (new Dimension (200, 25));

	menucard.add (birdpic);
	menucard.add (menubuttons);
	menucard.add (gameprogress);
	p_card.add ("menu", menucard); //adding widgets to the menu method and adding the menu method to the main screen layout, do the same thing with other screens
    }


    public void aboutpage ()
    {
	aboutcard = new Panel ();
	aboutcard.setBackground (Color.black);

	aboutpic = new JLabel (""); //declared reason in the global area
	abouttext = new JLabel ("");
	abouttext.setFont (new Font ("Algerian", Font.PLAIN, 14));
	abouttext.setForeground (Color.white);
	nextnum++;//Since the first part of the about page will already be printed

	Panel buttons = new Panel (new GridLayout (1, 2));

	aboutimages [0] = new String ("birdpark1.jpg"); //holds picture file names in the about page
	aboutimages [1] = new String ("birdpark2.jpg");
	aboutimages [2] = new String ("cagedbirds.jpg");
	aboutimages [3] = new String ("gameboard.jpg");
	aboutimages [4] = new String ("game1labels.jpg");
	aboutimages [5] = new String ("game2labels.jpg");
	//holds text in about page for each picture
	aboutsentences [0] = new String ("<html><center>There once were 5 birds living in harmony. Well most of them, unlike the angry bird<br><html><center>" +
		"who was angry at everything. These birds were all of a rare breed and<br><html><center>were located in Banff National Park. This was" +
		"all true until a man came, and everything changed.<html><center>");
	aboutsentences [1] = new String ("<html><center>He was a hunter, one of the deadliest hunters out there in fact. And he wanted to capture<br><html><center>" +
		"these rare birds. He ran into the area to do this, and accomplished his goal.<html><center>");
	aboutsentences [2] = new String ("<html><center>He locked the birds in a cage, where he would sell each one individually later. If he<br><html><center>" +
		"did this, it would change the birds' lives forever, and they would never be able to live freely again.<html><center>");
	aboutsentences [3] = new String ("<html><center>Its up to you to free them. And you can do this by playing a game. 4 grids represents the<br><html><center>" +
		"cage, in which you have to match each bird a specific amount of times to free<br><html><center>them. In addition, there are 2 different modes<br><html><center>" +
		"containing 2 levels each. You have to free them in all of the levels to free them completely.<html><center>");
	aboutsentences [4] = new String ("<html><center>The first mode is Full Free, in which you have to match every single bird and free them.<br><html><center>" +
		"In this mode you have a score. If you match 2 of the same images of 1 bird, part<br><html><center>of the bird will be set free, and your score will" +
		"increase by 4. If you match 2<br><html><center>images of different birds, none of them will be set free, your score will decrease<br><html><center>" +
		"by 1, and the cards will be flipped back over. Furthermore, you can set a High Score as well,<br><html><center>but it won't update until you complete" +
		"the level, so do that to set new High Scores.<br><html><center>There is an easy and hard level in this mode. In the easy level, you have 4 different birds" +
		"<br><html><center>to match 4 times each, resulting in a 4 x 4 grid. Int the hard level, you 5 different birds<br><html><center>to match 6 times each," +
		"resulting in a 5 x 6 grid. You have to<br><html><center>complete both of them to move on and take part in freeing the birds.<html><center>");
	aboutsentences [5] = new String ("<html><center>The second mode is Smart Free, in which you have a specific amount of moves to free as many birds as you can." +
		"In this mode, there is also a score and a High Score. If you match 2 of the same images of 1 bird, part of the bird will be set free, and your" +
		"score will increase by 4. Every time you make a move, regardless of if you match 2 bird images or not, 1 move will be taken away, until you run" +
		"out of moves. In addition, you can set a High Score as well, but it won't update until you complete the level, so do that and you can create new" +
		"High Scores. There is an easy and hard level in this mode. In the easy level, you have 24 moves, and 4 different birds that you are able to match" +
		"In the hard level, you have 20 moves, and 5 different birds that you are able to match. You have to complete both of them to move on and take part" +
		"in freeing the birds.<html><center>");

	aboutpic.setIcon (createImageIcon (aboutimages [0])); //setIcon since its a picture
	abouttext.setText (aboutsentences [0]);

	JButton next = new JButton ("Next");
	next.setFont (new Font ("Algerian", Font.PLAIN, 17));
	next.setBackground (Color.black);
	next.setForeground (Color.white);
	next.addActionListener (this);
	next.setActionCommand ("next"); //next button to navigate through story
	buttons.add (next);

	JButton mainmenu = new JButton ("Main Menu");
	mainmenu.setBackground (Color.black);
	mainmenu.setForeground (Color.white); //main menu shortcut if you are done or don't want to go through it
	mainmenu.setFont (new Font ("Algerian", Font.PLAIN, 17));
	mainmenu.addActionListener (this);
	mainmenu.setActionCommand ("mainmenu"); //anytime a user wants to go to the mainmenu, tha actioncommand is the same
	buttons.add (mainmenu);

	aboutcard.add (aboutpic);
	aboutcard.add (abouttext);
	aboutcard.add (buttons);
	p_card.add ("aboutpage", aboutcard);
    }


    public void gamepage ()
    {
	gamecard = new Panel ();
	gamecard.setBackground (Color.black);

	JButton space[] = new JButton [4]; //adding an insented space to balance the screen and not make everything look like its on the top, do this with other methods
	Panel spacegrid = new Panel (new GridLayout (1, 4));
	for (int i = 0 ; i < space.length ; i++)
	{
	    space [i] = new JButton ("");
	    space [i].setPreferredSize (new Dimension (175, 80));
	    space [i].setVisible (false);
	    spacegrid.add (space [i]);
	}

	JButton game1 = new JButton ("Full Free"); //first game mode
	game1.setFont (new Font ("Algerian", Font.PLAIN, 60));
	game1.setBackground (Color.black);
	game1.setForeground (purple);
	game1.addActionListener (this);
	game1.setActionCommand ("game1");

	JButton game2 = new JButton ("Smart Free"); //second game mode
	game2.setFont (new Font ("Algerian", Font.PLAIN, 60));
	game2.setBackground (Color.black);
	game2.setForeground (purple);
	game2.addActionListener (this);
	game2.setActionCommand ("game2");

	highscores = new JLabel [5]; //printing highscores on the game screen, once you get a highscore it will update
	highscoredisplay = new Panel (new GridLayout (5, 1));
	highscores [0] = new JLabel ("High Scores:");
	highscores [1] = new JLabel ("Full Free, Easy: " + highscoreeasy1);
	highscores [2] = new JLabel ("Full Free, Hard: " + highscorehard1);
	highscores [3] = new JLabel ("Smart Free, Easy: " + highscoreeasy2);
	highscores [4] = new JLabel ("Smart Free, Hard: " + highscorehard2);
	for (int i = 0 ; i < highscores.length ; i++)
	{
	    highscores [i].setFont (new Font ("Algerian", Font.PLAIN, 40));
	    highscores [i].setForeground (Color.white);
	    highscoredisplay.add (highscores [i]);
	}

	JButton menuarray[] = new JButton [3]; //array to space out and make it look like the menu button was positioned in the bottom left
	Panel pmenuarray = new Panel (new GridLayout (1, 3));
	for (int i = 0 ; i < menuarray.length ; i++)
	{
	    menuarray [i] = new JButton ("Main Menu");
	    menuarray [i].setFont (new Font ("Algerian", Font.PLAIN, 35));
	    menuarray [i].setBackground (Color.black);
	    menuarray [i].setForeground (Color.white);
	    menuarray [i].addActionListener (this);
	    menuarray [i].setActionCommand ("mainmenu");
	    if (i != 0)
		menuarray [i].setVisible (false);
	    pmenuarray.add (menuarray [i]);
	}

	gamecard.add (spacegrid);
	gamecard.add (game1);
	gamecard.add (game2);
	gamecard.add (highscoredisplay);
	gamecard.add (pmenuarray);
	p_card.add ("gamepage", gamecard);
    }


    public void game1page ()
    {
	game1card = new Panel ();
	game1card.setBackground (Color.black);

	JButton space[] = new JButton [4];
	Panel spacegrid = new Panel (new GridLayout (1, 4));
	for (int i = 0 ; i < space.length ; i++)
	{
	    space [i] = new JButton ("");
	    space [i].setPreferredSize (new Dimension (175, 100));
	    space [i].setVisible (false);
	    spacegrid.add (space [i]);
	}

	JButton easy1 = new JButton ("Easy"); //easy for game1
	easy1.setFont (new Font ("Algerian", Font.PLAIN, 65));
	easy1.setBackground (Color.black);
	easy1.setForeground (purple);
	easy1.addActionListener (this);
	easy1.setActionCommand ("easy1");

	JButton hard1 = new JButton ("Hard"); //hard for game1
	hard1.setFont (new Font ("Algerian", Font.PLAIN, 65));
	hard1.setBackground (Color.black);
	hard1.setForeground (purple);
	hard1.addActionListener (this);
	hard1.setActionCommand ("hard1");

	highscores2 = new JLabel [3]; //rpints out high scores like the last method but only for game1
	highscoredisplay2 = new Panel (new GridLayout (3, 1));
	highscores2 [0] = new JLabel ("High Scores:");
	highscores2 [1] = new JLabel ("Full Free, Easy: " + highscoreeasy1);
	highscores2 [2] = new JLabel ("Full Free, Hard: " + highscorehard1);
	for (int i = 0 ; i < highscores2.length ; i++)
	{
	    highscores2 [i].setFont (new Font ("Algerian", Font.PLAIN, 50));
	    highscores2 [i].setForeground (Color.white);
	    highscoredisplay2.add (highscores2 [i]);
	}

	JButton menuarray2[] = new JButton [3];
	Panel pmenuarray2 = new Panel (new GridLayout (1, 3));
	for (int i = 0 ; i < menuarray2.length ; i++)
	{
	    menuarray2 [i] = new JButton ("Main Menu");
	    menuarray2 [i].setFont (new Font ("Algerian", Font.PLAIN, 35));
	    menuarray2 [i].setBackground (Color.black);
	    menuarray2 [i].setForeground (Color.white);
	    menuarray2 [i].addActionListener (this);
	    menuarray2 [i].setActionCommand ("mainmenu");
	    if (i != 0)
		menuarray2 [i].setVisible (false);
	    pmenuarray2.add (menuarray2 [i]);
	}

	game1card.add (spacegrid);
	game1card.add (easy1);
	game1card.add (hard1);
	game1card.add (highscoredisplay2);
	game1card.add (pmenuarray2);
	p_card.add ("game1page", game1card);
    }


    public void easygame1page ()  //screen for easy game1
    {
	easygame1card = new Panel ();
	easygame1card.setBackground (Color.black);

	JButton space[] = new JButton [4];
	Panel spacegrid = new Panel (new GridLayout (1, 4));
	for (int i = 0 ; i < space.length ; i++)
	{
	    space [i] = new JButton ("");
	    space [i].setPreferredSize (new Dimension (175, 80));
	    space [i].setVisible (false);
	    spacegrid.add (space [i]);
	}

	birdorganization (1, "easy1"); //calling dthe grid method to sort out the grid for this level, do this for all 4 levels and input is different each time

	Panel gamefunctions = new Panel (new GridLayout (1, 3));

	JButton mainmenu = new JButton ("Main Menu"); //mainmenu
	mainmenu.setBackground (Color.black);
	mainmenu.setForeground (Color.white);
	mainmenu.setFont (new Font ("Algerian", Font.PLAIN, 14));
	mainmenu.addActionListener (this);
	mainmenu.setActionCommand ("mainmenu");
	gamefunctions.add (mainmenu);

	JButton reset = new JButton ("Reset"); //reset button, do this with other methods
	reset.setBackground (Color.black);
	reset.setForeground (Color.white);
	reset.setFont (new Font ("Algerian", Font.PLAIN, 14));
	reset.addActionListener (this);
	reset.setActionCommand ("easy1");
	gamefunctions.add (reset);

	JButton check = new JButton ("Check"); //check button to check if cards flippied match, do this with other methods
	check.setBackground (Color.black);
	check.setForeground (Color.white);
	check.setFont (new Font ("Algerian", Font.PLAIN, 14));
	check.addActionListener (this);
	check.setActionCommand ("easy1check");
	gamefunctions.add (check);

	pscoreeasy1 = new JLabel ("Score: " + scoreeasy1 + "      "); //printing out the score for the easy level
	pscoreeasy1.setForeground (Color.white);
	pscoreeasy1.setFont (new Font ("Algerian", Font.PLAIN, 14));
	gamefunctions.add (pscoreeasy1);

	phighscoreeasy1 = new JLabel ("Highscore: " + highscoreeasy1); //printing out score for high level
	phighscoreeasy1.setForeground (Color.white);
	phighscoreeasy1.setFont (new Font ("Algerian", Font.PLAIN, 14));
	gamefunctions.add (phighscoreeasy1);

	easygame1progress = new JProgressBar (0, 100);
	easygame1progress.setValue (easygame1progressamt); //progressbar showing completion of the leve;
	easygame1progress.setStringPainted (true);
	easygame1progress.setString ("Level is " + easygame1progressamt + "% Completed");
	easygame1progress.setFont (new Font ("Algerian", Font.PLAIN, 12));

	easygame1card.add (spacegrid);
	easygame1card.add (grid);
	easygame1card.add (gamefunctions);
	easygame1card.add (easygame1progress);
	p_card.add ("easygame1page", easygame1card);
    }


    public void hardgame1page ()
    {
	hardgame1card = new Panel ();
	hardgame1card.setBackground (Color.black);

	JButton space[] = new JButton [4];
	Panel spacegrid = new Panel (new GridLayout (1, 4));
	for (int i = 0 ; i < space.length ; i++)
	{
	    space [i] = new JButton ("");
	    space [i].setPreferredSize (new Dimension (175, 80));
	    space [i].setVisible (false);
	    spacegrid.add (space [i]);
	}

	birdorganization (2, "hard1");

	Panel gamefunctions = new Panel (new GridLayout (1, 3));
	Panel gamelabels = new Panel (new GridLayout (1, 2));

	JButton mainmenu = new JButton ("Main Menu");
	mainmenu.setBackground (Color.black);
	mainmenu.setForeground (Color.white);
	mainmenu.setFont (new Font ("Algerian", Font.PLAIN, 14));
	mainmenu.addActionListener (this);
	mainmenu.setActionCommand ("mainmenu");
	gamefunctions.add (mainmenu);

	JButton reset = new JButton ("Reset");
	reset.setBackground (Color.black);
	reset.setForeground (Color.white);
	reset.setFont (new Font ("Algerian", Font.PLAIN, 14));
	reset.addActionListener (this);
	reset.setActionCommand ("hard1");
	gamefunctions.add (reset);

	JButton check = new JButton ("Check");
	check.setBackground (Color.black);
	check.setForeground (Color.white);
	check.setFont (new Font ("Algerian", Font.PLAIN, 14));
	check.addActionListener (this);
	check.setActionCommand ("hard1check");
	gamefunctions.add (check);

	pscorehard1 = new JLabel ("Score: " + scorehard1 + "          ");
	pscorehard1.setForeground (Color.white);
	pscorehard1.setFont (new Font ("Algerian", Font.PLAIN, 14));
	gamelabels.add (pscorehard1);

	phighscorehard1 = new JLabel ("Highscore: " + highscorehard1 + "          ");
	phighscorehard1.setForeground (Color.white);
	phighscorehard1.setFont (new Font ("Algerian", Font.PLAIN, 14));
	gamelabels.add (phighscorehard1);

	hardgame1progress = new JProgressBar (0, 100);
	hardgame1progress.setValue (hardgame1progressamt);
	hardgame1progress.setStringPainted (true);
	hardgame1progress.setString ("Level is " + hardgame1progressamt + "% Completed");
	hardgame1progress.setFont (new Font ("Algerian", Font.PLAIN, 12));

	hardgame1card.add (spacegrid);
	hardgame1card.add (grid2);
	hardgame1card.add (gamefunctions);
	hardgame1card.add (gamelabels);
	hardgame1card.add (hardgame1progress);
	p_card.add ("hardgame1page", hardgame1card);
    }


    public void game2page ()  //screen for game2
    {
	game2card = new Panel ();
	game2card.setBackground (Color.black);

	JButton space[] = new JButton [4];
	Panel spacegrid = new Panel (new GridLayout (1, 4));
	for (int i = 0 ; i < space.length ; i++)
	{
	    space [i] = new JButton ("");
	    space [i].setPreferredSize (new Dimension (175, 100));
	    space [i].setVisible (false);
	    spacegrid.add (space [i]);
	}

	JButton easy2 = new JButton ("Easy"); //game 2 easy level
	easy2.setFont (new Font ("Algerian", Font.PLAIN, 65));
	easy2.setBackground (Color.black);
	easy2.setForeground (purple);
	easy2.addActionListener (this);
	easy2.setActionCommand ("easy2");

	JButton hard2 = new JButton ("Hard"); // game 2 hard level
	hard2.setFont (new Font ("Algerian", Font.PLAIN, 65));
	hard2.setBackground (Color.black);
	hard2.setForeground (purple);
	hard2.addActionListener (this);
	hard2.setActionCommand ("hard2");

	highscores3 = new JLabel [3]; // printing out high scores, but only for the levels in game 2
	highscoredisplay3 = new Panel (new GridLayout (3, 1));
	highscores3 [0] = new JLabel ("High Scores:");
	highscores3 [1] = new JLabel ("Smart Free, Easy: " + highscoreeasy2);
	highscores3 [2] = new JLabel ("Smart Free, Hard: " + highscorehard2);
	for (int i = 0 ; i < highscores3.length ; i++)
	{
	    highscores3 [i].setFont (new Font ("Algerian", Font.PLAIN, 50));
	    highscores3 [i].setForeground (Color.white);
	    highscoredisplay3.add (highscores3 [i]);
	}

	JButton menuarray3[] = new JButton [3];
	Panel pmenuarray3 = new Panel (new GridLayout (1, 3));
	for (int i = 0 ; i < menuarray3.length ; i++)
	{
	    menuarray3 [i] = new JButton ("Main Menu");
	    menuarray3 [i].setFont (new Font ("Algerian", Font.PLAIN, 35));
	    menuarray3 [i].setBackground (Color.black);
	    menuarray3 [i].setForeground (Color.white);
	    menuarray3 [i].addActionListener (this);
	    menuarray3 [i].setActionCommand ("mainmenu");
	    if (i != 0)
		menuarray3 [i].setVisible (false);
	    pmenuarray3.add (menuarray3 [i]);
	}

	game2card.add (spacegrid);
	game2card.add (easy2);
	game2card.add (hard2);
	game2card.add (highscoredisplay3);
	game2card.add (pmenuarray3);
	p_card.add ("game2page", game2card);
    }


    public void easygame2page ()
    {
	easygame2card = new Panel ();
	easygame2card.setBackground (Color.black);

	JButton space[] = new JButton [4];
	Panel spacegrid = new Panel (new GridLayout (1, 4));
	for (int i = 0 ; i < space.length ; i++)
	{
	    space [i] = new JButton ("");
	    space [i].setPreferredSize (new Dimension (175, 40));
	    space [i].setVisible (false);
	    spacegrid.add (space [i]);
	}

	birdorganization (3, "easy2");

	Panel gamefunctions = new Panel (new GridLayout (1, 3));
	Panel gamelabels = new Panel (new GridLayout (1, 4));

	JButton mainmenu = new JButton ("Main Menu");
	mainmenu.setBackground (Color.black);
	mainmenu.setForeground (Color.white);
	mainmenu.setFont (new Font ("Algerian", Font.PLAIN, 14));
	mainmenu.addActionListener (this);
	mainmenu.setActionCommand ("mainmenu");
	gamefunctions.add (mainmenu);

	JButton reset = new JButton ("Reset");
	reset.setBackground (Color.black);
	reset.setForeground (Color.white);
	reset.setFont (new Font ("Algerian", Font.PLAIN, 14));
	reset.addActionListener (this);
	reset.setActionCommand ("easy2");
	gamefunctions.add (reset);

	JButton check = new JButton ("Check");
	check.setBackground (Color.black);
	check.setForeground (Color.white);
	check.setFont (new Font ("Algerian", Font.PLAIN, 14));
	check.addActionListener (this);
	check.setActionCommand ("easy2check");
	gamefunctions.add (check);

	pscoreeasy2 = new JLabel ("Score: " + scoreeasy2 + "          ");
	pscoreeasy2.setForeground (Color.white);
	pscoreeasy2.setFont (new Font ("Algerian", Font.PLAIN, 14));
	gamelabels.add (pscoreeasy2);

	phighscoreeasy2 = new JLabel ("Highscore: " + highscoreeasy2 + "          ");
	phighscoreeasy2.setForeground (Color.white);
	phighscoreeasy2.setFont (new Font ("Algerian", Font.PLAIN, 14));
	gamelabels.add (phighscoreeasy2);

	peasymovesused = new JLabel ("Moves used: " + easymovesused + "          "); //for printing the amount of moves used and moves left, since game2 runs
	peasymovesused.setForeground (Color.white);                                 //on amount of moves, do the same with hard level of game 2
	peasymovesused.setFont (new Font ("Algerian", Font.PLAIN, 14));
	gamelabels.add (peasymovesused);

	peasymovesleft = new JLabel ("Moves left: " + easymovesleft);
	peasymovesleft.setForeground (Color.white);
	peasymovesleft.setFont (new Font ("Algerian", Font.PLAIN, 14));
	gamelabels.add (peasymovesleft);

	easygame2card.add (spacegrid);
	easygame2card.add (grid3);
	easygame2card.add (gamefunctions);
	easygame2card.add (gamelabels);
	p_card.add ("easygame2page", easygame2card);
    }


    public void hardgame2page ()
    {
	hardgame2card = new Panel ();
	hardgame2card.setBackground (Color.black);

	JButton space[] = new JButton [4];
	Panel spacegrid = new Panel (new GridLayout (1, 4));
	for (int i = 0 ; i < space.length ; i++)
	{
	    space [i] = new JButton ("");
	    space [i].setPreferredSize (new Dimension (175, 40));
	    space [i].setVisible (false);
	    spacegrid.add (space [i]);
	}

	birdorganization (4, "hard2");

	Panel gamefunctions = new Panel (new GridLayout (1, 3));
	Panel gamelabels = new Panel (new GridLayout (1, 4));

	JButton mainmenu = new JButton ("Main Menu");
	mainmenu.setBackground (Color.black);
	mainmenu.setForeground (Color.white);
	mainmenu.setFont (new Font ("Algerian", Font.PLAIN, 14));
	mainmenu.addActionListener (this);
	mainmenu.setActionCommand ("mainmenu");
	gamefunctions.add (mainmenu);

	JButton reset = new JButton ("Reset");
	reset.setBackground (Color.black);
	reset.setForeground (Color.white);
	reset.setFont (new Font ("Algerian", Font.PLAIN, 14));
	reset.addActionListener (this);
	reset.setActionCommand ("hard2");
	gamefunctions.add (reset);

	JButton check = new JButton ("Check");
	check.setBackground (Color.black);
	check.setForeground (Color.white);
	check.setFont (new Font ("Algerian", Font.PLAIN, 14));
	check.addActionListener (this);
	check.setActionCommand ("hard2check");
	gamefunctions.add (check);

	pscorehard2 = new JLabel ("Score: " + scorehard2 + "             ");
	pscorehard2.setForeground (Color.white);
	pscorehard2.setFont (new Font ("Algerian", Font.PLAIN, 14));
	gamelabels.add (pscorehard2);

	phighscorehard2 = new JLabel ("Highscore: " + highscorehard2 + "         ");
	phighscorehard2.setForeground (Color.white);
	phighscorehard2.setFont (new Font ("Algerian", Font.PLAIN, 14));
	gamelabels.add (phighscorehard2);

	phardmovesused = new JLabel ("Moves used: " + hardmovesused + "          ");
	phardmovesused.setForeground (Color.white);
	phardmovesused.setFont (new Font ("Algerian", Font.PLAIN, 14));
	gamelabels.add (phardmovesused);

	phardmovesleft = new JLabel ("Moves left: " + hardmovesleft + "          ");
	phardmovesleft.setForeground (Color.white);
	phardmovesleft.setFont (new Font ("Algerian", Font.PLAIN, 14));
	gamelabels.add (phardmovesleft);

	hardgame2card.add (spacegrid);
	hardgame2card.add (grid4);
	hardgame2card.add (gamefunctions);
	hardgame2card.add (gamelabels);
	p_card.add ("hardgame2page", hardgame2card);
    }


    public void endpage ()  //page if you complete all the levels
    {
	endcard = new Panel ();
	endcard.setBackground (Color.black);

	Panel buttons = new Panel (new GridLayout (1, 2));

	endimage = new JLabel (createImageIcon ("freebirds.jpg")); //ending cartoon image of birds escaping out of a briken cage

	endtext = new JLabel ("<html><center>You have completed all of the levels, and successfully freed the birds. They can now live freely<br><html><center>" +
		"and are now living in a hidden area of Banff National Park where no one has been and no hunter will be able to<br><html><center>catch the. This was" +
		"only a reality because of you. To continue on with the game and try to beat<br><html><center> your High Scores, press Continue. To replay the entire game," +
		"press Replay.<center><html>", SwingConstants.CENTER); //text telling the user they did a good job
	endtext.setForeground (Color.white);
	endtext.setFont (new Font ("Algerian", Font.PLAIN, 13));

	JButton continue1 = new JButton ("Continue"); //continue with the game to beat high scores and keep current highscores
	continue1.setBackground (Color.black);
	continue1.setForeground (Color.white);
	continue1.setFont (new Font ("Algerian", Font.PLAIN, 18));
	continue1.addActionListener (this);
	continue1.setActionCommand ("mainmenu"); //mainmenu because it doesnt need to update anything and just continue on with the game
	buttons.add (continue1);

	JButton replay = new JButton ("Replay"); //replay the entire game, resetting high scores and other variables
	replay.setBackground (Color.black);
	replay.setForeground (Color.white);
	replay.setFont (new Font ("Algerian", Font.PLAIN, 18));
	replay.addActionListener (this);
	replay.setActionCommand ("replay");
	buttons.add (replay);

	endcard.add (endimage);
	endcard.add (endtext);
	endcard.add (buttons);
	p_card.add ("endpage", endcard);
    }


    public void birdorganization (int a, String b)
    {
	if (a == 1) //game1easy
	{
	    a = 4;
	    c = a * a;
	}
	else if (a == 2) //game1hard
	{
	    a = 6;
	    c = 5 * a;
	}
	else if (a == 3) //game2easy
	{
	    a = 6;
	    c = a * 8;
	}
	else if (a == 4) //game2hard
	{
	    a = 6;
	    c = a * 8;
	}
	if (b.equals ("easy1"))
	{
	    d = new JButton [c]; //sets up the cards for the easy level in game1
	    ranpos = new int [c]; //declaring random positioning array to randomly position cards after
	    grid = new Panel (new GridLayout (a, a)); //gridlayout 4 x 4 for easy
	    for (int j = 0 ; j < d.length ; j++)
	    {
		d [j] = new JButton (createImageIcon ("checkeredboarda.jpg"));
		d [j].setVisible (false);
		d [j].setPreferredSize (new Dimension (80, 80));
		d [j].addActionListener (this);
		d [j].setActionCommand ("a" + j);
		grid.add (d [j]);
		ranpos [j] = 0; //declaring all off ranposition values to 0
		if (j < 4)
		{
		    d [j].setIcon (createImageIcon ("bird1a.jpg")); //since there are 16 cards this makes the use of 4 of each card, 2 pairs
		}
		else if (j < 8)
		{
		    d [j].setIcon (createImageIcon ("bird2a.jpg"));
		}
		else if (j < 12)
		{
		    d [j].setIcon (createImageIcon ("bird3a.jpg"));
		}
		else if (j < 16)
		{
		    d [j].setIcon (createImageIcon ("bird4a.jpg"));
		}
		ranpos [j] = 0;
	    }
	    for (int i = 0 ; i < d.length ; i++)
	    {
		birdran (ranorg, c); //getting a random number from the birdran method, random number from 1-16

		while (ranpos [ranorg] == 1) //seeing if the random number is already taken and choosing another one in place if it is
		{
		    ranorg = (int) (Math.random () * c);
		}
		grid.add (d [ranorg]); //adding it to the panel/grid
		ranpos [ranorg] = 1; //setting the position to taken so no other card can do the same
	    }
	}

	else if (b.equals ("hard1"))
	{
	    d2 = new JButton [c];
	    ranpos2 = new int [c];
	    grid2 = new Panel (new GridLayout (5, a)); //5 x 6
	    for (int j = 0 ; j < d2.length ; j++)
	    {
		d2 [j] = new JButton (createImageIcon ("checkeredboarda.jpg"));
		d2 [j].setVisible (false);
		d2 [j].setPreferredSize (new Dimension (80, 80));
		d2 [j].addActionListener (this);
		d2 [j].setActionCommand ("b" + j);
		grid2.add (d2 [j]);
		ranpos2 [j] = 0;
		if (j < 6)
		{
		    d2 [j].setIcon (createImageIcon ("bird1a.jpg")); //there are 30 cards so it makes 5 different birds have 6 cards each, 3 pairs
		}
		else if (j < 12)
		{
		    d2 [j].setIcon (createImageIcon ("bird2a.jpg"));
		}
		else if (j < 18)
		{
		    d2 [j].setIcon (createImageIcon ("bird3a.jpg"));
		}
		else if (j < 24)
		{
		    d2 [j].setIcon (createImageIcon ("bird4a.jpg"));
		}
		else if (j < 30)
		{
		    d2 [j].setIcon (createImageIcon ("bird5a.jpg"));
		}
		ranpos2 [j] = 0;
	    }
	    for (int i = 0 ; i < d2.length ; i++)
	    {
		birdran (ranorg2, c);

		while (ranpos2 [ranorg2] == 1)
		{
		    ranorg2 = (int) (Math.random () * c);
		}
		grid2.add (d2 [ranorg2]);
		ranpos2 [ranorg2] = 1;
	    }
	}

	else if (b.equals ("easy2"))
	{
	    d3 = new JButton [c];
	    ranpos3 = new int [c];
	    grid3 = new Panel (new GridLayout (a, 8)); //8 x 6
	    for (int j = 0 ; j < d3.length ; j++)
	    {
		d3 [j] = new JButton (createImageIcon ("checkeredboarda.jpg"));
		d3 [j].setVisible (false);
		d3 [j].setPreferredSize (new Dimension (80, 80));
		d3 [j].addActionListener (this);
		d3 [j].setActionCommand ("c" + j);
		grid3.add (d3 [j]);
		ranpos3 [j] = 0;
		if (j < 12)
		{
		    d3 [j].setIcon (createImageIcon ("bird1a.jpg")); //48 different spaces so giving 4 different birds have 12 cards each
		}
		else if (j < 24)
		{
		    d3 [j].setIcon (createImageIcon ("bird2a.jpg"));
		}
		else if (j < 36)
		{
		    d3 [j].setIcon (createImageIcon ("bird3a.jpg"));
		}
		else if (j < 48)
		{
		    d3 [j].setIcon (createImageIcon ("bird4a.jpg"));
		}
		ranpos3 [j] = 0;
	    }
	    for (int i = 0 ; i < d3.length ; i++)
	    {
		birdran (ranorg3, c);

		while (ranpos3 [ranorg3] == 1) //randomly organizing
		{
		    ranorg3 = (int) (Math.random () * c);
		}
		grid3.add (d3 [ranorg3]);
		ranpos3 [ranorg3] = 1;
	    }
	}

	else if (b.equals ("hard2"))
	{
	    d4 = new JButton [c];
	    ranpos4 = new int [c];
	    grid4 = new Panel (new GridLayout (a, 8)); //8 x 6, same as easy
	    for (int j = 0 ; j < d4.length ; j++)
	    {
		d4 [j] = new JButton (createImageIcon ("checkeredboarda.jpg"));
		d4 [j].setVisible (false);
		d4 [j].setPreferredSize (new Dimension (80, 80));
		d4 [j].addActionListener (this);
		d4 [j].setActionCommand ("d" + j);
		grid4.add (d4 [j]);
		ranpos4 [j] = 0;
		if (j < 10)
		{
		    d4 [j].setIcon (createImageIcon ("bird1a.jpg")); //48 different spaces so giving 4 birds have 10 spaces and the 5th brid have
		} //8 spaces, still using 5 birds to make it harder to sort when playing
		else if (j < 20)
		{
		    d4 [j].setIcon (createImageIcon ("bird2a.jpg"));
		}
		else if (j < 30)
		{
		    d4 [j].setIcon (createImageIcon ("bird3a.jpg"));
		}
		else if (j < 40)
		{
		    d4 [j].setIcon (createImageIcon ("bird4a.jpg"));
		}
		else if (j < 48)
		{
		    d4 [j].setIcon (createImageIcon ("bird5a.jpg"));
		}
		ranpos4 [j] = 0;
	    }
	    for (int i = 0 ; i < d4.length ; i++)
	    {
		birdran (ranorg4, c);

		while (ranpos4 [ranorg4] == 1)
		{
		    ranorg4 = (int) (Math.random () * c);
		}
		grid4.add (d4 [ranorg4]);
		ranpos4 [ranorg4] = 1;
	    }
	}
    }


    public void actionPerformed (ActionEvent e)
    {
	if (e.getActionCommand ().equals ("mainmenu")) //mainmenutakes user to the menu
	{
	    cdLayout.show (p_card, "menu");
	}
	else if (e.getActionCommand ().equals ("about"))
	{
	    cdLayout.show (p_card, "aboutpage");
	}
	else if (e.getActionCommand ().equals ("next")) //array for circling through about page
	{
	    if (nextnum == 0) //nextnum is a counter for the about page and subtitutes the values of the aboutpage arrays to a JLabel that can easily change
	    {
		nextnum++;
		aboutpic.setIcon (createImageIcon (aboutimages [0])); //aboutimages[0] holds the file image name so createImageiCon is still needed
		abouttext.setText (aboutsentences [0]); //sets text and icons for the about page
	    }
	    else if (nextnum == 1)
	    {
		nextnum++;
		aboutpic.setIcon (createImageIcon (aboutimages [1]));
		abouttext.setText (aboutsentences [1]);
	    }
	    else if (nextnum == 2)
	    {
		nextnum++;
		aboutpic.setIcon (createImageIcon (aboutimages [2]));
		abouttext.setText (aboutsentences [2]);
	    }
	    else if (nextnum == 3)
	    {
		nextnum++;
		aboutpic.setIcon (createImageIcon (aboutimages [3]));
		abouttext.setText (aboutsentences [3]);
	    }
	    else if (nextnum == 4)
	    {
		nextnum++;
		aboutpic.setIcon (createImageIcon (aboutimages [4]));
		abouttext.setText (aboutsentences [4]);
	    }
	    else if (nextnum == 5)
	    {
		nextnum = 0;
		aboutpic.setIcon (createImageIcon (aboutimages [5]));
		abouttext.setText (aboutsentences [5]);
	    }
	}
	else if (e.getActionCommand ().equals ("play")) //clicking to go on a specific level
	{
	    cdLayout.show (p_card, "gamepage");
	}
	else if (e.getActionCommand ().equals ("game1"))
	{
	    cdLayout.show (p_card, "game1page");
	}

	else if (e.getActionCommand ().equals ("easy1"))
	{
	    for (int i = 0 ; i < d.length ; i++)
	    {
		d [i].setVisible (true);
		d [i].setEnabled (true);
		d [i].setIcon (createImageIcon ("checkeredboarda.jpg"));
	    }
	    check = 0;
	    vischeckeasy1 = 0; //these values are default from the beginning but i set them to default again incase the user clicks reset, so it resets all of the
	    scoreeasy1 = 0;   //values that change in the game. the reset button for every level has the same action command as the actual level
	    pscoreeasy1.setText ("Score: " + scoreeasy1);

	    easygame1progressamt = 0;
	    easygame1progress.setValue (easygame1progressamt);
	    easygame1progress.setString ("Level is " + easygame1progressamt + "% Completed");

	    cdLayout.show (p_card, "easygame1page"); //shows game screen
	}
	for (int i = 0 ; i < d.length ; i++)
	{
	    if (e.getActionCommand ().equals ("a" + i))  //Checks the ActionCommand of the level. easy1 is a, hard1 is b, easy2 is c, hard2, is d
	    {
		if (check == 0) //Using a counter to allow more than one actioncommand to be assessed at the same time
		{
		    ac1 = e.getActionCommand (); //Storing the first ActionCommand
		    check++;

		    acnum1 = ""; //Defining a String for numbers of the first action command

		    for (int x = 0 ; x < ac1.length () ; x++)
		    {
			char z = ac1.charAt (x); //Collecting the individual characters from the first ActionCommand

			if (Character.isDigit (z)) //Checking if the character is a digit/number
			{
			    acnum1 = acnum1 + z; //Adding the character as a digit
			}
		    }

		    num1 = Integer.parseInt (acnum1); //Parsing the acnum String, into an integer
		    if (num1 < 4) //using the grid and amount of cards per bird from the birdorganization method
		    {
			imnum1 = 1; //image number, my images are bird1a, bird2a......etc and i originally decalred those birds to have and action command of that num
		    }
		    else if (num1 < 8)
		    {
			imnum1 = 2;
		    }
		    else if (num1 < 12)
		    {
			imnum1 = 3;
		    }
		    else if (num1 < 16)
		    {
			imnum1 = 4;
		    }

		    d [num1].setIcon (createImageIcon ("bird" + imnum1 + "a.jpg")); //setting the image from the action command of the card/button clicked
		}

		else if (check == 1) //check counter for second action command
		{
		    ac2 = e.getActionCommand (); // second action command/s second card clicked being assessed
		    check++;

		    acnum2 = ""; //same thing as before but storing as num2 instead of num1

		    for (int x = 0 ; x < ac2.length () ; x++)
		    {
			char z = ac2.charAt (x);

			if (Character.isDigit (z))
			{
			    acnum2 = acnum2 + z;
			}
		    }

		    num2 = Integer.parseInt (acnum2);
		    if (num2 < 4)
		    {
			imnum2 = 1;
		    }
		    else if (num2 < 8)
		    {
			imnum2 = 2;
		    }
		    else if (num2 < 12)
		    {
			imnum2 = 3;
		    }
		    else if (num2 < 16)
		    {
			imnum2 = 4;
		    }

		    d [num2].setIcon (createImageIcon ("bird" + imnum2 + "a.jpg"));
		}
	    }

	    else if (check == 2 && (num1 == num2)) //checking if the same card was clicked twice based on the amount of action commands it can take, which is 2
	    {
		ac1 = ""; //Setting the stored ActionCommands to be blank
		ac2 = "";

		for (int x = 0 ; x < d.length ; x++)
		{
		    d [x].setEnabled (true); // Setting all of the cards to be enabled again
		}

		d [num1].setIcon (createImageIcon ("checkeredboarda.jpg")); //Setting the card clicked to change to its original icon: the checker board

		check = 0; //Setting the check counter to 0 to check over the cards again, if they are clicked
	    }

	    else if (check == 2 && (num1 != num2)) //checking if two different cards were pressed based on the action commands taken
	    {
		for (int x = 0 ; x < d.length ; x++)
		{
		    if ((x == num1) || (x == num2)) //Keeping the clicked cards visible
		    {
			d [x].setVisible (true);
		    }

		    else
		    {
			d [x].setEnabled (false); //Setting all of the cards that were not clicked to be disabled, singling out the pressed cards
		    }
		}

		if (e.getActionCommand ().equals ("easy1check")) //Checking if the user wants to see if the cards match, and they would have to press the check button
		{
		    check = 0; //Setting the counter back to 0, so that the user can press some more cards after this is done

		    if (imnum1 == imnum2) //Checking if the cards hold this same image using imagenumber
		    {
			d [num1].setVisible (false); //Setting the first matching card to disappear
			d [num2].setVisible (false); //Setting the second matching card to disappear
			scoreeasy1 += 4; //Adding 4 points to the score for the easy level, for one pair

			pscoreeasy1.setText ("Score: " + scoreeasy1); //Updating the score for the level

			vischeckeasy1 += 2; //adding 2 to the visibility counter, for each card set to invisible, it will be later used to declare if all of the cards
			if (vischeckeasy1 < 15) //were matched since they would be invisible if they did
			{
			    easygame1progressamt += (100 / 8);
			    easygame1progress.setValue (easygame1progressamt);
			    easygame1progress.setString ("Level is " + easygame1progressamt + "% Completed");
			}
			else //running code to add 6% to the progress bar before the last match and 100%after the last match, adding by 6 to get to 100 is not even
			{ //and jprogressbars dont update by double
			    easygame1progressamt = 100;
			    easygame1progress.setValue (easygame1progressamt);
			    easygame1progress.setString ("Level is " + easygame1progressamt + "% Completed");
			}
		    }

		    else //Checking if the two cards don't match, hence else
		    {
			for (int x = 0 ; x < d.length ; x++)
			{
			    if (x == num1)
			    {
				d [x].setIcon (createImageIcon ("checkeredboarda.jpg")); //Flipping the first card back over
				d [num2].setIcon (createImageIcon ("checkeredboarda.jpg")); //Flipping the second card back over
			    }
			}
			scoreeasy1 -= 1; //decreasing the score by 1 if the user makes a wrong match
			pscoreeasy1.setText ("Score: " + scoreeasy1);
		    }

		    for (int x = 0 ; x < d.length ; x++)
		    {
			d [x].setEnabled (true); //Re- enabling all the of cards to be clicked and used again
		    }
		}
	    }
	}
	if (e.getActionCommand ().equals ("easy1check") && vischeckeasy1 == 16) //there are 16 cards so checking is 16 cards were set to invisible and if the user
	{ //pressed check for the final one
	    cdLayout.show (p_card, "menu"); //going to the menu
	    easy1progresscount++; //adding a counter for jprogressbar in the menu, if it = 1, adding the progress by 25% shown really below
	    if (easy1progresscount >= 1 && hard1progresscount >= 1 && easy2progresscount >= 1 && hard2progresscount >= 1) //if the user finishes the level even once,
	    { //the progresscounter of the level increase by 1 everytime, making it >= 1. If the user completes all of the levels, the ending button will be set
		end.setVisible (true);                         //to visible, and the user can go to the ending. I do this for every level
	    }
	    if (highscoreeasy1 < scoreeasy1) //Checking whether or not, the high score is less than the current score for the level
	    {
		highscoreeasy1 = scoreeasy1; //If the high score is less than the current score, the latest high score becomes the current score
		phighscoreeasy1.setText ("High Score: " + highscoreeasy1); //Updating the high score for the level
		JOptionPane.showMessageDialog (null, "You finished the level with a score of " + scoreeasy1 + ". New High Score! Great work!",
			"You Win", JOptionPane.INFORMATION_MESSAGE); //showing a message if they beat their previous score
	    }
	    else
	    {
		JOptionPane.showMessageDialog (null, "You finished the level with a score of " + scoreeasy1 + "! Great work!",
			"You Win", JOptionPane.INFORMATION_MESSAGE); //shoeing a message if they just beat the level
	    }
	    vischeckeasy1 = 0; //setting all of the counters and scores to their default
	    scoreeasy1 = 0;
	    pscoreeasy1.setText ("Score: " + scoreeasy1);
	    highscores [1].setText ("Full Free, Easy: " + highscoreeasy1); //updating highscore in the game screens
	    highscores2 [1].setText ("Full Free, Easy: " + highscoreeasy1);

	    easygame1progressamt = 0;
	    easygame1progress.setValue (easygame1progressamt);
	    easygame1progress.setString ("Level is " + easygame1progressamt + "% Completed");

	    if (easy1progresscount == 1) //adding to the jprogressbar if level was complete
	    {
		gameprogressamt += 25;
		gameprogress.setValue (gameprogressamt);
		gameprogress.setString ("Game is " + gameprogressamt + "% completed");
	    }
	}


	else if (e.getActionCommand ().equals ("hard1"))
	{
	    for (int i = 0 ; i < d2.length ; i++)
	    {
		d2 [i].setVisible (true);
		d2 [i].setEnabled (true);
		d2 [i].setIcon (createImageIcon ("checkeredboarda.jpg"));
	    }
	    scorehard1 = 0; //updating and putting widgets and values to default
	    check = 0;
	    vischeckhard1 = 0;
	    pscorehard1.setText ("Score: " + scorehard1);

	    hardgame1progressamt = 0;
	    hardgame1progress.setValue (hardgame1progressamt);
	    hardgame1progress.setString ("Level is " + hardgame1progressamt + "% Completed");

	    cdLayout.show (p_card, "hardgame1page");
	}
	for (int i = 0 ; i < d2.length ; i++)
	{
	    if (e.getActionCommand ().equals ("b" + i))
	    {
		if (check == 0)
		{
		    ac1 = e.getActionCommand ();
		    check++;

		    acnum1 = "";

		    for (int x = 0 ; x < ac1.length () ; x++)
		    {
			char z = ac1.charAt (x);

			if (Character.isDigit (z))
			{
			    acnum1 = acnum1 + z;
			}
		    }

		    num1 = Integer.parseInt (acnum1);
		    if (num1 < 6)
		    {
			imnum1 = 1;
		    }
		    else if (num1 < 12)
		    {
			imnum1 = 2;
		    }
		    else if (num1 < 18)
		    {
			imnum1 = 3;
		    }
		    else if (num1 < 24)
		    {
			imnum1 = 4;
		    }
		    else if (num1 < 30)
		    {
			imnum1 = 5;
		    }

		    d2 [num1].setIcon (createImageIcon ("bird" + imnum1 + "a.jpg"));
		}

		else if (check == 1)
		{
		    ac2 = e.getActionCommand ();
		    check++;

		    acnum2 = "";

		    for (int x = 0 ; x < ac2.length () ; x++)
		    {
			char z = ac2.charAt (x);

			if (Character.isDigit (z))
			{
			    acnum2 = acnum2 + z;
			}
		    }

		    num2 = Integer.parseInt (acnum2);
		    if (num2 < 6)
		    {
			imnum2 = 1;
		    }
		    else if (num2 < 12)
		    {
			imnum2 = 2;
		    }
		    else if (num2 < 18)
		    {
			imnum2 = 3;
		    }
		    else if (num2 < 24)
		    {
			imnum2 = 4;
		    }
		    else if (num2 < 30)
		    {
			imnum2 = 5;
		    }

		    d2 [num2].setIcon (createImageIcon ("bird" + imnum2 + "a.jpg"));
		}
	    }

	    else if (check == 2 && (num1 == num2))
	    {
		ac1 = "";
		ac2 = "";

		for (int x = 0 ; x < d2.length ; x++)
		{
		    d2 [x].setEnabled (true);
		}

		d2 [num1].setIcon (createImageIcon ("checkeredboarda.jpg"));

		check = 0;
	    }

	    else if (check == 2 && (num1 != num2))
	    {
		for (int x = 0 ; x < d2.length ; x++)
		{
		    if ((x == num1) || (x == num2))
		    {
			d2 [x].setVisible (true);
		    }

		    else
		    {
			d2 [x].setEnabled (false);
		    }
		}

		if (e.getActionCommand ().equals ("hard1check"))
		{
		    check = 0;

		    if (imnum1 == imnum2)
		    {
			d2 [num1].setVisible (false);
			d2 [num2].setVisible (false);
			scorehard1 += 4;

			pscorehard1.setText ("Score: " + scorehard1);

			vischeckhard1 += 2;
			if (vischeckhard1 == 6 || vischeckhard1 == 12 || vischeckhard1 == 18 || vischeckhard1 == 24 || vischeckhard1 == 30)
			{
			    hardgame1progressamt += 8;
			    hardgame1progress.setValue (hardgame1progressamt);
			    hardgame1progress.setString ("Level is " + hardgame1progressamt + "% Completed");
			}
			else
			{
			    hardgame1progressamt += (100 / 15);
			    hardgame1progress.setValue (hardgame1progressamt);
			    hardgame1progress.setString ("Level is " + hardgame1progressamt + "% Completed");
			}
		    }

		    else
		    {
			for (int x = 0 ; x < d2.length ; x++)
			{
			    if (x == num1)
			    {
				d2 [x].setIcon (createImageIcon ("checkeredboarda.jpg"));
				d2 [num2].setIcon (createImageIcon ("checkeredboarda.jpg"));
			    }
			}
			scorehard1 -= 1;
			pscorehard1.setText ("Score: " + scorehard1);
		    }

		    for (int x = 0 ; x < d2.length ; x++)
		    {
			d2 [x].setEnabled (true);
		    }
		}
	    }
	}
	if (e.getActionCommand ().equals ("hard1check") && vischeckhard1 == 30) //the hard level has 30 buttons so checking if all of them were clicked
	{
	    cdLayout.show (p_card, "menu");//same thing as easy level of game1
	    hard1progresscount++;//adding a counter for jprogressbar in the menu, if it = 1, adding the progress by 25% shown really below
	    if (easy1progresscount >= 1 && hard1progresscount >= 1 && easy2progresscount >= 1 && hard2progresscount >= 1) //if the user finishes the level even once,
	    { //the progresscounter of the level increase by 1 everytime, making it >= 1. If the user completes all of the levels, the ending button will be set
		end.setVisible (true);                         //to visible, and the user can go to the ending. I do this for every level
	    }
	    if (highscorehard1 < scorehard1)
	    {
		highscorehard1 = scorehard1;
		phighscorehard1.setText ("High Score: " + highscorehard1);
		JOptionPane.showMessageDialog (null, "You finished the level with a score of " + scorehard1 + ". New High Score! Great work!",
			"You Win", JOptionPane.INFORMATION_MESSAGE);
	    }
	    else
	    {
		JOptionPane.showMessageDialog (null, "You finished the level with a score of " + scorehard1 + "! Great work!",
			"You Win", JOptionPane.INFORMATION_MESSAGE);
	    }
	    vischeckhard1 = 0;
	    scorehard1 = 0;
	    pscorehard1.setText ("Score: " + scorehard1);
	    highscores [2].setText ("Full Free, Hard: " + highscorehard1);
	    highscores2 [2].setText ("Full Free, Hard: " + highscorehard1);

	    hardgame1progressamt = 0;
	    hardgame1progress.setValue (hardgame1progressamt);
	    hardgame1progress.setString ("Level is " + hardgame1progressamt + "% Completed");

	    if (hard1progresscount == 1)
	    {
		gameprogressamt += 25;
		gameprogress.setValue (gameprogressamt);
		gameprogress.setString ("Game is " + gameprogressamt + "% completed");
	    }
	}

	else if (e.getActionCommand ().equals ("game2"))
	{
	    cdLayout.show (p_card, "game2page");
	}

	else if (e.getActionCommand ().equals ("easy2"))
	{
	    for (int i = 0 ; i < d3.length ; i++)
	    {
		d3 [i].setVisible (true);
		d3 [i].setEnabled (true);
		d3 [i].setIcon (createImageIcon ("checkeredboarda.jpg"));
	    }
	    scoreeasy2 = 0;
	    pscoreeasy2.setText ("Score: " + scoreeasy2);

	    easymovesused = 0;
	    easymovesleft = 24; //setting moves used and moves left

	    peasymovesused.setText ("Moves used: " + easymovesused);
	    peasymovesleft.setText ("Moves left: " + easymovesleft);

	    check = 0;
	    cdLayout.show (p_card, "easygame2page");
	}
	for (int i = 0 ; i < d3.length ; i++)
	{
	    if (e.getActionCommand ().equals ("c" + i))
	    {
		if (check == 0)
		{
		    ac1 = e.getActionCommand ();
		    check++;

		    acnum1 = "";

		    for (int x = 0 ; x < ac1.length () ; x++)
		    {
			char z = ac1.charAt (x);

			if (Character.isDigit (z))
			{
			    acnum1 = acnum1 + z;
			}
		    }

		    num1 = Integer.parseInt (acnum1); //this level id 8 x 6 and is 48 cards
		    if (num1 < 12)
		    {
			imnum1 = 1;
		    }
		    else if (num1 < 24)
		    {
			imnum1 = 2;
		    }
		    else if (num1 < 36)
		    {
			imnum1 = 3;
		    }
		    else if (num1 < 48)
		    {
			imnum1 = 4;
		    }

		    d3 [num1].setIcon (createImageIcon ("bird" + imnum1 + "a.jpg"));
		}

		else if (check == 1)
		{
		    ac2 = e.getActionCommand ();
		    check++;

		    acnum2 = "";

		    for (int x = 0 ; x < ac2.length () ; x++)
		    {
			char z = ac2.charAt (x);

			if (Character.isDigit (z))
			{
			    acnum2 = acnum2 + z;
			}
		    }

		    num2 = Integer.parseInt (acnum2);
		    if (num2 < 12)
		    {
			imnum2 = 1;
		    }
		    else if (num2 < 24)
		    {
			imnum2 = 2;
		    }
		    else if (num2 < 36)
		    {
			imnum2 = 3;
		    }
		    else if (num2 < 48)
		    {
			imnum2 = 4;
		    }

		    d3 [num2].setIcon (createImageIcon ("bird" + imnum2 + "a.jpg"));
		}
	    }

	    else if (check == 2 && (num1 == num2))
	    {
		ac1 = "";
		ac2 = "";

		for (int x = 0 ; x < d3.length ; x++)
		{
		    d3 [x].setEnabled (true);
		}

		d3 [num1].setIcon (createImageIcon ("checkeredboarda.jpg"));

		check = 0;
	    }

	    else if (check == 2 && (num1 != num2))
	    {
		for (int x = 0 ; x < d3.length ; x++)
		{
		    if ((x == num1) || (x == num2))
		    {
			d3 [x].setVisible (true);
		    }

		    else
		    {
			d3 [x].setEnabled (false);
		    }
		}

		if (e.getActionCommand ().equals ("easy2check"))
		{
		    check = 0;

		    if (imnum1 == imnum2)
		    {
			d3 [num1].setVisible (false);
			d3 [num2].setVisible (false);
			scoreeasy2 += 4;

			pscoreeasy2.setText ("Score: " + scoreeasy2);
		    }

		    else
		    {
			for (int x = 0 ; x < d3.length ; x++)
			{
			    if (x == num1)
			    {
				d3 [x].setIcon (createImageIcon ("checkeredboarda.jpg"));
				d3 [num2].setIcon (createImageIcon ("checkeredboarda.jpg"));
			    }
			}
		    }

		    easymovesused += 1; //regardless of if they got a match or not, increasing moves used and decreasing moves left
		    easymovesleft -= 1; //also unlike th other game, this game is on moves so if the user does not get a match, they dont lose points

		    peasymovesused.setText ("Moves used: " + easymovesused); //updating the JLabels for the moves used and moves left, same for hard level of game2
		    peasymovesleft.setText ("Moves left: " + easymovesleft);

		    for (int x = 0 ; x < d3.length ; x++)
		    {
			d3 [x].setEnabled (true);
		    }
		}
	    }
	}
	if (e.getActionCommand ().equals ("easy2check") && easymovesleft == 0) //determining if the user finished the level by looking at moves left
	{ //which will eventually be 0
	    cdLayout.show (p_card, "menu");//same as other levels
	    easy2progresscount++; //adding a counter for jprogressbar in the menu, if it = 1, adding the progress by 25% shown really below
	    if (easy1progresscount >= 1 && hard1progresscount >= 1 && easy2progresscount >= 1 && hard2progresscount >= 1) //if the user finishes the level even once,
	    { //the progresscounter of the level increase by 1 everytime, making it >= 1. If the user completes all of the levels, the ending button will be set
		end.setVisible (true);                         //to visible, and the user can go to the ending. I do this for every level
	    }
	    if (highscoreeasy2 < scoreeasy2)
	    {
		highscoreeasy2 = scoreeasy2;
		phighscoreeasy2.setText ("High Score: " + highscoreeasy2);
		JOptionPane.showMessageDialog (null, "You finished the level with a score of " + scoreeasy2 + " using all of your moves. New High Score! Great work!",
			"You Win", JOptionPane.INFORMATION_MESSAGE);
	    }
	    else
	    {
		JOptionPane.showMessageDialog (null, "You finished the level with a score of " + scoreeasy2 + " using all of your moves! Great work!",
			"You Win", JOptionPane.INFORMATION_MESSAGE);
	    }
	    scoreeasy2 = 0;
	    pscoreeasy2.setText ("Score: " + scoreeasy2);

	    easymovesused = 0; //setting moves used and moves left and their jlabels to their default value
	    easymovesleft = 24;

	    peasymovesused.setText ("Moves used: " + easymovesused);
	    peasymovesleft.setText ("Moves left: " + easymovesleft);

	    highscores [3].setText ("Smart Free, Easy: " + highscoreeasy2);
	    highscores3 [1].setText ("Smart Free, Easy: " + highscoreeasy2);
	    if (easy2progresscount == 1)
	    {
		gameprogressamt += 25;
		gameprogress.setValue (gameprogressamt);
		gameprogress.setString ("Game is " + gameprogressamt + "% completed");
	    }
	}

	else if (e.getActionCommand ().equals ("hard2"))
	{
	    for (int i = 0 ; i < d4.length ; i++)
	    {
		d4 [i].setVisible (true);
		d4 [i].setEnabled (true);
		d4 [i].setIcon (createImageIcon ("checkeredboarda.jpg"));
	    }
	    scorehard2 = 0; //same thing as other methods
	    pscorehard2.setText ("Score: " + scorehard2);

	    hardmovesused = 0;
	    hardmovesleft = 20;

	    phardmovesused.setText ("Moves used: " + hardmovesused);
	    phardmovesleft.setText ("Moves left: " + hardmovesleft);

	    check = 0;
	    cdLayout.show (p_card, "hardgame2page");
	}
	for (int i = 0 ; i < d4.length ; i++)
	{
	    if (e.getActionCommand ().equals ("d" + i))
	    {
		if (check == 0)
		{
		    ac1 = e.getActionCommand ();
		    check++;

		    acnum1 = "";

		    for (int x = 0 ; x < ac1.length () ; x++)
		    {
			char z = ac1.charAt (x);

			if (Character.isDigit (z))
			{
			    acnum1 = acnum1 + z;
			}
		    }

		    num1 = Integer.parseInt (acnum1);
		    if (num1 < 10)
		    {
			imnum1 = 1;
		    }
		    else if (num1 < 20)
		    {
			imnum1 = 2;
		    }
		    else if (num1 < 30)
		    {
			imnum1 = 3;
		    }
		    else if (num1 < 40)
		    {
			imnum1 = 4;
		    }
		    else if (num1 < 48)
		    {
			imnum1 = 5;
		    }

		    d4 [num1].setIcon (createImageIcon ("bird" + imnum1 + "a.jpg"));
		}

		else if (check == 1)
		{
		    ac2 = e.getActionCommand ();
		    check++;

		    acnum2 = "";

		    for (int x = 0 ; x < ac2.length () ; x++)
		    {
			char z = ac2.charAt (x);

			if (Character.isDigit (z))
			{
			    acnum2 = acnum2 + z;
			}
		    }

		    num2 = Integer.parseInt (acnum2);
		    if (num2 < 10)
		    {
			imnum2 = 1;
		    }
		    else if (num2 < 20)
		    {
			imnum2 = 2;
		    }
		    else if (num2 < 30)
		    {
			imnum2 = 3;
		    }
		    else if (num2 < 40)
		    {
			imnum2 = 4;
		    }
		    else if (num2 < 48)
		    {
			imnum2 = 5;
		    }

		    d4 [num2].setIcon (createImageIcon ("bird" + imnum2 + "a.jpg"));
		}
	    }

	    else if (check == 2 && (num1 == num2))
	    {
		ac1 = "";
		ac2 = "";

		for (int x = 0 ; x < d4.length ; x++)
		{
		    d4 [x].setEnabled (true);
		}

		d4 [num1].setIcon (createImageIcon ("checkeredboarda.jpg"));

		check = 0;
	    }

	    else if (check == 2 && (num1 != num2))
	    {
		for (int x = 0 ; x < d4.length ; x++)
		{
		    if ((x == num1) || (x == num2))
		    {
			d4 [x].setVisible (true);
		    }

		    else
		    {
			d4 [x].setEnabled (false);
		    }
		}

		if (e.getActionCommand ().equals ("hard2check"))
		{
		    check = 0;

		    if (imnum1 == imnum2)
		    {
			d4 [num1].setVisible (false);
			d4 [num2].setVisible (false);
			scorehard2 += 4;

			pscorehard2.setText ("Score: " + scorehard2);
		    }

		    else
		    {
			for (int x = 0 ; x < d4.length ; x++)
			{
			    if (x == num1)
			    {
				d4 [x].setIcon (createImageIcon ("checkeredboarda.jpg"));
				d4 [num2].setIcon (createImageIcon ("checkeredboarda.jpg"));
			    }
			}
		    }

		    hardmovesused += 1;
		    hardmovesleft -= 1;

		    phardmovesused.setText ("Moves used: " + hardmovesused);
		    phardmovesleft.setText ("Moves left: " + hardmovesleft);

		    for (int x = 0 ; x < d4.length ; x++)
		    {
			d4 [x].setEnabled (true);
		    }
		}
	    }
	}
	if (e.getActionCommand ().equals ("hard2check") && hardmovesleft == 0)
	{
	    cdLayout.show (p_card, "menu");//same as other level
	    hard2progresscount++; //adding a counter for jprogressbar in the menu, if it = 1, adding the progress by 25% shown really below
	    if (easy1progresscount >= 1 && hard1progresscount >= 1 && easy2progresscount >= 1 && hard2progresscount >= 1) //if the user finishes the level even once,
	    { //the progresscounter of the level increase by 1 everytime, making it >= 1. If the user completes all of the levels, the ending button will be set
		end.setVisible (true);                         //to visible, and the user can go to the ending. I do this for every level
	    }
	    if (highscorehard2 < scorehard2)
	    {
		highscorehard2 = scorehard2;
		phighscorehard2.setText ("High Score: " + highscorehard2);
		JOptionPane.showMessageDialog (null, "You finished the level with a score of " + scorehard2 + " using all of your moves. New High Score! Great work!",
			"You Win", JOptionPane.INFORMATION_MESSAGE);
	    }
	    else
	    {
		JOptionPane.showMessageDialog (null, "You finished the level with a score of " + scorehard2 + " using all of your moves! Great work!",
			"You Win", JOptionPane.INFORMATION_MESSAGE);
	    }
	    scorehard2 = 0;
	    pscorehard2.setText ("Score: " + scorehard2);

	    hardmovesused = 0;
	    hardmovesleft = 20;

	    phardmovesused.setText ("Moves used: " + hardmovesused);
	    phardmovesleft.setText ("Moves left: " + hardmovesleft);

	    highscores [4].setText ("Smart Free, Hard: " + highscorehard2);
	    highscores3 [2].setText ("Smart Free, Hard: " + highscorehard2);
	    if (hard2progresscount == 1)
	    {
		gameprogressamt += 25;
		gameprogress.setValue (gameprogressamt);
		gameprogress.setString ("Game is " + gameprogressamt + "% completed");
	    }
	}


	else if (e.getActionCommand ().equals ("end")) //if the ending button is enabled and the user clicks it, shows the ending screen
	{
	    cdLayout.show (p_card, "endpage");
	}


	else if (e.getActionCommand ().equals ("replay")) //if the user clicks continue, they go to the main menu and nothing is reset, however, if the user
	{ //clicks replay on the ending screen, the values in the game resets
	    cdLayout.show (p_card, "menu"); //still lead back to the main menu, since thats the start of the game

	    int gameprogressamt = 0; //resetting the counters for the jprogressbar in the main menu
	    easy1progresscount = 0;
	    hard1progresscount = 0;
	    easy2progresscount = 0;
	    hard2progresscount = 0;

	    gameprogress.setValue (gameprogressamt); //resetting the value and string of the progress bar itself
	    gameprogress.setString ("Game is " + gameprogressamt + "% completed");

	    highscoreeasy1 = 0;
	    phighscoreeasy1.setText ("High Score: " + highscoreeasy1); //resetting highscores inside all of the levels to 0
	    highscorehard1 = 0;
	    phighscorehard1.setText ("High Score: " + highscorehard1);
	    highscoreeasy2 = 0;
	    phighscoreeasy2.setText ("High Score: " + highscoreeasy2);
	    highscorehard2 = 0;
	    phighscorehard2.setText ("High Score: " + highscorehard2);

	    highscores [1].setText ("Full Free, Easy: " + highscoreeasy1); // resetting the high score listings in the game pages back to 0
	    highscores [2].setText ("Full Free, Hard: " + highscorehard1);
	    highscores [3].setText ("Smart Free, Easy: " + highscoreeasy2);
	    highscores [4].setText ("Smart Free, Hard: " + highscorehard2);

	    highscores2 [1].setText ("Full Free, Easy: " + highscoreeasy1);
	    highscores2 [2].setText ("Full Free, Hard: " + highscorehard1);

	    highscores3 [1].setText ("Smart Free, Easy: " + highscoreeasy2); //not resetting things like other counters or scores because they are already reset
	    highscores3 [2].setText ("Smart Free, Hard: " + highscorehard2); //in the beginning and/or ending of each level itself
	}
    }


    public static int birdran (int num, int maxnum)  //method to select a random number from a given maximum number, 1-maxnum
    {
	num = (int) (Math.random () * maxnum);
	return num;
    }


    protected static ImageIcon createImageIcon (String path)  //method for materializing the use of pistures
    {
	java.net.URL imgURL = birdflip3.class.getResource (path);
	if (imgURL != null)
	    return new ImageIcon (imgURL);
	else
	{
	    System.err.println ("Couldn't find file: " + path);
	    return null;
	}
    }


    public void playaudio (String audio)  //method for playing and processing music/audio
    {
	music = getAudioClip (getDocumentBase (), audio); //getting the audio
	music.loop (); //looping the audio
    }
}
