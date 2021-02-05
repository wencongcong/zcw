package com.brokerage.enums;

public enum AreaEnum {

    HANG_ZHOU(571, "杭州市",613856447,"3301012107849"),
    JIN_HUA(579, "金华市",613858421,"3307012107906"),
    NING_BO(574, "宁波市",613859286,"3302012107883"),
    JIA_XING(573, "嘉兴市",613859298,"3304012107898"),
    WEN_ZHOU(577, "温州市",613856458,"3303012107885"),
    HU_ZHOU(572, "湖州市",613857739,"3305012107850"),
    SHAO_XING(575, "绍兴市",613859309,"3306012107923"),
    QU_ZHOU(570, "衢州市",613856480,"3308012107895"),
    ZHOU_SHAN(580, "舟山市",613856469,"3309012107888"),
    TAI_ZHOU(576, "台州市",613858443,"3310012107912"),
    LI_SHUI(578, "丽水市",613858432,"3311012107909"),;

    /**
     * 值
     */
    private int value;

    /**
     * 名称
     */
    private String name;
    /**
     * key值
     */
    private int key;
    /**
     * code值
     */
    private String code;

    public static String getNameByValue(int value) {
        for (AreaEnum area : AreaEnum.values()) {
            if (area.getValue() == value) {
                return area.getName();
            }
        }
        return "";
    }

    public static int getValueByName(String name) {
        for (AreaEnum area :AreaEnum.values()) {
            if (area.getName().equals(name)) {
                return area.getKey();
            }
        }
        return -1;
    }


    public static String getValueByCode(String name) {
        for (AreaEnum area :AreaEnum.values()) {
            if (area.getName().equals(name)) {
                return area.getCode();
            }
        }
        return "";
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int value) {
        this.key = key;
    }

    public String getCode() {
        return code;
    }

    public void setCode(long value) {
        this.code = code;
    }

    AreaEnum(int value, String name, int key, String code) {
        this.value = value;
        this.name = name;
        this.key=key;
        this.code=code;
    }
}
