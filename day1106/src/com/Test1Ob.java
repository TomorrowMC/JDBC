package com;

/**
 * @author Yifei.Hu
 * @create 2021-11--16:42
 */
public class Test1Ob {
    private int id=1;
    private String name="1";

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Test1Ob() {
    }

//    public Test1Ob(int id, String name) {
//        this.id = id;
//        this.name = name;
//    }

    @Override
    public String toString() {
        return "Test1Ob{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
