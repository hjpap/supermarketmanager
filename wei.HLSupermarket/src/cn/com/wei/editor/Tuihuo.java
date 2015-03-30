package cn.com.wei.editor;

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
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import cn.com.wei.shell.FinishReturns;
import cn.com.wei.shell.ReturnsCount;
import cn.com.wei.tools.JDBCTools;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Group;

public class Tuihuo extends EditorPart {

	public static final String ID = "cn.com.wei.editor.Tuihuo"; //$NON-NLS-1$
	private Text text;
	private Table table;
	private Table table_1;
	MessageBox m = new MessageBox(new Shell(), SWT.ICON_INFORMATION);

	public Tuihuo() {
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
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setImage(SWTResourceManager.getImage(Tuihuo.class, "/img/sunhaoTitle.png"));
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_2.setBounds(48, 0, 99, 94);
		
		Label label = new Label(container, SWT.NONE);
		label.setBounds(10, 126, 80, 17);
		label.setText("\u8D26\u5355\u6D41\u6C34\u53F7\uFF1A");
		
		text = new Text(container, SWT.BORDER);
		text.setBounds(96, 123, 157, 23);
		
		Button button = new Button(container, SWT.NONE);
		
		button.setBounds(259, 121, 106, 27);
		button.setText("\u9500\u552E\u8BB0\u5F55\u67E5\u8BE2");
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 158, 893, 216);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(144);
		tableColumn.setText("\u8D26\u5355\u6D41\u6C34\u53F7");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(144);
		tableColumn_1.setText("\u5546\u54C1\u7F16\u53F7/\u6761\u5F62\u7801");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(154);
		tableColumn_2.setText("\u5546\u54C1\u540D\u79F0");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(83);
		tableColumn_3.setText("\u96F6\u552E\u4EF7");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(80);
		tableColumn_4.setText("\u6298\u6263");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(77);
		tableColumn_5.setText("\u6570\u91CF");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(75);
		tableColumn_6.setText("\u5408\u8BA1\u4ED8\u6B3E");
		
		TableColumn tableColumn_7 = new TableColumn(table, SWT.NONE);
		tableColumn_7.setWidth(100);
		tableColumn_7.setText("\u9500\u552E\u65E5\u671F");
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		
		menuItem.setText("\u6DFB\u52A0\u5230\u9000\u8D27\u8868");
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 22, SWT.BOLD));
		lblNewLabel.setBounds(166, 32, 142, 51);
		lblNewLabel.setText("\u5546\u54C1\u9000\u8D27");
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 22, SWT.BOLD));
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_1.setBounds(0, 0, 944, 104);
		
		Group group = new Group(container, SWT.NONE);
		group.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		group.setText("\u9000\u8D27\u8868");
		group.setBounds(10, 380, 893, 283);
		
		table_1 = new Table(group, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setLocation(0, 60);
		table_1.setSize(893, 211);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		
		TableColumn tableColumn_8 = new TableColumn(table_1, SWT.NONE);
		tableColumn_8.setWidth(144);
		tableColumn_8.setText("\u8D26\u5355\u6D41\u6C34\u53F7");
		
		TableColumn tableColumn_9 = new TableColumn(table_1, SWT.NONE);
		tableColumn_9.setWidth(144);
		tableColumn_9.setText("\u5546\u54C1\u7F16\u53F7/\u6761\u5F62\u7801");
		
		TableColumn tableColumn_10 = new TableColumn(table_1, SWT.NONE);
		tableColumn_10.setWidth(153);
		tableColumn_10.setText("\u5546\u54C1\u540D\u79F0");
		
		TableColumn tableColumn_11 = new TableColumn(table_1, SWT.NONE);
		tableColumn_11.setWidth(85);
		tableColumn_11.setText("\u96F6\u552E\u4EF7");
		
		TableColumn tableColumn_12 = new TableColumn(table_1, SWT.NONE);
		tableColumn_12.setWidth(80);
		tableColumn_12.setText("\u6298\u6263");
		
		TableColumn tableColumn_13 = new TableColumn(table_1, SWT.NONE);
		tableColumn_13.setWidth(78);
		tableColumn_13.setText("\u6570\u91CF");
		
		TableColumn tableColumn_14 = new TableColumn(table_1, SWT.NONE);
		tableColumn_14.setWidth(73);
		tableColumn_14.setText("\u9700\u8981\u9000\u6B3E");
		
		Button button_1 = new Button(group, SWT.NONE);
		
		button_1.setBounds(59, 24, 116, 27);
		button_1.setText("\u6E05\u7A7A\u91CD\u7F6E");
		
		Button button_2 = new Button(group, SWT.NONE);
		
		button_2.setBounds(195, 24, 116, 27);
		button_2.setText("\u786E\u5B9A\u9000\u8D27");
		
		button_2.addSelectionListener(new SelectionAdapter() {
			//确定退货
			public void widgetSelected(SelectionEvent e) {
				JDBCTools jt=new JDBCTools();
				String sql="select * from returns";
				
				ResultSet rs=jt.query(sql);
				
				
				try {
					if(rs.absolute(1)){
						
						FinishReturns.start(text.getText());
						table.removeAll();
						table_1.removeAll();
						text.setText("");
					}else{
						m.setMessage("暂无退货信息，请添加退货信息！");
						m.open();
						
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				jt.close(rs);
			}
		});
		
        button_1.addSelectionListener(new SelectionAdapter() {
			//清空重置 退货表
			public void widgetSelected(SelectionEvent e) {
				JDBCTools jt=new JDBCTools();
				
				String sql="Delete from returns";
				jt.update(sql);
				table_1.removeAll();
				jt.close(null);
			}
		});
		
		button.addSelectionListener(new SelectionAdapter() {
			//商品购买   订单号查询
			public void widgetSelected(SelectionEvent e) {
				query();
			}
		});
		
		menuItem.addSelectionListener(new SelectionAdapter() {
			//添加退货 按钮
			public void widgetSelected(SelectionEvent e) {
				
				ReturnsCount.start(table.getSelection()[0]);
				queryReturns();
			
				
			}
		});

	}
	//退货临时表查询显示要退的货
	public void queryReturns(){
		JDBCTools jt=new JDBCTools();
		String sql="select * from returns";
		ResultSet rs=jt.query(sql);
		table_1.removeAll();
		try {
			while(rs.next()){
				TableItem tableItem=new TableItem(table_1,SWT.None);
				String[] temp=new String[7];
				temp[0]=rs.getString("billcode");
				temp[1]=rs.getString("code");
				temp[2]=rs.getString("name");
				temp[3]=rs.getString("retail");
				temp[4]=rs.getString("zhekou");
				temp[5]=rs.getString("count");
				temp[6]=rs.getString("total");
				tableItem.setText(temp);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jt.close(rs);
	}
	
	//根据流水号查询
	public void query(){
		JDBCTools jt=new JDBCTools();
		String sql="select * from sale_mingxi where billcode='"+text.getText()+"'";
		ResultSet rs=jt.query(sql);
		try {
			table.removeAll();
			while(rs.next()){
				TableItem tableItem=new TableItem(table,SWT.None);
				String[] temp=new String[8];
				temp[0]=rs.getString("billcode");
				temp[1]=rs.getString("code");
				temp[2]=rs.getString("name");
				temp[3]=rs.getString("retail");
				temp[4]=rs.getString("zhekou");
				temp[5]=rs.getString("count");
				temp[6]=rs.getString("total");
				temp[7]=rs.getString("date");
				tableItem.setText(temp);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		jt.close(rs);
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
