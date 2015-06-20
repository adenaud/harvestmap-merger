package fr.tpdo.teso.service;

import fr.tpdo.teso.dao.NodeDao;
import fr.tpdo.teso.merger.MapMerger;
import fr.tpdo.teso.model.Node;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anthony on 19/06/2015.
 */
@Service
public class MergerService {

    @Autowired
    private NodeDao nodeDao;

    public List<Node> getNodes(String lua){

        List<Node> nodes = new ArrayList<>();

        try {
            nodes= MapMerger.getNodes(lua);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nodes;
    }

    public String merge(String luaSource){

        JSONObject defaultObject = new JSONObject();
        try {
            defaultObject = MapMerger.toJson(luaSource).getJSONObject("Default");
            for(String account : defaultObject.keySet()) {
                JSONObject accountObject = defaultObject.getJSONObject(account);
                JSONObject accountWideObject = accountObject.getJSONObject("$AccountWide");
                accountWideObject.getJSONObject("nodes").remove("data");
                JSONObject data = new JSONObject();

                for(String zone : nodeDao.findAllZones()){
                    JSONObject zoneObject = new JSONObject();
                    for(Integer type : nodeDao.findAllType()){
                        JSONObject typeObject = new JSONObject();

                        int count = 0;
                        for(Node node : nodeDao.findByZoneAndType(type, zone)){
                            typeObject.put(String.valueOf(++count),node.getData());
                        }

                        zoneObject.put(String.valueOf(type), typeObject);
                    }

                    data.put(zone, zoneObject);
                }
                accountWideObject.getJSONObject("nodes").put("data", data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject root = new JSONObject();
        root.put("Default",defaultObject);

        return MapMerger.toLua(root);
    }
}
