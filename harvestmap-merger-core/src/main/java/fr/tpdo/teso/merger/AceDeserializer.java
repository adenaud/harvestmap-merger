package fr.tpdo.teso.merger;

import fr.tpdo.teso.model.Node;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Anthony on 20/06/2015.
 */
public class AceDeserializer {

    public static Node Deserialize(String serialzedData){


        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("main.lua");

        Globals  globals = JsePlatform.standardGlobals();
        LuaValue chunk = globals.load(new InputStreamReader(inputStream),"main.lua");
        Node node = new Node(serialzedData);

        try{
            LuaValue result = chunk.call(LuaValue.valueOf(serialzedData));

            String[] values = result.tojstring().split(",");
            double x = Double.parseDouble(values[0]);
            double y = Double.parseDouble(values[1]);
            String description = values[2];

            node.setX(x);
            node.setY(y);
            node.setDescription(description);
        }catch (LuaError error){
            System.out.println("error deserialize : " + serialzedData);
        }
        return node;
    }
}
