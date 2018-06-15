package com.example.puppy.subwayapp.Task;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author sdm32
 * @since 2018-06-13
 */

public class TaskPost extends AsyncTask<String,String,String>
{
    private String url_str;     // 연결할 jsp주소 "54.213.190.199";
    private String sendMsg, receiveMsg;

    public TaskPost(String url, String json)
    {
        this.url_str = "http://13.125.250.120/"+ url;
        this.sendMsg = json;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            String str;
            URL url = new URL(url_str);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestMethod("POST");
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

            osw.write(sendMsg);
            osw.flush();

            if(conn.getResponseCode() == conn.HTTP_OK)
            {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();

                while ((str = reader.readLine()) != null)
                {
                    buffer.append(str);
                }

                receiveMsg = buffer.toString();
                System.out.println(receiveMsg);
            } else {
                Log.i("통신 결과", conn.getResponseCode()+"에러");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return receiveMsg;
    }

}
