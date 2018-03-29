package com.s2u2m.slancer.core.formatchecker;


/**
 * @author Amos Xia
 * @date 2018/4/9
 */
public class CombineFormatChecker implements IFormatChecker {
    private IFormatChecker pre;
    private IFormatChecker next;

    public CombineFormatChecker(IFormatChecker pre, IFormatChecker next) {
        this.pre = pre;
        this.next = next;
    }

    @Override
    public boolean check() {
        return pre.check() && next.check();
    }

    public CombineFormatChecker add(IFormatChecker checker) {
        return new CombineFormatChecker(this, checker);
    }

}
