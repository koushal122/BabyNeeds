package com.example.babyneeds.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.babyneeds.modal.Item;
import com.example.babyneeds.modal.ItemDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Item.class}, version = 1)
public abstract class RoomdatabaseClass  extends RoomDatabase {

    public abstract ItemDao itemDao();

    public static  ExecutorService executorService= Executors.newFixedThreadPool(3);
     static RoomdatabaseClass Instance;

    public static  RoomdatabaseClass getRoomDatabase(Context context)
    {
        if(Instance==null)
        {
            synchronized (RoomdatabaseClass.class){
                if(Instance==null)
                {
                    Instance = Room.databaseBuilder(context.getApplicationContext(),
                            RoomdatabaseClass.class, "database-name").build();
                }
            }
        }
        return Instance;
    }


}
