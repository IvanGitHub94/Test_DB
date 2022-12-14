package testdb.service;

import javafx.collections.ObservableList;
import testdb.model.TableRecord;
import testdb.model.User;
import testdb.repository.TableRecordRepository;
import testdb.repository.TableRecordRepositoryFileImpl;

import java.util.List;

public class TableRecordService {
    private static TableRecordRepository tableRecordRepository = new TableRecordRepositoryFileImpl();


    public void deleteRecord(ObservableList<TableRecord> userRecordings, int selectedNumberRow) {
        userRecordings.remove(selectedNumberRow, selectedNumberRow + 1);
    }

    public List<TableRecord> findAllUserRecordings(User user) {
        return tableRecordRepository.findAllUserRecords(user);
    }


    public void saveAllUserRecords(User currentUser, ObservableList<TableRecord> userRecordings) {
        tableRecordRepository.saveAllUserRecords(currentUser, userRecordings);
    }
}
