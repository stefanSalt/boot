package com.lee.reservation.common.enums;

/**
 * TODO
 *
 * @Date 2024-10-15 13:37
 */
public enum RoleEnum {
    ADMIN(1, "管理员"),
    INSTRUCTOR(2, "教练"),
    STUDENT(3, "学员");
    ;

    private Integer code;
    private String desc;

    RoleEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
