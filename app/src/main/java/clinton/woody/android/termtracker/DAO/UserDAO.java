package clinton.woody.android.termtracker.DAO;

import clinton.woody.android.termtracker.Entity.User;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM users ORDER BY userID ASC")
    List<User> getAllUsers();

    @Query("SELECT * FROM users WHERE userName LIKE :search")
    User getUser(String search);

    @Query("SELECT * FROM users WHERE userName LIKE :search")
    List<User> searchName(String search);

    @Query("SELECT * FROM users WHERE enabled IS :search")
    List<User> searchEnabled(Boolean search);

    @Query("SELECT * FROM users ORDER BY lastLogin DESC")
    List<User> report();
}
