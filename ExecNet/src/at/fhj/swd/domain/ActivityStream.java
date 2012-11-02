package at.fhj.swd.domain;

public class ActivityStream implements IEntity {

    public static final String ENTITY_NAME = "ActivityStream";

    private Long id;

    public ActivityStream() {
        // TODO implement ActivityStream.
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
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
