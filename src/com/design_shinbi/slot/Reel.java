package com.design_shinbi.slot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Reel {
	
    private List<Symbol> symbols;
    private Random rand;
    private Scanner scanner;

    public Reel(List<Symbol> symbols, Scanner scanner) {
        this.symbols = symbols;
        this.scanner = scanner;
        this.rand = new Random();
    }
    
    public List<Symbol> getAllSymbol(){
    	return this.symbols;
    }
    
    public List<Symbol> spin() {
    	
		List<Symbol> results = new ArrayList<Symbol>();
		System.out.println("Enterを押してください！");
		for(int i = 0; i < 3; i++) {
			this.scanner.nextLine();
			results.add(randomSymbol());
			System.out.println(results);
		}
		return results;
	}
    
    /**
     * 重み付き抽選
     * @return
     */
    private Symbol randomSymbol(){
    	int totalRate = 0;
        // 合計計算
        for(Symbol symbol : symbols) {
            totalRate += symbol.getRate();
        }
        // レートのトータルを渡して例えば100の場合、0～99を排出
        int num = rand.nextInt(totalRate);
    	int total = 0;
    	for(Symbol symbol: symbols) {
    		// 順番にチェックしていく
    		//例 numが3、totalが5の時、7が当たる
    		// 違った場合次のチェックが入る
    		total += symbol.getRate();
    		if(num < total) {
    			return symbol;
    		}
    	}
    	
    	// 一番しょぼいやつ返しておく
    	return Symbol.BELL;
    }
}
