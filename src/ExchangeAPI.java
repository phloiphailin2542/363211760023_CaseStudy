import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ExchangeAPI {
   //class attributes
    private String result;
    private int time_last_update_unix;
    private String time_last_update_utc;
    private int time_next_update_unix;
    private String time_next_update_utc;
    private String base_code;
    private JSONObject eachRate;
    private static String url_API = "https://v6.exchangerate-api.com/v6/7865e4a73508cd23f2d9384c/latest/";
    private static JSONObject jsonObject;

    //connect to saver
    public boolean getConnection(String base_code) throws IOException {
        String json = "";
        URL url = null;
        HttpURLConnection request = null;
        try {
            url = new URL(url_API + base_code);
            request = (HttpURLConnection) url.openConnection();
            //connect to save
            request.connect();
            //read data from sever
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = reader.readLine();

            if (line.length() > 0) {
                json += line;

            }
            //json to json Object
            jsonObject = new JSONObject(json);
            if (jsonObject == null) {
                return false;

            }
            this.result = jsonObject.getString("result");
            this.base_code = jsonObject.getString("base_code");
            this.eachRate = jsonObject.getJSONObject("conversion_rates");

        }catch (MalformedURLException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }//getConnection

    public String getResult() {
        return result;
    }

    public String getTime_next_update_utc() {
        return time_next_update_utc;
    }

    public String getBase_code() {
        return base_code;
    }

    public JSONObject getEachRate() {
        return eachRate;
    }

    public double getEachRate(String newCurrency){
        double eachRate = 0.0;
        try{
            eachRate = this.eachRate.getDouble(newCurrency);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return  eachRate;
    }
}//class
