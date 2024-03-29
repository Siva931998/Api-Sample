package testapi;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

public class Testing {

	public static void main(String[] args) throws ClientProtocolException, IOException, JSONException 
	{
		Test t = new Test();
		String response = t.vhrurl();
		System.out.println("from testing.java" + " " +response);
	}

}
