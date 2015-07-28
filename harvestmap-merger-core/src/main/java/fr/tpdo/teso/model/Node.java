package fr.tpdo.teso.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Anthony on 17/06/2015.
 */
@Entity
@Table(name = "nodes")
public class Node {

    @Id
    @SequenceGenerator(name = "NODE_SEQ_GEN", sequenceName = "SQ_NODE", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NODE_SEQ_GEN")
    private Long id;
    private String data;
    private String description;
    private int category;
    private double x;
    private double y;
    private String zone;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_upload")
    private UploadData uploadData;

    public Node() {
    }

    public Node(String data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int type) {
        this.category = type;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UploadData getUploadData() {
        return uploadData;
    }

    public void setUploadData(UploadData uploadData) {
        this.uploadData = uploadData;
    }
}
