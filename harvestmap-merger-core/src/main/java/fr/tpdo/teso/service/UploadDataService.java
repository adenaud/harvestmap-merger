package fr.tpdo.teso.service;

import fr.tpdo.teso.dao.UploadDataDao;
import fr.tpdo.teso.merger.MapMerger;
import fr.tpdo.teso.model.UploadData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class UploadDataService {

    @Autowired
    private UploadDataDao uploadDataDao;

    public void add(int dataImportedCount, String lua) {

        if (dataImportedCount > 0) {
            UploadData data = new UploadData();
            data.setDataImportedCount(dataImportedCount);
            data.setDate(new Timestamp(new java.util.Date().getTime()));
            data.setUploader(MapMerger.getUserNames(lua));

            uploadDataDao.save(data);
        }
    }
}
