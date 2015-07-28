package fr.tpdo.teso.service;

import fr.tpdo.teso.dao.NodeDao;
import fr.tpdo.teso.model.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NodeService {

    @Autowired
    private NodeDao nodeDao;

    public List<Node> getAllNodes() {
        return nodeDao.findAll();
    }


    public void saveAll(List<Node> nodes) {
        nodeDao.save(nodes);
    }

    public List<Node> checkDuplicate(List<Node> nodes) {
        List<Node> validNodes = new ArrayList<>();
        for (Node node : nodes){
            if(nodeDao.count(node.getX(),node.getY(),node.getZone(),node.getCategory()) == 0){
                validNodes.add(node);
            }
        }
        return validNodes;
    }
}
