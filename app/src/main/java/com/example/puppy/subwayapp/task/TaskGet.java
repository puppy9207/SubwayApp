package com.example.puppy.subwayapp.task;

import android.os.AsyncTask;
import android.util.Log;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author sdm32
 * @since 2018-06-13
 */

public class TaskGet extends AsyncTask<String, String, String>
{
    private String url_str;     // 연결할 jsp주소 "54.213.190.199";
    private String sendMsg, receiveMsg;
    private String returnType;

    /**
     * 생성자, 주소와 주소 뒤에 붙일 쿼리스트링을 인자로 받는다
     * @param url         가져올 주소
     * @param queryString 주소에 붙을 쿼리스트링 ?를 앞에 붙여줘야한다
     */
    public TaskGet(String url,String queryString)
    {
        this.url_str = "http://13.125.250.120/"+ url + queryString;
        this.sendMsg = "";
        this.returnType = returnType;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            String str;
            URL url = new URL(url_str);
            JSONParser jsonParser = new JSONParser();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestMethod("GET");

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
                Log.e("받아온 json",receiveMsg);
                JSONObject json = (JSONObject) jsonParser.parse(receiveMsg);

                // success 이면 해당 리스트를, 실패면 false 반환
                if(json.get("result").equals("success"))
                {
                    return json.get("return").toString();
                }else{
                    Log.e("결과 실패", json.get("result").toString());
                    return null;
                }

            } else {
                printErrorMessage(conn);   // 에러메세지 파싱해서 출력
                Log.i("통신 결과", conn.getResponseCode() + "\n");
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 에러 메시지의 InputStream을 받아와서 콘솔에 출력한다.
     * @param conn 넘겨받을 HttpURLConnection 인스턴스
     */
    private void printErrorMessage(HttpURLConnection conn)
    {
        try {
            InputStream error = conn.getErrorStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] byteBuffer = new byte[1024];
            byte[] byteData = null;
            int nLength = 0;

            while((nLength = error.read(byteBuffer, 0, byteBuffer.length)) != -1)
            {
                baos.write(byteBuffer, 0, nLength);
            }

            byteData = baos.toByteArray();
            String response = new String(byteData);
            Log.e("에러 메시지","response = " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
