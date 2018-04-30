package com.s2u2m.slancer.core.token;

public interface ITokenOp <DT extends AbTokenData> {

    void set(String token, DT data);

    DT get(String token);

    void del(String token);

    void refresh(String token);
}
