package com.design_shinbi.slot.save;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.design_shinbi.slot.Player;
import com.google.gson.Gson;

public class SaveManager {

	public SaveManager() {
	}
	
    public static void save(SaveData data) {
   
    	try {
    		Gson gson = new Gson();
			FileWriter writer = new FileWriter("save/save.json");
			gson.toJson(data, writer);
			System.out.println("セーブしました。");
			writer.close();
		} catch (Exception e) {
			System.out.println("セーブに失敗しました。");
			e.printStackTrace();
		}
    	
    }

    public static void load(Player player) {
    	try {    		
    			System.out.println("ロードします");
            	Gson gson = new Gson();
            	FileReader reader = new FileReader("save/save.json");
            	SaveData data = gson.fromJson(reader, SaveData.class);
            	reader.close();
            	System.out.println(data.getCoin());
            	player.setCoin(data.getCoin());
            	player.setDebtCoin(data.getDebtCoin());
            	player.setIsDebt(data.getIsDebt());
		} catch (IOException e) {
			System.out.println("ロードに失敗しました。");
			e.printStackTrace();
		}
    }
}
