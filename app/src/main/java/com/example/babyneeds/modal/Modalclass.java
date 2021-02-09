package com.example.babyneeds.modal;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class Modalclass  extends AndroidViewModel {
    public static ItemRepository itemRepository;
    public  final LiveData<List<Item>> allItem;
    public Modalclass(@NonNull Application application) {
        super(application);
        itemRepository=new ItemRepository(application);
        allItem=itemRepository.getAllItem();
    }

    public LiveData<List<Item>> getAllItem() {
        return allItem;
    }

    public static LiveData<Item> getContact(int id)
    {
        return itemRepository.getItem(id);
    }
    public static void insert(Item item)
    {
        itemRepository.Insert(item);
    }

    public static void update(Item item)
    {
        itemRepository.update(item);
    }

    public static void delete(Item item)
    {
        itemRepository.delete(item);
    }
}
