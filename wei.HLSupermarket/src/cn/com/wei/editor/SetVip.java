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
import org.eclipse.swt.widgets.TableItem;

import cn.com.wei.shell.AlterVipKind;
import cn.com.wei.tools.IsNumerberTools;
import cn.com.wei.tools.JDBCTools;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class SetVip extends EditorPart {

	public static final String ID = "cn.com.wei.editor.SetVip"; //$NON-NLS-1$
	private Table table;
	private Text text;
	private Text text_1;
	private Text text_2;
	MessageBox m=new MessageBox(new Shell(),SWT.None);
	private Text text_3;
	private Text text_4;

	public SetVip() {
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
		
		Label label_8 = new Label(container, SWT.NONE);
		label_8.setImage(SWTResourceManager.getImage(SetVip.class, "/img/huiytitile.png"));
		label_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_8.setBounds(54, 27, 107, 73);
		
		Label label_9 = new Label(container, SWT.NONE);
		label_9.setText("\u4F1A\u5458\u5361\u8BBE\u7F6E");
		label_9.setFont(SWTResourceManager.getFont("微软雅黑", 20, SWT.BOLD));
		label_9.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_9.setBounds(183, 44, 153, 42);
		
		Label label = new Label(container, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setBounds(0, 0, 914, 125);
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(138, 485, 512, 157);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("\u7F16\u53F7");
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(121);
		tblclmnNewColumn.setText("\u7B49\u7EA7\u540D\u79F0");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(125);
		tblclmnNewColumn_1.setText("\u4EAB\u53D7\u6298\u6263");
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(121);
		tableColumn.setText("\u6240\u9700\u6D88\u8D39");
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.addSelectionListener(new SelectionAdapter() {
			//种类会员备注设置
			public void widgetSelected(SelectionEvent e) {
				
					text_3.setText(table.getSelection()[0].getText(1));
				
				
			}
		});
		menuItem.setText("\u8BBE\u7F6E\u4F1A\u5458\u6D3B\u52A8\u5907\u6CE8");
		
		Group group = new Group(container, SWT.NONE);
		group.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		group.setText("\u589E\u52A0\u7B49\u7EA7");
		group.setBounds(138, 144, 645, 245);
		
		Label label_1 = new Label(group, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_1.setBounds(52, 47, 80, 26);
		label_1.setText("\u7B49\u7EA7\u540D\u79F0\uFF1A");
		
		Label label_2 = new Label(group, SWT.NONE);
		label_2.setText("\u6240\u9700\u6D88\u8D39\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_2.setBounds(52, 96, 80, 26);
		
		Label label_3 = new Label(group, SWT.NONE);
		label_3.setText("\u4EAB\u53D7\u6298\u6263\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_3.setBounds(52, 151, 80, 26);
		
		text = new Text(group, SWT.BORDER);
		text.setBounds(138, 47, 116, 23);
		
		text_1 = new Text(group, SWT.BORDER);
		text_1.setBounds(138, 96, 116, 23);
		
		text_2 = new Text(group, SWT.BORDER);
		text_2.setBounds(138, 151, 116, 23);
		
		Label label_4 = new Label(group, SWT.NONE);
		label_4.setBounds(270, 98, 158, 17);
		label_4.setText("\uFF08\u5347\u7EA7\u5230\u8BE5\u7B49\u7EA7\u6240\u9700\u7684\u6D88\u8D39\uFF09");
		
		Label label_5 = new Label(group, SWT.NONE);
		label_5.setText("\uFF08\u8BE5\u7B49\u7EA7\u4EAB\u53D7\u7684\u6298\u6263\u4F18\u60E0\u3002\u4F8B\u5982\uFF1A1\u4E3A\u539F\u4EF7\uFF0C0.88\u4E3A\u516B\u516B\u6298\uFF09");
		label_5.setBounds(270, 152, 330, 17);
		
		Button button = new Button(group, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			//会员添加
			public void widgetSelected(SelectionEvent e) {
				if(text.getText()!=""&&text_1.getText()!=""&&text_2.getText()!=""){
					if(IsNumerberTools.isNumeric(text_1.getText())&&IsNumerberTools.isNumeric(text_2.getText())){
						//是否为数字
						add();
						query();
						text.setText("");
						text_1.setText("");
						text_2.setText("");
						
					}else{
						m.setMessage("所需消费和折扣率应为数字");
						m.open();
					}
					
				}else{
					m.setMessage("请将信息输入完整！");
					m.open();
				}
			}
		});
		button.setBounds(119, 195, 80, 27);
		button.setText("\u786E\u5B9A\u6DFB\u52A0");
		
		Button button_1 = new Button(group, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			//重置
			public void widgetSelected(SelectionEvent e) {
				text.setText("");
				text_1.setText("");
				text_2.setText("");
			}
		});
		button_1.setBounds(270, 195, 80, 27);
		button_1.setText("\u91CD\u7F6E");
		
		Button button_2 = new Button(container, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			//刷新
			public void widgetSelected(SelectionEvent e) {
				query();
			}
		});
		button_2.setBounds(679, 405, 104, 49);
		button_2.setText("\u5237\u65B0");
		
		Button button_3 = new Button(container, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			//修改
			public void widgetSelected(SelectionEvent e) {
				AlterVipKind.start(table.getSelection()[0]);
				query();
			}
		});
		button_3.setText("\u4FEE\u6539");
		button_3.setBounds(679, 500, 104, 49);
		
		Button btnNewButton = new Button(container, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			//删除
			public void widgetSelected(SelectionEvent e) {
				 delete();
			}
		});
		btnNewButton.setBounds(679, 593, 104, 49);
		btnNewButton.setText("\u5220\u9664");
		
		Group group_1 = new Group(container, SWT.NONE);
		group_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		group_1.setText("\u4F1A\u5458\u6D3B\u52A8\u5907\u6CE8");
		group_1.setBounds(138, 395, 512, 84);
		
		text_3 = new Text(group_1, SWT.BORDER);
		text_3.setBounds(32, 51, 176, 23);
		
		text_4 = new Text(group_1, SWT.BORDER);
		text_4.setBounds(250, 51, 248, 23);
		
		Button button_4 = new Button(group_1, SWT.NONE);
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override//会员活动备注修改
			public void widgetSelected(SelectionEvent e) {
				if(text_3.getText().isEmpty() || text_4.getText().isEmpty()){
					m.setMessage("请将信息输入完整！");
					m.open();
				}else{
					updateBz();
					text_3.setText("");
					text_4.setText("");
				}
					
			}
		});
		button_4.setBounds(406, 16, 93, 29);
		button_4.setText("\u786E\u5B9A");
		
		Label label_6 = new Label(group_1, SWT.NONE);
		label_6.setBounds(32, 28, 176, 17);
		label_6.setText("\uFF08\u4F1A\u5458\u7F16\u53F7\u3001\u59D3\u540D\u3001\u4F1A\u5458\u7C7B\u578B\uFF09");
		
		Label label_7 = new Label(group_1, SWT.NONE);
		label_7.setBounds(250, 28, 83, 17);
		label_7.setText("\uFF08\u6D3B\u52A8\u8BE6\u60C5\uFF09");
		query();//显示所有等级

	}
	
	

	//添加会员
	public void add(){
		JDBCTools jt=new JDBCTools();
		
		String sql="insert into vip_kind(kind,zhekou,Hcondition) values('"+text.getText()+"','"+text_2.getText()+"','"+text_1.getText()+"')";
		jt.update(sql);
		
		jt.close(null);
		
	}

	
	
	//查询所有等级
	public void query(){
		JDBCTools jt= new JDBCTools();
		String sql="select * from vip_kind";
		
		ResultSet rs=jt.query(sql);
		
		try {
			table.removeAll();
			while(rs.next()){
				TableItem item=new TableItem(table,SWT.None);
				String[] temp=new String[4];
				temp[0]=rs.getString("id");
				temp[1]=rs.getString("kind");
				temp[2]=rs.getString("zhekou");
				temp[3]=rs.getString("Hcondition");
				item.setText(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		jt.close(rs);
		
	}
	//会员活动备注
	public void updateBz(){
		JDBCTools jt=new JDBCTools();
		String sql="update vip set hy_bz='"+text_4.getText()+"' where hy_code='"+text_3.getText()+"' || hy_name='"+text_3.getText()+"' || hy_kind='"+text_3.getText()+"'";
		jt.update(sql);
		jt.close(null);
		
	}
	
	
	//删除
	public void delete(){
		JDBCTools jt =new JDBCTools();
		String sql="delete from vip_kind where id='"+table.getSelection()[0].getText(0)+"'";
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
