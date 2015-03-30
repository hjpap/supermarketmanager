package cn.com.wei.editor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
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

import cn.com.wei.shell.Xiajia;
import cn.com.wei.tools.JDBCTools;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class Grounding extends EditorPart {

	public static final String ID = "cn.com.wei.editor.Grounding"; //$NON-NLS-1$
	private Table table;

	public Grounding() {
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setImage(SWTResourceManager.getImage(Grounding.class, "/img/cxTitle.png"));
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_1.setBounds(59, 22, 85, 86);
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setText("\u4F9B\u8D27\u5546\u67E5\u8BE2");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 20, SWT.BOLD));
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_2.setBounds(179, 46, 153, 42);
		
		Label label = new Label(container, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setBounds(0, 0, 914, 125);
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(0, 216, 914, 475);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("\u5546\u54C1\u7F16\u53F7");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("\u5546\u54C1\u540D\u79F0");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("\u5546\u54C1\u5355\u4F4D");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("\u5546\u54C1\u5206\u7C7B");
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("\u8FDB\u4EF7");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("\u96F6\u552E\u4EF7");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(80);
		tableColumn_4.setText("\u4E0A\u67B6\u6570\u91CF");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(74);
		tblclmnNewColumn_2.setText("\u62A5\u8B66\u6570");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(74);
		tblclmnNewColumn_3.setText("\u4E0A\u67B6\u65E5\u671F");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_4.setWidth(100);
		tblclmnNewColumn_4.setText("\u72B6\u6001");
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.addSelectionListener(new SelectionAdapter() {
			//下架meun
			public void widgetSelected(SelectionEvent e) {
				Xiajia.start(table.getSelection()[0]);
				showTable();
			}
		});
		menuItem.setText("\u4E0B\u67B6");
		
		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			//商品下架
			public void widgetSelected(SelectionEvent e) {
				Xiajia.start(table.getSelection()[0]);
				showTable();
			}
		});
		button.setBounds(692, 183, 80, 27);
		button.setText("\u5546\u54C1\u4E0B\u67B6");
		
		Button button_1 = new Button(container, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			//刷新
			public void widgetSelected(SelectionEvent e) {
				showTable();
			}
		});
		button_1.setBounds(778, 183, 80, 27);
		button_1.setText("\u5237\u65B0");
		showTable();

	}
	
	//显示所有上架信息
	public void showTable(){
		JDBCTools jt=new JDBCTools();
		String sql="select * from grounding";
		ResultSet rs=jt.query(sql);
		try {
			table.removeAll();
			while(rs.next()){
				TableItem item=new TableItem(table,SWT.None);
				String[] temp=new String[10];
				temp[0]=rs.getString("code");
				temp[1]=rs.getString("name");
				temp[2]=rs.getString("unit");
				temp[3]=rs.getString("kind");
				temp[4]=rs.getString("cost");
				temp[5]=rs.getString("retail");
				temp[6]=rs.getString("sjcount");
				temp[7]=rs.getString("dangers");
				temp[8]=rs.getString("date");
				if(rs.getInt("sjcount")>rs.getInt("dangers")){
					temp[9]="正常";
				}else{
					temp[9]="报警！上架数量不足";
					item.setBackground(new Color(null, 255,165,0));
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
