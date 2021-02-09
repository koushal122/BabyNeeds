package com.example.babyneeds.modal;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.babyneeds.data.RoomdatabaseClass;

import java.util.List;

public class ItemRepository {

    LiveData<List<Item>> allItem;
    ItemDao itemDao;
    RoomdatabaseClass db;

    public ItemRepository(Context context) {

        db = RoomdatabaseClass.getRoomDatabase(context);
        itemDao = db.itemDao();
        allItem = itemDao.getAllitem();

    }

    public void Insert(Item item) {
        RoomdatabaseClass.executorService.execute(() -> itemDao.insert(item));
    }

    public void delete(Item item) {

        RoomdatabaseClass.executorService.execute(() -> itemDao.delete(item));

    }

    public void update(Item item) {

        RoomdatabaseClass.executorService.execute(new Runnable() {
            @Override
            public void run() {
                itemDao.update(item);
            }
        });
    }

    public LiveData<Item> getItem(int id) {
        return itemDao.getItem(id);
    }

    public LiveData<List<Item>> getAllItem() {
        return allItem;
    }


}
