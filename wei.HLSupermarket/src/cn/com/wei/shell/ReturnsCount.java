package cn.com.wei.shell;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import cn.com.wei.tools.IsNumerberTools;
import cn.com.wei.tools.JDBCTools;
import org.eclipse.wb.swt.SWTResourceManager;

public class ReturnsCount extends Shell {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_5;
    private  static TableItem item;
    private Button button;
    private Button button_1;
    MessageBox m=new MessageBox(getShell(),SWT.ICON_INFORMATION);
    private Label yiyouLabel;
    private Label lblNewLabel;
    private Label label_3;
    
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void start(TableItem item1) {
		item=item1;
		try {
			Display display = Display.getDefault();
			ReturnsCount shell = new ReturnsCount(display);
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
	public ReturnsCount(Display display) {
		super(display, SWT.SHELL_TRIM);
		setBackgroundMode(SWT.INHERIT_FORCE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		setImage(SWTResourceManager.getImage(ReturnsCount.class, "/img/22.png"));
		
		Label label = new Label(this, SWT.NONE);
		label.setBounds(51, 89, 72, 17);
		label.setText("\u8D26\u5355\u6D41\u6C34\u53F7:");
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setBounds(51, 157, 72, 17);
		label_1.setText("\u5546\u54C1\u540D\u79F0\uFF1A");
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setBounds(51, 192, 84, 17);
		label_2.setText("\u5546\u54C1\u8D2D\u4E70\u6570\u91CF\uFF1A");
		
		Label label_4 = new Label(this, SWT.NONE);
		label_4.setBounds(51, 256, 61, 17);
		label_4.setText("\u9000\u8D27\u6570\u91CF\uFF1A");
		
		text = new Text(this, SWT.READ_ONLY);
		text.setBounds(129, 89, 154, 23);
		text.setText(item.getText(0));
		
		text_1 = new Text(this, SWT.READ_ONLY);
		text_1.setBounds(129, 125, 154, 23);
		text_1.setText(item.getText(1));
		
		text_2 = new Text(this, SWT.READ_ONLY);
		text_2.setBounds(129, 154, 136, 23);
		text_2.setText(item.getText(2));
		
		text_3 = new Text(this, SWT.READ_ONLY);
		text_3.setBounds(141, 192, 84, 23);
		text_3.setText(item.getText(5));
		
		Label label_5 = new Label(this, SWT.NONE);
		label_5.setBounds(51, 124, 61, 17);
		label_5.setText("\u5546\u54C1\u7F16\u53F7\uFF1A");
		
		text_5 = new Text(this, SWT.BORDER);
		text_5.setBounds(123, 253, 127, 23);
		
		button = new Button(this, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			//确定添加到临时退货表
			public void widgetSelected(SelectionEvent e) {
				//1退货数 是数字 数量不能超过购买数量 
				if(IsNumerberTools.isNumeric(text_5.getText())){
					if(Integer.parseInt(text_5.getText())<=Integer.parseInt(text_3.getText())){
						
						//2 添加到临时表returns
						returns();
						getShell().dispose();
					}else{
						m.setMessage("退货数量不能超过购买数量!");
						m.open();
					}
				}else{
					m.setMessage("请正确输入退货数量！");
					m.open();
				}
			}
		});
		button.setBounds(51, 294, 80, 27);
		button.setText("\u786E\u5B9A");
		
		button_1 = new Button(this, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			//取消按钮
			public void widgetSelected(SelectionEvent e) {
				getShell().dispose();
			}
		});
		button_1.setBounds(171, 294, 80, 27);
		button_1.setText("\u53D6\u6D88");
		
		label_3 = new Label(this, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.BOLD));
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_3.setBounds(10, 21, 143, 27);
		label_3.setText("\u8BF7\u8F93\u5165\u9000\u8D27\u6570\u91CF\uFF1A");
		
		yiyouLabel = new Label(this, SWT.NONE);
		yiyouLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		yiyouLabel.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		yiyouLabel.setBounds(51, 226, 174, 24);
		
		lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel.setBounds(0, 0, 309, 68);
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	
	//将相应数据添加到returns退货临时表  方法
	public void returns(){
		JDBCTools jt=new JDBCTools();
		String sql="select * from sale_mingxi where billcode='"+text.getText()+"' and code='"+text_1.getText()+"'";
		ResultSet rs=jt.query(sql);
		String sql1="select * from returns where billcode='"+text.getText()+"' and code='"+text_1.getText()+"'";
		ResultSet rs1=jt.query(sql1);
		try {
			if(rs1.absolute(1)){
			//	退货表中有该数据
				rs.absolute(1);
				yiyouLabel.setText("已添加的退货数量:"+rs1.getString("count"));
				if((rs1.getInt("count")+Integer.parseInt(text_5.getText()))<=rs.getInt("count")){
					String sql2="update returns set count=count+"+text_5.getText()+" ,total=retail*zhekou*count where billcode='"+text.getText()+"' and code='"+text_1.getText()+"'";
				jt.update(sql2);
				}else{
				m.setMessage("退货商品数量不能超过购买数量!");
				m.open();
				}
				
			}else{
				//退货表中还没有该数据
				while(rs.next()){
					double tot=rs.getDouble("retail")*rs.getDouble("zhekou")*Double.parseDouble(text_5.getText());
					String sql2="insert into returns(billcode,code,name,retail,zhekou,count,total) values('"+rs.getString("billcode")+"','"+rs.getString("code")+"','"+rs.getString("name")+"','"+rs.getString("retail")+"','"+rs.getString("zhekou")+"','"+text_5.getText()+"','"+Double.toString(tot)+"')";
					jt.update(sql2);
					
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		
		jt.close(rs);
		jt.close(rs1);
	}
	
	protected void createContents() {
		setText("\u9000\u8D27");
		setSize(325, 385);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
