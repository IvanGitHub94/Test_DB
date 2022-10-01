package testdb.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import testdb.model.TableRecord;
import testdb.model.User;
import testdb.service.FileService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TableRecordRepositoryFileImpl implements TableRecordRepository{

    private final ObjectMapper mapper = new ObjectMapper();
    private static final Path RECORDINGS_DIRECTORY_PATH = Paths.get(System.getProperty("user.home"), ".test",  "users_data");

    @Override
    public List<TableRecord> findAllUserRecords(User user) {
        Path userRecordingsPath = Paths.get(RECORDINGS_DIRECTORY_PATH.toString(), user.getLogin() + "_" + user.getRegistrationTime() + ".json");
        File userRecordingsFile = userRecordingsPath.toFile();
        if (!userRecordingsFile.exists()) {
            return new ArrayList<>();
        }
        TableRecord[] userRecordings = new TableRecord[]{};
        try {
            userRecordings = mapper.readValue(userRecordingsFile, TableRecord[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(Arrays.asList(userRecordings));
    }

    @Override
    public void saveAllUserRecords(User user, List<TableRecord> userRecordings) {
        String userRecordingsFileName = user.getLogin() + "_" + user.getRegistrationTime() + ".json";
        Path userRecordingsFilePath = FileService.createFileIfNotExists(RECORDINGS_DIRECTORY_PATH.toString(), userRecordingsFileName);

        TableRecord[] recordingsToSave = userRecordings.toArray(new TableRecord[0]);

        try {
            mapper.writeValue(userRecordingsFilePath.toFile(), recordingsToSave);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
