package fr.dawan.cfa2022.entities;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@DiscriminatorColumn(name = "EMP_TYPE")
public class Employee {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                        ", name='" + name + '\'';
    }
}
