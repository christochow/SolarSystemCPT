// The "CPTtest" class.
/*This is the "Solar System" program (main method)
Programmed by Chow Lok Man (Christopher), finished on May 30, 2016.
This program will simulate our solar system along with some of the orbits of certain planets (The Moon of Earth etc.).*/
import java.awt.*;
public class CPTtest
{
    public static void main (String[] args) throws InterruptedException
    {
	RunnableDemo Mercury = new RunnableDemo ("Mercury", 45, 60, 700, 435, 0, Color.blue, 1, 100, 250, 248, 242);
	RunnableDemo Venus = new RunnableDemo ("Venus", 35, 75, 700, 435, 0, Color.blue, 2, 130, 255, 255, 242);
	RunnableDemo Earth = new RunnableDemo ("Earth", 28, 100, 700, 435, 1, Color.blue, 4, 180, 11, 92, 227);
	RunnableDemo Mars = new RunnableDemo ("Mars", 32, 135, 700, 435, 2, Color.red, 8, 230, 240, 198, 29);
	RunnableDemo Jupiter = new RunnableDemo ("Jupiter", 17, 190, 700, 435, 0, Color.green, 24, 280, 253, 199, 145);
	RunnableDemo Saturn = new RunnableDemo ("Saturn", 26, 260, 700, 435, 5, Color.green, 60, 330, 224, 196, 34);
	RunnableDemo Uranus = new RunnableDemo ("Uranus", 43, 315, 700, 435, 4, Color.red, 100, 380, 220, 241, 245);
	RunnableDemo Neptune = new RunnableDemo ("Neptune", 40, 370, 700, 435, 2, Color.blue, 140, 430, 57, 182, 247);
	//Creating the threads for the planets        
	Mercury.start ();
	Venus.start ();
	Earth.start ();
	Mars.start ();
	Jupiter.start ();
	Saturn.start ();
	Uranus.start ();
	Neptune.start ();
	//starting the threads
    } //main method
}
// CPTtest class

