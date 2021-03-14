package kuaishou;

public class Q04 {
    public static void main(String[] args) {
        char[][] pos = new char[3][5];
        pos[0][0] = '*';
        pos[0][1] = '.';
        pos[0][2] = '*';
        pos[0][3] = '*';
        pos[0][4] = '.';
        pos[1][0] = '*';
        pos[1][1] = '.';
        pos[1][2] = '*';
        pos[1][3] = '*';
        pos[1][4] = '*';
        pos[2][0] = '*';
        pos[2][1] = '.';
        pos[2][2] = '*';
        pos[2][3] = '*';
        pos[2][4] = '.';
        System.out.println(GetMaxStaffs(pos));
    }

    public static int GetMaxStaffs(char[][] pos) {
        Location all[][] = new Location[pos.length][pos[0].length];
        for (int i = 0; i < pos.length; i++) {
            for (int j = 0; j < pos[0].length; j++) {
                if (pos[i][j] == '.') {
                    all[i][j] = new Location();
                }
            }
        }
        for (int i = 0; i < pos.length; i++) {
            for (int j = 0; j < pos[0].length; j++) {
                if (pos[i][j] == '.') {
                    if (i > 0) all[i][j].up = all[i - 1][j];
                    if (i < pos.length - 1) all[i][j].down = all[i + 1][j];
                    if (j > 0) all[i][j].left = all[i][j - 1];
                    if (j < pos[0].length - 1) all[i][j].right = all[i][j + 1];
                }
            }
        }
        int num = 0;
        for (int i = 0; i < pos.length; i++) {
            for (int j = 0; j < pos[0].length; j++) {
                if (all[i][j] != null && all[i][j].isEmpty()) num++;
                else if (all[i][j] != null) num += (int) (Math.random() + 0.5);
            }
        }


        return num;
    }
}

class Location {
    Location left;
    Location right;
    Location up;
    Location down;

    public boolean isEmpty() {
        return (left == null && right == null && up == null && down == null);
    }
}
