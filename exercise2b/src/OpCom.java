// Code skeleton for the OpCom class in the Buttons exercise

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OpCom extends Thread {
	private Regul regul;
	
	// Constructor
	public OpCom(Regul regul, int priority);
	
	// run method
	public void run();
}

