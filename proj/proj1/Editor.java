import java.util.Scanner;

public class Editor {
  private Text txt = new MyText();

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
        if (line.length() == 2)
          txt.insert(line.charAt(1));
        else showError();
        break;
      case 'd':
        if (txt.canMoveRight())
          txt.delete();
        else showError();
        break;
      case '<':
        if (txt.canMoveLeft())
          txt.moveLeft();
        else showError();
        break;
      case '>':
        if (txt.canMoveRight())
          txt.moveRight();
        else showError();
        break;
      case 'p':
        txt.print();
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
}
