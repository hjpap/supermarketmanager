package cn.com.wei.shell;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import cn.com.wei.tools.DateTools;
import cn.com.wei.tools.IsNumerberTools;
import cn.com.wei.tools.JDBCTools;

public class AddGrounding extends Shell {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private static TableItem item;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private Text text_8;
	private Text text_9;
	MessageBox m=new MessageBox(getShell(),SWT.None);
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void start(TableItem item1) {
		try {
			item=item1;
			Display display = Display.getDefault();
			AddGrounding shell = new AddGrounding(display);
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
	public AddGrounding(Display display) {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(AddGrounding.class, "/img/22.png"));
		setBackgroundMode(SWT.INHERIT_FORCE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		
		Label label_11 = new Label(this, SWT.NONE);
		label_11.setText("\u5546\u54C1\u4E0A\u67B6");
		label_11.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.BOLD));
		label_11.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label_11.setBounds(56, 21, 92, 27);
		
		Label label = new Label(this, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label.setBounds(0, 0, 535, 73);
		
		Group group = new Group(this, SWT.NONE);
		group.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		group.setText("\u5546\u54C1\u4E0A\u67B6");
		group.setBounds(30, 96, 474, 352);
		
		Label label_1 = new Label(group, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_1.setText("\u8FDB\u8D27\u6279\u6B21\uFF1A");
		label_1.setBounds(45, 42, 75, 26);
		
		Label label_2 = new Label(group, SWT.NONE);
		label_2.setText("\u5546\u54C1\u7F16\u53F7\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_2.setBounds(45, 97, 75, 26);
		
		Label label_3 = new Label(group, SWT.NONE);
		label_3.setText("\u5546\u54C1\u540D\u79F0\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_3.setBounds(45, 154, 75, 26);
		
		Label label_4 = new Label(group, SWT.NONE);
		label_4.setText("\u4E0A\u67B6\u6570\u91CF\uFF1A");
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.BOLD));
		label_4.setBounds(45, 303, 75, 26);
		
		Label label_5 = new Label(group, SWT.NONE);
		label_5.setText("\u5E93\u5B58\u6570\u91CF\uFF1A");
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_5.setBounds(237, 201, 75, 26);
		
		text = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		text.setBounds(124, 42, 188, 23);
		text.setText(item.getText(0));
		
		text_1 = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		text_1.setBounds(124, 97, 107, 23);
		text_1.setText(item.getText(1));
		
		
		text_2 = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		text_2.setBounds(124, 154, 107, 23);
		text_2.setText(item.getText(2));
		
		
		text_3 = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		text_3.setBounds(124, 201, 107, 23);
		text_3.setText(item.getText(3));
		
		text_4 = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		text_4.setBounds(124, 253, 107, 23);
		text_4.setText(item.getText(4));
		
		Label label_6 = new Label(group, SWT.NONE);
		label_6.setText("\u5546\u54C1\u5355\u4F4D\uFF1A");
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_6.setBounds(45, 201, 75, 26);
		
		Label label_7 = new Label(group, SWT.NONE);
		label_7.setText("\u5546\u54C1\u7C7B\u578B\uFF1A");
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_7.setBounds(45, 253, 75, 26);
		
		Label label_8 = new Label(group, SWT.NONE);
		label_8.setText("\u8FDB\u8D27\u4EF7\u683C\uFF1A");
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_8.setBounds(237, 97, 75, 26);
		
		Label label_9 = new Label(group, SWT.NONE);
		label_9.setText("\u96F6\u552E\u4EF7\u683C\uFF1A");
		label_9.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_9.setBounds(237, 154, 75, 26);
		
		Label label_10 = new Label(group, SWT.NONE);
		label_10.setText("\u62A5\u8B66\u6570\uFF1A");
		label_10.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.BOLD));
		label_10.setBounds(252, 303, 60, 26);
		
		text_5 = new Text(group, SWT.BORDER);
		text_5.setBounds(126, 303, 107, 23);
		
		text_6 = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		text_6.setBounds(318, 97, 107, 23);
		text_6.setText(item.getText(5));
		
		
		text_7 = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		text_7.setBounds(318, 154, 107, 23);
		text_7.setText(item.getText(6));
		
		text_8 = new Text(group, SWT.BORDER);
		text_8.setBounds(318, 303, 107, 23);
		
		text_9 = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		text_9.setBounds(318, 201, 107, 23);
		text_9.setText(item.getText(11));
		
		Button button = new Button(this, SWT.NONE);
		button.setBounds(129, 482, 80, 27);
		button.addSelectionListener(new SelectionAdapter() {
			@Override//确定上架
			
			public void widgetSelected(SelectionEvent e) {
				if(IsNumerberTools.isNumeric(text_5.getText())&&IsNumerberTools.isNumeric(text_8.getText())){
					if(text_5.getText()!=""&&text_8.getText()!=""){
					add();
					getShell().dispose();
				}else{
					m.setMessage("请输入上架数量和报警数！");
					m.open();
				}
				}else{
					m.setMessage("请输入数字！");
					m.open();
				}
				
				
				
			}
		});
		button.setText("\u786E\u5B9A");
		
		Button button_1 = new Button(this, SWT.NONE);
		button_1.setBounds(307, 482, 80, 27);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override//取消
			public void widgetSelected(SelectionEvent e) {
				getShell().dispose();
			}
		});
		button_1.setText("\u53D6\u6D88");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	public void add(){
		JDBCTools jt=new JDBCTools();
		String sql="select count(*) as number from grounding where code='"+text_1.getText()+"'";
		ResultSet rs=jt.query(sql);
		try {
			rs.absolute(1);
			if(rs.getString("number").equals("1")){
				//上架表中有该商品 修改上架数量     /库存表中减少。商品信息表中库存减少
				if(Integer.parseInt(text_5.getText())<=Integer.parseInt(text_9.getText())){
					String sql1="update grounding set sjcount=sjcount+"+text_5.getText()+",dangers='"+text_8.getText()+"' where code='"+text_1.getText()+"'";
					String sql2="update kucun set kcount=kcount-"+text_5.getText()+" where billcode='"+text.getText()+"' && code='"+text_1.getText()+"'";
					String sql3="update goods set sp_kucun=sp_kucun-"+text_5.getText()+" where sp_code='"+text_1.getText()+"'";
					jt.update(sql3);
					jt.update(sql2);
					jt.update(sql1);
					//System.out.println(sql1+"\n"+sql2+"\n"+sql3);
				}else{
					
					m.setMessage("库存不足！");
					m.open();
				}
				
				
			}else{
				//上架表中没有该商品 添加商品      /库存表中减少。商品信息表中减少 
				if(Integer.parseInt(text_5.getText())<=Integer.parseInt(text_9.getText())){
					String sql1="insert into grounding(code,name,unit,kind,cost,retail,sjcount,dangers,date) values('"+text_1.getText()+"','"+text_2.getText()+"','"+text_3.getText()+"','"+text_4.getText()+"','"+text_6.getText()+"','"+text_7.getText()+"','"+text_5.getText()+"','"+text_8.getText()+"','"+DateTools.getDateTime()+"')";
					String sql2="update kucun set kcount=kcount-"+text_5.getText()+" where billcode='"+text.getText()+"' && code='"+text_1.getText()+"'";
					String sql3="update goods set sp_kucun=sp_kucun-"+text_5.getText()+" where sp_code='"+text_1.getText()+"'";
					//System.out.println(sql1+"\n"+sql2+"\n"+sql3);
					jt.update(sql3);
					jt.update(sql2);
					jt.update(sql1);
					
				}else{
					
					m.setMessage("库存不足！");
					m.open();
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		jt.close(rs);
	}
	protected void createContents() {
		setText("\u5546\u54C1\u4E0A\u67B6");
		setSize(551, 578);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
