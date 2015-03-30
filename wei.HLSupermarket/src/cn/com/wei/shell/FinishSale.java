package cn.com.wei.shell;

import java.text.DecimalFormat;

import org.eclipse.swt.SWT;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class FinishSale extends Shell {
	private Text text;
	private Text text_1;
	private Text text_2;
	private static String money;
	private static String back;
	private static String billcode;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void start(String money1,String back1,String billcode1) {
		money =money1;
		back=back1;
		billcode=billcode1;
		
		try {
			Display display = Display.getDefault();
			FinishSale shell = new FinishSale(display);
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
	public FinishSale(Display display) {
		super(display, SWT.NONE);
		setImage(SWTResourceManager.getImage(FinishSale.class, "/img/22.png"));
		addKeyListener(new KeyAdapter() {
			//½çÃæÖÐ°´ »Ø³µ
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.CR){
					getShell().dispose();
				}
			}
		});
		setBackgroundMode(SWT.INHERIT_FORCE);
		
		Label label = new Label(this, SWT.WRAP | SWT.CENTER);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 19, SWT.BOLD));
		label.setBounds(0, 22, 434, 44);
		label.setText("\u7ED3\u8D26\u6210\u529F");
		
		text = new Text(this, SWT.READ_ONLY);
		text.setBounds(200, 114, 149, 23);
		text.setText(billcode);
		
		
		text_1 = new Text(this, SWT.READ_ONLY);
		text_1.setBounds(200, 151, 149, 23);
		text_1.setText(money+" Ôª");
		
		text_2 = new Text(this, SWT.READ_ONLY);
		text_2.setBounds(200, 186, 149, 23);
		
		DecimalFormat dec = new DecimalFormat("0.##");
		
		text_2.setText(dec.format(Double.parseDouble(back))+" Ôª");
		
		Button button = new Button(this, SWT.NONE);
		button.setFocus();
		button.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				getShell().dispose();
			}
		});
		button.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 13, SWT.NORMAL));
		button.setBounds(179, 227, 93, 33);
		button.setText("\u786E\u5B9A");
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 14, SWT.NORMAL));
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_2.setBounds(101, 147, 93, 31);
		label_2.setText("\u5B9E\u6536\u91D1\u989D\uFF1A");
		
		Label label_3 = new Label(this, SWT.NONE);
		label_3.setText("\u627E \u96F6\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 14, SWT.NORMAL));
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_3.setBounds(101, 184, 93, 31);
		
		Label label_4 = new Label(this, SWT.NONE);
		label_4.setText("\u5C0F\u7968\u53F7\u7801\uFF1A");
		label_4.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 14, SWT.NORMAL));
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_4.setBounds(101, 110, 93, 31);
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_1.setBounds(0, 79, 448, 219);
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("\u7ED3\u8D26\u6210\u529F");
		setSize(450, 300);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
