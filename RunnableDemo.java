// The "RunnableDemo" class.
/*This is the "Solar System" program (parent class)
Programmed by Chow Lok Man (Christopher), finished on May 30, 2016.
This program will simulate our solar system along with some of the orbits of certain planets (The Moon of Earth etc.).*/
import java.awt.*;
import javax.swing.*;
import hsa.Console;
class RunnableDemo implements Runnable
{
    private Thread t;
    private String threadName;
    private static Console c = new Console (33, 200);
    private Color circleColor, sunColor;
    private int numberOfCircles = 0, xOrbit = 0, yOrbit = 0, r = 0, num = 0, orbitRadius = 0, mx = 0, my = 0, p = 0, leg = 0, R = 0, g = 0, b = 0;
    //variable declaration
    private int[] [] stars = new int [300] [2]; //an array to store the coordinates of the stars in the background
    public void run ()
    {
	background ();
	//creating the background
	do
	{
	    Orbit (numberOfCircles, orbitRadius, xOrbit, yOrbit);
	}
	while (true);
	//creating an infinite loop so that the planets will forever orbit the sun
    }


    public RunnableDemo (String name, int size, int radius, int x, int y, int n, Color clr, int per, int leg, int r, int g, int b)
    {
	this.threadName = name; //thread name
	this.xOrbit = x; //x coordinate  of the centre of orbit(i.e. the Sun)
	this.yOrbit = y; //y coordinate  of the centre of orbit(i.e. the Sun)
	this.orbitRadius = radius; //radius of orbit
	this.numberOfCircles = size; //size of planet
	this.num = n; //determines the starting position
	this.p = per; //the period(the speed) of the orbit
	this.leg = leg; //the position of planet on the legend
	this.R = r;
	this.g = g;
	this.b = b;
	//RGB values for the color of the planet
	circleColor = new Color (r, g, b); //creating the color for the planet
	//Constructor
    }


    public void background ()
    {
	c.setColor (Color.black);
	c.fillRect (0, 0, 10000, 10000);
	c.setColor (Color.white);
	Font m = new Font ("Arial", Font.BOLD, 40);
	c.setFont (m);
	c.drawString ("The Solar System", 45, 75);
	sunColor = new Color (253, 184, 19);
	c.setColor (sunColor);
	c.fillOval (xOrbit - 50, yOrbit - 50, 100, 100);
	//drawing the sun
    }


    public void Legend ()
    {
	Font n = new Font ("Arial", Font.BOLD, 20);
	c.setFont (n);
	c.setColor (Color.white);
	c.drawString (threadName, 45, leg);
	c.setColor (circleColor);
	c.fillOval (100, leg, r, r);
    } //drawing the planets on the lengend



    public void drawTracks (int x, int y, int r, Color clr)
    {
	c.setColor (clr);
	c.drawOval (x - r, y - r, r * 2, r * 2);
    } //drawing orbit tracks


    public void start ()
    {
	t = new Thread (this, threadName);
	t.start ();
    }


    public void drawCircle (int x, int y, int radius, Color clr)
    {
	int height = 0;
	height = radius * 2;
	c.setColor (clr);
	c.fillOval (x, y, height, height);
    } //drawing the planets


    public void coverCircle (int x, int y, int radius)
    {
	int height = 0;
	height = radius * 2;
	c.setColor (Color.black);
	c.fillOval (x, y, height, height);
    } //erasing the planets


    public void delay (int time)
    {
	try
	{
	    t.sleep (time);
	}
	catch (InterruptedException e)
	{

	}
    }


    public void rotatePlanet (int numCircles, int orbitR, int xOrbitCentre, int yOrbitCentre, double newRotateAngle)
    {
	double rAngle = 0;
	int[] [] orbits = new int [num] [2];
	int yShift = 0, xShift = 0;
	rAngle = 2 * Math.PI / (numCircles);
	double the = newRotateAngle;
	xShift = (int) Math.round ((orbitR * Math.cos (newRotateAngle)));
	yShift = (int) Math.round ((orbitR * Math.sin (newRotateAngle)));
	//calculating the shift of the centre of the orbit
	r = (int) Math.round (orbitR * Math.sin (rAngle / 2));
	int xFinal = xOrbitCentre + xShift - r; //x coordinate for drawing the planets
	int yFinal = yOrbitCentre + yShift - r; // y coordinate for drawing the planets
	drawTracks (xOrbit, yOrbit, orbitRadius, Color.white);
	drawCircle (xFinal, yFinal, r, circleColor);
	//drawing the shifted circle and it's tracks
	if (num > 0)
	{
	    int rr = 5;
	    //distance between the moons and other orbits
	    for (int a = 0 ; a < num ; a++, rr += 8)
	    {
		moons (xFinal + r, yFinal + r, the * 12 / (a + 1), rr, 45, 3, r);
		//the position of the moon is determined by the angle {the * 12* (num/(a+1))}
		orbits [a] [0] = mx;
		orbits [a] [1] = my;
		if (num == 2)
		{
		    moons (orbits [1] [0] + 3, orbits [1] [1] + 3, -the * 30, 2, 60, 1, 5);
		}
		//calling the method to create moons according to the value num
	    }
	}
	delay (85);
	coverCircle (xFinal, yFinal, r);
	drawTracks (xOrbit, yOrbit, orbitRadius, Color.white);
	if (num > 0)
	{
	    int ra = 5;
	    for (int b = 0 ; b < num ; b++, ra += 8)
	    {
		drawTracks (xFinal + r, yFinal + r, r + ra, Color.black);
		if (num == 2)
		{
		    drawTracks (orbits [1] [0] + 3, orbits [1] [1] + 3, 7, Color.black);
		    coverCircle (mx, my, 1);
		}
		coverCircle (orbits [b] [0], orbits [b] [1], 3);
	    }
	} //covering the planets and the tracks
    }


    public void moons (int x, int y, double thet, int rr, int size, int ra, int rOr)
    {
	double angle = 0;
	angle = 2 * Math.PI / size;
	int xx = (int) Math.round (((rOr + rr) * Math.cos (thet)));
	int yy = (int) Math.round (((rOr + rr) * Math.sin (thet)));
	mx = x + xx - ra; //x coordinate for drawing the moon
	my = y + yy - ra; //y coordinate for drawing the moon
	c.setColor (Color.white);
	drawTracks (x, y, rOr + rr, Color.white);
	drawCircle (mx, my, ra, Color.white);
	//making the moon orbit the planet
    }


    public void Orbit (int numberCircles, int orbitR, int xOrbitCentre, int yOrbitCentre)
    {
	double theta = 0;
	theta = 2 * Math.PI / (numberCircles * 2 * p);
	for (double newRotateAngle = num ; newRotateAngle <= (2 * Math.PI) + num ; newRotateAngle += theta)
	{
	    rotatePlanet (numberCircles, orbitR, xOrbitCentre, yOrbitCentre, -newRotateAngle);
	    //rotating the orbits
	    Legend ();
	    //creating the legend
	}
    }
}
// RunnableDemo class

