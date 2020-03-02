package realization;

import java.io.InputStream;
import java.util.List;

import model.data.FullTextInputData;
import model.data.FullTextOutputData;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zul.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AbbrApplication extends SelectorComposer<Window> {

	private static final long serialVersionUID = 1L;
	private final String url = "http://localhost:8090/AbbrResolver-1.0/fullText";
	private final boolean useClassifier = false;
	
	@Wire
    Textbox input;
    @Wire
    Textbox output;
    @Wire
    Listbox list;
    @Wire
    Checkbox checkReturn;
    @Wire
    Listbox listAbbrs;

    @Listen("onClick=#ok")
    public void submit() throws Exception {
    	String po = null;
        trace(input.getValue());
        trace("checkReturn = " + checkReturn.isChecked());
    	if (list.getSelectedItem() != null && !"Не выбрано".equals(list.getSelectedItem().getValue())) {
    		trace("list item = " + list.getSelectedItem().getValue());
    		po = list.getSelectedItem().getValue();
    	}
    	FullTextOutputData out = fillOutputObj(input.getValue(), checkReturn.isChecked(), po);
    	if (out != null) {
    		for (int j = 0; j < out.getAbbrList().size(); j++) {
    			trace("out.getAbbrList()_j = " + out.getAbbrList().get(j));
    		}
    		initListbox(listAbbrs, out.getAbbrList());
    		if (listAbbrs != null && listAbbrs.getItemCount() != 0)
    			listAbbrs.setVisible(checkReturn.isChecked());
    		trace("out.getText() = " + out.getText());
    		output.setValue(out.getText());
	    	if (out.getTextPO() != null) {
	    		int index = getElementIndexByValue(list, out.getTextPO());
	    		if (index != -1) {
	    			trace("index = " + index);
	    			list.setSelectedIndex(index);
	    			trace("list.getSelectedItem().getValue() = " + list.getSelectedItem().getValue());
	    		}
	    	}
    	}
    	else {
    		throw new Exception();
    	}
    }
    public FullTextOutputData fillOutputObj(String text, boolean checkGetAbbr, String po) {
	    FullTextInputData input = new FullTextInputData();
	
	    input.setText(text);
	    input.setCheckGetAbbr(checkGetAbbr);
	    input.setCheckPO(useClassifier);
	    input.setPO(po);
	    
	    try {
	    	FullTextOutputData out = new FullTextOutputData();
	    	out =  sendREST_POST(input, url);
	    	return out;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return null;
    }
    
    private FullTextOutputData sendREST_POST(FullTextInputData obj, String uri) throws Exception {
    	trace("sendREST_POST: start");
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(obj);	
   
        StringEntity strEntity = new StringEntity(str, "UTF-8");
        strEntity.setContentType("application/json");
        HttpPost post = new HttpPost(uri);
        post.setEntity(strEntity);
        //
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(post);
        try {
            HttpEntity entity = response.getEntity();
            if (response.getStatusLine().getStatusCode() != 200) {
                trace("sendREST_POST : error!");
                return null;
            }
            InputStream is = entity.getContent();
            FullTextOutputData result = mapper.readValue(is, FullTextOutputData.class);
            trace("sendREST_POST: result = " + result.getText());
            return result;
        }

        finally {
            response.close();
            httpClient.close();
        }
    }
    
    private void initListbox(Listbox lb, List<String> data) {
    	  lb.setModel(new ListModelList<String>(data));
    	}    
    
    private int getElementIndexByValue(Listbox list, String po) {
    	for(int i = 0; i < list.getItemCount(); i++) {
    		if(po.equals(list.getItemAtIndex(i).getValue())) {
    			trace("getElementIndexByValue: i = " + i);
    			return i;
    		}
    	}
    	return -1;
    }
    
    private void trace(String s) {
//    	System.out.println(s);
    }
}
