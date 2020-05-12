package model.data.list;

import java.util.ArrayList;
import java.util.List;

import model.data.list.ListGroupsModel.ListGroupInfo;

import org.zkoss.zul.Label;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listgroup;
import org.zkoss.zul.Listgroupfoot;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;

import realization.AbbrApplication;
	 
public class ListGroupRenderer implements ListitemRenderer<Object> {
 
	@Override
    public void render(Listitem listitem, Object obj, int index) throws Exception {	
		AbbrApplication.trace("render: listitem = " + listitem);
		AbbrApplication.trace("render: obj = " + obj);
		AbbrApplication.trace("render: index = " + index);
    	try {
	    	ListItem data = ListItemData.getAllFoods().get(index);
	    	
	    	Listcell self0 = new Listcell();
	    	self0.setLabel(data.getAbbr());
	        listitem.appendChild(self0);
	        
	        Tabbox tabbox = new Tabbox();
	        tabbox.setWidth("900px");
	        tabbox.setHeight("125px");
	        Tabs tabs = new Tabs();
	        Tabpanels tabpanels = new Tabpanels();
	        
	        for (int i = 0; i < data.getAbbrDesc().size(); i++) {
		        Tab tab = new Tab();
		        tab.setLabel(String.valueOf(i));
		        
		        Tabpanel tabpanel = new Tabpanel();
		        Label l = new Label();
		        l.setValue(data.getAbbrDesc().get(i));
		        
		        tabbox.appendChild(tabs);
		        tabbox.appendChild(tabpanels);
		        tabs.appendChild(tab);
		        tabpanels.appendChild(tabpanel);
		        tabpanel.appendChild(l);
	        }
	        
	        Listcell self = new Listcell();
	        self.appendChild(tabbox);
	        listitem.appendChild(self);
    	}
    	catch(Exception e) {
    		//пустой список
    		e.printStackTrace();
    		return;
    	}
    }
 
}
