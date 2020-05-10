package model.data.list;
 
import java.util.Comparator;
import org.zkoss.zul.GroupsModelArray;
import org.zkoss.zul.Listgroup;
import org.zkoss.zul.Listgroupfoot;
 
public class ListGroupsModel extends GroupsModelArray<ListItem, ListGroupsModel.ListGroupInfo, Object, Object> {
    private static final long serialVersionUID = 1L;

    public ListGroupsModel(ListItem[] data, Comparator<ListItem> cmpr) {
        super(data, cmpr);
    }

    protected ListGroupInfo createGroupHead(ListItem[] groupdata, int index, int col) {
        return new ListGroupInfo(groupdata[0], -1, col);
    }
    
    protected Object createGroupFoot(ListItem[] groupdata, int index, int col) {
        // Return the sum number of each group
        return groupdata.length;
    }

    
    public static class ListGroupInfo {
        private ListItem firstChild;
        private int groupIndex;
        private int colIndex;
         
        public ListGroupInfo(ListItem firstChild, int groupIndex, int colIndex) {
            super();
            this.firstChild = firstChild;
            this.groupIndex = groupIndex;
            this.colIndex = colIndex;
        }
         
        public ListItem getFirstChild() {
            return firstChild;
        }
        public int getGroupIndex() {
            return groupIndex;
        }
        public int getColIndex() {
            return colIndex;
        }
    }
}

