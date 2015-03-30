package cn.com.wei.shell;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;

public class Help extends Shell {

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void start() {
		try {
			Display display = Display.getDefault();
			Help shell = new Help(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	public Help(Display display) {
		super(display, SWT.CLOSE | SWT.RESIZE | SWT.TITLE);
		setBackgroundMode(SWT.INHERIT_FORCE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		setImage(SWTResourceManager.getImage(Help.class, "/img/22.png"));
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(51, 204, 255));
		lblNewLabel_1.setImage(SWTResourceManager.getImage(Help.class, "/img/cxTitle.png"));
		lblNewLabel_1.setBounds(34, 40, 90, 91);
		
		Label label = new Label(this, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label.setBounds(0, 0, 150, 181);
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 11, SWT.NORMAL));
		lblNewLabel.setBounds(172, 10, 159, 28);
		lblNewLabel.setText("\u6D77\u91CF\u8D85\u5E02\u8FDB\u9500\u7BA1\u7406\u7CFB\u7EDF");
		
		Label lblV = new Label(this, SWT.NONE);
		lblV.setText("Version: 1.0");
		lblV.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 11, SWT.NORMAL));
		lblV.setBounds(172, 58, 159, 28);
		
		Label lblcCopyrightEclipse = new Label(this, SWT.NONE);
		lblcCopyrightEclipse.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 11, SWT.NORMAL));
		lblcCopyrightEclipse.setBounds(172, 103, 159, 28);
		lblcCopyrightEclipse.setText("\u4F5C\u8005:  \u738B  \u5A01");
		
		Label lblQQ = new Label(this, SWT.NONE);
		lblQQ.setText("Q Q :  49977244");
		lblQQ.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 11, SWT.NORMAL));
		lblQQ.setBounds(172, 137, 159, 28);
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("\u5E2E\u52A9");
		setSize(449, 215);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
