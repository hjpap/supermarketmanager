package cn.com.wei.shell;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;

import cn.com.wei.tools.DateTools;
import cn.com.wei.tools.JDBCTools;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Xiajia extends Shell {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Combo combo;
	private static TableItem item;
	MessageBox m=new MessageBox(getShell(),SWT.ICON_INFORMATION);

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void start(TableItem item1) {
		try {
			item=item1;
			Display display = Display.getDefault();
			Xiajia shell = new Xiajia(display);
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
	public Xiajia(Display display) {
		super(display, SWT.MAX | SWT.RESIZE | SWT.TITLE);
		setBackgroundMode(SWT.INHERIT_FORCE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		setImage(SWTResourceManager.getImage(Xiajia.class, "/img/22.png"));
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(51, 204, 255));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.BOLD));
		lblNewLabel_1.setBounds(144, 22, 92, 27);
		lblNewLabel_1.setText("\u5546\u54C1\u4E0B\u67B6");
		
		Label label = new Label(this, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label.setBounds(0, 0, 369, 73);
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setBounds(57, 124, 61, 17);
		label_1.setText("\u5546\u54C1\u7F16\u53F7\uFF1A");
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setText("\u5546\u54C1\u540D\u79F0\uFF1A");
		label_2.setBounds(57, 170, 61, 17);
		
		Label label_3 = new Label(this, SWT.NONE);
		label_3.setText("\u8FDB\u8D27\u4EF7\u683C\uFF1A");
		label_3.setBounds(57, 220, 61, 17);
		
		Label label_4 = new Label(this, SWT.NONE);
		label_4.setText("\u96F6\u552E\u4EF7\uFF1A");
		label_4.setBounds(68, 265, 50, 17);
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(57, 310, 61, 17);
		lblNewLabel.setText("\u4E0A\u67B6\u6570\u91CF\uFF1A");
		
		text = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text.setBounds(133, 121, 155, 23);
		text.setText(item.getText(0));
		
		text_1 = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text_1.setBounds(133, 167, 155, 23);
		text_1.setText(item.getText(1));
		
		text_2 = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text_2.setBounds(133, 217, 155, 23);
		text_2.setText(item.getText(4));
		
		text_3 = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text_3.setBounds(133, 262, 155, 23);
		text_3.setText(item.getText(5));
		
		text_4 = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text_4.setBounds(133, 307, 155, 23);
		text_4.setText(item.getText(6));
		
		Group group = new Group(this, SWT.NONE);
		group.setBounds(10, 349, 349, 156);
		
		Label label_5 = new Label(group, SWT.NONE);
		label_5.setBounds(46, 24, 60, 17);
		label_5.setText("\u4E0B\u67B6\u6570\u91CF\uFF1A");
		
		text_5 = new Text(group, SWT.BORDER);
		text_5.setBounds(125, 21, 152, 23);
		
		Label label_6 = new Label(group, SWT.NONE);
		label_6.setBounds(46, 65, 60, 17);
		label_6.setText("\u4E0B\u67B6\u539F\u56E0\uFF1A");
		
		combo = new Combo(group, SWT.NONE);
		combo.setBounds(125, 62, 152, 25);
		
		Button button = new Button(group, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			//添加下架原因
			public void widgetSelected(SelectionEvent e) {
				UpdateXiajiaKind.start();
				getKind();
			}
		});
		button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		button.setBounds(288, 60, 39, 27);
		button.setText("+");
		
		Button button_1 = new Button(group, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			//确定下架
			public void widgetSelected(SelectionEvent e) {
				if(text_5.getText().isEmpty() || combo.getText().isEmpty()){
					//输入完整数据
					m.setMessage("请将信息填写完整!");
					m.open();
				}else{
					if(Integer.parseInt(text_5.getText())>Integer.parseInt(text_4.getText())){
						//超出上架数
						m.setMessage("下架数量超过上架数量！");
						m.open();
					}else{
						xiajia();
						getShell().dispose();
					}
				}
			}
		});
		button_1.setBounds(49, 107, 80, 27);
		button_1.setText("\u786E\u5B9A\u4E0B\u67B6");
		
		Button button_2 = new Button(group, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			//取消
			public void widgetSelected(SelectionEvent e) {
				getShell().dispose();
			}
		});
		button_2.setBounds(210, 107, 80, 27);
		button_2.setText("\u53D6\u6D88\u4E0B\u67B6");
		createContents();
		getKind();
	}

	/**
	 * Create contents of the shell.
	 */
	//下架进损耗 //上架数减少
	public void xiajia(){
		JDBCTools jt=new JDBCTools();
		String sql="insert into sunhao(code,name,cost,retail,xcount,total,yuanyin,date) values('"+text.getText()+"','"+text_1.getText()+"','"+text_2.getText()+"','"+text_3.getText()+"','"+text_5.getText()+"',"+Double.parseDouble(text_2.getText())*Double.parseDouble(text_5.getText())+",'"+combo.getText()+"','"+DateTools.getDateTime()+"')";
		String sql1="update grounding set sjcount=sjcount-"+text_5.getText()+" where code='"+text.getText()+"'";
		jt.update(sql1);
		jt.update(sql);
		jt.close(null);
		
	}
	/*//下架进库存 //上架数减少
	public void ruku(){
		JDBCTools jt=new JDBCTools();
		String sql="";
	}
	*/
	//获取下架原因
		public void getKind(){
			JDBCTools jt=new JDBCTools();
			String sql="select count(*) as number from xiajia_kind";
			String sql1="select * from xiajia_kind";
			
			ResultSet rs=jt.query(sql);
			ResultSet rs1=jt.query(sql1);
			try {
				rs.absolute(1);
				int i=rs.getInt("number");
				String[] temp=new String[i];
				while(rs1.next()){
					temp[i-1]=rs1.getString("kind");
					i--;
				}
				combo.setItems(temp);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			jt.close(rs1);
			jt.close(rs);	
			
		}
	protected void createContents() {
		setText("\u5546\u54C1\u4E0B\u67B6");
		setSize(385, 553);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
