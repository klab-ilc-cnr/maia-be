package it.cnr.ilc.maia.xresults;

import it.cnr.ilc.maia.xresults.fail.Fail;

public class XResultVoid {

    private Fail fail;

    private XResultState State;

    public XResultVoid() {
    }

    public XResultVoid(Fail fail) {
        if (fail == null) {
            throw new IllegalArgumentException("fail");
        }
        this.fail = fail;
    }

    public Fail getFail() {
        return fail;
    }

    public XResultState getState() {
        return getFail() == null ? XResultState.Success : XResultState.Fail;
    }

    public boolean IsSuccess() {
        return getState() == XResultState.Success;
    }

    public boolean IsFailed() {
        return getState() == XResultState.Fail;
    }

    public static XResultVoid Success() {
        return new XResultVoid();
    }

    public enum XResultState {
        None,
        Success,
        Fail
    }
}
