package com.stevenhu;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		
		Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
		terminal.enterPrivateMode();
		terminal.setCursorVisible(false);
		List<Flake> flakes = new ArrayList<>();
		int stormLevel = 20;
		while (true) {
			
			updateFlake(flakes);
			for (int i = 0; i < stormLevel; i++) {
				flakes.add(new Flake());
			}
			
			
			drawFlake(terminal, flakes);
//			Uncomment following code so it waits for input
//			Key key;
//			do {
				Thread.sleep(50);
//				key = terminal.readInput();
//			}
//			while (key == null);
			
			
		}
	}
	
	private static void drawFlake(Terminal terminal, List<Flake> flakes) {
		terminal.clearScreen();
		for (Flake flake :
				flakes) {
			terminal.moveCursor(flake.x, flake.y);
			if (flake.stationary){
				terminal.putCharacter('\u2588');
			}else{
				terminal.putCharacter('*');
			}
			
		}
	}
	
	private static void updateFlake(List<Flake> flakes) {
		for (Flake flake : flakes) {
			if (canMoveDown(flake, flakes) && flake.y <= 27) {
				flake.y += 1;
			} else if (canSlideLeft(flake, flakes) && flake.y <= 27 && flake.x > 0) {
				flake.x -= 1;
				flake.y += 1;
			} else if (canSlideRight(flake, flakes) && flake.y <= 27 && flake.x < 99) {
				flake.x += 1;
				flake.y += 1;
			} else{
				flake.stationary = true;
			}
		}
		
	}
	
	private static boolean canMoveDown(Flake flakeToCheck, List<Flake> flakes) {
		for (Flake flake : flakes) {
			if (flakeToCheck.x == flake.x && (flakeToCheck.y + 1) == flake.y) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean canSlideLeft(Flake flakeToCheck, List<Flake> flakes) {
		for (Flake flake : flakes) {
			if (flakeToCheck.x - 1 == flake.x && (flakeToCheck.y + 1) == flake.y) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean canSlideRight(Flake flakeToCheck, List<Flake> flakes) {
		for (Flake flake : flakes) {
			if (flakeToCheck.x + 1 == flake.x && (flakeToCheck.y + 1) == flake.y) {
				return false;
			}
		}
		return true;
	}
	
	
}
