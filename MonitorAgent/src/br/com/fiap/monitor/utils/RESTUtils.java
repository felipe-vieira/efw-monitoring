package br.com.fiap.monitor.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.AuthPolicy;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;

public class RESTUtils {
	
    private static final String ADMINISTRATION_URL = "http://localhost:4848/management";
    private static final String MONITORING_URL = "http://localhost:4848/monitoring";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String CONTENT_TYPE_XML = "application/xml";
    private static final String ACCEPT_ALL = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";
    private static final Logger LOG = Logger.getLogger(RESTUtils.class.getName());
	
	
    public static String getInformation(String resourcePath, String user, String password) throws IOException, AuthenticationException {
    	HttpHost targetHost = new HttpHost("localhost", 4848, "http"); 
    	DefaultHttpClient httpClient = new DefaultHttpClient();

    	AuthScope authScope = new AuthScope("localhost", 4848);
        UsernamePasswordCredentials userPass = new UsernamePasswordCredentials("admin", "admin");
        
        httpClient.getCredentialsProvider().setCredentials(authScope,userPass);
        
        // Create AuthCache instance
        AuthCache authCache = new BasicAuthCache();
        // Generate BASIC scheme object and add it to the local auth cache
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(targetHost, basicAuth);
        
        BasicHttpContext localContext = new BasicHttpContext();
        localContext.setAttribute(ClientContext.AUTH_CACHE, authCache); 
        
        HttpGet httpG = new HttpGet(ADMINISTRATION_URL + resourcePath);
        httpG.setHeader("Accept", CONTENT_TYPE_JSON);
        HttpResponse response = httpClient.execute(targetHost,httpG,localContext);
        HttpEntity entity = response.getEntity();
        InputStream instream = entity.getContent();
        return isToString(instream);
    }
 
    //using HTTP post for creating and partially updating resources
    public static String postInformation(String resourcePath, String content) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(ADMINISTRATION_URL + resourcePath);
        StringEntity entity = new StringEntity(content);
 
        //setting the content type
        entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, CONTENT_TYPE_JSON));
        httpPost.addHeader("Accept",ACCEPT_ALL);
        httpPost.setEntity(entity);
        HttpResponse response = httpClient.execute(httpPost);
 
        return response.toString();
    }
 
    //using HTTP delete to delete a resource
    public static String deleteResource(String resourcePath) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpDelete httpDelete = new HttpDelete(ADMINISTRATION_URL + resourcePath);
        httpDelete.addHeader("Accept",
                ACCEPT_ALL);
        HttpResponse response = httpClient.execute(httpDelete);
        return response.toString();
 
    }
 
    //converting the get output stream to something printable
    private static String isToString(InputStream in) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(in), 1024);
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            sb.append(line);
        }
        in.close();
        return sb.toString();
    }

}
