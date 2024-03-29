package testapi;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

public class VHRPDFInterface {
	String vhrurl()
			throws ClientProtocolException, IOException, JSONException {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("https://tkmapi-uat.tkm.co.in/vhrpdf");
		JSONObject json = new JSONObject();
		
//		String urlStr = "https://dev-vhr-report.s3.ap-south-1.amazonaws.com/report/ST/2022/06_June/29/VHR_Report_VIN-1_1656481593685.pdf?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=ASIAQGMBAWYIALVXLXFJ%2F20220713%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20220713T071042Z&X-Amz-Expires=600&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEDcaCmFwLXNvdXRoLTEiRjBEAiAqkDilsnPS1i3vIopu5DrDFiUpSJ9Q4aJPGbwZkZX4CQIgUdQsX55mssncFuHXRk6W42A7GI9oysgR1X%2BQSEBfnwsqrwIIcBABGgwwMTM2OTIzNTIwMTYiDJ0ZosUCmYXUv2FJRCqMAiocgBXH5%2BH3M3RZSqDDIhVSFBUkncL5bRnbADuZvoYK9K0RnLX64dENf%2FrVdmwALwBF87hsiZ9HGLgQBwpfClx8FhR3JqtFbRACY0umDRrwMViYO8aAOHCHzLeyBwjnRVAPOrObcClXMRu5PEnL74HFYzwwrvVPzgvh7OsNMzCce3YQQcfN%2Fpvcyk93V%2F0yPlVN4srzd2H6dQ4VlWf5A75C4v9z9g%2FPVq6fVZwxcxbrfMW025sy%2FzHrUhhOVwssFB6ePX%2BrzORe5AUihhpOynoEmjMFUZYaiXFq2Bjc%2FVjMkWoiTA3vPgYrVSsQGwdYsIZHuzvdQcTH%2BMpugRQdoA2OwcLHebMsyjTbTL4w8N%2B5lgY6mwFF500AHtL%2BoAneiKO4mMWxwNQyxcO%2FSDufOPw2%2FRENsHai%2BotGgjk5xd9hK0Cx4RDfr9lBKs090DRdemRGPh6rS4CWBMVZnC4t3rNp9Q67gZ3j31CRMhK4Qi%2F9BcW%2B3WfLjqQLifDLECGtBeARF%2B7by65DZAoZ72iZgeAMX349Hz2OMpWwLdaElBfFj%2Fvki780kN%2BK4bbg74ZzIA%3D%3D&X-Amz-Signature=e2c88db6fb30eca50b0a976d8330735191dbeb07c9484cfb7f0047bff6f6f05c&X-Amz-SignedHeaders=host";
//		String urlStr = "https://dev-vhr-report.s3.ap-south-1.amazonaws.com/report/ST/2022/06_June/29/VHR_Report_VIN-1_1656481593685.pdf?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=ASIAQGMBAWYIEY3D6QCP%2F20220707%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20220707T060950Z&X-Amz-Expires=25200&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEKb%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCmFwLXNvdXRoLTEiRjBEAiBQ1pZh3%2BeXWNM8jCTfnoUGesrIOLtEjOFBbwiiyREsZQIgN9M%2F1%2FKi7NmXUvyS4UIyCgcEbQa%2BbqtayztVJSa5hmwquAIIz%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FARABGgwwMTM2OTIzNTIwMTYiDNjtQ0sWoDy9XtZsviqMAvyN5eaX1rPdAXnX48kMwGfwGfrON6NlUGUSeva%2F0l9h2uBiefeEBJBsyFOm%2B4scCD%2BAwOMdqVgGun00I87A19%2FPLDWM%2BV7XdiPHCsQf4Y3xxIeWX040Fi%2FUWoJgALYSs0AnUubzi%2BmERN3nFmqR4b9YsCBGQyj1FnsVfu%2BzzFzo3PK4zVpGN%2FPTZswzlPsLadqWKpRXsg4QpdYTwfXDQHSFsXrgkL%2B%2FFu%2Bkhob%2FtghWCSoimjjhoNj0tv%2BuzhkvJM0vslZ1CFvhtya%2BeV7wNIUuUhO0z1ttH0Lh1ICKu6n3dWVd6y3wReQUTpS68qyvtIWT1bnGxgSJqICvt%2F15HbXhNiXEdTFdW5KVgwswrPGZlgY6mwFtsXHiv1AGnShc1sbSndRmBSmQW9SOhz%2Fhpz19MGLEGgpC%2F%2FT6HiVSrdAKIuHWEafXc4SCMq0rk3vNHgFE10z3wNWBRT93QdIb8%2Bk7z%2B79qlkKaoXb7XmPAIHLF0U2Oe8WWso06gekDq%2F0sxoHmh0xHjKmWneHpjoLG4FsLipyfKlaKr3loKU6UcDR7xfe1saRgSujSq4m4YgbfQ%3D%3D&X-Amz-Signature=1ccc3d00caf3bc2b56e638d20eec692d8d6d41dcbe34ea39cc18e5303cfe637d&X-Amz-SignedHeaders=host";
//		json.put("vhr_url", url);
		String filePath = "";
		String apikey = "";
		String apisecret = "";
		StringEntity input = new StringEntity(json.toString());
		input.setContentType("application/json");
		Properties prop = new Properties();
		InputStream input1 = getClass().getClassLoader().getResourceAsStream("config.properties");
		prop.load(input1);
		apikey = prop.getProperty("apikey");
		apisecret = prop.getProperty("apisecret");
		filePath = prop.getProperty("ecaretemp");
		post.setHeader("apikey", apikey);
		post.setHeader("apisecret", apisecret);
		post.setEntity(input);
		HttpResponse response = client.execute(post);
		//downloading the file
		String ecareFile = "D:\\kb_attach\\CONNECT_ATTACHMENTS\\TFQS_ATTACHEMENTS\\ECARE\\VH_REPORT\\" + "vhrpdf.pdf";
//		response.setHeader("content-type","application/pdf");
		FileOutputStream fos = new FileOutputStream(ecareFile, false);
		if(response.getStatusLine().toString().contains("403") || response.getStatusLine().toString().contains("500")){
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
//			response.getEntity().getContent()
			String output = "";
			while ((line = rd.readLine()) != null) {
//				output += line;
//				fos.write(rd.readLine());
				System.out.println(line);
			}
		}else {
			InputStream in = response.getEntity().getContent();
//			IOUtils.copy(in, fos);
//			fos.close();
//			fos.flush();
//			response.getEntity().getContent().close();
//			System.out.println("print"+ response.getEntity().toString());
//			System.out.println(in);
			System.out.println(response.getStatusLine());
			int length;
			byte[] buffer = new byte[8*1024];
			while ((length = in.read(buffer))!= -1) {
//				if((line = rd.readLine())==null){
//					System.out.println(line.contains("AccessDenied"));
//				}
				fos.write(buffer, 0, length);
				System.out.println("inside Test.java" + length);
			}
			IOUtils.closeQuietly(fos);
			IOUtils.closeQuietly(in);
		}
		return "Y";
	}
}
