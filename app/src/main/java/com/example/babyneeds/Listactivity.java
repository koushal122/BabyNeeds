package com.example.babyneeds;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.babyneeds.data.ArrayAdapter;
import com.example.babyneeds.modal.Item;
import com.example.babyneeds.modal.ItemRepository;
import com.example.babyneeds.modal.Modalclass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Listactivity extends AppCompatActivity {


    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private EditText nametext;
    private EditText quantitytext;
    private EditText colortext;
    private EditText sizetext;
    Button savebutton;
    Modalclass modalclass;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listactivity);
        floatingActionButton = findViewById(R.id.addIteminRecycler);

        RecyclerView recyclerView = findViewById(R.id.RecylerviewList);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        modalclass = new ViewModelProvider.AndroidViewModelFactory(Listactivity.this.getApplication()).create(Modalclass.class);
        modalclass.getAllItem().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {

                ArrayAdapter arrayAdapter = new ArrayAdapter(items, Listactivity.this);
                recyclerView.setAdapter(arrayAdapter);

            }
        });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(Listactivity.this);
                View view = getLayoutInflater().inflate(R.layout.popup, null);
                nametext = view.findViewById(R.id.Entername);
                quantitytext = view.findViewById(R.id.EnterQuantitiy);
                colortext = view.findViewById(R.id.EnterColor);
                sizetext = view.findViewById(R.id.EnterSize);

                savebutton = view.findViewById(R.id.savebutton);

                savebutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ItemRepository itemRepository = new ItemRepository(Listactivity.this);
                        Item item = new Item();
                        item.setItemname(String.valueOf(nametext.getText()));
                        item.setItemQuantity(Integer.parseInt(quantitytext.getText().toString().trim()));
                        item.setItemcolor(String.valueOf(colortext.getText()));
                        item.setItemsize(Integer.parseInt(sizetext.getText().toString().trim()));
                        itemRepository.Insert(item);
                        startActivity(new Intent(Listactivity.this, Listactivity.class));
                    }
                });

                builder.setView(view);
                dialog = builder.create();
                dialog.show();

            }
        });
    }


    @Override
    protected void onPause() {
        finish();
        super.onPause();
    }
}