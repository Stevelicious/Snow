package com.stevenhu;

import java.util.Random;

/**
 * Created by Steven Hu on 2016-08-19.
 */
public class Flake {
	public int x;
	public int y;
	
	public Flake(){
		Random rand = new Random();
		x = rand.nextInt(100);
		y = 0;
	}
	
}
