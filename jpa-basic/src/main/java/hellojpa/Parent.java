package hellojpa;

import javax.persistence.*;
import javax.swing.text.AbstractWriter;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Child> childList = new ArrayList<>();

    public void addChild(Child child) {
        this.childList.add(child);
        child.setParent(this);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Child> getChildList() {
        return childList;
    }
}
