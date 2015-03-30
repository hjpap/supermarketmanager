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
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import cn.com.wei.tools.JDBCTools;

public class AlterVip extends Shell {
	private Text text;
	private Text text_1;
	private Text text_2;
	private static TableItem item;
	private Combo combo;
	private DateTime dateTime;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void start(TableItem item1) {
		try {
			item=item1;
			Display display = Display.getDefault();
			AlterVip shell = new AlterVip(display);
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
	
	
	
	
	
	public AlterVip(Display display) {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(AlterVip.class, "/img/22.png"));
		
		Label label_6 = new Label(this, SWT.NONE);
		label_6.setText("\u4FEE\u6539\u4F1A\u5458\u4FE1\u606F");
		label_6.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 15, SWT.BOLD));
		label_6.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label_6.setBounds(53, 22, 126, 27);
		
		Label label = new Label(this, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label.setBounds(0, 0, 504, 73);
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setText("\u4F1A\u5458\u7F16\u53F7\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 12, SWT.BOLD));
		label_1.setBounds(92, 100, 87, 27);
		
		text = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 11, SWT.NORMAL));
		text.setBounds(185, 100, 163, 23);
		text.setText(item.getText(0));
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setText("\u4F1A\u5458\u59D3\u540D\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 12, SWT.BOLD));
		label_2.setBounds(92, 158, 87, 27);
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 11, SWT.NORMAL));
		text_1.setBounds(185, 158, 163, 23);
		text_1.setText(item.getText(2));
		
		Label label_3 = new Label(this, SWT.NONE);
		label_3.setText("\u8054\u7CFB\u7535\u8BDD\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 12, SWT.BOLD));
		label_3.setBounds(92, 214, 87, 27);
		
		text_2 = new Text(this, SWT.BORDER);
		text_2.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 11, SWT.NORMAL));
		text_2.setBounds(185, 214, 163, 23);
		text_2.setText(item.getText(4));
		
		Label label_4 = new Label(this, SWT.NONE);
		label_4.setText("\u4F1A\u5458\u751F\u65E5\uFF1A");
		label_4.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 12, SWT.BOLD));
		label_4.setBounds(92, 270, 87, 27);
		
		dateTime = new DateTime(this, SWT.BORDER | SWT.DROP_DOWN | SWT.LONG);
		dateTime.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 11, SWT.NORMAL));
		dateTime.setBounds(185, 270, 161, 24);
		dateTime.setData(item.getText(3));
		
		Label label_5 = new Label(this, SWT.NONE);
		label_5.setText("\u4F1A\u5458\u7C7B\u578B\uFF1A");
		label_5.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 12, SWT.BOLD));
		label_5.setBounds(92, 325, 87, 27);
		
		combo = new Combo(this, SWT.NONE);
		combo.setItems(new String[] {});
		combo.setBounds(185, 325, 163, 25);
		getKind();
		combo.setText(item.getText(1));
		
		Button button = new Button(this, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override//»∑»œ–ﬁ∏ƒ
			public void widgetSelected(SelectionEvent e) {
				if(text.getText()!=""&&text_1.getText()!=""&&text_2.getText()!=""&&combo.getText()!=""){
					
					alter();
					getShell().dispose();
				}else{
					MessageBox m=new MessageBox(getShell(),SWT.None);
					m.setMessage("«ÎΩ´–≈œ¢ ‰»ÎÕÍ’˚£°");
					m.open();
				}
				
			}
		});
		button.setBounds(92, 387, 80, 27);
		button.setText("\u786E\u5B9A\u4FEE\u6539");
		
		Button button_1 = new Button(this, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			//
			public void widgetSelected(SelectionEvent e) {
				getShell().dispose();
			}
		});
		button_1.setBounds(266, 387, 80, 27);
		button_1.setText("\u53D6\u6D88");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	
	//»∑»œ–ﬁ∏ƒ
		public void alter(){
			JDBCTools jt=new JDBCTools();
			String sql1="select zhekou from vip_kind where kind='"+combo.getText()+"'";
			ResultSet rs=jt.query(sql1);
			double zhekou=1;
			try {
				rs.absolute(1);
				zhekou=rs.getDouble("zhekou");
				String sql="UPDATE vip SET hy_kind='"+combo.getText()+"',hy_name='"+text_1.getText()+"',hy_tel='"+text_2.getText()+"',hy_birthday='"+dateTime.getYear()+"-"+(dateTime.getMonth()+1)+"-"+dateTime.getDay()+"',hy_zhekou="+zhekou+" WHERE hy_code='"+text.getText()+"'";
				jt.update(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			jt.close(rs);
			
			
		}
		
		//ªÒ»°vip∑÷¿‡
		public void getKind(){
			JDBCTools jt=new JDBCTools();
			String sql="select count(*) as number from vip_kind";
			String sql1="select * from vip_kind";
			
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
		setText("\u4FEE\u6539\u4F1A\u5458\u4FE1\u606F");
		setSize(519, 500);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
