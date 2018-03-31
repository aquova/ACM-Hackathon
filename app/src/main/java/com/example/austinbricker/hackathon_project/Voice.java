// Authors: Griffin Shaw
package com.example.austinbricker.hackathon_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Voice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voice);

        // Grab speech enable button and converted text view
        final Button speechEnable = findViewById(R.id.speechEnable);
        final TextView speechText = findViewById((R.id.speechText));

        // Set listener
        speechEnable.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Call converting function
                String convertedText = speechToText(speechEnable);
                speechText.setText(convertedText);
            }
        });
    }

    String speechToText(Button speechEnable) {
        // Check whether enbaled or disabled
        if (!speechEnable.isSelected()) {
            String text = "Speech converted to Text";
            return text;
        }
        else {
            String text = "Stop detected";
            return text;
        }
    }

    void record() {
        
    }


}
