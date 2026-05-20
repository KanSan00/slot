package com.design_shinbi.slot;

public class Main {

	public static void main(String[] args) {
		Player player = new Player();
		SlotMachine slotMachine = new SlotMachine(player);
		slotMachine.start();
	}

}
