package com.vnpt.common.base;

import com.vnpt.common.constants.ErrorCodes;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class BaseResponse<T> {
    private String code;
    private String desc;

    private T data;

    public void success(T data){
        this.code = ErrorCodes.SUCCESS.code;
        this.desc = ErrorCodes.SUCCESS.message;
        this.data = data;
    }

    public void error(ErrorCodes errorCodes){
        this.code = errorCodes.code;
        this.desc = errorCodes.message;
        this.data = null;
    }
}
