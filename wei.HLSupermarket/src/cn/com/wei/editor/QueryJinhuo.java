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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import cn.com.wei.tools.JDBCTools;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class QueryJinhuo extends EditorPart {

	public static final String ID = "cn.com.wei.editor.QueryJinhuo"; //$NON-NLS-1$
	private Text text;
	private Button button;
	private Button button_1;
	private Button button_2;
	private DateTime dateTime;
	private DateTime dateTime_1;
	private Table table;

	public QueryJinhuo() {
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		
		Label label_3 = new Label(container, SWT.NONE);
		label_3.setImage(SWTResourceManager.getImage(QueryJinhuo.class, "/img/jinhuoTitle.png"));
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_3.setBounds(48, 20, 86, 90);
		
		Label label_4 = new Label(container, SWT.NONE);
		label_4.setText("\u8FDB\u8D27\u8BB0\u5F55");
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 20, SWT.BOLD));
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_4.setBounds(163, 47, 153, 42);
		
		Label label = new Label(container, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setBounds(0, 0, 914, 125);
		
		Group group = new Group(container, SWT.NONE);
		group.setBackgroundMode(SWT.INHERIT_FORCE);
		group.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		group.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		group.setText("\u67E5\u627E");
		group.setBounds(388, 131, 512, 125);
		
		Group group_1 = new Group(group, SWT.NONE);
		group_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		group_1.setBounds(373, 16, 129, 90);
		
		button = new Button(group_1, SWT.RADIO);
		
		button.setSelection(true);
		button.setBounds(10, 24, 97, 17);
		button.setText("\u6309\u65E5\u671F\u67E5\u8BE2");
		
		button_1 = new Button(group_1, SWT.RADIO);
		button_1.setBounds(10, 47, 110, 17);
		button_1.setText("\u6309\u8FDB\u8D27\u5355\u53F7\u67E5\u8BE2");
		
		final Composite composite = new Composite(group, SWT.NONE);
		composite.setBounds(10, 27, 357, 79);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setBounds(10, 10, 80, 17);
		label_1.setText("\u65E5\u671F\u533A\u95F4\uFF1A \u4ECE");
		
		dateTime = new DateTime(composite, SWT.BORDER | SWT.DROP_DOWN | SWT.LONG);
		dateTime.setBounds(96, 10, 134, 24);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBounds(72, 52, 18, 17);
		lblNewLabel.setText("\u81F3");
		
		dateTime_1 = new DateTime(composite, SWT.BORDER | SWT.DROP_DOWN | SWT.LONG);
		dateTime_1.setBounds(96, 45, 134, 24);
		
		button_2 = new Button(composite, SWT.NONE);
		
		button_2.setBounds(239, 20, 108, 36);
		button_2.setText("\u67E5\u627E");
		
		final Composite composite_1 = new Composite(group, SWT.NONE);
		composite_1.setBounds(10, 27, 357, 79);
		
		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.NORMAL));
		label_2.setBounds(10, 29, 61, 17);
		label_2.setText("\u8FDB\u8D27\u5355\u53F7\uFF1A");
		
		text = new Text(composite_1, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		text.setBounds(75, 24, 158, 25);
		
		Button button_3 = new Button(composite_1, SWT.NONE);
		
		button_3.setText("\u67E5\u627E");
		button_3.setBounds(239, 19, 108, 36);
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setFont(SWTResourceManager.getFont("微软雅黑", 8, SWT.NORMAL));
		table.setBounds(10, 262, 890, 361);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn_9 = new TableColumn(table, SWT.NONE);
		tableColumn_9.setWidth(126);
		tableColumn_9.setText("\u8FDB\u8D27\u5355\u53F7");
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(86);
		tableColumn.setText("\u5546\u54C1\u7F16\u53F7");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(94);
		tableColumn_1.setText("\u5546\u54C1\u540D\u79F0");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(58);
		tableColumn_2.setText("\u5355\u4F4D");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(76);
		tableColumn_3.setText("\u5546\u54C1\u5206\u7C7B");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(62);
		tableColumn_4.setText("\u8FDB\u8D27\u4EF7\u683C");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(66);
		tableColumn_5.setText("\u96F6\u552E\u4EF7");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(65);
		tableColumn_6.setText("\u8FDB\u8D27\u6570\u91CF");
		
		TableColumn tableColumn_7 = new TableColumn(table, SWT.NONE);
		tableColumn_7.setWidth(76);
		tableColumn_7.setText("\u8FDB\u8D27\u65E5\u671F");
		
		TableColumn tableColumn_8 = new TableColumn(table, SWT.NONE);
		tableColumn_8.setWidth(63);
		tableColumn_8.setText("\u5408\u8BA1\u91D1\u989D");
		
		TableColumn tableColumn_10 = new TableColumn(table, SWT.NONE);
		tableColumn_10.setWidth(59);
		tableColumn_10.setText("\u9884\u8BA1\u6536\u5165");
		
		TableColumn tableColumn_11 = new TableColumn(table, SWT.NONE);
		tableColumn_11.setWidth(56);
		tableColumn_11.setText("\u7ECF\u529E\u4EBA");
		composite_1.setVisible(false);
		
		
		button_3.addSelectionListener(new SelectionAdapter() {
			//按单号查询
			public void widgetSelected(SelectionEvent e) {
				
					queryCode();
				
			}
		});
		
		button_2.addSelectionListener(new SelectionAdapter() {
			//按时间查询 查询按钮
			public void widgetSelected(SelectionEvent e) {
				queryDate();
			}
		});
		
		//选择查询方式 botton按日期  botton_1按进货单号
		button.addSelectionListener(new SelectionAdapter() {
			//按时间查询 radio按钮
			public void widgetSelected(SelectionEvent e) {
				if(button.getSelection()){
					composite.setVisible(true);
					composite_1.setVisible(false);
				}else{
					composite.setVisible(false);
					composite_1.setVisible(true);
				}

			}
		});
		
		button_1.addSelectionListener(new SelectionAdapter() {
			//按时间查询 radio按钮
			public void widgetSelected(SelectionEvent e) {
				if(button.getSelection()){
					composite.setVisible(true);
					composite_1.setVisible(false);
				}else{
					composite.setVisible(false);
					composite_1.setVisible(true);
				}

			}
		});
		
		
		
		
	}

	
	
	
	
	//按日期查询
	public void queryDate(){
		JDBCTools jt=new JDBCTools();
		String sql="SELECT * FROM kucun WHERE DATE BETWEEN '"+dateTime.getYear()+"-"+(dateTime.getMonth()+1)+"-"+dateTime.getDay()+"' AND '"+dateTime_1.getYear()+"-"+(dateTime_1.getMonth()+1)+"-"+dateTime_1.getDay()+"' ";
		//System.out.println(sql);
		ResultSet rs=jt.query(sql);
		try {
			table.removeAll();
			while(rs.next()){
				TableItem item=new TableItem(table,SWT.None);
				String[] temp=new String[12];
				temp[0]=rs.getString("billcode");
				temp[1]=rs.getString("code");
				temp[2]=rs.getString("name");
				temp[3]=rs.getString("unit");
				temp[4]=rs.getString("kind");
				temp[5]=rs.getString("cost");
				temp[6]=rs.getString("retail");
				temp[7]=rs.getString("jhcount");
				temp[8]=rs.getString("date");
				double sum=rs.getDouble("cost")*rs.getInt("jhcount");
				temp[9]=Double.toString(sum);
				double lirun=(rs.getDouble("retail")*rs.getInt("jhcount"))-(rs.getDouble("cost")*rs.getInt("jhcount"));
				temp[10]=Double.toString(lirun);
				temp[11]=rs.getString("people");
				item.setText(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		jt.close(rs);
		
	}
	//按单号查询
	public void queryCode(){
		JDBCTools jt=new JDBCTools();
		String sql="SELECT * FROM kucun WHERE billcode='"+text.getText()+"'";
		//System.out.println(sql);
		ResultSet rs=jt.query(sql);
		try {
			table.removeAll();
			while(rs.next()){
				TableItem item=new TableItem(table,SWT.None);
				String[] temp=new String[12];
				temp[0]=rs.getString("billcode");
				temp[1]=rs.getString("code");
				temp[2]=rs.getString("name");
				temp[3]=rs.getString("unit");
				temp[4]=rs.getString("kind");
				temp[5]=rs.getString("cost");
				temp[6]=rs.getString("retail");
				temp[7]=rs.getString("jhcount");
				temp[8]=rs.getString("date");
				double sum=rs.getDouble("cost")*rs.getInt("jhcount");
				temp[9]=Double.toString(sum);
				double lirun=(rs.getDouble("retail")*rs.getInt("jhcount"))-(rs.getDouble("cost")*rs.getInt("jhcount"));
				temp[10]=Double.toString(lirun);
				temp[11]=rs.getString("people");
				item.setText(temp);
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
