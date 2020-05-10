package model.data.list;

import java.util.ArrayList;
import java.util.List;
 
public class ListItemData {
	
    private static ListItemData instance;
    private ListItemData(){}
    public static ListItemData getInstance(){ // #3
    if(instance == null){		//���� ������ ��� �� ������
        instance = new ListItemData();	//������� ����� ������
    }
    return instance;		// ������� ����� ��������� ������
}
    
    private static List<ListItem> foods = new ArrayList<>();
    
    public static List<ListItem> getAllFoods() {
        return new ArrayList<ListItem>(foods);
    }
    public static void setAllFoods(List<ListItem> foods2) {
        foods = foods2;
    }    
    public static ListItem[] getAllFoodsArray() {
        return foods.toArray(new ListItem[foods.size()]);
    }
    
}
