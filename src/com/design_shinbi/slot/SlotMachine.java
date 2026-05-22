package com.design_shinbi.slot;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SlotMachine {

	private Player player;
	private Reel reel;
	private Scanner scanner;
	private int betCoin = 4;
	List<Symbol> results = new ArrayList<Symbol>();
	
    public static final int ACTION_BET   = 1;
    public static final int ACTION_CHANGE_BET = 2;
    public static final int ACTION_DEBT = 3;
    public static final int ACTIN_REPAYMENT = 4;
    public static final int ACTION_STOP = 0;
	
	public SlotMachine(Player player) {
		this.player = player;
		this.scanner = new Scanner(System.in);
		// List.ofこれをやることで全部を取得できる(なんか不変のリストを返すらしい。
		//引数には最大255個まで。ただし、配列を引数に渡す場合はこの限りではないらしい)
		reel = new Reel(List.of(Symbol.values()), scanner);	
	}
	
	public void start() {
		if(player.getCoin() <= 0) {
			payment();
		}
		
		 while(player.getCoin() > 0) {
			 System.out.println("============================================");
			 
			 System.out.println("現在の所持コイン: " + player.getCoin() + "枚");
			 if(player.getDebt()) {
				 System.out.println("現在の借金額: " + player.getDebtCoin() + "枚");
			 }
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
			 } 
			 else if(action == ACTION_CHANGE_BET) {
				 changeBet();
			 }
			 else if(action == ACTION_DEBT) {
				 debt();
			 }
			 else if(action == ACTIN_REPAYMENT) {
				 repayment();
			 }
			 else {
				 // やめる
				 System.out.println("ゲーム終了");
				 break;
			 }			 
			 
			 if(player.getCoin() > 0 && player.getCoin() < betCoin) {
				 System.out.println("BET枚数を変更してください");
				 changeBet();
			 }else if(player.getCoin() <= 0 && player.getDebtCoin() <= 0) {
				 System.out.println("コインがなくなりました");
				 System.out.println("もう一度遊びますか？");
				 action = selectOnemore();
				 if(action == ACTION_BET) {					 
					 payment();
				 }
				 else {
					 System.out.println("ゲームを終了します。");
					 break;
				 }
				 continue;
			 }
			 else if(player.getCoin() <= 0 && player.getDebtCoin() > 0){
				 System.out.println("借金してでも返してもらおうか");
				 player.setDebt(true);
				 player.addDebetCoin(100);
				 player.addCoin(100);
				 continue;
			 }
		 }
	}
	
	/**
	 * ゲームをするか、BET枚数を変えるか、やめるかの選択を表示する
	 * @return
	 */
	private int selectAction() {
		String selectMessage = String.format(
	            "[%d] BET (スロットを回す),[%d] BET変更 [%d] 借金 [%d] 返済 [%d] STOP (やめる)",
	            ACTION_BET, ACTION_CHANGE_BET, ACTION_DEBT, ACTIN_REPAYMENT, ACTION_STOP);
		System.out.println(selectMessage);
		
		int action = -1;
		while(action != ACTION_BET
	            && action != ACTION_CHANGE_BET
	            && action != ACTION_DEBT
	            && action != ACTIN_REPAYMENT
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
	
	private int selectOnemore() {
		String selectMessage = String.format(
	            "[%d] START [%d] STOP (やめる)",
	            ACTION_BET, ACTION_STOP);
		System.out.println(selectMessage);
		
		int action = -1;
		while(action != ACTION_BET && action != ACTION_STOP) {
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
	
	private void debt() {
		System.out.println("借金する額を入力してください");
		try {
				int debtCoin = Integer.parseInt(scanner.nextLine());
				player.setDebt(true);
				player.addDebetCoin(debtCoin);
				player.addCoin(debtCoin);
			}
			catch(Exception e) {
		    	System.out.println("数値を入力してください");
			}
	}
	
	private void repayment() {
		if(!player.getDebt()) {
			System.out.println("あなたは借金していません。");
			return;
		}
		
		
		System.out.println("返済する額を入力してください");
		try {
			int repaymentCoin = Integer.parseInt(scanner.nextLine());
			if(repaymentCoin < player.getCoin()) {
				if(player.getDebt()) {
					player.divDebtCoin(repaymentCoin);
					player.useCoin(repaymentCoin);
					System.out.println("残りの返済額： "+player.getDebtCoin());
				}
				if(player.getDebtCoin() <= 0) {
					player.setDebt(false);
				}
			}
			else {
				System.out.println("手持ちから可能な額を返済してください");
			}
		}
		catch(Exception e) {
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
	
	private void payment() {
		System.out.println("使用するコインの枚数を入力してください");
		int payment = Integer.parseInt(scanner.nextLine());
		player.addCoin(payment);
	}
	
	/**
	 * もらえる額の計算処理
	 * @param results
	 */
	private void payout(List<Symbol> results) {
		int payoutCoin = 0;
		 payoutCoin = betCoin * results.get(0).getPayout();
		 player.addCoin(payoutCoin);
		 System.out.println("\u001B[33m"+payoutCoin+"枚獲得!!" + "\u001B[0m");
	}
	
}
