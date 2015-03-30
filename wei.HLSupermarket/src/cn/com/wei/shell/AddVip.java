package cn.com.wei.shell;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Combo;

import cn.com.wei.tools.JDBCTools;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AddVip extends Shell {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Combo combo;
	private DateTime dateTime;
	MessageBox m=new MessageBox(getShell(),SWT.None);

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void start() {
		try {
			Display display = Display.getDefault();
			AddVip shell = new AddVip(display);
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
	public AddVip(Display display) {
		super(display, SWT.CLOSE | SWT.RESIZE | SWT.TITLE);
		setBackgroundMode(SWT.INHERIT_FORCE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		setImage(SWTResourceManager.getImage(AddVip.class, "/img/22.png"));
		
		Label label_6 = new Label(this, SWT.NONE);
		label_6.setText("\u6DFB\u52A0\u4F1A\u5458");
		label_6.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 15, SWT.BOLD));
		label_6.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label_6.setBounds(57, 22, 92, 27);
		
		Label label = new Label(this, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label.setBounds(0, 0, 504, 73);
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 12, SWT.BOLD));
		label_1.setBounds(57, 103, 87, 27);
		label_1.setText("\u4F1A\u5458\u7F16\u53F7\uFF1A");
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setText("\u4F1A\u5458\u59D3\u540D\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 12, SWT.BOLD));
		label_2.setBounds(57, 161, 87, 27);
		
		Label label_3 = new Label(this, SWT.NONE);
		label_3.setText("\u8054\u7CFB\u7535\u8BDD\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 12, SWT.BOLD));
		label_3.setBounds(57, 217, 87, 27);
		
		Label label_4 = new Label(this, SWT.NONE);
		label_4.setText("\u4F1A\u5458\u751F\u65E5\uFF1A");
		label_4.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 12, SWT.BOLD));
		label_4.setBounds(57, 273, 87, 27);
		
		Label label_5 = new Label(this, SWT.NONE);
		label_5.setText("\u4F1A\u5458\u7C7B\u578B\uFF1A");
		label_5.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 12, SWT.BOLD));
		label_5.setBounds(57, 328, 87, 27);
		
		text = new Text(this, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 11, SWT.NORMAL));
		text.setBounds(150, 103, 163, 23);
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 11, SWT.NORMAL));
		text_1.setBounds(150, 161, 163, 23);
		
		text_2 = new Text(this, SWT.BORDER);
		text_2.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 11, SWT.NORMAL));
		text_2.setBounds(150, 217, 163, 23);
		
		dateTime = new DateTime(this, SWT.BORDER | SWT.DROP_DOWN | SWT.LONG);
		dateTime.setFont(SWTResourceManager.getFont("Œ¢»Ì—≈∫⁄", 11, SWT.NORMAL));
		dateTime.setBounds(152, 276, 161, 24);
		
		combo = new Combo(this, SWT.NONE);
		combo.setBounds(150, 328, 163, 25);
		
		Button button = new Button(this, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			//ÃÌº”
			public void widgetSelected(SelectionEvent e) {
				if(text.getText()!=""&&text_1.getText()!=""&&text_2.getText()!=""&&combo.getText()!=""){
					add();
					
				}else{
					m.setMessage("«ÎΩ´–≈œ¢ÃÓ–¥ÕÍ’˚£°");
					m.open();
				}
				
			}
		});
		button.setBounds(99, 390, 80, 27);
		button.setText("\u786E\u5B9A\u6DFB\u52A0");
		
		Button button_1 = new Button(this, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			//»°œ˚
			public void widgetSelected(SelectionEvent e) {
				getShell().dispose();
			}
		});
		button_1.setBounds(227, 390, 80, 27);
		button_1.setText("\u53D6\u6D88");
		createContents();
		
		//ªÒ»°ª·‘±¿‡–Õ
		getKind();
	}

	/**
	 * Create contents of the shell.
	 */
	
	//ªÒ»°ª·‘±¿‡–Õ
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
		
		
		//ÃÌº”
		public void add(){
			JDBCTools jt=new JDBCTools();
			String sql2="select * from vip where hy_code='"+text.getText()+"'";
			ResultSet rs1=jt.query(sql2);
			try {
				if(rs1.absolute(1)){
					//“—”–±‡∫≈
					
					m.setMessage("“—”–∏√±‡∫≈!");
					m.open();
				}else{
					//√ª”–±‡∫≈
					String sql="select * from vip_kind where kind='"+combo.getText()+"'";
					ResultSet rs=jt.query(sql);
					double zhekou=1;
					
					rs.absolute(1);
					zhekou=rs.getDouble("zhekou");
						
					
					String sql1="INSERT INTO vip (hy_code,hy_kind,hy_name,hy_tel,hy_zhekou,hy_birthday) VALUES('"+text.getText()+"','"+combo.getText()+"','"+text_1.getText()+"','"+text_2.getText()+"','"+zhekou+"','"+dateTime.getYear()+"-"+(dateTime.getMonth()+1)+"-"+dateTime.getDay()+"')";    
					jt.update(sql1);
					jt.close(rs);
					getShell().dispose();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			jt.close(rs1);
			
		}
		
	protected void createContents() {
		setText("\u6DFB\u52A0\u4F1A\u5458");
		setSize(520, 500);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
