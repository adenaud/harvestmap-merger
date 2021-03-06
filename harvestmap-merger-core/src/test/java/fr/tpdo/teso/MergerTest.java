package fr.tpdo.teso;

import fr.tpdo.teso.merger.MapMerger;
import fr.tpdo.teso.model.Node;
import junit.framework.TestCase;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/testContext.xml")
@Transactional
public class MergerTest extends TestCase {

    @Test
    public void testReadWrite() {

        try {
            InputStream luaStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("HarvestMap.lua");
            String luaString = IOUtils.toString(luaStream);

            JSONObject jsonObject = MapMerger.toJson(luaString);
            List<Node> refList = MapMerger.getNodes(luaString);
            assertTrue(refList.size() > 0);
            System.out.println(refList.size());

            String lua = MapMerger.toLua(jsonObject);

            IOUtils.write(lua,new FileOutputStream("HarvestMap.out.lua"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getNamesTest() throws IOException {
        InputStream luaStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("HarvestMap.lua");
        String luaString = IOUtils.toString(luaStream);
        System.out.println(MapMerger.getUserNames(luaString));
    }
}
