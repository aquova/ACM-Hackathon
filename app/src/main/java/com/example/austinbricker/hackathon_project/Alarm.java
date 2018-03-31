package com.example.austinbricker.hackathon_project;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

public class Alarm extends AppCompatActivity {
    public String getUserData(boolean police, boolean fire, boolean medical) {
        String userData = "{";
        userData += "\"id\": \"507f191e810c19729de860ea\",";
        userData += "\"status\": \"ACTIVE\",";
        userData += "\"services\": {";
        userData += "\"police\": " + String.valueOf(police) + ",";
        userData += "\"fire\": " + String.valueOf(fire) + ",";
        userData += "\"medical\": " + String.valueOf(medical) + ",";
        userData += "},";
        userData += "\"locations\": {";
        userData += "\"addresses\": [],";
        userData += "\"coordinates\": [{";
        userData += "\"lat\": 34.32334,";
        userData += "\"lng\": -117.3343,";
        userData += "\"accuracy\": 5,";
        userData += "\"created_at\": \"2017-09-23T17:59:02Z\"";
        userData += "}]";
        userData += "},";
        userData += "\"created_at\": \"2017-09-23T17:59:02Z\"";
        userData += "}";

        return userData;
    }

    public void callAlarm(boolean police, boolean fire, boolean medical) {
        final String userData = getUserData(police, fire, medical);



        MyAsyncTask sendData = new MyAsyncTask() {
            @Override
            protected Void doInBackground(Void... voids) {
                HttpURLConnection client = null;

                try {
                    // Open connection to alarm API
                    URL url = new URL("https://api.safetrek.io/v1/alarms");
                    client = (HttpURLConnection) url.openConnection();
                    client.setRequestMethod("POST");
                    // Get properties to send
                    client.setRequestProperty("Content-Type", "application/json");
                    client.setDoInput(true);

                    // Write stream
                    OutputStream outputPost = new BufferedOutputStream(client.getOutputStream());
                    outputPost.write(userData.getBytes());
                    outputPost.flush();
                    outputPost.close();


                }
                catch (MalformedURLException error) {
                    // Incorrect URL
                }
                catch (SocketTimeoutException error) {
                    // URL access timeout
                }
                catch (IOException error) {
                    // IO Error
                }
                // After everything is done, if client still exists, disconnect
                finally {
                    if (client != null) {
                        client.disconnect();
                    }
                }
                return null;
            }
        };
        sendData.execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm);

        Button sendAlarm = findViewById(R.id.helpButton);
        sendAlarm.setOnClickListener(new View.OnClickListener() {
            // On helpButton click
            public void onClick(View view) {
                callAlarm(true, false, false); // Some example inputs
            }
        });
    }
}

abstract class MyAsyncTask extends AsyncTask<Void, Void, Void>
{
    @Override
    protected void onPostExecute(Void result) {

    }

}
