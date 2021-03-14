package fall.tencent;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * ClassName: Q12
 * Description:
 * date: 2020/9/6 19:51
 *
 * @author :涔岄甫鍧愰鏈轰籂
 * @version:
 */
public class Q12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        Person[] persons = new Person[n];
        for (int i = 0; i < n; i++) persons[i] = new Person();
        Team[] teams = new Team[m];
        for (int i = 0; i < m; i++) teams[i] = new Team();
        for (int i = 0; i < m; i++) {
            int num = sc.nextInt();
            for (int j = 0; j < num; j++) {
                int id = sc.nextInt();
                persons[id].list.add(teams[i]);
                teams[i].list.add(persons[id]);
            }
        }

        int before = 0;
        Set<Team> set = new HashSet<>(persons[0].list);
        while (set.size() != before) {
            Set<Team> temp = new HashSet<>();
            before = set.size();
            for (Team t : set) {
                Iterator<Person> it = t.list.iterator();
                while (it.hasNext()) {
                    for (Team tempT : it.next().list)
                        temp.add(tempT);
                }
            }

            set.addAll(temp);
        }

        Set<Person> pset = new HashSet<>();
        for (
                Team t : set) {
            pset.addAll(t.list);
        }
        System.out.println(pset.size());
        sc.close();
    }
}

class Person {
    Set<Team> list = new HashSet<>();
}

class Team {
    Set<Person> list = new HashSet<>();
}
