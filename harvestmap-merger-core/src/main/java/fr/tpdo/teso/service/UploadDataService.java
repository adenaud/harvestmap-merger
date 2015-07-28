package fr.tpdo.teso.service;

import fr.tpdo.teso.dao.UploadDataDao;
import fr.tpdo.teso.model.Node;
import fr.tpdo.teso.model.UploadData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class UploadDataService {

    @Autowired
    private UploadDataDao uploadDataDao;

    public UploadData add(List<Node> nodes, String userName) {

        UploadData data = new UploadData();
        data.setDataImportedCount(nodes.size());
        data.setDate(new Timestamp(new java.util.Date().getTime()));
        data.setUploader(userName);
        data.setNodes(nodes);

        for (Node node : nodes){
            node.setUploadData(data);
        }

        return uploadDataDao.save(data);
    }
}
