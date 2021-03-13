package sf.Q5_31;

import java.util.Scanner;

public class Q05 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String[] str = new String[2];
            for(int i=0;i<2;i++){
                str[i] = sc.next();
            }

            int[][] map = new int[str[0].length()+1][str[1].length()+1];
            for(int i=0;i<=str[0].length();i++){
                for(int j=0;j<=str[1].length();j++){
                    if(i == 0||j == 0){
                        map[i][j] = 0;
                        continue;
                    }
                    if(str[0].charAt(i-1) == str[1].charAt(j-1)){
                        map[i][j] = map[i-1][j-1]+1;
                    }else{
                        map[i][j] = Math.max(map[i-1][j],map[i][j-1]);
                    }
                }
            }
            System.out.println(map[str[0].length()][str[1].length()]);
        }
    }
}
