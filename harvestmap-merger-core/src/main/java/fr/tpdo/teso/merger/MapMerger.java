package fr.tpdo.teso.merger;


import fr.tpdo.teso.model.Node;
import org.json.JSONObject;
import org.luaj.vm2.ast.Str;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anthony on 13/06/2015.
 */
public class MapMerger {

    /**
     * Read the HarvestMap.lua file and convert it in JSON format.
     * @param lua HarvestMap.lua content
     * @return
     */
    public static JSONObject toJson(String lua) throws IOException {
        lua = lua.substring(lua.indexOf('\n')+1);
        lua = lua.replace(" -- invalid value type [function] used", "");
        lua = lua.replace(" =", " :");
        lua = lua.replace("nil,", "\"null\",");
        lua = lua.replaceAll("\\[\"?([^]^\"]*)\"?\\]","\"$1\"");
        lua = lua.replaceAll(",\\r?\\n([ ]*)\\}","\n$1}");

        return new JSONObject(lua);
    }

    public static String toLua(JSONObject object){

        StringBuilder builder = new StringBuilder();
        builder.append("Harvest_SavedVars =");
        builder.append(object.toString(4));
        String lua = builder.toString();

        lua = lua.replace(" : ", " = ");
        lua = lua.replaceAll("\"([^\"]*)\":","[\"$1\"] =");
        lua = lua.replaceAll("\\[\"([0-9]*)\"\\]","[$1]");
        lua = lua.replaceAll("\n([ ]*)}",",\n$1}");
        lua = lua.replace("\"null\",", "nil, -- invalid value type [function] used");

        return lua;
    }

    /**
     * Read the HarvestMap.lua file and convert it to a node List.
     * @param lua HarvestMap.lua content
     * @return the node List
     */
    public static List<Node> getNodes(String lua) throws IOException {

        List<Node> nodes = new ArrayList<Node>();

        JSONObject defaultObject = toJson(lua).getJSONObject("Default");

        for(String account : defaultObject.keySet()){

            JSONObject accountObject = defaultObject.getJSONObject(account);

            JSONObject accountWideObject = accountObject.getJSONObject("$AccountWide");
            JSONObject zones = accountWideObject.getJSONObject("nodes").getJSONObject("data");

            for(String zoneKey :  zones.keySet()){
                JSONObject zone = zones.getJSONObject(zoneKey);
                for (String typeKey : zone.keySet()){
                    for(String dataKey : zone.getJSONObject(typeKey).keySet()){
                        String data = zone.getJSONObject(typeKey).getString(dataKey);


                        Node node = AceDeserializer.Deserialize(data);
                        node.setType(Integer.parseInt(typeKey));
                        node.setZone(zoneKey);
                        nodes.add(node);
                    }
                }
            }
        }


        return nodes;
    }
}
