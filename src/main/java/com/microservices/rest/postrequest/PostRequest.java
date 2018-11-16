package com.microservices.rest.postrequest;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import com.utlities.http.ParameterStringBuilder;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PostRequest{

	public static void main(String[] args) throws IOException  {
		System.out.println("SAmple lang");
		postRequest_Json();
	}
	
    public static void whenGetRequest_thenOk() throws IOException {
        URL url = new URL("http://localhost:8080/users");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("param1", "val");
        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
        out.flush();
        out.close();

        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        int status = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        assertEquals("status code incorrect", status, 200);
        assertTrue("content incorrect", content.toString().contains("Example Domain"));
    }

    public static void getRequest() throws IOException {
        URL url = new URL("http://localhost:8080/users");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");

        con.setDoOutput(true);

        int status = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        
        

        System.out.println(status +  content.toString());
        //assertEquals("status code incorrect", status, 200);*/
    }
    
    public static void postRequest() throws IOException {
    	CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("http://localhost:8080/users");
        System.out.println("Requesting : " + httppost.getURI());

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("name", "Kean");
        builder.addTextBody("birthdate", "2018-11-12T12:41:27.394+0000");        
        //httppost.addHeader("Content-Type", "application/json");
        
        HttpEntity multipart = builder.build();
        
        httppost.setEntity(multipart);

        CloseableHttpResponse response = client.execute(httppost);
        //assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
        System.out.println("Response" + response.getStatusLine().getStatusCode());
        client.close();
    }
    
    public static void postRequest_Json() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/users");
     
        String json = "{\"name\":\"Kean\",\"birthdate\":\"2018-11-12T12:41:27.394+0000\"}";
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
     
        CloseableHttpResponse response = client.execute(httpPost);
        System.out.println("Response" + response.getStatusLine().getStatusCode());
        client.close();
    }

    public void whenGetCookies_thenOk() throws IOException {
        URL url = new URL("http://example.com");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        CookieManager cookieManager = new CookieManager();
        String cookiesHeader = con.getHeaderField("Set-Cookie");
        Optional<HttpCookie> usernameCookie = null;
        if (cookiesHeader != null) {
            List<HttpCookie> cookies = HttpCookie.parse(cookiesHeader);
            cookies.forEach(cookie -> cookieManager.getCookieStore().add(null, cookie));
            usernameCookie = cookies.stream().findAny().filter(cookie -> cookie.getName().equals("username"));
        }

        if (usernameCookie == null) {
            cookieManager.getCookieStore().add(null, new HttpCookie("username", "john"));
        }

        con.disconnect();

        con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Cookie", StringUtils.join(cookieManager.getCookieStore().getCookies(), ";"));

        int status = con.getResponseCode();

        assertEquals("status code incorrect", status, 200);
    }

    @Test
    public void whenRedirect_thenOk() throws IOException {
        URL url = new URL("http://example.com");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        con.setInstanceFollowRedirects(true);
        int status = con.getResponseCode();

        if (status == HttpURLConnection.HTTP_MOVED_TEMP || status == HttpURLConnection.HTTP_MOVED_PERM) {
            String location = con.getHeaderField("Location");
            URL newUrl = new URL(location);
            con = (HttpURLConnection) newUrl.openConnection();
        }

        assertEquals("status code incorrect", con.getResponseCode(), 200);
    }


}
