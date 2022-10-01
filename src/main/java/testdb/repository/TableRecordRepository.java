package testdb.repository;

import testdb.model.TableRecord;
import testdb.model.User;

import java.util.List;

public interface TableRecordRepository {
    List<TableRecord> findAllUserRecords(User user);

    void saveAllUserRecords(User user, List<TableRecord> recordings);
}
