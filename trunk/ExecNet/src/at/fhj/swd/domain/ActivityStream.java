package at.fhj.swd.domain;

public class ActivityStream implements IEntity {

    public static final String ENTITY_NAME = "ActivityStream";

    private Long id;

    public ActivityStream() {
    }

    @Override
    public String getEntityName() {
        return ENTITY_NAME;
    }

    @Override
    public Long getId() {
        return id;
    }

}
