package cn.com.wei.editor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;

import cn.com.wei.shell.AddGhs;
import cn.com.wei.shell.JinhuoShell;
import cn.com.wei.shell.LoginShell;
import cn.com.wei.shell.UpdateKind;
import cn.com.wei.shell.UpdateUnit;
import cn.com.wei.tools.DateTools;
import cn.com.wei.tools.IsNumerberTools;
import cn.com.wei.tools.JDBCTools;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.DateTime;

public class Jinhuo extends EditorPart {

	public static final String ID = "cn.com.wei.editor.Jinhuo"; //$NON-NLS-1$
	private Text text;
	private Table table;
	private Text text_1;
	private Combo combo;
	private Combo combo_1;
	private Combo combo_2;
	private Combo combo_3;
	private DateTime dateTime;
	private DateTime dateTime_1;
	

	MessageBox m=new MessageBox(new Shell(),SWT.ICON_INFORMATION);
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_9;
	private Text text_7;
	private Text text_8;
	
	
	public Jinhuo() {
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		container.setBackgroundMode(SWT.INHERIT_FORCE);
		
		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel_1.setImage(SWTResourceManager.getImage(Jinhuo.class, "/img/jinhuoTitle.png"));
		lblNewLabel_1.setBounds(51, 22, 86, 90);
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 20, SWT.BOLD));
		label_1.setBounds(170, 50, 153, 42);
		label_1.setText("\u5546\u54C1\u8FDB\u8D27");
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel.setBounds(686, 97, 72, 17);
		lblNewLabel.setText("\u8FDB\u8D27\u6D41\u6C34\u53F7\uFF1A");
		
		text = new Text(container, SWT.READ_ONLY);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text.setBounds(758, 97, 156, 23);
		orderCode();//产生一个流水号
		
		TabFolder tabFolder = new TabFolder(container, SWT.NONE);
		tabFolder.setBounds(0, 124, 914, 482);
		
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("\u5DF2\u6709\u5546\u54C1\u8FDB\u8D27");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_FORCE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		tabItem.setControl(composite);
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(189, 43, 707, 401);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(153);
		tableColumn.setText("\u5546\u54C1\u7F16\u53F7");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(131);
		tableColumn_1.setText("\u540D\u79F0");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("\u53C2\u8003\u8FDB\u4EF7");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("\u53C2\u8003\u96F6\u552E\u4EF7");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("\u5E93\u5B58\u6570\u91CF");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("\u4F9B\u8D27\u5546");
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		
		menuItem.setText("\u8FDB\u8D27");
		
		combo = new Combo(composite, SWT.READ_ONLY);
		
		combo.setBounds(10, 97, 157, 25);
		getKind();//获取商品分类
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		label_2.setBounds(189, 10, 80, 27);
		label_2.setText("\u5546\u54C1\u6E05\u5355\uFF1A");
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_3.setBounds(10, 66, 104, 25);
		label_3.setText("\u6309\u7C7B\u522B\u67E5\u8BE2\uFF1A");
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_4.setBounds(10, 150, 127, 25);
		label_4.setText("\u6309\u5546\u54C1\u7F16\u53F7\u67E5\u8BE2\uFF1A");
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setBounds(10, 190, 157, 25);
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		
		btnNewButton.setBounds(87, 221, 80, 27);
		btnNewButton.setText("\u67E5");
		
		Button button_5 = new Button(composite, SWT.NONE);
		button_5.addSelectionListener(new SelectionAdapter() {
			//查看全部
			public void widgetSelected(SelectionEvent e) {
				showGoods();
			}
		});
		button_5.setBounds(816, 9, 80, 27);
		button_5.setText("\u67E5\u770B\u5168\u90E8");
		
		TabItem tabItem_1 = new TabItem(tabFolder, SWT.NONE);
		tabItem_1.setText("\u65B0\u5546\u54C1\u8FDB\u8D27");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tabItem_1.setControl(composite_1);
		
		Group group = new Group(composite_1, SWT.NONE);
		group.setText("\u5546\u54C1\u8BE6\u60C5");
		group.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.BOLD));
		group.setBounds(116, 10, 664, 431);
		
		Label label_5 = new Label(group, SWT.NONE);
		label_5.setText("\u5546\u54C1\u7F16\u53F7\uFF1A");
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_5.setBounds(37, 41, 80, 24);
		
		text_2 = new Text(group, SWT.BORDER);

		text_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		text_2.setBounds(137, 38, 173, 29);
		
		Label label_6 = new Label(group, SWT.NONE);
		label_6.setText("\u5546\u54C1\u540D\u79F0\uFF1A");
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_6.setBounds(37, 108, 80, 24);
		
		text_3 = new Text(group, SWT.BORDER);
		
		text_3.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		text_3.setBounds(137, 105, 173, 29);
		
		Label label_7 = new Label(group, SWT.NONE);
		label_7.setText("\u5546\u54C1\u5355\u4F4D\uFF1A");
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_7.setBounds(37, 168, 80, 24);
		
		combo_1 = new Combo(group, SWT.READ_ONLY);
		
		combo_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		combo_1.setBounds(137, 165, 173, 29);
		getUnit();//获取单位
		
		Label label_8 = new Label(group, SWT.NONE);
		label_8.setText("\u5546\u54C1\u5206\u7C7B\uFF1A");
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_8.setBounds(37, 227, 80, 24);
		
		combo_2 = new Combo(group, SWT.READ_ONLY);
		
		combo_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		combo_2.setBounds(137, 224, 173, 29);
		getKind1();//获取分类
		
		Label label_9 = new Label(group, SWT.NONE);
		label_9.setText("   \u4F9B\u8D27\u5546\uFF1A");
		label_9.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_9.setBounds(37, 285, 80, 24);
		
		combo_3 = new Combo(group, SWT.READ_ONLY);
		
		combo_3.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		combo_3.setBounds(137, 282, 201, 29);
		getGhs();//获取供货商
		
		Label label_10 = new Label(group, SWT.NONE);
		label_10.setText("  \u62A5\u8B66\u6570\uFF1A");
		label_10.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_10.setBounds(413, 44, 80, 24);
		
		text_4 = new Text(group, SWT.BORDER);
		text_4.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		text_4.setBounds(500, 41, 124, 27);
		
		Label label_11 = new Label(group, SWT.NONE);
		label_11.setText("  \u8FDB\u8D27\u4EF7\uFF1A");
		label_11.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_11.setBounds(413, 111, 80, 24);
		
		text_5 = new Text(group, SWT.BORDER);
		
		text_5.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		text_5.setBounds(500, 108, 124, 29);
		
		Label label_12 = new Label(group, SWT.NONE);
		label_12.setText("  \u96F6\u552E\u4EF7\uFF1A");
		label_12.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_12.setBounds(413, 171, 80, 24);
		
		text_6 = new Text(group, SWT.BORDER);
		
		text_6.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		text_6.setBounds(500, 166, 124, 29);
		
		Label label_13 = new Label(group, SWT.NONE);
		label_13.setText("\u8FDB\u8D27\u6570\u91CF\uFF1A");
		label_13.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		label_13.setBounds(413, 332, 80, 24);
		
		Button button = new Button(group, SWT.NONE);
		
		button.setText("\u786E\u5B9A");
		button.setBounds(289, 369, 93, 38);
		
		Button button_2 = new Button(group, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			//添加单位
			public void widgetSelected(SelectionEvent e) {
				UpdateUnit.start();
				getUnit();
				
			}
		});
		button_2.setText("+");
		button_2.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		button_2.setBounds(344, 168, 38, 27);
		
		Button button_3 = new Button(group, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			//添加分类
			public void widgetSelected(SelectionEvent e) {
				UpdateKind.start();
				getKind1();
			}
		});
		button_3.setText("+");
		button_3.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		button_3.setBounds(344, 226, 38, 27);
		
		Button button_4 = new Button(group, SWT.NONE);
		button_4.addSelectionListener(new SelectionAdapter() {
			//添加供货商
			public void widgetSelected(SelectionEvent e) {
				AddGhs.start();
				getGhs();
			}
		});
		button_4.setText("+");
		button_4.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		button_4.setBounds(344, 287, 38, 27);
		
		Label label_14 = new Label(group, SWT.NONE);
		label_14.setText("\u751F\u4EA7\u65E5\u671F\uFF1A");
		label_14.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_14.setBounds(413, 230, 80, 24);
		
		Label label_15 = new Label(group, SWT.NONE);
		label_15.setText("\u8FC7\u671F\u65E5\u671F\uFF1A");
		label_15.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_15.setBounds(413, 288, 80, 24);
		
		text_9 = new Text(group, SWT.BORDER);
		text_9.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		text_9.setBounds(499, 329, 124, 29);
		
		dateTime = new DateTime(group, SWT.BORDER | SWT.DROP_DOWN);
		dateTime.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		dateTime.setBounds(500, 227, 124, 29);
		
		dateTime_1 = new DateTime(group, SWT.BORDER | SWT.DROP_DOWN);
		dateTime_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		dateTime_1.setBounds(500, 285, 124, 27);
		
		Label label = new Label(container, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setBounds(0, 0, 914, 125);
		
		Label label_16 = new Label(container, SWT.NONE);
		label_16.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_16.setBounds(52, 630, 86, 30);
		label_16.setText("\u5F53\u524D\u65E5\u671F\uFF1A");
		
		text_7 = new Text(container, SWT.READ_ONLY);
		text_7.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		text_7.setBounds(144, 630, 153, 30);
		text_7.setText(DateTools.getDateTime());
		
		Label label_17 = new Label(container, SWT.NONE);
		label_17.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_17.setBounds(303, 630, 72, 30);
		label_17.setText("\u7ECF\u529E\u4EBA\uFF1A");
		
		text_8 = new Text(container, SWT.NONE);
		text_8.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		text_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		text_8.setBounds(381, 630, 153, 30);
		text_8.setText(LoginShell.getLoginUsername());
		
		Button button_6 = new Button(container, SWT.NONE);
		button_6.addSelectionListener(new SelectionAdapter() {
			//完成此次进货
			public void widgetSelected(SelectionEvent e) {
				getSite().getWorkbenchWindow().getActivePage().closeEditor(Jinhuo.this, true);
			}
		});
		button_6.setBounds(661, 620, 123, 45);
		button_6.setText("\u5B8C\u6210\u6B64\u6B21\u8FDB\u8D27");
		showGoods();//显示所有商品信息
		
		button.addSelectionListener(new SelectionAdapter() {
			//进货按钮
			public void widgetSelected(SelectionEvent e) {
				if(text_2.getText()!=""&&text_3.getText()!=""&&text_4.getText()!=""&&text_5.getText()!=""&&text_6.getText()!=""&&text_9.getText()!=""&&combo_1.getText()!=""&&combo_2.getText()!=""&&combo_3.getText()!=""){
					//把进货商品信息添加到库存
					addKucun();
					//把进货商品添加到商品信息表
					addGoods();
					text_2.setText("");
					text_3.setText("");
					text_4.setText("");
					text_5.setText("");
					text_6.setText("");
					text_9.setText("");
					combo_1.select(-1);
					combo_2.select(-1);
					combo_3.select(-1);
					
					
				}else{
					m.setMessage("请输入完整信息!");
					m.open();
				}
			}
		});
		
		menuItem.addSelectionListener(new SelectionAdapter() {
			//进货菜单
 			public void widgetSelected(SelectionEvent e) {
 				JinhuoShell.start(table.getSelection()[0],text.getText());
 				showGoods();
 				
 				
			}
		});
		
		
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			//按商品编号查询
			public void widgetSelected(SelectionEvent e) {
				if(text_1.getText()!=""){
					JDBCTools jt=new JDBCTools();
					String sql="select * from goods where sp_code='"+text_1.getText()+"'";
					ResultSet rs=jt.query(sql);
					table.removeAll();
					try {
						while(rs.next()){
							TableItem tableItem=new TableItem(table,SWT.None);
							String[] temp=new String[6];
							temp[0]=rs.getString("sp_code");
							temp[1]=rs.getString("sp_name");
							temp[2]=rs.getString("sp_cost");
							temp[3]=rs.getString("sp_retail");
							temp[4]=rs.getString("sp_kucun");
							temp[5]=rs.getString("sp_gonghuoshang");
							tableItem.setText(temp);
						}
						jt.close(rs);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else{
					
					m.setMessage("请输入商品编号！");
					m.open();
					
				}
			}
		});
		
		
		combo.addSelectionListener(new SelectionAdapter() {
			//按分类查询
			public void widgetSelected(SelectionEvent e) {
				JDBCTools jt=new JDBCTools();
				String sql="select * from goods where sp_kind='"+combo.getText()+"'";
				ResultSet rs=jt.query(sql);
				try {
					table.removeAll();
					while(rs.next()){
						TableItem tableItem=new TableItem(table,SWT.None);
						String[] temp=new String[6];
						temp[0]=rs.getString("sp_code");
						temp[1]=rs.getString("sp_name");
						temp[2]=rs.getString("sp_cost");
						temp[3]=rs.getString("sp_retail");
						temp[4]=rs.getString("sp_kucun");
						temp[5]=rs.getString("sp_gonghuoshang");
						tableItem.setText(temp);
						
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				jt.close(rs);
			}
		});

	}
	
	//显示所有商品信息
	public void showGoods(){
		JDBCTools jt=new JDBCTools();
		String sql="select * from goods";
		ResultSet rs=jt.query(sql);
		try {
			table.removeAll();
			while(rs.next()){
				TableItem tableItem=new TableItem(table,SWT.None);
				String[] temp=new String[6];
				temp[0]=rs.getString("sp_code");
				temp[1]=rs.getString("sp_name");
				temp[2]=rs.getString("sp_cost");
				temp[3]=rs.getString("sp_retail");
				temp[4]=rs.getString("sp_kucun");
				temp[5]=rs.getString("sp_gonghuoshang");
				tableItem.setText(temp);
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		jt.close(rs);
	}
	
	//添加到商品信息表
	public void addGoods(){
		JDBCTools jt=new JDBCTools();
		String sql="select * from goods where sp_code='"+text_2.getText()+"'";
		ResultSet rs=jt.query(sql);
		
		try {
			if(rs.absolute(1)){
				//已有新进商品信息(修改库存信息,成本价，零售价，供货商)
				String sql1="select * from kucun where code='"+text_2.getText()+"'";
				ResultSet rs1=jt.query(sql1);
				int kucun=0;
				while(rs1.next()){
					kucun=kucun+rs1.getInt("kcount");
				}
				
				String sql2="update goods set sp_cost='"+text_5.getText()+"',sp_retail='"+text_6.getText()+"',sp_gonghuoshang='"+combo_3.getText()+"',sp_kucun='"+kucun+"' where sp_code='"+text_2.getText()+"'";
				jt.update(sql2);
				jt.close(rs);
				jt.close(rs1);
				
			}else{
				//没有新进商品信息(添加商品信息)
				String sql1="insert into goods (sp_code,sp_name,sp_unit,sp_kind,sp_cost,sp_retail,sp_kucun,sp_dangerous,sp_gonghuoshang) values('"+text_2.getText()+"','"+text_3.getText()+"','"+combo_1.getText()+"','"+combo_2.getText()+"','"+text_5.getText()+"','"+text_6.getText()+"','"+text_9.getText()+"','"+text_4.getText()+"','"+combo_3.getText()+"')";
				jt.update(sql1);
				jt.close(rs);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	//添加到库存方法
	public void addKucun(){
		
		if(IsNumerberTools.isNumeric(text_5.getText())&&IsNumerberTools.isNumeric(text_6.getText())&&IsNumerberTools.isNumeric(text_4.getText())&&IsNumerberTools.isNumeric(text_9.getText())){
			JDBCTools jt=new JDBCTools();
			String sql="INSERT INTO kucun(billcode,CODE,NAME,unit,kind,gonghuoshang,cost,retail,prodate,baddate,DATE,dangerous,jhcount,kCOUNT,state,people) VALUES('"+text.getText()+"','"+text_2.getText()+"','"+text_3.getText()+"','"+combo_1.getText()+"','"+combo_2.getText()+"','"+combo_3.getText()+"',"+text_5.getText()+","+text_6.getText()+",'"+dateTime.getYear()+"-"+(dateTime.getMonth()+1)+"-"+dateTime.getDay()+"','"+dateTime_1.getYear()+"-"+(dateTime_1.getMonth()+1)+"-"+dateTime_1.getDay()+"','"+DateTools.getDateTime()+"',"+text_4.getText()+","+text_9.getText()+","+text_9.getText()+",'正常','"+LoginShell.getLoginUsername()+"')";
			//System.out.println(sql);
		jt.update(sql);
		jt.close(null);
		m.setMessage("进货成功！");
		m.open();
		}else{
			m.setMessage("请正确输入价格或数量！(应为数字)");
			m.open();
		}
		
	}
	
	//获取商品分类
	public void getKind(){
		JDBCTools jt=new JDBCTools();
		String sql="select count(*) as number from goods_kind";
		String sql1="select * from goods_kind";
		
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
	//获取分类2
		public void getKind1(){
			JDBCTools jt=new JDBCTools();
			String sql="select count(*) as number from goods_kind";
			String sql1="select * from goods_kind";
			
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
				combo_2.setItems(temp);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			jt.close(rs1);
			jt.close(rs);	
			
		}
		
		//获取单位
		public void getUnit(){
			JDBCTools jt=new JDBCTools();
			String sql="select count(*) as number from goods_unit";
			String sql1="select * from goods_unit";
			
			ResultSet rs=jt.query(sql);
			ResultSet rs1=jt.query(sql1);
			try {
				rs.absolute(1);
				int i=rs.getInt("number");
				String[] temp=new String[i];
				while(rs1.next()){
					temp[i-1]=rs1.getString("unit");
					i--;
				}
				combo_1.setItems(temp);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			jt.close(rs1);
			jt.close(rs);	
			
		}
		
		//获取供货商
		public void getGhs(){
			JDBCTools jt=new JDBCTools();
			String sql="select count(*) as number from gonghuoshang";
			String sql1="select * from gonghuoshang";
			
			ResultSet rs=jt.query(sql);
			ResultSet rs1=jt.query(sql1);
			try {
				rs.absolute(1);
				int i=rs.getInt("number");
				String[] temp=new String[i];
				while(rs1.next()){
					temp[i-1]=rs1.getString("name");
					i--;
				}
				combo_3.setItems(temp);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			jt.close(rs1);
			jt.close(rs);	
			
		}
	
	// 产生一个流水号
		public void orderCode() {
			int i=new Random().nextInt(99999);
			String j=DateTools.getDateTimeBillcode();
			String z=i+j;
			text.setText(z);
		}

	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// Do the Save operation
	}

	@Override
	public void doSaveAs() {
		// Do the Save As operation
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		this.setSite(site);
		this.setInput(input);
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
}
