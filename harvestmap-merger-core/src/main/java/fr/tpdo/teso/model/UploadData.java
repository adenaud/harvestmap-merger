package fr.tpdo.teso.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "upload_datas")
public class UploadData {

    @Id
    @SequenceGenerator(name = "UPLOAD_SEQ_GEN", sequenceName = "SQ_UPLOAD", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UPLOAD_SEQ_GEN")
    private Long id;
    private Timestamp time;
    private String uploader;
    private int dataImportedCount;

    @OneToMany(mappedBy = "uploadData",cascade = CascadeType.PERSIST)
    private List<Node> nodes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return time;
    }

    public void setDate(Timestamp time) {
        this.time = time;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public int getDataImportedCount() {
        return dataImportedCount;
    }

    public void setDataImportedCount(int dataImportedCount) {
        this.dataImportedCount = dataImportedCount;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
}
