package com.design_shinbi.slot.save;

public class SaveData {

	private int coin;
	private int debtCoin;
	private Boolean isDebt;

	public SaveData(int coin, int debtCoin, Boolean isDebt) {
		this.coin = coin;
		this.debtCoin = debtCoin;
		this.isDebt = isDebt;
	}
	
	public int getCoin() {
		return this.coin;
	}

	public int getDebtCoin() {
		return this.debtCoin;
	}
	
	public Boolean getIsDebt() {
		return isDebt;
	}
}
