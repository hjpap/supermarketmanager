package cn.com.wei.shell;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import cn.com.wei.tools.IsNumerberTools;
import cn.com.wei.tools.JDBCTools;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class AlterBuyCount extends Shell {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
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
			AlterBuyCount shell = new AlterBuyCount(display);
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
	public AlterBuyCount(Display display) {
		super(display, SWT.SHELL_TRIM);
		setBackgroundMode(SWT.INHERIT_FORCE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		setImage(SWTResourceManager.getImage(AlterBuyCount.class, "/img/22.png"));
		
		Label label_5 = new Label(this, SWT.NONE);
		label_5.setText("\u4FEE\u6539\u5546\u54C1\u6570\u91CF");
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.BOLD));
		label_5.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label_5.setBounds(112, 22, 130, 27);
		
		Label label = new Label(this, SWT.NONE);
		label.setBounds(89, 131, 61, 17);
		label.setText("\u5546\u54C1\u7F16\u53F7\uFF1A");
		
		text = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text.setBounds(173, 125, 95, 23);
		text.setText(item.getText(0));
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setBounds(89, 170, 61, 17);
		label_1.setText("\u5546\u54C1\u540D\u79F0\uFF1A");
		
		text_1 = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text_1.setBounds(173, 164, 95, 23);
		text_1.setText(item.getText(1));
		
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setBounds(89, 209, 61, 17);
		label_2.setText("\u73B0\u6709\u6570\u91CF\uFF1A");
		
		text_2 = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text_2.setBounds(173, 209, 95, 23);
		text_2.setText(item.getText(4));
		
		Label label_3 = new Label(this, SWT.NONE);
		label_3.setBounds(89, 250, 61, 17);
		label_3.setText("\u4FEE\u6539\u4E3A\uFF1A");
		
		text_3 = new Text(this, SWT.BORDER);
		
		text_3.setBounds(173, 244, 95, 23);
		text_3.setFocus();
		
		
		Button button = new Button(this, SWT.NONE);
		
		button.setBounds(89, 295, 80, 27);
		button.setText("\u786E\u5B9A");
		
		Button button_1 = new Button(this, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			//取消按钮
			public void widgetSelected(SelectionEvent e) {
				getShell().dispose();
			}
		});
		button_1.setBounds(188, 295, 80, 27);
		button_1.setText("\u53D6\u6D88");
		
		Label label_4 = new Label(this, SWT.NONE);
		label_4.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label_4.setBounds(0, 0, 365, 73);
		createContents();
		
		text_3.addKeyListener(new KeyAdapter() {
			//回车
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.CR){
					if(text_3.getText().equals("")){
						m.setMessage("请输入正确的个数(大于零的正整数)!");
						m.open();
					}else{
						if(IsNumerberTools.isNumeric(text_3.getText())){
							
							if(Integer.parseInt(text_3.getText())<=0){
								m.setMessage("请输入大于零的正整数！");
								m.open();
							}else{
								JDBCTools jt=new JDBCTools();
								String sql="UPDATE cashier SET COUNT="+text_3.getText()+" ,total=COUNT*retail*zhekou  WHERE CODE="+text.getText()+"";
								jt.update(sql);
								jt.close(null);
								getShell().dispose();
							}
							
						}else{
							m.setMessage("请正确输入个数(大于零的正整数)!");
							m.open();
						}
						
					}
				}
			}
		});
		
		button.addSelectionListener(new SelectionAdapter() {
			//确定修改数量按钮
			public void widgetSelected(SelectionEvent e) {
				if(text_3.getText().equals("")){
					m.setMessage("请输入正确的个数(大于零的正整数)!");
					m.open();
				}else{
					if(IsNumerberTools.isNumeric(text_3.getText())){
						
						if(Integer.parseInt(text_3.getText())<=0){
							m.setMessage("请输入大于零的正整数！");
							m.open();
						}else{
							JDBCTools jt=new JDBCTools();
							String sql="UPDATE cashier SET COUNT="+text_3.getText()+" ,total=COUNT*retail*zhekou  WHERE CODE="+text.getText()+"";
							jt.update(sql);
							jt.close(null);
							getShell().dispose();
						}
						
					}else{
						m.setMessage("请正确输入个数(大于零的正整数)!");
						m.open();
					}
					
				}
			}
		});
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("\u4FEE\u6539\u5546\u54C1\u6570\u91CF");
		setSize(362, 390);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
