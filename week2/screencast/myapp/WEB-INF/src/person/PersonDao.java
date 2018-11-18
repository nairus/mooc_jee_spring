package person;

import java.util.List;

public interface PersonDao {

    /*
        * TODO :
        public void create(Person person);
        public Person find(int id);
        public void update(Person person);
        public void delete(int id);
        */

    public List<Person> listAll();

}
