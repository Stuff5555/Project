import com.sun.deploy.util.SessionState;
import com.sun.javafx.fxml.builder.URLBuilder;
import jdk.nashorn.api.scripting.JSObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import sun.net.www.http.HttpClient;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by IIS on 31.03.2016.
 */
public class Films {
    public static void main(String[] args) throws IOException, URISyntaxException {
        CloseableHttpClient client= HttpClients.createDefault();
        HttpGet get=new HttpGet("http://www.omdbapi.com/?s=terminator");
        CloseableHttpResponse resp= client.execute(get);
String str= EntityUtils.toString(
        resp.getEntity(), "UTF-8");
 System.out.println(str);
        JSONObject root=new JSONObject(str);
        JSONArray search = root.getJSONArray("Search");
        for (int i=0; i<search.length(); i++){
            JSONObject item=search.getJSONObject(i);
            String title=item.getString("Title");
            System.out.println(title);
            String id=item.getString("imdbID");
            URI uri = new URIBuilder("http://www.omdbapi.com").addParameter("i", id).addParameter("plot", "full").build();
            HttpGet get2=new HttpGet(uri);
            CloseableHttpResponse resp2= client.execute(get2);
            String str2= EntityUtils.toString(
                    resp2.getEntity(), "UTF-8");
            JSONObject root2=new JSONObject(str2);
            String plot = root2.getString("Plot");
            System.out.println("Plot: "+plot);
        }
    }
}
