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
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TableItem;

import cn.com.wei.shell.AddVip;
import cn.com.wei.shell.AlterVip;
import cn.com.wei.shell.VipBill;
import cn.com.wei.tools.JDBCTools;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class QueryVip extends EditorPart {

	public static final String ID = "cn.com.wei.editor.QueryVip"; //$NON-NLS-1$
	private Table table;
	private Text countt;
	private Text totalt;
	private Text xfcountt;
	private Text text;
	MessageBox m=new MessageBox(new Shell(),SWT.ICON_INFORMATION);
	public QueryVip() {
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
		
		Label label_7 = new Label(container, SWT.NONE);
		label_7.setText("\u4F1A\u5458\u4FE1\u606F");
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 20, SWT.BOLD));
		label_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_7.setBounds(176, 48, 153, 42);
		
		Label label_6 = new Label(container, SWT.NONE);
		label_6.setImage(SWTResourceManager.getImage(QueryVip.class, "/img/huiytitile.png"));
		label_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_6.setBounds(47, 31, 107, 73);
		
		Label label = new Label(container, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setBounds(0, 0, 914, 125);
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table.setBounds(185, 240, 720, 420);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(91);
		tableColumn.setText("\u4F1A\u5458\u7F16\u53F7");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(72);
		tableColumn_1.setText("\u4F1A\u5458\u7C7B\u578B");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(67);
		tableColumn_2.setText("\u4F1A\u5458\u59D3\u540D");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(87);
		tableColumn_6.setText("\u751F\u65E5");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(96);
		tableColumn_3.setText("\u8054\u7CFB\u7535\u8BDD");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(67);
		tableColumn_4.setText("\u6D88\u8D39\u91D1\u989D");
		
		TableColumn tableColumn_7 = new TableColumn(table, SWT.NONE);
		tableColumn_7.setWidth(67);
		tableColumn_7.setText("\u6D88\u8D39\u6B21\u6570");
		
		TableColumn tblclmnlv = new TableColumn(table, SWT.NONE);
		tblclmnlv.setWidth(76);
		tblclmnlv.setText("\u4EAB\u53D7\u6298\u6263\u7387");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(84);
		tableColumn_5.setText("\u5907\u6CE8");
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.addSelectionListener(new SelectionAdapter() {
			//会员明细
			public void widgetSelected(SelectionEvent e) {
				VipBill.start(table.getSelection()[0]);
			}
		});
		menuItem.setText("\u4F1A\u5458\u6D88\u8D39\u660E\u7EC6");
		
		Group group = new Group(container, SWT.NONE);
		group.setBounds(10, 235, 169, 425);
		
		Button button = new Button(group, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			//添加
			public void widgetSelected(SelectionEvent e) {
				AddVip.start();
				showTable();
			}
		});
		button.setBounds(33, 28, 102, 36);
		button.setText("\u6DFB\u52A0");
		
		Button button_1 = new Button(group, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			//删除按钮
			public void widgetSelected(SelectionEvent e) {
				delete();
				
			}
		});
		button_1.setBounds(33, 130, 102, 36);
		button_1.setText("\u5220\u9664");
		
		Button button_2 = new Button(group, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			//修改
			public void widgetSelected(SelectionEvent e) {
				
						AlterVip.start(table.getSelection()[0]);
						showTable();
			}
		});
		button_2.setBounds(33, 232, 102, 36);
		button_2.setText("\u4FEE\u6539");
		
		Button button_3 = new Button(group, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			//刷新
			public void widgetSelected(SelectionEvent e) {
				showTable();
			}
		});
		button_3.setBounds(33, 332, 102, 36);
		button_3.setText("\u5237\u65B0");
		
		Group group_1 = new Group(container, SWT.NONE);
		group_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		group_1.setText("\u7EDF\u8BA1");
		group_1.setBounds(10, 131, 169, 103);
		
		Label label_1 = new Label(group_1, SWT.NONE);
		label_1.setBounds(21, 26, 60, 17);
		label_1.setText("\u4F1A\u5458\u6570\u91CF\uFF1A");
		
		Label label_2 = new Label(group_1, SWT.NONE);
		label_2.setBounds(10, 50, 72, 17);
		label_2.setText("\u603B\u6D88\u8D39\u91D1\u989D\uFF1A");
		
		countt = new Text(group_1, SWT.READ_ONLY);
		countt.setBounds(88, 26, 73, 23);
		
		totalt = new Text(group_1, SWT.READ_ONLY);
		totalt.setBounds(88, 51, 73, 23);
		
		Label label_3 = new Label(group_1, SWT.NONE);
		label_3.setText("\u603B\u6D88\u8D39\u6B21\u6570\uFF1A");
		label_3.setBounds(10, 76, 72, 17);
		
		xfcountt = new Text(group_1, SWT.READ_ONLY);
		xfcountt.setBounds(88, 77, 73, 23);
		
		Button button_4 = new Button(container, SWT.NONE);
		button_4.addSelectionListener(new SelectionAdapter() {
			//查询
			public void widgetSelected(SelectionEvent e) {
				if(text.getText()!=""){
					query();
				}else{
					
					m.setMessage("请输入查询内容!");
					m.open();
				}
			}
		});
		button_4.setBounds(825, 194, 80, 27);
		button_4.setText("\u67E5\u8BE2");
		
		text = new Text(container, SWT.BORDER);
		text.setBounds(594, 196, 225, 23);
		
		Label label_4 = new Label(container, SWT.NONE);
		label_4.setBounds(594, 156, 80, 17);
		label_4.setText("\u5173\u952E\u5B57\u67E5\u8BE2\uFF1A");
		
		Label label_5 = new Label(container, SWT.NONE);
		label_5.setText("\uFF08\u6309\u4F1A\u5458\u7C7B\u578B\u3001\u4F1A\u5458\u7F16\u53F7\u3001\u4F1A\u5458\u59D3\u540D\uFF09");
		label_5.setBounds(591, 171, 312, 17);

		
		
		showTable();
	}
	//查询
	public void query(){
		JDBCTools jt=new JDBCTools();
		String sql="SELECT * FROM vip WHERE hy_name='"+text.getText()+"' || hy_code='"+text.getText()+"' || hy_kind='"+text.getText()+"'";
		ResultSet rs=jt.query(sql);
		
		table.removeAll();
		double total=0;
		int count=0;
		int xfcount=0;
		try {
			while(rs.next()){
				TableItem item=new TableItem(table,SWT.None);
				String[] temp=new String[9];
				temp[0]=rs.getString("hy_code");
				temp[1]=rs.getString("hy_kind");
				temp[2]=rs.getString("hy_name");
				temp[3]=rs.getString("hy_birthday");
				temp[4]=rs.getString("hy_tel");
				temp[5]=rs.getString("hy_expense");
				temp[6]=rs.getString("hy_count");
				temp[7]=rs.getString("hy_zhekou");
				temp[8]=rs.getString("hy_bz");
				item.setText(temp);
				
				
				total=total+rs.getDouble("hy_expense");
				xfcount=xfcount+rs.getInt("hy_count");
				count++;
			}
			
			totalt.setText(total+"元");
			xfcountt.setText(xfcount+"");
			countt.setText(count+"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jt.close(rs);
	}
	
	
	//查看所有会员数据 显示
	public void showTable(){
		JDBCTools jt=new JDBCTools();
		String sql="select * from vip order by hy_expense desc";
		
		ResultSet rs=jt.query(sql);
		try {
			table.removeAll();
			double total=0;
			int count=0;
			int xfcount=0;
			while(rs.next()){
				TableItem item=new TableItem(table,SWT.None);
				String[] temp=new String[9];
				temp[0]=rs.getString("hy_code");
				temp[1]=rs.getString("hy_kind");
				temp[2]=rs.getString("hy_name");
				temp[3]=rs.getString("hy_birthday");
				temp[4]=rs.getString("hy_tel");
				temp[5]=rs.getString("hy_expense");
				temp[6]=rs.getString("hy_count");
				temp[7]=rs.getString("hy_zhekou");
				temp[8]=rs.getString("hy_bz");
				item.setText(temp);
				
				total=total+rs.getDouble("hy_expense");
				xfcount=xfcount+rs.getInt("hy_count");
				count++;
					
			}
			
			totalt.setText(total+"元");
			xfcountt.setText(xfcount+"");
			countt.setText(count+"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jt.close(rs);
		
	}
	//删除
	public void delete(){
		JDBCTools jt=new JDBCTools();
		 String s="";

		 int[] t = table.getSelectionIndices();

		 TableItem[] items = table.getSelection();

		    for(int i=0;i<items.length;i++)
		     {

		        s=table.getSelection()[i].getText(0);

		        String sql = "delete from vip where hy_code='"+s+"'";

		        jt.update(sql);
		     
		 }    
		    table.remove(t);
		    jt.close(null);
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
