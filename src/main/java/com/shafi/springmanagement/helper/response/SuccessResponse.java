package com.shafi.springmanagement.helper.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuccessResponse<T> extends BasedResponse {
    public T response;

    public SuccessResponse(T message){
        this.isSuccess = true;
        this.response = message;
    }
}
