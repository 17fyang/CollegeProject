package express;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/6/22 13:00
 * @Description:
 */
public enum Province {
    GUANGDONG(1, "广东省"),
    FUJIAN(2, "福建省"),
    BEIJING(3, "北京市");


    private final int id;
    private final String name;

    Province(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Province getProvinceById(int id) {
        for (Province p : Province.values()) {
            if (p.id == id) return p;
        }
        throw new RuntimeException("illegal province id");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
