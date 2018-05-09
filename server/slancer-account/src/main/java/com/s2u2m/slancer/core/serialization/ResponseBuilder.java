package com.s2u2m.slancer.core.serialization;

/**
 * @author Amos Xia
 * @date 2018/4/24
 */
public class ResponseBuilder {
    public static <T> Response<T> build(Object obj, Class<T> tClass) {
        T data = tClass.cast(obj);
        return build(data);
    }

    public static <T> Response<T> build(T data) {
        Response<T> response = new Response<>();
        response.setData(data);
        return response;
    }

    public static NoDataResponse nothing() {
        return new NoDataResponse();
    }
}
