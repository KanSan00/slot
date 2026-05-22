package com.design_shinbi.slot;

public class Player {

	private int coin = 0;
	private int debtCoin = 0;
	private Boolean debt = false;
	
	public int getCoin() {
		return this.coin;
	}
	
	public int getDebtCoin() {
		return this.debtCoin;
	}
	
	public Boolean getDebt() {
		return this.debt;
	}
	
	public void setDebt(Boolean debt) {
		this.debt = debt;
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
	
	public void addDebetCoin(int addDebtCoin) {
		debtCoin += addDebtCoin;
	}
	
	public void divDebtCoin(int divDebtCoin) {
		debtCoin -= divDebtCoin;
	}
}
