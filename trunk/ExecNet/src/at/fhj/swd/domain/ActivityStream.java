package at.fhj.swd.domain;

import at.fhj.swd.application.Application;
import at.fhj.swd.application.IRuntimeContext;

public class ActivityStream {

    private IRuntimeContext _runtime;

    public ActivityStream() {
        Application _app = Application.getInstance();
        _runtime = _app.getRuntime();
    }

}
