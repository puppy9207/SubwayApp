package com.example.puppy.subwayapp.task;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author sdm32
 * @since 2018-06-15
 */

public class TaskLogin
{
    public static class DoLogout extends AsyncTask<String, String, Boolean>
    {
        @Override
        protected Boolean doInBackground(String... strings)
        {
            return null;
        }
    }

    public static class DoLogin extends AsyncTask<String, String, String>
    {
        String url_str,id,pw;
        public DoLogin(String url, String id,String pw)
        {
            this.url_str = "http://13.125.250.120/" + url;
            this.id  = id;
            this.pw  = pw;
        }

        @Override
        protected String doInBackground(String... strings)
        {
            URL url = null;
            String input = String.format("{\"id\":\"%s\",\"pw\":\"%s\"}",id,pw);
            Log.e("확인",input);
            String output = "";
            String temp;
            try {
                url = new URL(url_str);

                JSONParser jsonParser = new JSONParser();
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Accept", "application/json");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

                osw.write(input);
                osw.flush();

                if(conn.getResponseCode() == conn.HTTP_OK)
                {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();

                    while ((temp = reader.readLine()) != null)
                    {
                        buffer.append(temp);
                    }
                    output = buffer.toString();

                    // success 일 때 JWT 값 반환,
                    // 실패면 null 반환
                    Log.e("확인",output);
                    JSONObject json = (JSONObject)jsonParser.parse(output);
                    if(json.get("result").equals("success"))
                    {
                        return json.get("JWT").toString();
                    }else{
                        Log.e("에러", json.toString());
                        Log.e("에러", json.get("result").toString());
                        return null;
                    }
                } else {
                    Log.i("통신 결과", conn.getResponseCode()+"에러" + "\n");
                }
            } catch (java.io.IOException | ParseException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
