package com.design_shinbi.slot;

public enum Symbol {
	SEVEN(5, 10),
    BAR(15, 5),
    CHERRY(30, 3),
    BELL(50, 2);
	
	private int rate;
	private int payout;
	
	private Symbol(int rate, int payout) {
		this.rate = rate;
		this.payout = payout;
	}
	
	public int getRate() {
		return this.rate;
	}
	
	public int getPayout() {
		return this.payout;
	}
}
