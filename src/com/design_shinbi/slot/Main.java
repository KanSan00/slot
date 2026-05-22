package com.design_shinbi.slot;

import com.design_shinbi.slot.save.SaveManager;

public class Main {

	public static void main(String[] args) {
		Player player = new Player();
		SaveManager.load(player);
		SlotMachine slotMachine = new SlotMachine(player);
		slotMachine.start();
	}
}
