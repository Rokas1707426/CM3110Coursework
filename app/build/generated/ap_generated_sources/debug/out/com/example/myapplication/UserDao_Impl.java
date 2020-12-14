package com.example.myapplication;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<tableData> __insertionAdapterOftableData;

  private final EntityDeletionOrUpdateAdapter<tableData> __deletionAdapterOftableData;

  public UserDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOftableData = new EntityInsertionAdapter<tableData>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `tableData` (`id`,`data`,`playerData`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, tableData value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getPlayerData() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPlayerData());
        }
      }
    };
    this.__deletionAdapterOftableData = new EntityDeletionOrUpdateAdapter<tableData>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `tableData` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, tableData value) {
        stmt.bindLong(1, value.getId());
      }
    };
  }

  @Override
  public void insert(final tableData tableData) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOftableData.insert(tableData);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final tableData tableData) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOftableData.handle(tableData);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<tableData> getAll() {
    final String _sql = "SELECT * FROM tableData";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "data");
      final int _cursorIndexOfPlayerData = CursorUtil.getColumnIndexOrThrow(_cursor, "playerData");
      final List<tableData> _result = new ArrayList<tableData>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final tableData _item;
        _item = new tableData();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final String _tmpPlayerData;
        _tmpPlayerData = _cursor.getString(_cursorIndexOfPlayerData);
        _item.setPlayerData(_tmpPlayerData);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
