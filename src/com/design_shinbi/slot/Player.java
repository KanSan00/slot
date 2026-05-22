package com.design_shinbi.slot;

public class Player {

	private int coin = 0;
	private int debtCoin = 0;
	private Boolean isDebt = false;
	
	public int getCoin() {
		return this.coin;
	}
	
	public void setCoin(int coin) {
		this.coin = coin;
	}
	
	public int getDebtCoin() {
		return this.debtCoin;
	}
	
	public void setDebtCoin(int debtCoin) {
		this.debtCoin = debtCoin;
	}
	
	public Boolean getIsDebt() {
		return this.isDebt;
	}
	
	public void setIsDebt(Boolean debt) {
		this.isDebt = debt;
	}

	/**
	 * コインの追加
	 * @param addCoin
	 */
	public void addCoin(int addCoin) {
		coin += addCoin;
	}
	
	/**
	 *  使用枚数分引く
	 * @param use
	 */
	public void useCoin(int useCoin) {
		coin -= useCoin;
	}
	
	public void addDebtCoin(int addDebtCoin) {
		debtCoin += addDebtCoin;
	}
	
	public void divDebtCoin(int divDebtCoin) {
		debtCoin -= divDebtCoin;
	}
}
