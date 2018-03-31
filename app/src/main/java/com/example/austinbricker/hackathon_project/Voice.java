// Authors: Griffin Shaw
package com.example.austinbricker.hackathon_project;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

public class Voice extends AppCompatActivity {
    private String convertedText = "Waiting for input";
    private boolean fire = false;
    private boolean police = false;
    private boolean medical = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voice);

        // Grab speech enable button and converted text view
        final Button speechEnable = findViewById(R.id.speechEnable);
        final TextView speechText = findViewById((R.id.speechText));
        // Set value for text view
        speechText.setText(convertedText);

        // Set listener
        speechEnable.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Call converting function
                displaySpeechRecognizer();
            }
        });
    }

    /* Taken from https://developer.android.com/training/wearables/apps/voice.html */
    private static final int SPEECH_REQUEST_CODE = 0;
    // Create an intent that can start the Speech Recognizer activity
    private void displaySpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        // Start the activity, the intent will be populated with the speech text
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    // This callback is invoked when the Speech Recognizer returns.
    // This is where you process the intent and extract the speech text from the intent.
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        final TextView speechText = findViewById((R.id.speechText));
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);
            // Do something with spokenText

            // Start of code by Griffin Shaw
            convertedText = spokenText;

            // See what the user requested
            police = convertedText.contains("police");
            fire = convertedText.contains("fire");
            medical = convertedText.contains("medical");

            // Set text and include information parsed
            speechText.setText(spokenText + "\n police: " + police + "\n fire: " + fire + "\n medical: " + medical);


        }
        // Else if logic credited to Indragni Soft Solutions Youtube Tutorial
        else if (resultCode == RecognizerIntent.RESULT_AUDIO_ERROR){
            String spokenText = "Audio error";
            convertedText = spokenText;
            speechText.setText(spokenText);

        }
        else if ((resultCode == RecognizerIntent.RESULT_CLIENT_ERROR)){
            String spokenText = "Client error";
            convertedText = spokenText;
            speechText.setText(spokenText);

        }
        else if (resultCode == RecognizerIntent.RESULT_NETWORK_ERROR){
            String spokenText = "Network error";
            convertedText = spokenText;
            speechText.setText(spokenText);
        }
        else if (resultCode == RecognizerIntent.RESULT_NO_MATCH){
            String spokenText = "No Match error";
            convertedText = spokenText;
            speechText.setText(spokenText);
        }
        else if (resultCode == RecognizerIntent.RESULT_SERVER_ERROR){
            String spokenText = "Server error";
            convertedText = spokenText;
            speechText.setText(spokenText);
        }
        else if(resultCode == 0){
            String spokenText = "Recording Cancelled";
            convertedText = spokenText;
            speechText.setText(spokenText);
        }
        else {
            String spokenText = "Unknow error";
            convertedText = spokenText;
            speechText.setText(spokenText);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    /* End of code taken from https://developer.android.com/training/wearables/apps/voice.html */

}
