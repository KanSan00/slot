package com.design_shinbi.slot;

public enum Symbol {
	// 何か追加するときはここに追加するだけ
	SEVEN("7", 5, 200),
	BAR("BAR", 10, 100),
	APPLE("🍎", 10, 40),
	ORANGE("🍊", 10, 30),
	GRAPE("🍇", 10, 30),
	CHERRY("🍒", 15, 20),
	WATERMELON("🍉", 15, 20),
	BELL("🔔", 25, 10);
	
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
