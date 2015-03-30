package cn.com.wei.shell;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import cn.com.wei.tools.JDBCTools;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class LoginShell extends Shell {
	private Text text;
	private Text text_1;
	private Combo combo;
	private Label label;
	private static String limits="无";  //获取权限
	private static boolean isLogin=false; //判断可否登录
	private static String loginUsername="";//取出登录的用户名
	
    MessageBox m=new MessageBox(getShell(),SWT.ICON_INFORMATION);
	
    
    
    
	public static String getLimits() {
		return limits;
	}

	public void setLimits(String limits) {
		LoginShell.limits = limits;
	}

	public static boolean isLogin() {
		return isLogin;
	}

	public static void setLogin(boolean isLogin) {
		LoginShell.isLogin = isLogin;
	}

	public static String getLoginUsername() {
		return loginUsername;
	}

	public static void setLoginUsername(String loginUsername) {
		LoginShell.loginUsername = loginUsername;
	}

	public static void start() {
		try {
			Display display = Display.getDefault();
			LoginShell shell = new LoginShell(display);
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
	public LoginShell(Display display) {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(LoginShell.class, "/img/22.png"));
		setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		
		/*Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblNewLabel.setBounds(100, 154, 61, 17);
		lblNewLabel.setText("\u7528\u6237\u7C7B\u578B\uFF1A");*/
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblNewLabel_1.setBounds(100, 183, 61, 17);
		lblNewLabel_1.setText("\u7528\u6237\u540D\uFF1A");
		
		Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblNewLabel_2.setBounds(100, 223, 61, 17);
		lblNewLabel_2.setText("\u5BC6   \u7801\uFF1A");
		
		text = new Text(this, SWT.BORDER);
		text.addKeyListener(new KeyAdapter() {
			//回车登录
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.CR){
					if(right()){
						isLogin=true;
						getShell().dispose();
					}
				}
			}
		});
		text.setBounds(167, 180, 139, 23);
		
		text_1 = new Text(this, SWT.BORDER | SWT.PASSWORD);
		text_1.addKeyListener(new KeyAdapter() {
			//按回车 登录
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.CR){
					if(right()){
						isLogin=true;
						getShell().dispose();
					}
				}
				
			}
		});
		text_1.setBounds(167, 220, 139, 23);
		
		/*combo = new Combo(this, SWT.READ_ONLY);
		
		combo.addKeyListener(new KeyAdapter() {
			//回车登录
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.CR){
					if(right()){
						isLogin=true;
						getShell().dispose();
					}
				}
			}
		});
		combo.setItems(new String[] {"\u6536\u94F6\u5458", "\u7BA1\u7406\u5458", "\u7ECF\u7406"});
		combo.setBounds(167, 151, 139, 25);*/
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			//登录 按钮方法
			public void widgetSelected(SelectionEvent e) {
				
				if(right()){
					isLogin=true;
					getShell().dispose();
				}
				
			}
		});
		btnNewButton.setBounds(100, 261, 80, 27);
		btnNewButton.setText("\u767B\u5F55");
		
		Button btnNewButton_1 = new Button(this, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			//取消 按钮方法
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(226, 261, 80, 27);
		btnNewButton_1.setText("\u53D6\u6D88");
		
		label = new Label(this, SWT.NONE);
		label.setImage(SWTResourceManager.getImage(LoginShell.class, "/img/logo1.jpg"));
		label.setBounds(0, 0, 405, 142);
		createContents();
		
		/*combo.addSelectionListener(new SelectionAdapter() {
			//根据combo选项切换图片
			
			public void widgetSelected(SelectionEvent e) {
				//经理的图片
				if(combo.getText().equals("经理")){
					label.setImage(SWTResourceManager.getImage(LoginShell.class, "/img/logo2.png"));
				}else if(combo.getText().equals("管理员")){
					//商品管理员的图片
					label.setImage(SWTResourceManager.getImage(LoginShell.class, "/img/logo3.png"));
				}else{
					//收银员的图片
					label.setImage(SWTResourceManager.getImage(LoginShell.class, "/img/logo4.png"));
				}
				
			}
		});*/
	}

	/**
	 * Create contents of the shell.
	 */
	//登录前 验证数据库中是否有输入的用户名 用户名是否合法
	public boolean right(){
		boolean is=false;
		
			if(text.getText()==""||text_1.getText()==""){
				m.setMessage("请输入用户名或密码！");
				m.open();
				
				
			}else{
				JDBCTools jt=new JDBCTools();
				String sql="select *  from user_info where user='"+text.getText()+"' and password='"+text_1.getText()+"'";
				ResultSet rs=jt.query(sql);
				
				
				try {
					
						if(rs.absolute(1)){
							setLoginUsername(text.getText());
							setLimits(rs.getString("limits"));
							is=true;
						    
						}else{
							
							m.setMessage("用户名或密码错误，请重新输入！");
							m.open();
							
						}
						
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			jt.close(rs);
			}	
		
		
		
		return is;
	}
	
	
	protected void createContents() {
		setText("\u6D77\u91CF\u8D85\u5E02\u8FDB\u9500\u7BA1\u7406\u7CFB\u7EDF");
		setSize(421, 349);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
