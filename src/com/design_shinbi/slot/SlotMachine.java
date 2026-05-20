package com.design_shinbi.slot;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SlotMachine {

	private Player player;
	private Reel reel;
	private Scanner scanner;
	private int betCoin = 10;
	List<Symbol> results = new ArrayList<Symbol>();
	
    public static final int ACTION_BET   = 1;
    public static final int ACTION_CHANGE_BET = 2;
    public static final int ACTION_STOP = 0;
	
	public SlotMachine(Player player) {
		this.player = player;
		this.scanner = new Scanner(System.in);
		// List.ofこれをやることで全部を取得できる(なんか不変のリストを返すらしい。
		//引数には最大255個まで。ただし、配列を引数に渡す場合はこの限りではないらしい)
		reel = new Reel(List.of(Symbol.values()), scanner);	
	}
	
	public void start() {
		 while(player.getCoin() > 0) {
			 System.out.println("============================================");
			 System.out.println("現在の所持コイン: " + player.getCoin() + "枚");
			 System.out.println("初期BET枚数： "+betCoin+"枚");
			 
			 int action = selectAction();
			 
			 if(action == ACTION_BET) {
				 
				 if(player.getCoin() < betCoin) {
					 System.out.println("コインの枚数が足りません。");
					 continue;
				 }
				 
				 // コイン消費
				 player.useCoin(betCoin);
				 // スロット回転
				 results = reel.spin();
				 // あたりはずれの結果の表示
				 result(results);	
			 } else if(action == ACTION_CHANGE_BET) {
				 changeBet();
			 }
			 else {
				 // やめる
				 System.out.println("ゲーム終了");
				 System.exit(0);
				 break;
			 }			 
			 
			 
			 if(player.getCoin() >= betCoin) {
				 // 回す
			 }
			 else {
				 System.out.println("コインがなくなりました");
				 System.exit(0);
				 break;
			 }
		 }
	}
	
	/**
	 * ゲームをするか、BET枚数を変えるか、やめるかの選択を表示する
	 * @return
	 */
	private int selectAction() {
		String selectMessage = String.format(
	            "[%d] BET (スロットを回す),[%d] BET変更 [%d] STOP (やめる)",
	            ACTION_BET, ACTION_CHANGE_BET, ACTION_STOP);
		System.out.println(selectMessage);
		
		int action = -1;
		while(action != ACTION_BET
	            && action != ACTION_CHANGE_BET
	            && action != ACTION_STOP) {

	        try {

	            action = Integer.parseInt(scanner.nextLine());

	        } catch(Exception e) {

	            System.out.println("正しい数値を入力してください");
	            continue;
	        }
	    }
		return action;
	}
	
	/**
	 * BET枚数を変えられる
	 */
	private void changeBet() {
		System.out.println("BET枚数を入力してください");
	    try {
	    	int newBet = Integer.parseInt(scanner.nextLine());
	    	if(newBet > 0 && newBet <= player.getCoin()) {
	    		betCoin = newBet;
	        } else {
	        	System.out.println("設定できません");
	        }
	    } catch(Exception e) {
	    	System.out.println("数値を入力してください");
	    }
	}
	
	/**
	 * 当たった柄によってもらえる額が変わるようにしてある
	 * @param results
	 */
	private void result(List<Symbol> results) {

		if(results.get(0) == Symbol.SEVEN
				&& results.get(1) == Symbol.SEVEN 
				&& results.get(2) == Symbol.SEVEN) {
			 System.out.println("777!!");
			 payout(results);
		}
		else if(results.get(0) == Symbol.BAR
				&& results.get(1) == Symbol.BAR
				&& results.get(2) == Symbol.BAR) {
			payout(results);
		}
		else if(results.get(0) == results.get(1)
	            && results.get(1) == results.get(2)) {
			payout(results);
		}else {
			 System.out.println("はずれ");
		}
	}
	
	/**
	 * もらえる額の計算処理
	 * @param results
	 */
	private void payout(List<Symbol> results) {
		int payoutCoin = 0;
		 payoutCoin = betCoin * results.get(0).getPayout();
		 player.addCoin(payoutCoin);
		 System.out.println(payoutCoin+"枚獲得!!");
	}
}
