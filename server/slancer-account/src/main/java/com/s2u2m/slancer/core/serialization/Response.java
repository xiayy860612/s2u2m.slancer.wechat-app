package com.s2u2m.slancer.core.serialization;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * The type Response.
 *
 * @param <T>  the type parameter
 */
@Getter
@Setter
@Accessors(chain = true)
public class Response<T extends ResponseData> extends BaseResponse {
    private T data;
}
