package com.miit.test.components;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement(name = "clothes")
@XmlType(propOrder = {"items"})
public class Clothes {

    private List<ClothesItem> items;

    @XmlElement(name = "item")
    public List<ClothesItem> getItems() {
        return items;
    }

    public void setItems(List<ClothesItem> items) {
        this.items = items;
    }
}
