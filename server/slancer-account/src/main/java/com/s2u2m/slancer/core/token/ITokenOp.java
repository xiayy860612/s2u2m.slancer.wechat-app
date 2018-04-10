package com.s2u2m.slancer.core.token;

public interface ITokenOp <DT extends AbTokenData> {

    String token(DT data);

    DT data(String token);

    void refresh(String token);
}
