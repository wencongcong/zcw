package com.brokerage.enums;

public enum ErrorEnum {

    /**
     * 常见错误
     */
    DEFAULT_ERROR(100, "系统错误"),
    PARAMS_ERROR(101, "参数错误"),
    Data_NOT_SUIT(103,"数据不适合"),
    NO_MESSAGE(501,"无对应数据"),
    OATHER_MESSAGE(502,"数据异常"),
    FIAL_ERROR(505,"插入失败"),
    CHONG_FU(506,"数据存在"),
    USERNAME_NULL(801,"用户名为空"),
    PASSWORD_NULL(802,"密码为空"),
    USER_NULL(803,"不存在该用户"),
    OLDPASSWORD_ERRO(804,"原密码错误"),
    OLDPASSWORD_NULL(805,"原密码为空"),
    USERNAME_ALIVE(806,"用户名已经存在"),
    ;

    /**
     * 值
     */
    private int value;

    /**
     * 名称
     */
    private String name;


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

    ErrorEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }


}
