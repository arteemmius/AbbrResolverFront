package model.data.list;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zul.Listbox;
 
public class ListGroupViewModel {
 
    private ListGroupsModel groupModel;
     
    @Init
    public void init() {
        groupModel = new ListGroupsModel(ListItemData.getAllFoodsArray(), new ListItemComparator());
    }
 
    public ListGroupsModel getGroupModel() {
        return groupModel;
    }
}

