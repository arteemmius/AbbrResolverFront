package model.data.list;

import java.util.ArrayList;
import java.util.List;

public class ListItem {
	private String abbr;
	private List<String> abbrDesc = new ArrayList<>();
	
	public ListItem(String abbr, List<String> abbrDesc) {
		this.abbr = abbr;
		this.abbrDesc = abbrDesc;
	}
	public String getAbbr() {
		return abbr;
	}
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}
	public List<String> getAbbrDesc() {
		return abbrDesc;
	}
	public void setAbbrDesc(List<String> abbrDesc) {
		this.abbrDesc = abbrDesc;
	}
	
	
	
}
