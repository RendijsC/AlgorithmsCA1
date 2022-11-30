import Resources.LinkedList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {
    LinkedList days = new LinkedList<>();
    LinkedList months = new LinkedList();

    @BeforeEach
    void setUp() {
        days.addNode("Monday");
        days.addNode("Tuesday");
    }

    @AfterEach
    void tearDown() {
        days.deleteAll();
    }

    @Test
    void getHeadTest() {
        assertEquals("Monday",days.getHead().getData());

    }

    @Test
    void AddNodeTest() {
        assertNull(days.get(2));//in 0 and 1 index
        days.addNode("Sunday");
        assertEquals("Sunday",days.get(2));//now should be in 2
    }

    @Test
    void getTest() {
        assertEquals(days.getHead().getData(),days.get(0));
        assertNull(months.get(0));
        assertEquals("Tuesday",days.get(1));
    }

    @Test
    void deleteAllTest() {
        assertEquals(days.getHead().getData(),days.get(0));
        days.deleteAll();
        assertNull(days.get(0));

    }

    @Test
    void listSizeTest() {
        assertEquals(2,days.listSize());
        assertEquals(0,months.listSize());
    }




}
