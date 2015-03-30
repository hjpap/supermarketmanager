package cn.com.wei.shell;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import cn.com.wei.tools.DateTools;
import cn.com.wei.tools.JDBCTools;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Notice extends Shell {
	private Text text;
	private Text text_1;
	private Text text_2;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void start() {
		try {
			Display display = Display.getDefault();
			Notice shell = new Notice(display);
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
	
	
	
	public Notice(Display display) {
		super(display, SWT.NONE);
		setImage(SWTResourceManager.getImage(Notice.class, "/img/22.png"));
		setBackgroundMode(SWT.INHERIT_FORCE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		
		Label label = new Label(this, SWT.NONE);
		label.setText("\u4ECA\u65E5\u63D0\u9192");
		label.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.BOLD));
		label.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label.setBounds(72, 22, 130, 27);
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label_1.setBounds(0, 0, 618, 73);
		
		Group group = new Group(this, SWT.NONE);
		group.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		group.setText("\u5E93\u5B58\u4FE1\u606F");
		group.setBounds(10, 79, 575, 173);
		
		Label label_2 = new Label(group, SWT.NONE);
		label_2.setBounds(48, 31, 84, 17);
		label_2.setText("\u5E93\u5B58\u4E0D\u8DB3\u63D0\u9192\uFF1A");
		
		Label label_3 = new Label(group, SWT.NONE);
		label_3.setText("\u5E93\u5B58\u8FC7\u671F\u63D0\u9192\uFF1A");
		label_3.setBounds(48, 108, 84, 17);
		
		text = new Text(group, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		text.setBounds(138, 28, 427, 53);
		
		text_1 = new Text(group, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		text_1.setBounds(138, 108, 427, 53);
		
		Group group_1 = new Group(this, SWT.NONE);
		group_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		group_1.setText("\u8D27\u67B6\u4FE1\u606F");
		group_1.setBounds(10, 258, 575, 110);
		
		Label label_4 = new Label(group_1, SWT.NONE);
		label_4.setText("\u8D27\u67B6\u4E0D\u8DB3\u63D0\u9192\uFF1A");
		label_4.setBounds(48, 50, 84, 17);
		
		text_2 = new Text(group_1, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		text_2.setBounds(139, 47, 426, 53);
		
		Button button = new Button(this, SWT.NONE);
		button.setFocus();
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getShell().dispose();
			}
		});
		button.setBounds(505, 374, 80, 27);
		button.setText("\u5173\u95ED");
		createContents();
		kucunNotice();
		grounding();
	}

	/**
	 * Create contents of the shell.
	 */
	//货架不足提醒
	public void grounding(){
		JDBCTools jt=new JDBCTools();
		String sql="select * from grounding where sjcount<=dangers";
		ResultSet rs=jt.query(sql);
		
		try {
			while(rs.next()){
				String a="商品编号："+rs.getString("code")+" 商品名称："+rs.getString("name")+"\n";
				text_2.setText(text_2.getText()+a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jt.close(rs);
	}
	
	//库存提醒
		public void kucunNotice(){
			JDBCTools jt=new JDBCTools();
			String sql="select * from kucun where kcount<=dangerous && kcount>0";
			String sql1="select DATE_SUB(baddate,INTERVAL 30 DAY) as kuaiguoqi,billcode,code,name,baddate from kucun where kcount>0";
			ResultSet rs=jt.query(sql);
			ResultSet rs1=jt.query(sql1);
			try {
				while(rs.next()){
					String a="商品批次："+rs.getString("billcode")+" 商品编号："+rs.getString("code")+" 商品名称："+rs.getString("name")+"\n";
					text.setText(text.getText()+a);
						
				}
				
				while(rs1.next()){
					if(rs1.getDate("kuaiguoqi").compareTo(Date.valueOf(DateTools.getDateTime()))>0){
						//没过期
						continue;
					}else if(rs1.getDate("kuaiguoqi").compareTo(Date.valueOf(DateTools.getDateTime()))<0 && rs1.getDate("baddate").compareTo(Date.valueOf(DateTools.getDateTime()))>0){
						//即将过期
						String a="商品批次："+rs1.getString("billcode")+" 商品编号："+rs1.getString("code")+" 商品名称："+rs1.getString("name")+"\n";
						text_1.setText(text_1.getText()+a);
						
					}else if(rs1.getDate("baddate").compareTo(Date.valueOf(DateTools.getDateTime()))<=0){
						//过期
						String a="商品批次："+rs1.getString("billcode")+" 商品编号："+rs1.getString("code")+" 商品名称："+rs1.getString("name")+"\n";
						text_1.setText(text_1.getText()+a);
						
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			jt.close(rs);
			jt.close(rs1);
		}
		
		
	protected void createContents() {
		setText("\u4ECA\u65E5\u63D0\u9192");
		setSize(611, 419);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
