package fr.tpdo.teso.service;

import fr.tpdo.teso.merger.MapMerger;
import fr.tpdo.teso.model.Node;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anthony on 19/06/2015.
 */
@Service
public class MergerService {

    public List<Node> getNodes(String lua){

        List<Node> nodes = new ArrayList<>();

        try {
            nodes= MapMerger.getNodes(lua);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nodes;
    }

}
