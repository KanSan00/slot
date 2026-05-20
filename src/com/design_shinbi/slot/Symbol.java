package com.design_shinbi.slot;

public enum Symbol {
	SEVEN("7", 5, 10),
    BAR("BAR", 15, 5),
    CHERRY("🍒", 30, 3),
    BELL("🔔", 50, 2);
	
	private String displayName;
	private int rate;
	private int payout;
	
	private Symbol(String displayName, int rate, int payout) {
		this.displayName = displayName;
		this.rate = rate;
		this.payout = payout;
	}
	
	public String toString() {
		return getDisplayName();
	}
	
	public String getDisplayName(){
		return this.displayName;
	}
	public int getRate() {
		return this.rate;
	}
	
	public int getPayout() {
		return this.payout;
	}
}
