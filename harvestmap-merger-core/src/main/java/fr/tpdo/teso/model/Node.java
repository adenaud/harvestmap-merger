package fr.tpdo.teso.model;

import javax.persistence.*;

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

    private String zone;

    private int type;

    private String x;
    private String y;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
}
