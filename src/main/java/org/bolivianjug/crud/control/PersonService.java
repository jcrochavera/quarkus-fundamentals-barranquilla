package org.bolivianjug.crud.control;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.bolivianjug.crud.entity.Person;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;

/**
 * Created by julio.rocha on 27/6/20.
 *
 * @author julio.rocha
 */
@ApplicationScoped
public class PersonService implements PanacheRepositoryBase<Person, String> {

    public boolean customUpdate(String id, Person newData) {
        boolean updated = false;
        Person person = findById(id);
        if (Objects.nonNull(person)) {
            update("name = ?1, number =?2, numberMessage=?3 where id=?4",
                    newData.name, newData.number, newData.numberMessage, person.id);
            updated = true;
        }
        return updated;
    }
}
