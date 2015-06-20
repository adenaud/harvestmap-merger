package fr.tpdo.teso;

import fr.tpdo.teso.dao.NodeDao;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

/**
 * Created by Anthony on 20/06/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/testContext.xml")
@Transactional
public class NodeDaoTest extends TestCase{

    @Autowired
    private NodeDao nodeDao;

    @Test
    public void test(){
        nodeDao.count(0.69,0.52,"glenumbra/glenumbra_base",8);
    }

}
