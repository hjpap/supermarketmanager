package cn.com.wei.editor;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import cn.com.wei.shell.AddGrounding;
import cn.com.wei.shell.AddSunhao;
import cn.com.wei.shell.AlterGoods;
import cn.com.wei.tools.DateTools;
import cn.com.wei.tools.JDBCTools;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Combo;

public class ShangpinXinxi extends EditorPart {

	public static final String ID = "cn.com.wei.editor.ShangpinXinxi"; //$NON-NLS-1$
	private Table table;
	private Table table_1;

	public ShangpinXinxi() {
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundMode(SWT.INHERIT_FORCE);
		container.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		
		Label label_4 = new Label(container, SWT.NONE);
		label_4.setImage(SWTResourceManager.getImage(ShangpinXinxi.class, "/img/spxinxi.png"));
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_4.setBounds(28, 0, 61, 49);
		
		Label label_5 = new Label(container, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.BOLD));
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_5.setBounds(95, 10, 91, 34);
		label_5.setText("\u5546\u54C1\u4FE1\u606F");
		
		Label label = new Label(container, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setBounds(0, 0, 914, 49);
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(0, 75, 914, 297);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(127);
		tableColumn.setText("\u5546\u54C1\u7F16\u53F7");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(130);
		tableColumn_1.setText("\u5546\u54C1\u540D\u79F0");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(59);
		tableColumn_2.setText("\u5355\u4F4D");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(70);
		tableColumn_3.setText("\u5546\u54C1\u5206\u7C7B");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(80);
		tableColumn_4.setText("\u8FDB\u4EF7");
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(82);
		tblclmnNewColumn.setText("\u96F6\u552E\u4EF7");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("\u5E93\u5B58\u6570\u91CF");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(112);
		tblclmnNewColumn_1.setText("\u4F9B\u8D27\u5546");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(147);
		tableColumn_6.setText("\u5E93\u5B58\u6570\u91CF\u72B6\u6001");
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.addSelectionListener(new SelectionAdapter() {
			//查看进货批次详情
			public void widgetSelected(SelectionEvent e) {
				showSpXQ();
			}
		});
		menuItem.setText("\u67E5\u770B\u8FDB\u8D27\u6279\u6B21\u8BE6\u60C5");
		
		MenuItem menuItem_1 = new MenuItem(menu, SWT.NONE);
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			//修改商品信息
			public void widgetSelected(SelectionEvent e) {
				AlterGoods.start(table.getSelection()[0]);
				showSpTable();
				table_1.removeAll();
			}
		});
		menuItem_1.setText("\u4FEE\u6539\u5546\u54C1\u4FE1\u606F");
		
		table_1 = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setFont(SWTResourceManager.getFont("微软雅黑", 8, SWT.NORMAL));
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		table_1.setBounds(0, 431, 914, 260);
		
		TableColumn tableColumn_7 = new TableColumn(table_1, SWT.NONE);
		tableColumn_7.setWidth(111);
		tableColumn_7.setText("\u8FDB\u8D27\u5355\u53F7");
		
		TableColumn tableColumn_8 = new TableColumn(table_1, SWT.NONE);
		tableColumn_8.setWidth(65);
		tableColumn_8.setText("\u5546\u54C1\u7F16\u53F7");
		
		TableColumn tableColumn_9 = new TableColumn(table_1, SWT.NONE);
		tableColumn_9.setWidth(72);
		tableColumn_9.setText("\u5546\u54C1\u540D\u79F0");
		
		TableColumn tableColumn_10 = new TableColumn(table_1, SWT.NONE);
		tableColumn_10.setWidth(55);
		tableColumn_10.setText("\u5355\u4F4D");
		
		TableColumn tableColumn_11 = new TableColumn(table_1, SWT.NONE);
		tableColumn_11.setWidth(66);
		tableColumn_11.setText("\u5546\u54C1\u5206\u7C7B");
		
		TableColumn tableColumn_12 = new TableColumn(table_1, SWT.NONE);
		tableColumn_12.setWidth(55);
		tableColumn_12.setText("\u8FDB\u4EF7");
		
		TableColumn tableColumn_13 = new TableColumn(table_1, SWT.NONE);
		tableColumn_13.setWidth(50);
		tableColumn_13.setText("\u96F6\u552E\u4EF7");
		
		TableColumn tableColumn_14 = new TableColumn(table_1, SWT.NONE);
		tableColumn_14.setWidth(72);
		tableColumn_14.setText("\u751F\u4EA7\u65E5\u671F");
		
		TableColumn tableColumn_15 = new TableColumn(table_1, SWT.NONE);
		tableColumn_15.setWidth(73);
		tableColumn_15.setText("\u8FC7\u671F\u65E5\u671F");
		
		TableColumn tableColumn_16 = new TableColumn(table_1, SWT.NONE);
		tableColumn_16.setWidth(74);
		tableColumn_16.setText("\u8FDB\u8D27\u65E5\u671F");
		
		TableColumn tableColumn_17 = new TableColumn(table_1, SWT.NONE);
		tableColumn_17.setWidth(63);
		tableColumn_17.setText("\u8FDB\u8D27\u6570\u91CF");
		
		TableColumn tableColumn_18 = new TableColumn(table_1, SWT.NONE);
		tableColumn_18.setWidth(62);
		tableColumn_18.setText("\u73B0\u6709\u5E93\u5B58");
		
		TableColumn tableColumn_19 = new TableColumn(table_1, SWT.NONE);
		tableColumn_19.setWidth(69);
		tableColumn_19.setText("\u72B6\u6001");
		
		Menu menu_1 = new Menu(table_1);
		table_1.setMenu(menu_1);
		
		MenuItem menuItem_2 = new MenuItem(menu_1, SWT.NONE);
		menuItem_2.addSelectionListener(new SelectionAdapter() {
			//商品上架
			public void widgetSelected(SelectionEvent e) {
				AddGrounding.start(table_1.getSelection()[0]);
				showSpTable();
				table_1.removeAll();
			}
		});
		menuItem_2.setText("\u5546\u54C1\u4E0A\u67B6");
		
		MenuItem menuItem_3 = new MenuItem(menu_1, SWT.NONE);
		menuItem_3.addSelectionListener(new SelectionAdapter() {
			//添加到损耗
			public void widgetSelected(SelectionEvent e) {
				AddSunhao.start(table_1.getSelection()[0]);
				showSpTable();
				table_1.removeAll();
			}
		});
		menuItem_3.setText("\u6DFB\u52A0\u5230\u635F\u8017");
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		label_1.setBounds(10, 50, 91, 25);
		label_1.setText("\u5546\u54C1\u4FE1\u606F\uFF1A");
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setText("\u5546\u54C1\u6279\u6B21\u8BE6\u60C5\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		label_2.setBounds(10, 400, 119, 25);
		
		Group group = new Group(container, SWT.NONE);
		group.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		group.setText("\u7B5B\u9009");
		group.setBounds(448, 370, 455, 56);
		
		final Combo combo = new Combo(group, SWT.NONE);
		combo.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		combo.setItems(new String[] {"\u5E93\u5B58\u6570\u91CF\u62A5\u8B66\u5546\u54C1", "\u5FEB\u8FC7\u671F\u5546\u54C1", "\u5DF2\u8FC7\u671F\u5546\u54C1"});
		combo.setBounds(77, 19, 161, 25);
		
		Label label_3 = new Label(group, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_3.setBounds(28, 22, 43, 22);
		label_3.setText("\u6761\u4EF6\uFF1A");
		
		Button button_1 = new Button(group, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			//筛选查找
			public void widgetSelected(SelectionEvent e) {
				if(combo.getText().equals("库存数量报警商品")){
					KucunCount();
				}else if(combo.getText().equals("快过期商品")){
					kuaiGuoqi();
				}else if(combo.getText().equals("已过期商品")){
					yiGuoqi();
				}
				
			}
		});
		button_1.setBounds(252, 17, 80, 31);
		button_1.setText("\u67E5\u770B");
		
		Button button = new Button(group, SWT.NONE);
		button.setBounds(337, 16, 80, 32);
		button.addSelectionListener(new SelectionAdapter() {
			//刷新
			public void widgetSelected(SelectionEvent e) {
				showSpTable();
				table_1.removeAll();
			}
		});
		button.setText("\u5237\u65B0");
		
		
		showSpTable();//显示商品信息

	}
	
	//查看库存不足商品
	public void KucunCount(){
		JDBCTools jt=new JDBCTools();
		String sql="select * from goods";
		ResultSet rs=jt.query(sql);
		
		try {
			table.removeAll();
			table_1.removeAll();
			while(rs.next()){
				if(rs.getInt("sp_kucun")<=rs.getInt("sp_dangerous")){
					TableItem item=new TableItem(table,SWT.None);
					String[] temp=new String[9];
					temp[0]=rs.getString("sp_code");
					temp[1]=rs.getString("sp_name");
					temp[2]=rs.getString("sp_unit");
					temp[3]=rs.getString("sp_kind");
					temp[4]=rs.getString("sp_cost");
					temp[5]=rs.getString("sp_retail");
					temp[6]=rs.getString("sp_kucun");
					temp[7]=rs.getString("sp_gonghuoshang");
					//库存小于报警数
					temp[8]="报警！库存不足，请及时进货！";	
					item.setBackground(new Color(null, 220,20,60));
					item.setText(temp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jt.close(rs);
	}
	//快过期商品查询
	public void kuaiGuoqi(){
		JDBCTools jt=new JDBCTools();
		String sql="select DATE_SUB(baddate,INTERVAL 30 DAY) as kuaiguoqi,billcode,code,name,unit,kind,cost,retail,prodate,baddate,date,jhcount,kcount from kucun";
		ResultSet rs=jt.query(sql);
		try {
			table.removeAll();
			table_1.removeAll();
			while(rs.next()){
					if(rs.getDate("kuaiguoqi").compareTo(Date.valueOf(DateTools.getDateTime()))<0 && rs.getDate("baddate").compareTo(Date.valueOf(DateTools.getDateTime()))>0){
							TableItem item=new TableItem(table_1,SWT.None);
							String[] temp=new String[13];
							temp[0]=rs.getString("billcode");
							temp[1]=rs.getString("code");
							temp[2]=rs.getString("name");
							temp[3]=rs.getString("unit");
							temp[4]=rs.getString("kind");
							temp[5]=rs.getString("cost");
							temp[6]=rs.getString("retail");
							temp[7]=rs.getString("prodate");
							temp[8]=rs.getString("baddate");
							temp[9]=rs.getString("date");
							temp[10]=rs.getString("jhcount");
							temp[11]=rs.getString("kcount");
						
							//即将过期
							temp[12]="快要过期";
							item.setBackground(new Color(null, 255,165,0));
							item.setText(temp);
						}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jt.close(rs);
	}
	
	//已过期
	public void yiGuoqi(){
		JDBCTools jt=new JDBCTools();
		String sql="select * from kucun";
		ResultSet rs=jt.query(sql);
		try {
			table.removeAll();
			table_1.removeAll();
			while(rs.next()){
				if(rs.getDate("baddate").compareTo(Date.valueOf(DateTools.getDateTime()))<=0){
					TableItem item=new TableItem(table_1,SWT.None);
					String[] temp=new String[13];
					temp[0]=rs.getString("billcode");
					temp[1]=rs.getString("code");
					temp[2]=rs.getString("name");
					temp[3]=rs.getString("unit");
					temp[4]=rs.getString("kind");
					temp[5]=rs.getString("cost");
					temp[6]=rs.getString("retail");
					temp[7]=rs.getString("prodate");
					temp[8]=rs.getString("baddate");
					temp[9]=rs.getString("date");
					temp[10]=rs.getString("jhcount");
					temp[11]=rs.getString("kcount");
					
					//过期
					temp[12]="已过期";
					item.setBackground(new Color(null, 220,20,60));
					item.setText(temp);
				}
							
						
							
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jt.close(rs);
	}
	
	//显示商品批次详情
	public void showSpXQ(){
		JDBCTools jt=new JDBCTools();
		String sql="select DATE_SUB(baddate,INTERVAL 30 DAY) as kuaiguoqi,billcode,code,name,unit,kind,cost,retail,prodate,baddate,date,jhcount,kcount from kucun where code='"+table.getSelection()[0].getText(0)+"'";
		
		jt.query(sql);
		ResultSet rs=jt.query(sql);
		try {
			table_1.removeAll();
			while(rs.next()){
				TableItem item=new TableItem(table_1,SWT.None);
				String[] temp=new String[13];
				temp[0]=rs.getString("billcode");
				temp[1]=rs.getString("code");
				temp[2]=rs.getString("name");
				temp[3]=rs.getString("unit");
				temp[4]=rs.getString("kind");
				temp[5]=rs.getString("cost");
				temp[6]=rs.getString("retail");
				temp[7]=rs.getString("prodate");
				temp[8]=rs.getString("baddate");
				temp[9]=rs.getString("date");
				temp[10]=rs.getString("jhcount");
				temp[11]=rs.getString("kcount");
				//System.out.println(rs.getDate("baddate")+"/"+DateTools.getDateYear()+DateTools.getDateMonth()+DateTools.getDateDay());
				//rs.getDate("baddate");
					//如果快过期  已过期  状态显示       
				
				if(rs.getDate("kuaiguoqi").compareTo(Date.valueOf(DateTools.getDateTime()))>0){
					//没过期
					temp[12]="正常";
				}else if(rs.getDate("kuaiguoqi").compareTo(Date.valueOf(DateTools.getDateTime()))<0 && rs.getDate("baddate").compareTo(Date.valueOf(DateTools.getDateTime()))>0){
					//即将过期
					temp[12]="快要过期";
					item.setBackground(new Color(null, 255,165,0));
				}else if(rs.getDate("baddate").compareTo(Date.valueOf(DateTools.getDateTime()))<=0){
					//过期
					temp[12]="已过期";
					item.setBackground(new Color(null, 220,20,60));
				}
				
				
				
				item.setText(temp);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//显示商品信息
	public void showSpTable(){
		JDBCTools jt=new JDBCTools();
		
		String sql="select * from goods";
		ResultSet rs=jt.query(sql);
		
		try {
			table.removeAll();
			while(rs.next()){
				TableItem item=new TableItem(table,SWT.None);
				String[] temp=new String[9];
				temp[0]=rs.getString("sp_code");
				temp[1]=rs.getString("sp_name");
				temp[2]=rs.getString("sp_unit");
				temp[3]=rs.getString("sp_kind");
				temp[4]=rs.getString("sp_cost");
				temp[5]=rs.getString("sp_retail");
				temp[6]=rs.getString("sp_kucun");
				temp[7]=rs.getString("sp_gonghuoshang");
				if(rs.getInt("sp_kucun")>rs.getInt("sp_dangerous")){
					//库存量大于报警数
					temp[8]="正常";
				}else{
					//库存小于报警数
					temp[8]="报警！库存不足，请及时进货！";	
					item.setBackground(new Color(null, 220,20,60));
				}
				
				item.setText(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
