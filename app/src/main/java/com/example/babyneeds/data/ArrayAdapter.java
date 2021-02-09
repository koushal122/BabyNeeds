package com.example.babyneeds.data;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.menu.MenuView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.babyneeds.Listactivity;
import com.example.babyneeds.MainActivity;
import com.example.babyneeds.R;
import com.example.babyneeds.modal.Item;
import com.example.babyneeds.modal.ItemRepository;
import com.example.babyneeds.modal.Modalclass;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class ArrayAdapter extends RecyclerView.Adapter<ArrayAdapter.ViewHolder> {

    private List<Item> Itemlist;
    Context context;


    public ArrayAdapter(List<Item> itemlist, Context context) {
        Itemlist = new ArrayList<>();
        this.context = context;
        Itemlist = itemlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.text_row_item, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(String.format("Name :-%s", Itemlist.get(position).getItemname()));
        holder.quantity.setText(String.format("Quantity :- %s", String.valueOf(Itemlist.get(position).getItemQuantity())));
        holder.color.setText(String.format("Color :- %s", Itemlist.get(position).getItemcolor()));
        holder.size.setText(String.format("Size :- %s", String.valueOf(Itemlist.get(position).getItemsize())));

    }

    @Override
    public int getItemCount() {

        return Math.max(Itemlist.size(), 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView quantity;
        public TextView color;
        public TextView size;
        public int id;
        public ImageButton updateButton;
        private ImageButton deleteButton;
        public Modalclass modalclass;
        Context context;
        private AlertDialog.Builder builder;
        private AlertDialog dialog;
        public LayoutInflater inflate;
        private EditText nametext;
        private EditText quantitytext;
        private EditText colortext;
        private EditText sizetext;
        Button savebutton;


        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            quantity = itemView.findViewById(R.id.quantity);
            color = itemView.findViewById(R.id.color);
            size = itemView.findViewById(R.id.size);
            this.context = context;
            updateButton = itemView.findViewById(R.id.updateImageButton);
            deleteButton = itemView.findViewById(R.id.DeleteImagebutton);
            deleteButton.setOnClickListener(this);
            updateButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.DeleteImagebutton:
                    builder = new AlertDialog.Builder(context);
                    inflate = LayoutInflater.from(context);
                    View view = inflate.inflate(R.layout.confirmationpopup, null);
                    Button yesButton = view.findViewById(R.id.Yesbutton);
                    Button noButton = view.findViewById(R.id.Nobutton);
                    builder.setView(view);
                    dialog = builder.create();
                    dialog.show();
                    yesButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            int position = getAdapterPosition();
                            Item item = Itemlist.get(position);
                            Modalclass.delete(item);
                            dialog.dismiss();

                        }
                    });
                    noButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();

                        }
                    });

                    break;
                case R.id.updateImageButton:
                    createpoupDialog();

            }
        }

        @SuppressLint("SetTextI18n")
        private void createpoupDialog() {

            builder = new AlertDialog.Builder(context);
            inflate = LayoutInflater.from(context);
            View view = inflate.inflate(R.layout.popup, null);
            nametext = view.findViewById(R.id.Entername);
            quantitytext = view.findViewById(R.id.EnterQuantitiy);
            colortext = view.findViewById(R.id.EnterColor);
            sizetext = view.findViewById(R.id.EnterSize);
            int position = getAdapterPosition();
            Item item = Itemlist.get(position);
            int id = item.getId();
            nametext.setText(item.getItemname());
            quantitytext.setText(String.valueOf(item.getItemQuantity()));
            colortext.setText(item.getItemcolor());
            sizetext.setText(String.valueOf(item.getItemsize()));
            builder.setView(view);
            dialog = builder.create();
            dialog.show();

            savebutton = view.findViewById(R.id.savebutton);
            savebutton.setText("Update");

            savebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ItemRepository itemRepository = new ItemRepository(context);
                    Item item = new Item();
                    item.setId(id);
                    item.setItemname(String.valueOf(nametext.getText()));
                    item.setItemQuantity(Integer.parseInt(quantitytext.getText().toString().trim()));
                    item.setItemcolor(String.valueOf(colortext.getText()));
                    item.setItemsize(Integer.parseInt(sizetext.getText().toString().trim()));
                    itemRepository.update(item);
                    dialog.dismiss();
                }
            });


        }
    }

}
