package application2;

import org.junit.jupiter.api.Test;

public class StackControllerTest {

    private StackModel stackModel;

    @BeforeEach
    public void setUp() {
        // Set up a new StackModel instance before each test
        stackModel = new StackModel();
    }

    @Test
    public void testPush() {
        // Test pushing an element onto the stack
        stackModel.push(10);
        // Check if the size of the stack is 1 after pushing
        assertEquals(1, stackModel.size());
    }

    @Test
    public void testPop() {
        // Test popping an element from the stack
        stackModel.push(10);
        // Pop the element from the stack and store the value
        int value = stackModel.pop();
        // Check if the popped value is equal to the pushed value
        assertEquals(10, value);
        // Check if the size of the stack is 0 after popping
        assertEquals(0, stackModel.size());
    }

    @Test
    public void testIsEmpty() {
        // Test if the stack is initially empty
        assertTrue(stackModel.isEmpty());
        // Push an element onto the stack
        stackModel.push(10);
        // Test if the stack is not empty after pushing
        assertFalse(stackModel.isEmpty());
    }
}
