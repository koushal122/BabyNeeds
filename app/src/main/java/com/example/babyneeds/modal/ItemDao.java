package com.example.babyneeds.modal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ItemDao {

    @Query("SELECT * FROM itemtable")
    public LiveData<List<Item>> getAllitem();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(Item item);

    @Update
    public void update(Item item);

    @Delete
    public  void delete(Item item);

    @Query("SELECT * FROM itemtable WHERE id == :id")
    public LiveData<Item> getItem(int id);

}
