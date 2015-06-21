package fr.tpdo.teso.dao;

import fr.tpdo.teso.model.UploadData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Anthony on 21/06/2015.
 */
public interface UploadDataDao extends JpaRepository<UploadData, Long> {
}
