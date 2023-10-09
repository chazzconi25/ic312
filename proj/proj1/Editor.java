import java.util.Scanner;

public class Editor {
  private Text txt = new MyText();
  private BoundedStack actns = new MyBoundedStack<Action>(50);
  private BoundedStack redo = new MyBoundedStack<Action>(50);
  /** Displays information on available commands.
   * THis will NOT be part of any autotesting.
   * You can (and should!) update as new commands are enabled.
   */
  public static void showHelp() {
    // Note to students: we won't test this function with autotesting.
    // You should keep it up to date if you add new commands, but
    // the exact wording etc. is up to you!
    System.out.println("HELP");
    System.out.println("  iX   insert letter X before the cursor");
    System.out.println("  d    delete letter at current position, then move cursor right");
    System.out.println("  <    move cursor left");
    System.out.println("  >    move cursor right");
    System.out.println("  p    print the entire txt on one line, with the cursor on the next line");
    System.out.println("  h    show this help message");
    System.out.println("  q    quit");
  }

  /** A simple helper function to display if anything goes wrong.
   * Do NOT modify this to make more descriptive error messages,
   * as that will break the autotests.
   */
  public static void showError() {
    System.out.println("ERROR");
  }

  /** Runs the command specified by the given input line.
   * @return true if the command was not "quit".
   */
  public boolean processLine(String line) {
    if (line.length() == 0) line = "h";
    
    switch (line.charAt(0)) {
      case 'i':
        if (line.length() == 2) {
          txt.insert(line.charAt(1));
          actns.push(new Action(line, txt));
          redo.clear();
        } else {
          showError();
        }
        break;
      case 'd':
        if (txt.canMoveRight()){
          actns.push(new Action(line + txt.get(), txt));
          txt.delete();
          redo.clear();
        } else {
          showError();
        }
        break;
      case '<':
        if (txt.canMoveLeft()){
          txt.moveLeft();
          actns.push(new Action(line, txt));
          redo.clear();
        }
        else {
          showError();
        }
        break;
      case '>':
        if (txt.canMoveRight()) {
          txt.moveRight();
          actns.push(new Action(line, txt));
          redo.clear();
        } else {
          showError();
        }
        break;
      case 'p':
        txt.print();
        break;
      case 'u':
        try {
          Action tmp = (Action)actns.pop();
          redo.push(tmp);
          tmp.undo();
        } catch (Exception e) {
          showError();
        }
        break;
      case 'c':
        actns.setCapacity(Integer.parseInt(line.substring(1)));
        break;
      case 'r':
        try {
          Action tmp = (Action)redo.pop();
          actns.push(tmp);
          tmp.redo();
        } catch (Exception e) {
          showError();
        }
        break;
      case 'q':
        return false;
      default:
        showHelp();
    }
    return true;
  }

  public static void main(String[] args) {
    Editor editor = new Editor();
    Scanner in = new Scanner(System.in);
    java.io.Console cons = System.console();
    do {
      if (cons != null) {
        // Only print the "command: " prompt if the output is to a
        // live terminal window (so it won't show up in autotesting).
        cons.printf("command: ");
        cons.flush();
      }
    } while (editor.processLine(in.nextLine()));
  }
  public class Action {
    private String sym;
    private Text editor;

    public Action(String sym, Text editor) {
        this.sym = sym;
        this.editor = editor;
    }

    public void undo() {
        switch(sym.charAt(0)) {
          case 'i':
            editor.moveLeft();
            editor.delete();
            break;
          case 'd':
            editor.insert(sym.charAt(1));
            editor.moveLeft();
            break;
          case '<':
            editor.moveRight();
            break;
          case '>':
            editor.moveLeft();
            break;
        }
    }
    public void redo() {
        switch(sym.charAt(0)) {
          case 'i':
            txt.insert(sym.charAt(1));
            break;
          case 'd':
            txt.delete();
            break;
          case '<':
            editor.moveLeft();
            break;
          case '>':
            editor.moveRight();
            break;
        }
    }
}
}
