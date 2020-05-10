package model.data.list;
	 

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
 
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

