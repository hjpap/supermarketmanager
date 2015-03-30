package cn.com.wei.shell;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import cn.com.wei.tools.JDBCTools;

public class AddGhs extends Shell {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	MessageBox m=new MessageBox(getShell(),SWT.ICON_INFORMATION);
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void start() {
		try {
			Display display = Display.getDefault();
			AddGhs shell = new AddGhs(display);
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
	public AddGhs(Display display) {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(AddGhs.class, "/img/22.png"));
		setBackgroundMode(SWT.INHERIT_FORCE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		
		Label label_5 = new Label(this, SWT.NONE);
		label_5.setText("\u6DFB\u52A0\u4F9B\u8D27\u5546");
		label_5.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 15, SWT.BOLD));
		label_5.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label_5.setBounds(131, 21, 108, 27);
		
		Label label = new Label(this, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label.setBounds(0, 0, 365, 73);
		
		Button button = new Button(this, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			//ÃÌº”
			public void widgetSelected(SelectionEvent e) {
				if(text.getText()!=""&&text_1.getText()!=""&&text_2.getText()!=""&&text_3.getText()!=""){
					add();
					getShell().dispose();
				}else{
					
					m.setText("¥ÌŒÛ");
					m.setMessage("«ÎΩ´–≈œ¢ ‰»ÎÕÍ’˚");
					m.open();
				}
			}
		});
		button.setText("\u6DFB\u52A0");
		button.setBounds(45, 339, 80, 27);
		
		Button button_1 = new Button(this, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
		//»°œ˚
			public void widgetSelected(SelectionEvent e) {
				getShell().dispose();
			}
		});
		button_1.setText("\u53D6\u6D88");
		button_1.setBounds(241, 339, 80, 27);
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 11, SWT.BOLD));
		label_1.setBounds(31, 118, 94, 27);
		label_1.setText("\u4F9B\u8D27\u5546\u540D\u79F0\uFF1A");
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 11, SWT.BOLD));
		label_2.setBounds(45, 165, 72, 27);
		label_2.setText("\u8054\u7CFB\u7535\u8BDD\uFF1A");
		
		Label label_3 = new Label(this, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 11, SWT.BOLD));
		label_3.setBounds(45, 210, 72, 27);
		label_3.setText("\u8054\u7CFB\u5730\u5740\uFF1A");
		
		Label label_4 = new Label(this, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 11, SWT.BOLD));
		label_4.setBounds(58, 253, 61, 27);
		label_4.setText("\u8054\u7CFB\u4EBA\uFF1A");
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(131, 118, 165, 23);
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(131, 169, 165, 23);
		
		text_2 = new Text(this, SWT.BORDER);
		text_2.setBounds(131, 209, 165, 23);
		
		text_3 = new Text(this, SWT.BORDER);
		text_3.setBounds(131, 252, 165, 23);
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	
	//ÃÌº”
	public void add(){
		JDBCTools jt=new JDBCTools();
		String sql="insert into gonghuoshang(name,tel,address,people) values('"+text.getText()+"','"+text_1.getText()+"','"+text_2.getText()+"','"+text_3.getText()+"')";
		jt.update(sql);
		text.setText("");
		text_1.setText("");
		text_2.setText("");
		text_3.setText("");
		jt.close(null);
	}
	
	
	protected void createContents() {
		setText("\u6DFB\u52A0\u4F9B\u8D27\u5546");
		setSize(381, 527);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
