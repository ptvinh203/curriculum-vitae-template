package model.dto;

import java.io.Serializable;

import model.bean.Wife;

public class WifeDTO implements Serializable {
    private int id;
    public int getId() {
        return id;
    }
    private String name;
    private String address;
    private boolean alive;

    public WifeDTO(int id, String name, String address, boolean alive) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.alive = alive;
    }

    public static WifeDTO fromBean(Wife wife) {
        return new WifeDTO(
            wife.getId(),
            wife.getName(),
            wife.getAddress(),
            wife.isAlive()
        );
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public boolean isAlive() {
        return alive;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
