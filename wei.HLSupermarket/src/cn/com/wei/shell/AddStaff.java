package cn.com.wei.shell;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;

import cn.com.wei.tools.IsNumerberTools;
import cn.com.wei.tools.JDBCTools;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AddStaff extends Shell {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Combo combo;
	private Combo combo_1;
	MessageBox m=new MessageBox(getShell(),SWT.None);
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void start() {
		try {
			Display display = Display.getDefault();
			AddStaff shell = new AddStaff(display);
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
	public AddStaff(Display display) {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(AddStaff.class, "/img/22.png"));
		setBackgroundMode(SWT.INHERIT_FORCE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		
		Label label_10 = new Label(this, SWT.NONE);
		label_10.setText("\u6DFB\u52A0\u65B0\u5458\u5DE5");
		label_10.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.BOLD));
		label_10.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label_10.setBounds(75, 24, 128, 27);
		
		Label label = new Label(this, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label.setBounds(0, 0, 710, 73);
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_1.setBounds(54, 111, 75, 20);
		label_1.setText("\u59D3\u540D\uFF1A");
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(135, 111, 152, 23);
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setText("\u6027\u522B\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_2.setBounds(54, 159, 75, 20);
		
		combo = new Combo(this, SWT.NONE);
		combo.setItems(new String[] {"\u7537", "\u5973"});
		combo.setBounds(135, 154, 68, 25);
		combo.select(0);
		
		Label label_3 = new Label(this, SWT.NONE);
		label_3.setText("\u5E74\u9F84\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_3.setBounds(54, 211, 75, 20);
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(135, 208, 152, 23);
		
		Label label_4 = new Label(this, SWT.NONE);
		label_4.setText("\u8054\u7CFB\u7535\u8BDD\uFF1A");
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_4.setBounds(54, 265, 75, 20);
		
		text_2 = new Text(this, SWT.BORDER);
		text_2.setBounds(135, 262, 152, 23);
		
		Label label_5 = new Label(this, SWT.NONE);
		label_5.setText("\u8EAB\u4EFD\u8BC1\u53F7\uFF1A");
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_5.setBounds(54, 320, 75, 20);
		
		text_3 = new Text(this, SWT.BORDER);
		text_3.setBounds(135, 317, 152, 23);
		
		Group group = new Group(this, SWT.NONE);
		group.setBounds(307, 101, 295, 239);
		
		Label label_6 = new Label(group, SWT.NONE);
		label_6.setText("\u767B\u5F55\u540D\uFF1A");
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_6.setBounds(10, 28, 75, 20);
		
		text_4 = new Text(group, SWT.BORDER);
		text_4.setBounds(91, 25, 152, 23);
		
		Label label_7 = new Label(group, SWT.NONE);
		label_7.setText("\u767B\u5F55\u5BC6\u7801\uFF1A");
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_7.setBounds(10, 77, 75, 20);
		
		text_5 = new Text(group, SWT.BORDER | SWT.PASSWORD);
		text_5.setBounds(91, 77, 152, 23);
		
		Label label_8 = new Label(group, SWT.NONE);
		label_8.setText("\u91CD\u590D\u5BC6\u7801\uFF1A");
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_8.setBounds(10, 131, 75, 20);
		
		text_6 = new Text(group, SWT.BORDER | SWT.PASSWORD);
		text_6.setBounds(91, 131, 152, 23);
		
		Label label_9 = new Label(group, SWT.NONE);
		label_9.setText("\u767B\u5F55\u6743\u9650\uFF1A");
		label_9.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_9.setBounds(10, 179, 75, 20);
		
		combo_1 = new Combo(group, SWT.NONE);
		combo_1.setItems(new String[] {"\u6536\u94F6\u5458", "\u7BA1\u7406\u5458", "\u7ECF\u7406"});
		combo_1.setBounds(91, 174, 152, 25);
		combo_1.select(0);
		
		Button button = new Button(this, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			//添加员工信息
			public void widgetSelected(SelectionEvent e) {
				if(kun() && age() && idcard() && loginname()){
					addStaff();
					getShell().dispose();
				}
			}
		});
		button.setBounds(421, 365, 80, 27);
		button.setText("\u6DFB\u52A0");
		
		Button button_1 = new Button(this, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			//取消
			public void widgetSelected(SelectionEvent e) {
				getShell().dispose();
			}
		});
		button_1.setText("\u53D6\u6D88");
		button_1.setBounds(526, 365, 80, 27);
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	
	//添加
	public void addStaff(){
		JDBCTools jt=new JDBCTools();
		String sql="insert into staff(user_name,limits,name,sex,age,tel,idcard) values('"+text_4.getText()+"','"+combo_1.getText()+"','"+text.getText()+"','"+combo.getText()+"','"+text_1.getText()+"','"+text_2.getText()+"','"+text_3.getText()+"')";
		String sql1="insert into user_info values('"+text_4.getText()+"','"+text_6.getText()+"','"+combo_1.getText()+"')";
		System.out.println(sql1);
		jt.update(sql);
		jt.update(sql1);
		
		jt.close(null);
	}
	//登录名 密码
	public boolean loginname(){
		JDBCTools jt=new JDBCTools();
		String sql="select * from user_info where user='"+text_4.getText()+"'";
		ResultSet rs=jt.query(sql);
		try {
			if(rs.absolute(1)){
				m.setMessage("已存在该登录名！");
				m.open();
				jt.close(rs);
				return false;
			}else{
				
				if(text_5.getText().equals(text_6.getText())){
					jt.close(rs);
					return true;
				}else{
					m.setMessage("两次密码不相同！");
					m.open();
					jt.close(rs);
					return false;
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		jt.close(rs);
		return false;
	}
	//信息判断不为空
	public boolean kun(){
		if(text.getText().isEmpty() || text_1.getText().isEmpty() || text_2.getText().isEmpty() || text_3.getText().isEmpty() || text_4.getText().isEmpty() || text_5.getText().isEmpty() || text_6.getText().isEmpty()){
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
		setText("\u6DFB\u52A0\u65B0\u5458\u5DE5");
		setSize(686, 440);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
