package cn.com.wei.shell;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import cn.com.wei.tools.JDBCTools;

public class AlterGhs extends Shell {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private static TableItem item;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void start(TableItem item1) {
		item=item1;
		try {
			Display display = Display.getDefault();
			AlterGhs shell = new AlterGhs(display);
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
	public AlterGhs(Display display) {
		super(display, SWT.CLOSE | SWT.RESIZE | SWT.TITLE);
		setImage(SWTResourceManager.getImage(AlterGhs.class, "/img/22.png"));
		setBackgroundMode(SWT.INHERIT_FORCE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		setText("\u4FEE\u6539\u4F9B\u5E94\u5546\u4FE1\u606F");
		
		Label label_5 = new Label(this, SWT.NONE);
		label_5.setText("\u4FEE\u6539\u4F9B\u5E94\u5546\u4FE1\u606F");
		label_5.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 15, SWT.BOLD));
		label_5.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label_5.setBounds(115, 21, 146, 27);
		
		Label label = new Label(this, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label.setBounds(0, 0, 365, 73);
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(152, 134, 165, 23);
		text.setText(item.getText(1));
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setText("\u4F9B\u8D27\u5546\u540D\u79F0\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 11, SWT.BOLD));
		label_1.setBounds(52, 134, 94, 27);
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setText("\u8054\u7CFB\u7535\u8BDD\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 11, SWT.BOLD));
		label_2.setBounds(66, 181, 72, 27);
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(152, 185, 165, 23);
		text_1.setText(item.getText(2));
		
		Label label_3 = new Label(this, SWT.NONE);
		label_3.setText("\u8054\u7CFB\u5730\u5740\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 11, SWT.BOLD));
		label_3.setBounds(66, 226, 72, 27);
		
		text_2 = new Text(this, SWT.BORDER);
		text_2.setBounds(152, 225, 165, 23);
		text_2.setText(item.getText(3));
		
		Label label_4 = new Label(this, SWT.NONE);
		label_4.setText("\u8054\u7CFB\u4EBA\uFF1A");
		label_4.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 11, SWT.BOLD));
		label_4.setBounds(79, 269, 61, 27);
		
		text_3 = new Text(this, SWT.BORDER);
		text_3.setBounds(152, 268, 165, 23);
		text_3.setText(item.getText(4));
		
		Button button = new Button(this, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
		//È·¶¨ÐÞ¸Ä
			public void widgetSelected(SelectionEvent e) {
				if(text.getText()!=""&&text_1.getText()!=""&&text_2.getText()!=""&&text_3.getText()!=""){
					alter();
					getShell().dispose();
				}else{
					MessageBox m=new MessageBox(getShell(),SWT.None);
					m.setMessage("Çë½«ÐÅÏ¢ÊäÈëÍêÕû£¡");
					m.open();
					
				}
			}
		});
		button.setBounds(66, 333, 80, 27);
		button.setText("\u786E\u5B9A\u4FEE\u6539");
		
		Button button_1 = new Button(this, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			//È¡Ïû
			public void widgetSelected(SelectionEvent e) {
				getShell().dispose();
			}
		});
		button_1.setBounds(222, 333, 80, 27);
		button_1.setText("\u53D6\u6D88");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	
	public void alter(){
		JDBCTools jt=new JDBCTools();
		String sql="update gonghuoshang set name='"+text.getText()+"',tel='"+text_1.getText()+"',address='"+text_2.getText()+"',people='"+text_3.getText()+"' where id='"+item.getText(0)+"'";
		jt.update(sql);
		jt.close(null);
	}
	protected void createContents() {
		setSize(379, 480);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
