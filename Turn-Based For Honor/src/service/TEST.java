package service;

import java.sql.SQLException;

import bean.Map;
import database.dao.MapDAO;

public class TEST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MapDAO dao = new MapDAO();
		Map map = null;
		try {
			map = dao.findMapById(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int[][] maparr = map.getMap();
		maparr[17][6]=0;
		map.setMap(maparr);
		MapService service = new MapService();
		System.out.println(service.updateMap(map));
		try {
			map = dao.findMapById(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("BEFORE");
		System.out.print("   ");
		for(int i=0;i<map.getWidth();i++) {
			System.out.print(String.format("%2s ", Integer.toHexString(i)));
		}
		System.out.println();
		System.out.println();
		int row = 0;
		for(int[] arr:maparr) {
			System.out.print(String.format("%2s  ", Integer.toHexString(row)));
			for(int i:arr) {
				String str = "-";
				switch(i){
				case 1:str="W";break;
				case 2:str="F";break;
				case 3:str="T";break;
				case 4:str="P";break;
				}
				System.out.print(str+"  ");
			}
			System.out.print(Integer.toHexString(row++)+"\n\n");
		}
		System.out.println("AFTER");
		System.out.print("   ");
		for(int i=0;i<map.getWidth();i++) {
			System.out.print(String.format("%2s ", Integer.toHexString(i)));
		}
		maparr = map.getMap();
		System.out.print("   ");
		for(int i=0;i<map.getWidth();i++) {
			System.out.print(String.format("%2s ", Integer.toHexString(i)));
		}
		System.out.println();
		System.out.println();
		row = 0;
		for(int[] arr:maparr) {
			System.out.print(String.format("%2s  ", Integer.toHexString(row)));
			for(int i:arr) {
				String str = "-";
				switch(i){
				case 1:str="W";break;
				case 2:str="F";break;
				case 3:str="T";break;
				case 4:str="P";break;
				}
				System.out.print(str+"  ");
			}
			System.out.print(Integer.toHexString(row++)+"\n\n");
		}
		System.out.print("   ");
		for(int i=0;i<map.getWidth();i++) {
			System.out.print(String.format("%2s ", Integer.toHexString(i)));
		}
	}

}
