package com.homework1;

public class UndoRedoTest {

    /**
     * A small test case for an undo/redo system.
     *
     */
    public static void main() {
        StringDoublyLinkedList actions = new StringDoublyLinkedList();

        actions.doAction("create outline");
        actions.doAction("write introduction paragraph");
        actions.doAction("write paragraph 1a");
        actions.undo();
        actions.doAction("write paragraph 1b");
        actions.doAction("write paragraph 2a");
        actions.undo();
        actions.undo();
        System.out.println(actions.redo());
        System.out.println(actions.redo());
        System.out.println(actions.redo());
        System.out.println(actions.undo());
        System.out.println(actions.undo());
        System.out.println(actions.redo());
        System.out.println(actions.redo());
        System.out.println(actions.redo());
        actions.undo();
        actions.undo();
        System.out.println(actions.redo());
        System.out.println(actions.redo());
        System.out.println(actions.redo());




        actions.doAction("write paragraph 2b");
        actions.doAction("write paragraph 3");
        actions.doAction("write paragraph 4");
        actions.undo();
        actions.doAction("write conclusion paragraph");
        actions.doAction("add expletive about how long this assignment took");
        actions.undo();

        String[] correctActions = {
                "create outline",
                "write introduction paragraph",
                "write paragraph 1b",
                "write paragraph 2b",
                "write paragraph 3",
                "write conclusion paragraph"
        };

        // create a variable for overall correctness
        boolean allCorrect;
        // check the number of actions
        System.out.println(
                "Expected " + Integer.toString(correctActions.length) + " actions " +
                        "and found " + Integer.toString(actions.getNumActions())
        );
        allCorrect = (actions.getNumActions() == correctActions.length);
        // if the number of actions is correct, check each action
        if (allCorrect) {
            for (int i = 0; i < correctActions.length; i++) {
                // get the expected and action actions
                String expectedAction = correctActions[i];
                String actualAction = actions.getAction(i);
                // compare them
                boolean correct = (expectedAction == actualAction);
                // print them out
                System.out.println(
                        "(" + (correct ? "correct" : "incorrect") + ") " +
                                "Action " + Integer.toString(i) + " should be \"" + correctActions[i] + "\" " +
                                "and got \"" + actions.getAction(i) + "\"."
                );
                // update the overall correctness
                allCorrect = (allCorrect && correct);
            }
        }
        // give a summary correct/incorrect judgment
        if (allCorrect) {
            System.out.println("CORRECT!");
        } else {
            System.out.println("INCORRECT!");
        }
    }
}





