package com.example.lineage3.DataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.lineage3.RelationUser;

import java.util.List;

@Dao
public interface RelationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRelation(RelationUser relationUser);

    @Update
    void updateRelation(RelationUser relationUser);


    @Delete
    void deleteRelation(RelationUser relationUser);

    @Query("SELECT * FROM relations")
    LiveData<List<RelationUser>> getAllRelationLive();

    @Query("SELECT * FROM relations")
    List<RelationUser> getAllRelationFuture();

    @Query("SELECT * FROM relations WHERE rid=:id")
    RelationUser getRelation(int id);

}

