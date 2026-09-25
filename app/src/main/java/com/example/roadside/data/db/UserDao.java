package com.example.roadside.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roadside.data.models.User;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
    LiveData<User> getUser(String userId);

    @Query("SELECT * FROM users WHERE phoneNumber = :phone LIMIT 1")
    User getUserByPhone(String phone);

    @Query("SELECT * FROM users LIMIT 1")
    LiveData<User> getCurrentUser();

    @Query("DELETE FROM users")
    void clear();
}
