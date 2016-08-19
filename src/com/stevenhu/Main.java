package com.stevenhu;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
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
		
		while (true) {
			
			updateFlake(flakes);
			flakes.add(new Flake());
			drawFlake(terminal, flakes);
			Key key;
			do {
				Thread.sleep(5);
				key = terminal.readInput();
			}
			while (key == null);
			
			
		}
	}
	
	private static void drawFlake(Terminal terminal, List<Flake> flakes) {
		terminal.clearScreen();
		for (Flake flake :
				flakes) {
			terminal.moveCursor(flake.x, flake.y);
			terminal.putCharacter('O');
		}
	}
	
	private static void updateFlake(List<Flake> flakes) {
		for (Flake flake : flakes) {
			if (canMove(flake, flakes) && flake.y <= 25) {
				flake.y += 1;
			}
		}
		
	}
	
	private static boolean canMove(Flake flake, List<Flake> flakes) {
		for (Flake f : flakes) {
			if (flake.x == f.x && (flake.y + 1) == f.y){
				return false;
			}
		}
		return true;

	}
	
	
}
