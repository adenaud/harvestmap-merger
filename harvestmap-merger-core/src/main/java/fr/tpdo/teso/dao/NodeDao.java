package fr.tpdo.teso.dao;

import fr.tpdo.teso.model.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Anthony on 19/06/2015.
 */
@Repository
public interface NodeDao extends JpaRepository<Node,Long> {

    @Query("SELECT count(n) FROM Node n WHERE abs(n.x-:x) < 0.001 AND  abs(n.y - :y) < 0.001 AND n.zone = :zone AND n.category =:type ")
    double count(@Param("x") double x, @Param("y") double y, @Param("zone") String zone, @Param("type") int type);

    @Query("SELECT DISTINCT n.zone FROM Node n")
    List<String> findAllZones();

    @Query("SELECT DISTINCT n.category FROM Node n")
    List<Integer> findAllType();

    @Query("SELECT n FROM Node n WHERE n.zone = :zone AND n.category =:type")
    List<Node> findByZoneAndType( @Param("type") int type, @Param("zone") String zone);
}
