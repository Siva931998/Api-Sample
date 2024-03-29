package testapi;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

public class Test {
	String vhrurl()
			throws ClientProtocolException, IOException, JSONException {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("https://tkmapi-uat.tkm.co.in/vhrpdf1");
		JSONObject json = new JSONObject();
		String urlStr = "https://dev-vhr-report.s3.ap-south-1.amazonaws.com/report/ST/2022/06_June/29/VHR_Report_VIN-1_1656481593685.pdf?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=ASIAQGMBAWYIKDLXHPWY%2F20220713%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20220713T122657Z&X-Amz-Expires=600&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEDwaCmFwLXNvdXRoLTEiSDBGAiEAkgo2kh8tzWwiLqCQEopBFiMG%2Bynh6YY7D%2F0204VCA7ICIQDNxH0BsdBSTv%2FPMRCZPWwjBDqhGALkauCfaNY%2FrUEc3SqvAgh2EAEaDDAxMzY5MjM1MjAxNiIMZGSKotF0K5sr%2BqguKowCXCubgKxJ%2Fea6lcfIIosp3qgje6KBu2CZU3WR851Qa1sTvjmHDk9CmJFWlPLHkiOdsBH430p2rQATqZ3d1ypLud3G4xpA4cHDAjbdU6eS6XPt7uD6Y2KBFP%2FHuy1IPqxw7toMH4AyHH9YgQAgPA5Cb1p%2FniuQQzYVOVFxwxH33H%2B18bTuWpNAQ70H9ujfgpOk%2Ftf%2BpkeVZl6ZPb8pqDewjpmr6HBfy8XSqCZwNOYWDK1wU0lDuTlTuAo35%2Ffm%2Fa6qoVhW3On%2B1MCYSlqCB8jsf02kjPmFawClTm8k40P5qdyL8CiIHHiUuAwCneMAwo2NcBj%2B6hwKy%2BKamRXIVnYXRwGa3KuekCF1gJ2JtzCP9LqWBjqZAQLyOUnSeqYpXTxDaaYV%2FO%2FWBPlnkSeeRGDxRk2kjCxfr0d1kzGMBbM%2FV6TdL8X47TAna3MyBj7Ikr4IayYCrxTW8DTFgfgtsoSyn5M%2BfBcyw8vBedk466sMMx6j0d2RwLwI7bYCS%2Fz0dgVCKe5Fn3qbnbVUqEeQf6yY6862AH8DEK7nq%2F0r04csu%2FzdRvxwVUCeje%2FbMq%2BErQ%3D%3D&X-Amz-Signature=8e42061800b3b75ccdfba91159b7fb0b995fadae92b2b5902bb5a1ef8322ad1f&X-Amz-SignedHeaders=host";
//		String urlStr = "https://dev-vhr-report.s3.ap-south-1.amazonaws.com/report/ST/2022/06_June/29/VHR_Report_VIN-1_1656481593685.pdf?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=ASIAQGMBAWYIEY3D6QCP%2F20220707%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20220707T060950Z&X-Amz-Expires=25200&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEKb%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCmFwLXNvdXRoLTEiRjBEAiBQ1pZh3%2BeXWNM8jCTfnoUGesrIOLtEjOFBbwiiyREsZQIgN9M%2F1%2FKi7NmXUvyS4UIyCgcEbQa%2BbqtayztVJSa5hmwquAIIz%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FARABGgwwMTM2OTIzNTIwMTYiDNjtQ0sWoDy9XtZsviqMAvyN5eaX1rPdAXnX48kMwGfwGfrON6NlUGUSeva%2F0l9h2uBiefeEBJBsyFOm%2B4scCD%2BAwOMdqVgGun00I87A19%2FPLDWM%2BV7XdiPHCsQf4Y3xxIeWX040Fi%2FUWoJgALYSs0AnUubzi%2BmERN3nFmqR4b9YsCBGQyj1FnsVfu%2BzzFzo3PK4zVpGN%2FPTZswzlPsLadqWKpRXsg4QpdYTwfXDQHSFsXrgkL%2B%2FFu%2Bkhob%2FtghWCSoimjjhoNj0tv%2BuzhkvJM0vslZ1CFvhtya%2BeV7wNIUuUhO0z1ttH0Lh1ICKu6n3dWVd6y3wReQUTpS68qyvtIWT1bnGxgSJqICvt%2F15HbXhNiXEdTFdW5KVgwswrPGZlgY6mwFtsXHiv1AGnShc1sbSndRmBSmQW9SOhz%2Fhpz19MGLEGgpC%2F%2FT6HiVSrdAKIuHWEafXc4SCMq0rk3vNHgFE10z3wNWBRT93QdIb8%2Bk7z%2B79qlkKaoXb7XmPAIHLF0U2Oe8WWso06gekDq%2F0sxoHmh0xHjKmWneHpjoLG4FsLipyfKlaKr3loKU6UcDR7xfe1saRgSujSq4m4YgbfQ%3D%3D&X-Amz-Signature=1ccc3d00caf3bc2b56e638d20eec692d8d6d41dcbe34ea39cc18e5303cfe637d&X-Amz-SignedHeaders=host";
		json.put("vhr_url", urlStr);
		StringEntity input = new StringEntity(json.toString());
		input.setContentType("application/json");
		
		post.setHeader("apikey", "8QNPUELMswOLbKfvmsDmeTiYkhJKIAYoeoVzGSmrG1pcEsOa");
		post.setHeader("apisecret","9gurE9L1A0NBltZEfwnlL9jagD8cG0AEgFA3FBd8PwG8BKqp3E6FnkZk1j1QQSAD");
		post.setEntity(input);
		HttpResponse response = client.execute(post);
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
