package model.data.list;

import java.io.Serializable;
import java.util.Comparator;
 
import org.zkoss.zul.GroupComparator;
 
 
public class ListItemComparator implements Comparator<ListItem>, GroupComparator<ListItem>, Serializable {
    private static final long serialVersionUID = 1L;
 
    public int compare(ListItem o1, ListItem o2) {
        return o1.getAbbr().compareTo(o2.getAbbr().toString());
    }
 
    public int compareGroup(ListItem o1, ListItem o2) {
        if(o1.getAbbr().equals(o2.getAbbr()))
            return 0;
        else
            return 1;
    }
}
