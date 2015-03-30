package cn.com.wei.shell;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;

import cn.com.wei.tools.IsNumerberTools;
import cn.com.wei.tools.JDBCTools;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AlterStaff extends Shell {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Combo combo;
	private static TableItem item;
	MessageBox m=new MessageBox(getShell(),SWT.ICON_INFORMATION);
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void start(TableItem item1) {
		item=item1;
		try {
			Display display = Display.getDefault();
			AlterStaff shell = new AlterStaff(display);
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
	public AlterStaff(Display display) {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(AlterStaff.class, "/img/22.png"));
		setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		setBackgroundMode(SWT.INHERIT_FORCE);
		
		Label label_6 = new Label(this, SWT.NONE);
		label_6.setText("\u4FEE\u6539\u5458\u5DE5\u4FE1\u606F");
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.BOLD));
		label_6.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label_6.setBounds(154, 26, 133, 27);
		
		Label label = new Label(this, SWT.NONE);
		label.setText("\u59D3\u540D\uFF1A");
		label.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label.setBounds(101, 114, 75, 20);
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(182, 114, 152, 23);
		text.setText(item.getText(3));
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setText("\u6027\u522B\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_1.setBounds(101, 162, 75, 20);
		
		combo = new Combo(this, SWT.NONE);
		combo.setItems(new String[] {"\u7537", "\u5973"});
		combo.setBounds(182, 157, 68, 25);
		combo.setText(item.getText(4));
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setText("\u5E74\u9F84\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_2.setBounds(101, 214, 75, 20);
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(182, 211, 152, 23);
		text_1.setText(item.getText(5));
		
		Label label_3 = new Label(this, SWT.NONE);
		label_3.setText("\u8054\u7CFB\u7535\u8BDD\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_3.setBounds(101, 268, 75, 20);
		
		text_2 = new Text(this, SWT.BORDER);
		text_2.setBounds(182, 265, 152, 23);
		text_2.setText(item.getText(6));
		
		Label label_4 = new Label(this, SWT.NONE);
		label_4.setText("\u8EAB\u4EFD\u8BC1\u53F7\uFF1A");
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_4.setBounds(101, 323, 75, 20);
		
		text_3 = new Text(this, SWT.BORDER);
		text_3.setBounds(182, 320, 152, 23);
		text_3.setText(item.getText(7));
		
		Label label_5 = new Label(this, SWT.NONE);
		label_5.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label_5.setBounds(0, 0, 710, 73);
		
		Button button = new Button(this, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			//确定修改
			public void widgetSelected(SelectionEvent e) {
				if(kun() && age() && idcard() ){
					JDBCTools jt=new JDBCTools();
					String sql="update staff set name='"+text.getText()+"',sex='"+combo.getText()+"',age='"+text_1.getText()+"',tel='"+text_2.getText()+"',idcard='"+text_3.getText()+"' where id='"+item.getText(0)+"'";
					jt.update(sql);
					jt.close(null);
					getShell().dispose();
				}
			}
		});
		button.setBounds(101, 385, 80, 27);
		button.setText("\u786E\u5B9A\u4FEE\u6539");
		
		Button button_1 = new Button(this, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getShell().dispose();
			}
		});
		button_1.setBounds(254, 385, 80, 27);
		button_1.setText("\u53D6\u6D88\u4FEE\u6539");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	//信息判断不为空
		public boolean kun(){
			if(text.getText().isEmpty() || text_1.getText().isEmpty() || text_2.getText().isEmpty() || text_3.getText().isEmpty() ){
				m.setMessage("请将信息输入完整！");
				m.open();
				return false;
			}
			return true;
		}
		//判断年龄合法
		public boolean age(){
			if(IsNumerberTools.isNumeric(text_1.getText())){
				if(Integer.parseInt(text_1.getText())>150 || Integer.parseInt(text_1.getText())<=0){
					m.setMessage("年龄不合法！请重新输入！");
					m.open();
					return false;
				}else{
					return true;
				}
			}else{
				
				m.setMessage("年龄为数字！");
				m.open();
				return false;
			}
		}
		//判断身份证号
		public boolean idcard(){
			if(IsNumerberTools.isNumeric(text_3.getText())){
				if(text_3.getText().length()==18 || text_3.getText().length()==15){
				return true;	
				}else{
					m.setMessage("身份证号码不合法，请输入18位或15位身份证号码！");
					m.open();
					return false;
				}
				
			}else{
				m.setMessage("身份证号码不合法！");
				m.open();
				return false;
			}
		}
	protected void createContents() {
		setText("\u4FEE\u6539\u5458\u5DE5\u4FE1\u606F");
		setSize(450, 480);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
