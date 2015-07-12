package fr.tpdo.teso.service;

import fr.tpdo.teso.dao.NodeDao;
import fr.tpdo.teso.model.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Anthony on 19/06/2015.
 */
@Service
public class NodeService {

    @Autowired
    private NodeDao nodeDao;

    public List<Node> getAllNodes() {
        return nodeDao.findAll();
    }


    public int saveAll(List<Node> nodes) {
        int count = 0;
        for (Node node : nodes){
            if(nodeDao.count(node.getX(),node.getY(),node.getZone(),node.getCategory()) == 0){
                nodeDao.save(node);
                count++;
            }
        }
        return count;
    }
}
