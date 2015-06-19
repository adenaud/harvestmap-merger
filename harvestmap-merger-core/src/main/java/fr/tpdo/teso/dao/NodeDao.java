package fr.tpdo.teso.dao;

import fr.tpdo.teso.model.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Anthony on 19/06/2015.
 */
@Repository
public interface NodeDao extends JpaRepository<Node,Long> {

}
