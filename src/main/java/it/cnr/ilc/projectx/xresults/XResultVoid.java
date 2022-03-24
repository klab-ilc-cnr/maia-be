package it.cnr.ilc.projectx.xresults;

import it.cnr.ilc.projectx.xresults.fail.Fail;

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

//    public bool IsFailedType<TFail>() where TFail : Fail => Fail is TFail;

//    public TFail? GetFail<TFail>() where TFail : Fail => (TFail?)Fail;

//    public static implicit operator XResult(Fail fail) => new XResult(fail);

    public enum XResultState {
        None,
        Success,
        Fail
    }
}
