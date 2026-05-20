package com.design_shinbi.slot;

public class Player {

	int coin = 100;
	
	public int getCoin() {
		return coin;
	}

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
}
