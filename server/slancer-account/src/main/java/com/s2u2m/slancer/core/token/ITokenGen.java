package com.s2u2m.slancer.core.token;

/**
 * interface ITokenGen
 *
 * @author xiayy860612
 * @date 2018/5/3
 */
public interface ITokenGen<DT extends AbTokenData> {
    String token(DT data);
}
