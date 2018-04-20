/**
 *
 */
package clickcopy;

import clickcopy.view.ClickCopyViewAndController;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Main application class.
 */
public class Main {

    /**
     * Default window width.
     */
    private static final int DEFAULT_WIDTH = 600;
    /**
     * Default window height.
     */
    private static final int DEFAULT_HEIGHT = 150;

    /**
     * This function create new window(shell)
     * with necessary UI elements for display.
     *
     * @param display - display, for which sets window(shell)
     * @return Shell
     */
    public static Shell configureShell(final Display display) {
        Shell shell = new Shell(display, SWT.SHELL_TRIM & (~SWT.RESIZE));
        shell.setText("Click&Copy");
        shell.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        shell.setLayout(ClickCopyViewAndController.getLayout(shell));

        return shell;
    }

    /**
     * The entry point to the application.
     *
     * @param args - Array of arguments passed to this application.
     */
    public static void main(final String[] args) {
        Display display = new Display();
        Shell shell = configureShell(display);
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }
}
