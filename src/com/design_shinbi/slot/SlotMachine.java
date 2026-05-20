package com.design_shinbi.slot;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SlotMachine {

	private Player player;
	private Reel reel;
	private Scanner scanner;
	private int betCoin;
	List<Symbol> results = new ArrayList<Symbol>();
	
    public static final int ACTION_BET   = 1;
    public static final int ACTION_STOP = 2;
	
	public SlotMachine(Player player) {
		this.player = player;
		this.scanner = new Scanner(System.in);
		// List.ofこれをやることで全部を取得できる
		reel = new Reel(List.of(Symbol.values()), scanner);	
	}
	
	public void start() {
		 while(player.getCoin() > 0) {
			 System.out.println("============================================");
			 System.out.println("現在の所持コイン: " + player.getCoin() + "枚");
			 System.out.println("BET枚数を決めてください");
			 String input = this.scanner.nextLine();
			 betCoin = Integer.parseInt(input);
			 if(player.getCoin() >= betCoin) {
				 int action = selectAction();
				 // 回す
				 if(action == ACTION_BET) {
					// コイン消費
					 player.useCoin(betCoin);
					 // スロット回転
					 results = reel.spin();
					 // あたりはずれの結果の表示
					 result(results);	
				 } else {
					 // やめる
					 System.out.println("ゲーム終了");
					 System.exit(0);
					 break;
				 }			 
			 }
			 else {
				 System.out.println("その枚数を持っていません。");
				 continue;
			 }
		 }
		 System.out.println("コインがなくなりました");
		 System.exit(0);
	}
	
	private int selectAction() {
		String selectMessage = String.format(
	            "[%d] BET (スロットを回す), [%d] STOP (やめる)",
	            ACTION_BET, ACTION_STOP);
		System.out.println(selectMessage);
		
		int action = 0;
		while(action != ACTION_BET && action != ACTION_STOP) {
			String input = this.scanner.nextLine();
			try {
					action = Integer.parseInt(input);
				}
			catch(Exception e) {
				 System.out.println("[1]or[2]を選択してください。");
				 continue;
				}
		}
		return action;
	}
	
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
	
	private void payout(List<Symbol> results) {
		int payoutCoin = 0;
		 payoutCoin = betCoin * results.get(0).getPayout();
		 player.addCoin(payoutCoin);
		 System.out.println(payoutCoin+"枚獲得!!");
	}
}
