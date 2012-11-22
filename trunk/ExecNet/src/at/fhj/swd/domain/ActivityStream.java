/*
 * 
 * 
 *   gmoik: OBSOLET -> wie kann man das löschen?? wie reorganisert man die DB?? 
 * 
 * */
package at.fhj.swd.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = User.ENTITY_NAME)
@Table(name = "tbl_ActivityStream")
public class ActivityStream implements IEntity {

    public static final String ENTITY_NAME = "ActivityStream";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public ActivityStream() {
        // TODO remove ActivityStream.
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
