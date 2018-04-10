package com.s2u2m.slancer.core.formatchecker;

/**
 * @author Amos Xia
 * @date 2018/4/9
 */
public abstract class AbFormatChecker<DT> implements IFormatChecker {
    protected DT data;
    protected AbFormatChecker(DT data) {
        this.data = data;
    }
}
