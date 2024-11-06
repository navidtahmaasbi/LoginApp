package com.example.loginapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginapp.R;
import com.example.loginapp.models.Card;

public class CardActivity extends AppCompatActivity {
    private TextView textViewCardTitle, textViewCardDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);


        // Initialize views
        textViewCardTitle = findViewById(R.id.textViewCardTitle);
        textViewCardDescription = findViewById(R.id.textViewCardDescription);

        //Retrieve data passed from the previous activity (card)
        Intent intent = getIntent();
        Card selectedCard = (Card) intent.getSerializableExtra("card_data");

        //Display card data
        if (selectedCard != null){
            textViewCardTitle.setText(selectedCard.getTitle());
            textViewCardDescription.setText(selectedCard.getDescription());
        } else {
            Toast.makeText(this, "No card data found!", Toast.LENGTH_SHORT).show();

        }
    }

}
