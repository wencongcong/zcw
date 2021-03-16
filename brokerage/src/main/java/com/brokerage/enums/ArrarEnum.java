package com.brokerage.enums;

public enum ArrarEnum {

   // HE_XUN(1,"18158505340"),
  //  LI_SHUI(2,"15325783676"),
  //  NI_BO(3,"18006709243"),
  //  HU_ZHONG(4,"17367135383"),
   // SHAO_XING(2,"13372516626"),
  //  JIA_XING(6,"17767102019"),
  //  XIA_SHA(7,"19957800656 "),
   RUI_TONG(1,"18072892408"),
   JIA_XING(2,"17767102019");


    /**
     * 值
     */
    private int value;

    /**
     * 名称
     */
    private String  name;

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

    ArrarEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

}
