package com.example.babyneeds;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.babyneeds.data.RoomdatabaseClass;
import com.example.babyneeds.modal.Item;
import com.example.babyneeds.modal.ItemDao;
import com.example.babyneeds.modal.ItemRepository;
import com.example.babyneeds.modal.Modalclass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class MainActivity extends AppCompatActivity {

    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private EditText nametext;
    private EditText quantitytext;
    private EditText colortext;
    private EditText sizetext;
    Button savebutton;
    Modalclass modalclass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        modalclass = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this.getApplication()).create(Modalclass.class);
        if (modalclass.getAllItem() != null) {
            setContentView(R.layout.activity_listactivity);
            startActivity(new Intent(MainActivity.this, Listactivity.class));
        } else {
            setContentView(R.layout.activity_main);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            FloatingActionButton fab = findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CreatePopupDialog();
                }
            });
        }
    }

    private void saveItem() {

    }

    private void CreatePopupDialog() {

        builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.popup, null);
        nametext = view.findViewById(R.id.Entername);
        quantitytext = view.findViewById(R.id.EnterQuantitiy);
        colortext = view.findViewById(R.id.EnterColor);
        sizetext = view.findViewById(R.id.EnterSize);

        savebutton = view.findViewById(R.id.savebutton);

        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemRepository itemRepository = new ItemRepository(MainActivity.this);
                Item item = new Item();
                item.setItemname(String.valueOf(nametext.getText()));
                item.setItemQuantity(Integer.parseInt(quantitytext.getText().toString().trim()));
                item.setItemcolor(String.valueOf(colortext.getText()));
                item.setItemsize(Integer.parseInt(sizetext.getText().toString().trim()));
                itemRepository.Insert(item);
                startActivity(new Intent(MainActivity.this, Listactivity.class));
            }
        });

        builder.setView(view);
        dialog = builder.create();
        dialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onPause() {
        finish();
        super.onPause();
    }
}