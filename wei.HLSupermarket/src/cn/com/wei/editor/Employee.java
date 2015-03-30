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
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import cn.com.wei.shell.AddStaff;
import cn.com.wei.shell.AlterLimits;
import cn.com.wei.shell.AlterStaff;
import cn.com.wei.tools.JDBCTools;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Employee extends EditorPart {

	public static final String ID = "cn.com.wei.editor.Employee"; //$NON-NLS-1$
	private Table table;

	public Employee() {
		setTitleImage(SWTResourceManager.getImage(Employee.class, "/img/22.png"));
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
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setImage(SWTResourceManager.getImage(Employee.class, "/img/employee.png"));
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_1.setBounds(78, 22, 80, 85);
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setText("\u5458\u5DE5\u4FE1\u606F");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 20, SWT.BOLD));
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_2.setBounds(177, 43, 153, 42);
		
		Label label = new Label(container, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setBounds(0, 0, 914, 125);
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(0, 200, 914, 446);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("\u7F16\u53F7");
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("\u5458\u5DE5\u767B\u5F55\u540D");
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(91);
		tableColumn.setText("\u6743\u9650");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(90);
		tableColumn_1.setText("\u59D3\u540D");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(55);
		tblclmnNewColumn_1.setText("\u6027\u522B");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(72);
		tblclmnNewColumn_2.setText("\u5E74\u9F84");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(91);
		tblclmnNewColumn_3.setText("\u8054\u7CFB\u7535\u8BDD");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(186);
		tableColumn_2.setText("\u8EAB\u4EFD\u8BC1");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("\u4E1A\u7EE9");
		
		Group group = new Group(container, SWT.NONE);
		group.setBounds(271, 131, 631, 63);
		
		final Combo combo = new Combo(group, SWT.NONE);
		combo.addSelectionListener(new SelectionAdapter() {
			//类型查找
			public void widgetSelected(SelectionEvent e) {
				if(combo.getText().equals("所有")){
					all();
				}else if(combo.getText().equals("收银员")){
					cashier();
				}else if(combo.getText().equals("管理员")){
					manager();
				}else if(combo.getText().equals("经理")){
					boss();
				}
			}
		});
		combo.setItems(new String[] {"\u6240\u6709", "\u6536\u94F6\u5458", "\u7BA1\u7406\u5458", "\u7ECF\u7406"});
		combo.setBounds(89, 22, 111, 25);
		
		Label label_3 = new Label(group, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_3.setBounds(23, 22, 57, 25);
		label_3.setText("\u7C7B\u578B\uFF1A");
		
		Button button = new Button(group, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			//添加员工
			public void widgetSelected(SelectionEvent e) {
				AddStaff.start();
				all();
			}
		});
		button.setBounds(265, 20, 80, 27);
		button.setText("\u6DFB\u52A0\u5458\u5DE5");
		
		Button button_1 = new Button(group, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			//修改权限
			public void widgetSelected(SelectionEvent e) {
				AlterLimits.start(table.getSelection()[0]);
				all();
			}
		});
		button_1.setBounds(351, 20, 80, 27);
		button_1.setText("\u4FEE\u6539\u6743\u9650");
		
		Button button_2 = new Button(group, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			//修改信息
			public void widgetSelected(SelectionEvent e) {
				AlterStaff.start(table.getSelection()[0]);
				all();
			}
		});
		button_2.setBounds(437, 20, 80, 27);
		button_2.setText("\u4FEE\u6539\u4FE1\u606F");
		
		Button button_3 = new Button(group, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			//删除
			public void widgetSelected(SelectionEvent e) {
				delete();
			}
		});
		button_3.setBounds(523, 20, 80, 27);
		button_3.setText("\u5220\u9664");
		all();
	}
	//经理
	public void boss(){
		JDBCTools jt=new JDBCTools();
		String sql="select * from staff where limits='经理'";
		ResultSet rs=jt.query(sql);
		try {
			table.removeAll();
			while(rs.next()){
				TableItem item=new TableItem(table,SWT.None);
				String[] temp=new String[9];
				temp[0]=rs.getString("id");
				temp[1]=rs.getString("user_name");
				temp[2]=rs.getString("limits");
				temp[3]=rs.getString("name");
				temp[4]=rs.getString("sex");
				temp[5]=rs.getString("age");
				temp[6]=rs.getString("tel");
				temp[7]=rs.getString("idcard");
				temp[8]=rs.getString("income");
				item.setText(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jt.close(rs);
	}
	

	//管理员查找
	public void manager(){
		JDBCTools jt=new JDBCTools();
		String sql="select * from staff where limits='管理员'";
		ResultSet rs=jt.query(sql);
		try {
			table.removeAll();
			while(rs.next()){
				TableItem item=new TableItem(table,SWT.None);
				String[] temp=new String[9];
				temp[0]=rs.getString("id");
				temp[1]=rs.getString("user_name");
				temp[2]=rs.getString("limits");
				temp[3]=rs.getString("name");
				temp[4]=rs.getString("sex");
				temp[5]=rs.getString("age");
				temp[6]=rs.getString("tel");
				temp[7]=rs.getString("idcard");
				temp[8]=rs.getString("income");
				item.setText(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jt.close(rs);
	}
	
	//按收银员查找
	public void cashier(){
		JDBCTools jt=new JDBCTools();
		String sql="select * from staff where limits='收银员' order by income desc";
		ResultSet rs=jt.query(sql);
		try {
			table.removeAll();
			while(rs.next()){
				TableItem item=new TableItem(table,SWT.None);
				String[] temp=new String[9];
				temp[0]=rs.getString("id");
				temp[1]=rs.getString("user_name");
				temp[2]=rs.getString("limits");
				temp[3]=rs.getString("name");
				temp[4]=rs.getString("sex");
				temp[5]=rs.getString("age");
				temp[6]=rs.getString("tel");
				temp[7]=rs.getString("idcard");
				temp[8]=rs.getString("income");
				item.setText(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jt.close(rs);
		
	}
	//查询所有员工
	public void all(){
		JDBCTools jt=new JDBCTools();
		String sql="select * from staff";
		ResultSet rs=jt.query(sql);
		try {
			table.removeAll();
			while(rs.next()){
				TableItem item=new TableItem(table,SWT.None);
				String[] temp=new String[9];
				temp[0]=rs.getString("id");
				temp[1]=rs.getString("user_name");
				temp[2]=rs.getString("limits");
				temp[3]=rs.getString("name");
				temp[4]=rs.getString("sex");
				temp[5]=rs.getString("age");
				temp[6]=rs.getString("tel");
				temp[7]=rs.getString("idcard");
				temp[8]=rs.getString("income");
				item.setText(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jt.close(rs);
	}
	
	//删除
	public void delete(){
		JDBCTools jt=new JDBCTools();
		String sql="delete from staff where user_name='"+table.getSelection()[0].getText(1)+"'";
		jt.update(sql);
		jt.close(null);
		table.remove(table.getSelectionIndices());
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
