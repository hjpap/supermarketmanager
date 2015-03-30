package cn.com.wei.shell;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import cn.com.wei.tools.DateTools;
import cn.com.wei.tools.JDBCTools;

public class FinishReturns extends Shell {
	private Text text;
	private Text text_1;
	private Text text_2;
	private static String billcode;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void start(String billcode1) {
		billcode=billcode1;
		try {
			Display display = Display.getDefault();
			FinishReturns shell = new FinishReturns(display);
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
	public FinishReturns(Display display) {
		super(display, SWT.RESIZE | SWT.TITLE);
		setImage(SWTResourceManager.getImage(FinishReturns.class, "/img/22.png"));
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 22, SWT.BOLD));
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_1.setBounds(158, 22, 153, 49);
		label_1.setText("\u9000\u8D27\u6210\u529F");
		
		Label label = new Label(this, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setBounds(0, 0, 442, 88);
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setBounds(113, 128, 61, 17);
		label_2.setText("\u9000\u8D27\u5355\u53F7\uFF1A");
		
		text = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text.setBounds(179, 122, 159, 23);
		text.setText(billcode);
		
		Label label_3 = new Label(this, SWT.NONE);
		label_3.setBounds(113, 165, 61, 17);
		label_3.setText("\u7ECF\u529E\u4EBA\uFF1A");
		
		
		text_1 = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text_1.setBounds(179, 159, 159, 23);
		text_1.setText(LoginShell.getLoginUsername());
		
		Label label_4 = new Label(this, SWT.NONE);
		label_4.setBounds(113, 204, 61, 17);
		label_4.setText("\u5408\u8BA1\u9000\u6B3E\uFF1A");
		
		text_2 = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text_2.setBounds(179, 198, 159, 23);
		setTuikuan();//显示合计退款方法
		
		Button button = new Button(this, SWT.NONE);
		
		button.setBounds(179, 245, 80, 27);
		button.setText("\u786E\u5B9A");
		createContents();
		
		
		
        button.addSelectionListener(new SelectionAdapter() {
			//确定 退款 按钮
			public void widgetSelected(SelectionEvent e) {
				
				//添加到账单
				addBill();
				//添加到明细
				addMingxi();
				//删除退货临时表
				delete();
				//关闭
				getShell().dispose();
				
				
			}
		});
	}

	/**
	 * Create contents of the shell.
	 */
	//把退货信息写入退货账单
	public void addBill(){
		JDBCTools jt=new JDBCTools();
		String sql="select billcode,code,name,retail,zhekou,count,total from returns";
		ResultSet rs=jt.query(sql);
		double cost=0;
		double sum=0;
		try {
			while(rs.next()){
			cost=cost+(rs.getDouble("retail")*rs.getInt("count"));	
			sum=sum+rs.getDouble("total");
			}
			String sql1="insert into returns_bill (billcode,cost,total,agent,date) values('"+billcode+"',"+cost+",'"+sum+"','"+LoginShell.getLoginUsername()+"','"+DateTools.getDateTime()+"')";
			jt.update(sql1);
		} catch (SQLException e) {
	      e.printStackTrace();
		}
		
		jt.close(rs);
	}
	//添加到明细
	public void addMingxi(){
		JDBCTools jt=new JDBCTools();
		String sql="select billcode,code,name,retail,zhekou,count,total from returns";
		ResultSet rs=jt.query(sql);
		try {
			while(rs.next()){
				String sql1="insert into returns_mingxi(billcode,code,name,retail,zhekou,count,total,date) values ('"+rs.getString("billcode")+"','"+rs.getString("code")+"','"+rs.getString("name")+"','"+rs.getString("retail")+"','"+rs.getString("zhekou")+"','"+rs.getString("count")+"','"+rs.getString("total")+"','"+DateTools.getDateTime()+"')";
				jt.update(sql1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jt.close(rs);
	}
	//删除退货临时表信息
	public void delete(){
		JDBCTools jt=new JDBCTools();
		String sql="delete from returns";
		jt.update(sql);
		jt.close(null);
	}
	
	//显示合计退款金额
	public void setTuikuan(){
		JDBCTools jt=new JDBCTools();
		String sql="select total from returns";
		
		ResultSet rs=jt.query(sql);
		try {
			double sum=0;
			while(rs.next()){
				sum=sum+rs.getDouble("total");
			}
			text_2.setText(Double.toString(sum));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jt.close(rs);
	}
	
	
	protected void createContents() {
		setText("\u786E\u8BA4\u9000\u8D27");
		setSize(458, 339);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
