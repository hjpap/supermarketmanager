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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableItem;

import cn.com.wei.shell.AddGhs;
import cn.com.wei.shell.AlterGhs;
import cn.com.wei.shell.DeleteAllgong;
import cn.com.wei.tools.JDBCTools;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class QueryGonghuoshang extends EditorPart {

	public static final String ID = "cn.com.wei.editor.QueryGonghuoshang"; //$NON-NLS-1$
	private Table table;

	public QueryGonghuoshang() {
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
		label_1.setImage(SWTResourceManager.getImage(QueryGonghuoshang.class, "/img/cxTitle.png"));
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_1.setBounds(50, 23, 85, 86);
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setText("\u4F9B\u8D27\u5546\u67E5\u8BE2");
		label_2.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 20, SWT.BOLD));
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_2.setBounds(170, 47, 153, 42);
		
		Label label = new Label(container, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setBounds(0, 0, 914, 125);
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 12, SWT.NORMAL));
		table.setBounds(0, 243, 914, 420);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("\u7F16\u53F7");
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(203);
		tableColumn.setText("\u4F9B\u8D27\u5546");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(148);
		tableColumn_1.setText("\u8054\u7CFB\u7535\u8BDD");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(142);
		tableColumn_2.setText("\u8054\u7CFB\u4EBA");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(303);
		tableColumn_3.setText("\u5730\u5740");
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.addSelectionListener(new SelectionAdapter() {
			//ÓÒ¼ü É¾³ý
			public void widgetSelected(SelectionEvent e) {
				delete();
			}
		});
		menuItem.setText("\u5220\u9664");
		
		MenuItem menuItem_1 = new MenuItem(menu, SWT.NONE);
		menuItem_1.setText("\u4FEE\u6539");
		
		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			//Ìí¼Ó
			public void widgetSelected(SelectionEvent e) {
				AddGhs.start();
				showTable();
			}
		});
		button.setBounds(10, 199, 80, 27);
		button.setText("\u6DFB\u52A0");
		
		Button button_1 = new Button(container, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			//ÐÞ¸Ä
			public void widgetSelected(SelectionEvent e) {
				AlterGhs.start(table.getSelection()[0]);
				showTable();
			}
		});
		button_1.setBounds(111, 199, 80, 27);
		button_1.setText("\u4FEE\u6539");
		
		Button button_2 = new Button(container, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			//É¾³ý
			public void widgetSelected(SelectionEvent e) {
				delete();
			}
		});
		button_2.setBounds(219, 199, 80, 27);
		button_2.setText("\u5220\u9664");
		
		Button button_3 = new Button(container, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			//É¾³ýÈ«²¿
			public void widgetSelected(SelectionEvent e) {
				DeleteAllgong.start();
				showTable();
			}
		});
		button_3.setBounds(327, 199, 80, 27);
		button_3.setText("\u5168\u90E8\u5220\u9664");
		
		Button button_4 = new Button(container, SWT.NONE);
		button_4.addSelectionListener(new SelectionAdapter() {
			//Ë¢ÐÂ
			public void widgetSelected(SelectionEvent e) {
				showTable();
			}
		});
		button_4.setBounds(645, 199, 80, 27);
		button_4.setText("\u5237\u65B0");
		showTable();

	}
	//É¾³ý
	public void delete(){
		JDBCTools jt=new JDBCTools();
		 String s="";

		 int[] t = table.getSelectionIndices();

		 TableItem[] items = table.getSelection();

		    for(int i=0;i<items.length;i++)
		     {

		        s=table.getSelection()[i].getText(0);

		        String sql = "delete from gonghuoshang where id='"+s+"'";

		        jt.update(sql);
		     
		 }    
		    table.remove(t);
		    jt.close(null);
	}
	
	
	//²éÕÒÏÔÊ¾¹©»õÉÌÐÅÏ¢
	public void showTable(){
		JDBCTools jt=new JDBCTools();
		String sql="select * from gonghuoshang";
		
		ResultSet rs=jt.query(sql);
		
		try {
			table.removeAll();
			while(rs.next()){
				TableItem item=new TableItem(table,SWT.None);
				String[] temp=new String[5];
				temp[0]=rs.getString("id");
				temp[1]=rs.getString("name");
				temp[2]=rs.getString("tel");
				temp[3]=rs.getString("people");
				temp[4]=rs.getString("address");
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
