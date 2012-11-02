package at.fhj.swd.domain;

import at.fhj.swd.application.Application;
import at.fhj.swd.application.IRuntimeContext;

public class ActivityStream implements IEntity {

    private IRuntimeContext _runtime;

    public ActivityStream() {
        Application _app = Application.getInstance();
        _runtime = _app.getRuntime();
    }

    // TODO implement ActivityStream.

    @Override
    public String getEntityName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long getId() {
        // TODO Auto-generated method stub
        return null;
    }

}
