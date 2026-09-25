package com.example.roadside.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roadside.data.models.Request;

import java.util.List;

@Dao
public interface RequestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Request request);

    @Update
    void update(Request request);

    @Query("SELECT * FROM requests WHERE id = :requestId LIMIT 1")
    LiveData<Request> getRequest(String requestId);

    @Query("SELECT * FROM requests WHERE id = :requestId LIMIT 1")
    LiveData<Request> getRequestById(int requestId);

    @Query("SELECT * FROM requests WHERE id = :requestId LIMIT 1")
    Request getSyncRequestById(int requestId);

    @Query("SELECT * FROM requests ORDER BY createdAt DESC")
    LiveData<List<Request>> getAllRequests();

    @Query("SELECT * FROM requests WHERE status = :status ORDER BY createdAt DESC")
    LiveData<List<Request>> getRequestsByStatus(String status);

    @Query("SELECT * FROM requests WHERE userId = :userId ORDER BY createdAt DESC")
    LiveData<List<Request>> getRequestsByUserId(int userId);

    @Query("SELECT * FROM requests WHERE userId = :userId ORDER BY createdAt DESC")
    List<Request> getSyncRequestsByUserId(int userId);
}
