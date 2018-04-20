package clickcopy.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * This class create UI and sets
 * two simple handlers.
 */
public final class ClickCopyViewAndController {
    /**
     * Default columns count.
     */
    public static final int DEFAULT_COLUMNS_COUNT = 3;

    /**
     * Private constructor.
     */
    private ClickCopyViewAndController() {

    }

    /**
     * @param shell - Shell, for which sets UI elements.
     * @return GridLayout - layout, that will be set to shell.
     */
    public static GridLayout getLayout(final Shell shell) {
        GridLayout gridLayout = new GridLayout(DEFAULT_COLUMNS_COUNT, true);
        GridData gridData = new GridData(
                GridData.FILL,
                GridData.FILL,
                true,
                true,
                1,
                1
        );

        Label firLabel = new Label(shell, SWT.CENTER);
        firLabel.setText("1");
        firLabel.setLayoutData(gridData);

        Label secLabel = new Label(shell, SWT.CENTER);
        secLabel.setText("2");
        secLabel.setLayoutData(gridData);

        Label thLabel = new Label(shell, SWT.CENTER);
        thLabel.setText("3");
        thLabel.setLayoutData(gridData);

        Text firstTextField = new Text(shell, SWT.BORDER);
        firstTextField.setLayoutData(gridData);

        Text secondTextField = new Text(shell, SWT.BORDER);
        secondTextField.setLayoutData(gridData);

        Text thirdTextField = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
        thirdTextField.setLayoutData(gridData);

        Button firButton = new Button(shell, SWT.PUSH);
        firButton.setText("Копировать из 1 в 2");
        firButton.setLayoutData(gridData);
        firButton.addListener(SWT.MouseDown,
                event -> secondTextField.setText(firstTextField.getText())
        );

        Button secButton = new Button(shell, SWT.PUSH);
        secButton.setText("Копировать из 2 в 3");
        secButton.setLayoutData(gridData);
        secButton.addListener(SWT.MouseDown,
                event -> thirdTextField.setText(secondTextField.getText())
        );

        Button clearButton = new Button(shell, SWT.PUSH);
        clearButton.setText("Clear");
        clearButton.setLayoutData(gridData);
        clearButton.addListener(SWT.MouseDown,
                event -> {
                    firstTextField.setText("");
                    secondTextField.setText("");
                    thirdTextField.setText("");
                }
        );

        return gridLayout;
    }
}
