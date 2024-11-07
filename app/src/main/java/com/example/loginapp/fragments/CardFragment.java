package com.example.loginapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.models.Card;
import com.example.loginapp.R;
import com.example.loginapp.adapters.CardAdapter;

import java.util.ArrayList;
import java.util.List;

public class CardFragment extends Fragment {
    private RecyclerView recyclerViewCards;
    private CardAdapter cardAdapter;
    private List<Card> cards;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card, container, false);

        recyclerViewCards = view.findViewById(R.id.recyclerViewCards);
        recyclerViewCards.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        cards = new ArrayList<>();
        // Add some initial data (replace with actual data from API)


        cardAdapter = new CardAdapter(cards);
        recyclerViewCards.setAdapter(cardAdapter);

        return view;
    }
}
