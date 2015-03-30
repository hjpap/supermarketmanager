package cn.com.wei.shell;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
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

public class AddSunhao extends Shell {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Combo combo;
	private static TableItem item;
	private Text text_6;
	MessageBox m=new MessageBox(getShell(),SWT.ICON_INFORMATION);

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void start(TableItem item1) {
		try {
			item=item1;
			Display display = Display.getDefault();
			AddSunhao shell = new AddSunhao(display);
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
	public AddSunhao(Display display) {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(AddSunhao.class, "/img/22.png"));
		setBackgroundMode(SWT.INHERIT_FORCE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		
		Label label_9 = new Label(this, SWT.NONE);
		label_9.setText("\u635F\u8017");
		label_9.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.BOLD));
		label_9.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label_9.setBounds(162, 25, 92, 27);
		
		Label label = new Label(this, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label.setBounds(0, 0, 369, 73);
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setText("\u5546\u54C1\u7F16\u53F7\uFF1A");
		label_1.setBounds(57, 151, 61, 17);
		
		text = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text.setBounds(133, 148, 155, 23);
		text.setText(item.getText(1));
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setText("\u5546\u54C1\u540D\u79F0\uFF1A");
		label_2.setBounds(57, 197, 61, 17);
		
		text_1 = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text_1.setBounds(133, 194, 155, 23);
		text_1.setText(item.getText(2));
		
		Label label_3 = new Label(this, SWT.NONE);
		label_3.setText("\u8FDB\u8D27\u4EF7\u683C\uFF1A");
		label_3.setBounds(57, 247, 61, 17);
		
		text_2 = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text_2.setBounds(133, 244, 155, 23);
		text_2.setText(item.getText(5));
		
		Label label_4 = new Label(this, SWT.NONE);
		label_4.setText("\u96F6\u552E\u4EF7\uFF1A");
		label_4.setBounds(68, 292, 50, 17);
		
		text_3 = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text_3.setBounds(133, 289, 155, 23);
		text_3.setText(item.getText(6));
		
		Label label_5 = new Label(this, SWT.NONE);
		label_5.setText("\u5E93\u5B58\u6570\u91CF\uFF1A");
		label_5.setBounds(57, 337, 61, 17);
		
		text_4 = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text_4.setBounds(133, 334, 155, 23);
		text_4.setText(item.getText(11));
		
		Group group = new Group(this, SWT.NONE);
		group.setBounds(10, 376, 349, 156);
		
		Label label_6 = new Label(group, SWT.NONE);
		label_6.setText("\u635F\u8017\u6570\u91CF\uFF1A");
		label_6.setBounds(46, 24, 60, 17);
		
		text_5 = new Text(group, SWT.BORDER);
		text_5.setBounds(125, 21, 152, 23);
		
		Label label_7 = new Label(group, SWT.NONE);
		label_7.setText("\u635F\u8017\u539F\u56E0\uFF1A");
		label_7.setBounds(46, 65, 60, 17);
		
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
		button.setText("+");
		button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		button.setBounds(288, 60, 39, 27);
		
		Button button_1 = new Button(group, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			//损耗
			public void widgetSelected(SelectionEvent e) {
				if(Integer.parseInt(text_5.getText())>Integer.parseInt(text_4.getText())){
					//大于库存
					
					m.setMessage("损耗数量超过库存！");
					m.open();
				}else{
					if(Integer.parseInt(text_4.getText())!=0){
						sunhao();
						getShell().dispose();
					}else{
						m.setMessage("库存为零！");
						m.open();
					}
					
				}
			}
		});
		button_1.setText("\u6DFB\u52A0\u5230\u635F\u8017");
		button_1.setBounds(49, 107, 80, 27);
		
		Button button_2 = new Button(group, SWT.NONE);
		button_2.setText("\u53D6\u6D88");
		button_2.setBounds(210, 107, 80, 27);
		
		Label label_8 = new Label(this, SWT.NONE);
		label_8.setBounds(57, 109, 61, 17);
		label_8.setText("\u5546\u54C1\u6279\u6B21\uFF1A");
		
		text_6 = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text_6.setBounds(133, 106, 155, 23);
		text_6.setText(item.getText(0));
		createContents();
		
		getKind();
	}

	/**
	 * Create contents of the shell.
	 */
	
	//损耗--库存数量减少--商品信息 库存数量减少--
	public void sunhao(){
		JDBCTools jt=new JDBCTools();
		String sql="update kucun set kcount=kcount-"+text_5.getText()+"  where billcode='"+text_6.getText()+"' and code='"+text.getText()+"'";
		String sql1="update goods set sp_kucun=sp_kucun-"+text_5.getText()+" where sp_code='"+text.getText()+"'";
		String sql2="insert into sunhao(code,name,cost,retail,xcount,total,yuanyin,date) values('"+text.getText()+"','"+text_1.getText()+"','"+text_2.getText()+"','"+text_3.getText()+"','"+text_5.getText()+"',"+Double.parseDouble(text_2.getText())*Double.parseDouble(text_5.getText())+",'"+combo.getText()+"','"+DateTools.getDateTime()+"')";
		//System.out.println(sql+"\n"+sql1+"\n"+sql2);
		jt.update(sql2);
		jt.update(sql1);
		jt.update(sql);
		jt.close(null);
	}
	
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
		setText("\u635F\u8017");
		setSize(382, 580);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
