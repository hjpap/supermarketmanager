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

import cn.com.wei.tools.IsNumerberTools;
import cn.com.wei.tools.JDBCTools;

public class AlterVipKind extends Shell {
	private Text text;
	private Text text_1;
	private Text text_2;
	private static TableItem item;
	MessageBox m=new MessageBox(getShell(),SWT.None);

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void start(TableItem item1) {
		try {
			item=item1;
			Display display = Display.getDefault();
			AlterVipKind shell = new AlterVipKind(display);
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
	public AlterVipKind(Display display) {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(AlterVipKind.class, "/img/22.png"));
		setBackgroundMode(SWT.INHERIT_FORCE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		
		Label label_6 = new Label(this, SWT.NONE);
		label_6.setText("\u4FEE\u6539");
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.BOLD));
		label_6.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label_6.setBounds(56, 23, 92, 27);
		
		Label label = new Label(this, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label.setBounds(0, 0, 602, 73);
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setText("\u7B49\u7EA7\u540D\u79F0\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_1.setBounds(30, 117, 80, 26);
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(116, 117, 116, 23);
		text.setText(item.getText(1));
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setText("\u6240\u9700\u6D88\u8D39\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_2.setBounds(30, 166, 80, 26);
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(116, 166, 116, 23);
		text_1.setText(item.getText(3));
		
		
		Label label_3 = new Label(this, SWT.NONE);
		label_3.setText("\u4EAB\u53D7\u6298\u6263\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_3.setBounds(30, 221, 80, 26);
		
		text_2 = new Text(this, SWT.BORDER);
		text_2.setBounds(116, 221, 116, 23);
		text_2.setText(item.getText(2));
		
		Label label_4 = new Label(this, SWT.NONE);
		label_4.setText("\uFF08\u5347\u7EA7\u5230\u8BE5\u7B49\u7EA7\u6240\u9700\u7684\u6D88\u8D39\uFF09");
		label_4.setBounds(248, 168, 158, 17);
		
		Label label_5 = new Label(this, SWT.NONE);
		label_5.setText("\uFF08\u8BE5\u7B49\u7EA7\u4EAB\u53D7\u7684\u6298\u6263\u4F18\u60E0\u3002\u4F8B\u5982\uFF1A1\u4E3A\u539F\u4EF7\uFF0C0.88\u4E3A\u516B\u516B\u6298\uFF09");
		label_5.setBounds(248, 222, 330, 17);
		
		Button button = new Button(this, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			//确定修改
			public void widgetSelected(SelectionEvent e) {
				if(text.getText()!=""&&text_1.getText()!=""&&text_2.getText()!=""){
					if(IsNumerberTools.isNumeric(text_1.getText())&&IsNumerberTools.isNumeric(text_2.getText())){
						update();
						getShell().dispose();
					}else{
						m.setMessage("折扣率和所需消费应为数字！");
						m.open();
					}
					
				}else{
					m.setMessage("请输入完整信息！");
					m.open();
				}
			}
		});
		button.setBounds(152, 289, 80, 27);
		button.setText("\u786E\u5B9A\u4FEE\u6539");
		
		Button button_1 = new Button(this, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			//取消修改
			public void widgetSelected(SelectionEvent e) {
				getShell().dispose();
			}
		});
		button_1.setBounds(371, 289, 80, 27);
		button_1.setText("\u53D6\u6D88\u4FEE\u6539");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	
	
	//修改
	public void update(){
		JDBCTools jt=new JDBCTools();
		String sql="UPDATE vip_kind SET kind='"+text.getText()+"',zhekou='"+text_2.getText()+"',Hcondition='"+text_1.getText()+"' WHERE id='"+item.getText(0)+"'";
		
		jt.update(sql);
		jt.close(null);
		
	}
	
	
	protected void createContents() {
		setText("\u4FEE\u6539");
		setSize(618, 416);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
