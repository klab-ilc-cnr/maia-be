package it.cnr.ilc.projectx.xresults;

import it.cnr.ilc.projectx.xresults.fail.Fail;

public class XResult<T> extends XResultVoid {

    private final T payload;

    public XResult() {
        super();
        this.payload = null;
    }

    public XResult(T payload) {
        super();
        this.payload = payload;
    }

    public T getPayload() throws Exception {
        if (getState() == XResultState.Success) {
            return payload;
        }
        throw new Exception("You can't access payload if the state is" + getState() + ".");
    }

    public XResult(Fail fail) {
        super(fail);
        payload = null;
    }

}
