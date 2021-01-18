package by.dma.puzzle.syntax;

/**
 * Syntax puzzles.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
public class SyntaxPuzzlers {
    public static void main(String[] args) {
//<editor-fold desc="NewLine character and brackets">
/*
\u000a - the newline character \n
\u007d - a closing curly brace }
\u007b - an opening curly brace {
*/
//</editor-fold>
        if (1 == 2) { // one down, one to go: \u000a\u007d\u007b
            System.out.println("1 is 2");
        }
//<editor-fold desc="Optional first parameter">
        new SyntaxPuzzlers().callMe(123);
//</editor-fold>
    }

    //Block statement can contain ClassDeclarations!
    public void howDeepCanWeGo() {
        class Foo {
            public void hello() {
                class Bar {
                    public void helloFromBar() {
                        // You musn't be afraid to dream a little bigger, darling.
                    }
                }
                new Bar().helloFromBar();
            }
        }
        final Foo instance = new Foo();
        instance.hello();
    }

    public void callMe(SyntaxPuzzlers this, int param) {
        System.out.println(this + "called with " + param);
    }
}
