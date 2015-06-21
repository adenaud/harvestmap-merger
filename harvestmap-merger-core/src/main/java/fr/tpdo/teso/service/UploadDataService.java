package fr.tpdo.teso.service;

import fr.tpdo.teso.dao.UploadDataDao;
import fr.tpdo.teso.merger.MapMerger;
import fr.tpdo.teso.model.UploadData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by Anthony on 21/06/2015.
 */
@Service
public class UploadDataService {

    @Autowired
    private UploadDataDao uploadDataDao;

    public void add(int dataImportedCount,String lua){
        UploadData data = new UploadData();
        data.setDataImportedCount(dataImportedCount);
        data.setDate(new Timestamp(new java.util.Date().getTime()));
        data.setUploader(MapMerger.getUserNames(lua));

        uploadDataDao.save(data);
    }
}
