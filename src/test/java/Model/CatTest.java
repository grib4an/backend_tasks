package Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CatTest {

    @Test
    public void setName() {
        Cat cat=new Cat();
        cat.setName(" ");
    }

    @Test
    public void setTail_length() {
        Cat cat=new Cat();
        cat.setTail_length(-1);
    }

    @Test
    public void setWhiskers_length() {
        Cat cat=new Cat();
        cat.setWhiskers_length(0);
    }
}