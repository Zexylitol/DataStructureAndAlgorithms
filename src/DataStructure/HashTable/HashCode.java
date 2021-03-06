package DataStructure.HashTable;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Java中的HashCode
 * @author yzze
 * @create 2020-05-18 20:38
 */
public class HashCode {
    public  static void main(String[] args) {
        int a = 42;
        System.out.println(((Integer)a).hashCode());

        int b = -42;
        System.out.println(((Integer)b).hashCode());

        double c = 3.1415926;
        System.out.println(((Double)c).hashCode());

        String d = "imooc";
        System.out.println((d).hashCode());

        Student student = new Student(3,2,"bobo","liu");
        System.out.println(student.hashCode());

        HashSet<Student> set = new HashSet<>();
        set.add(student);

        HashMap<Student, Integer> scores = new HashMap<>();
        scores.put(student, 100);
    }
}
