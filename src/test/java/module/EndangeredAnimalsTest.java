package module;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class EndangeredAnimalsTest {
    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    public Endangeredanimal setUpEndangeredAnimal(){
        return new Endangeredanimal("elephant","weak","young");
    }

    @Test
    public void endangeredAnimal_instantiatesCorrectly_true(){
        Endangeredanimal animal = setUpEndangeredAnimal();
        assertEquals(true,animal instanceof Endangeredanimal);
    }

    @Test
    public void endangeredAnimal_instantiatesWithName_String(){
        Endangeredanimal animal = setUpEndangeredAnimal();
        assertEquals("elephant",animal.getName());
    }

    @Test
    public void endangeredAnimal_instantiatesWithHealth_String(){
        Endangeredanimal animal = setUpEndangeredAnimal();
        assertEquals("weak",animal.getHealth());
    }

    @Test
    public void endangeredAnimal_instantiatesWithAge_String(){
        Endangeredanimal animal = setUpEndangeredAnimal();
        assertEquals("young",animal.getAge());
    }

    @Test
    public void equals_returnsTrueIfNameAreSame_true() {
        Endangeredanimal testAnimal = new Endangeredanimal("lion","okay","newborn");
        Endangeredanimal anotherAnimal = new Endangeredanimal("lion","okay","newborn");
        assertTrue(testAnimal.equals(anotherAnimal));
    }

    @Test
    public void save_successfullyAddsEndangeredAnimalToDatabase_List() {
        Endangeredanimal animal = setUpEndangeredAnimal();
        animal.save();
        assertTrue(Endangeredanimal.all().get(0).equals(animal));
    }

    @Test
    public void save_assignsIdToEndangeredAnimal() {
        Endangeredanimal animal = setUpEndangeredAnimal();
        animal.save();
        Endangeredanimal savedAnimal = Endangeredanimal.all().get(0);
        assertEquals(savedAnimal.getId(), animal.getId());
    }

    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        Endangeredanimal firstAnimal = setUpEndangeredAnimal();
        firstAnimal.save();
        Endangeredanimal secondAnimal = Endangeredanimal.all().get(0);
        secondAnimal.save();
        assertEquals(true, Endangeredanimal.all().get(0).equals(firstAnimal));
        assertEquals(true, Endangeredanimal.all().get(1).equals(secondAnimal));
    }

    @Test
    public void find_returnsAnimalWithSameId_secondAnimal() {
        Endangeredanimal firstAnimal = setUpEndangeredAnimal();
        firstAnimal.save();
        Endangeredanimal secondAnimal = Endangeredanimal.all().get(0);
        secondAnimal.save();
        assertEquals(Endangeredanimal.find(secondAnimal.getId()), secondAnimal);
    }

}