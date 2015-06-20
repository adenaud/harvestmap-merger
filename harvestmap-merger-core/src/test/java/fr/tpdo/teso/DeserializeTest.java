package fr.tpdo.teso;

import fr.tpdo.teso.merger.AceDeserializer;
import fr.tpdo.teso.model.Node;
import junit.framework.TestCase;
import junit.framework.TestResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

/**
 * Created by Anthony on 20/06/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/testContext.xml")
@Transactional
public class DeserializeTest extends TestCase {

    @Test
    public void test(){
        Node node = AceDeserializer.Deserialize("^1^T^N1^F6365733701287936^f-54^N2^F5890980901289984^f-53^N3^T^N1^SMinerai~`de~`fer^t^N4^N808^t^^");
        assertEquals(0.3533692,node.getX());
        assertEquals(0.65403026,node.getY());
    }
}
