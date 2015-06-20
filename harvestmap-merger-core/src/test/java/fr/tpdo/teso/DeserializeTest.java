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
        Node node = AceDeserializer.Deserialize("^1^T^N1^F4927344858890240^f-53^N2^F6323799788093440^f-53^N3^T^N1^SFil~`d'ébonite^N2^SSoie~`d'araignée^t^N4^N23131^t^^");
        assertEquals(0.5470452,node.getX());
        assertEquals(0.7020828,node.getY());
    }
}
