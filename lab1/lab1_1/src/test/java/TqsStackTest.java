import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import ua.tqs.TqsStack;

class TqsStackTest<T> {
    
    TqsStack<T> stack; // can be reused in different tests

    @BeforeEach
    void setUp(){ // a stack has size 0 on construction ?
        stack = new TqsStack<T>();
    }

    @DisplayName("verify if the stack is empty")
    @Test
    void isEmpty(){
        assertTrue(stack.isEmpty());
    }

    @DisplayName("after n pushes to an empty stack, n > 0, the stack is not empty and its size is n")
    @Test
    void sizeAfterNPushes(){
        stack.push((T) "batatinha");
        stack.push((T) "espinafres");
        stack.push((T) "bolachinhas");

        // stack size equals 3
        assertEquals(3, stack.size());

    }

    @DisplayName("if one pushes x then pops, the value popped is x")
    @Test
    void PushxPop(){
        String icetea = "pessego";
        String chocolate = "kiner bueno";
        stack.push((T) icetea);
        stack.push((T) chocolate);
        
        // value popped is x
        assertEquals(chocolate, stack.pop());
    }

    @DisplayName("if one pushes x then peeks, the value returned is x, but the size stays the same")
    @Test
    void PushxPeek(){
        String snacks = "ruffles";
        String icetea = "manga";
        stack.push((T) snacks);
        stack.push((T) icetea);

        // value peeked is x
        assertAll(
            () -> assertEquals(icetea, stack.peek()),
            () -> assertEquals(2, stack.size())
        );
    }

    @DisplayName("if the size is n, then after n pops, the stack is empty and has a size 0")
    @Test
    void PopToEmpty(){
        String snacks = "amendoins";
        String icetea = "manga e maracujá";
        String chocolate = "milka";
        stack.push((T) snacks);
        stack.push((T) icetea);
        stack.push((T) chocolate);

        assertEquals(3, stack.size());

        Integer inicialSize = stack.size();

        // pop n, size is n
        // stack.pop();
        // stack.pop();
        // stack.pop();
        
        for(int i = 0; i < inicialSize; i++){
            // System.out.println(i);
            // T peeked = stack.peek();
            // System.out.println(peeked.toString());
            // T pooped = stack.pop();
            stack.pop();
            // System.out.println(pooped.toString());
        }

        // stack is empty, size is 0
        assertAll(
            () -> assertTrue(stack.isEmpty()),
            () -> assertEquals(0, stack.size())
        );
    }

    @DisplayName("popping from an empty stack does throw a NoSuchElementException")
    @Test
    void testPopNoSuchElementException() {

        assertThrows(NoSuchElementException.class, () -> stack.pop());
    }

    @DisplayName("peeking into an empty stack does throw a NoSuchElementException")
    @Test
    void testPeekNoSuchElementException() {
        
        assertThrows(NoSuchElementException.class, () -> stack.peek());
    }

    @DisplayName("for bounded stacks only: pushing onto a full stack does throw an IllegalStateException")
    @Test
    void boundedStacks() {
        stack = new TqsStack<>(3);
        stack.push((T) "banana");
        stack.push((T) "kiwi");
        stack.push((T) "laranja");

        assertThrows(IllegalStateException.class, () -> stack.push((T) "framboesa"));
    }
}