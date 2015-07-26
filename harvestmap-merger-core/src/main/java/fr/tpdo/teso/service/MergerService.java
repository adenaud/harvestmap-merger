package fr.tpdo.teso.service;

import fr.tpdo.teso.ReadException;
import fr.tpdo.teso.dao.NodeDao;
import fr.tpdo.teso.merger.MapMerger;
import fr.tpdo.teso.model.Node;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MergerService {

    private static Logger logger = LoggerFactory.getLogger(MergerService.class);

    @Autowired
    private NodeDao nodeDao;

    public List<Node> getNodes(String lua){

        List<Node> nodes;

        try {
            nodes= MapMerger.getNodes(lua);
        } catch (JSONException e) {
            throw new ReadException("Impossible de lire le fichier HarvestMap.lua",e);
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
        } catch (JSONException e) {
            throw new ReadException("Impossible de lire le fichier HarvestMap.lua",e);
        }

        JSONObject root = new JSONObject();
        root.put("Default",defaultObject);

        return MapMerger.toLua(root);
    }
}
