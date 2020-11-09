package com.businness.result;


import com.businness.enums.ErrorEnum;

public class Result<T>{

    /**
     * 状态
     */
    private boolean status;

    /**
     * 错误消息
     */
    private String message;

    /**
     * 返回值
     */
    private int code;

    /**
     * 结果对象
     */
    private T entry;

    /**
     * 记录数
     */
    private int totalCount;

    public static Result success() {
        Result Result = new Result();
        Result.setStatus(true);

        return Result;
    }

    public static Result success(Object entry) {
        Result Result = new Result();
        Result.setStatus(true);
        Result.setEntry(entry);

        return Result;
    }

    public static Result success(int code, Object entry) {
        Result Result = new Result();
        Result.setCode(code);
        Result.setStatus(true);
        Result.setEntry(entry);

        return Result;
    }



    public static Result fail(ErrorEnum errorEnum) {
        Result Result = new Result();
        Result.setStatus(false);
        Result.setMessage(errorEnum.getName());
        Result.setCode(errorEnum.getValue());

        return Result;
    }
    public static Result fail(Object object) {
        Result Result = new Result();
        Result.setStatus(false);
        Result.setMessage(object.toString());
        Result.setEntry(object);


        return Result;
    }
    public static Result fail(Object object,ErrorEnum errorEnum) {
        Result Result = new Result();
        Result.setStatus(false);
        Result.setEntry(object);
        Result.setMessage(errorEnum.getName());
        Result.setCode(errorEnum.getValue());

        return Result;
    }

    public static Result fail(int code, String msg) {
        Result Result = new Result();
        Result.setStatus(false);
        Result.setMessage(msg);
        Result.setCode(code);

        return Result;
    }

    public Result() {
        super();
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getEntry() {
        return entry;
    }

    public void setEntry(T entry) {
        this.entry = entry;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
