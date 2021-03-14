package cloneTest;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: ListClone
 * Description:
 * date: 2020/7/11 10:34
 *
 * @author :乌鸦坐飞机亠
 * @version:
 */
public class ListClone {
    public static void main(String[] args) {
        new ListClone().classClone();
    }

    //常量克隆
    public void finalClone(){
        ArrayList<Integer> list=new ArrayList<>();
        list.add(1);
        list.add(2);
        ArrayList<Integer> list2= (ArrayList<Integer>) list.clone();
        list2.remove(1);
        System.out.println(list.size());
        System.out.println(list2.size());
    }
    //变量克隆
    public void classClone(){
        ArrayList<StudentClone> list=new ArrayList<>();
        list.add(new StudentClone(10));
        list.add(new StudentClone(20));
        ArrayList<StudentClone> list2= (ArrayList<StudentClone>) list.clone();
        list2.get(0).setAge(80);
        System.out.println(list.get(0).getAge());
        System.out.println(list2.get(0).getAge());
    }
}
class StudentClone{
    private int age=10;

    public StudentClone(int age) {
        this.age = age;
    }

    public void setAge(int age){
        this.age=age;
    }
    public int getAge(){
        return age;
    }
}
