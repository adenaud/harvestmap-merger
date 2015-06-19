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

    public void save(Node node) {
        nodeDao.save(node);
    }

    public void importNodes(List<Node> nodes) {

        for (Node node : nodes) {

            nodeDao.save(node);
        }
    }
}
