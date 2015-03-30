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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import cn.com.wei.tools.JDBCTools;
import org.eclipse.swt.widgets.Group;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;

public class Sunhao extends EditorPart {

	public static final String ID = "cn.com.wei.editor.Sunhao"; //$NON-NLS-1$
	private Table table;
	private Label label_2;
	private Label label_1;

	public Sunhao() {
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
		
		Label label_3 = new Label(container, SWT.NONE);
		label_3.setImage(SWTResourceManager.getImage(Sunhao.class, "/img/sunhaoTitle.png"));
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_3.setBounds(51, 21, 99, 94);
		
		Label label_4 = new Label(container, SWT.NONE);
		label_4.setText("\u635F\u8017");
		label_4.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 20, SWT.BOLD));
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_4.setBounds(171, 49, 153, 42);
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 157, 894, 468);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(140);
		tblclmnNewColumn.setText("\u5546\u54C1\u7F16\u53F7");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(133);
		tblclmnNewColumn_1.setText("\u5546\u54C1\u540D\u79F0");
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("\u8FDB\u4EF7");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("\u96F6\u552E\u4EF7");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("\u6570\u91CF");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("\u5408\u8BA1");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("\u539F\u56E0");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("\u635F\u8017\u65E5\u671F");
		
		Label label = new Label(container, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setBounds(0, 0, 914, 125);
		
		label_1 = new Label(container, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 11, SWT.NORMAL));
		label_1.setBounds(647, 631, 105, 17);
		label_1.setText("\u5408\u8BA1\u635F\u8017\u4EF7\u503C\uFF1A");
		
		label_2 = new Label(container, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 11, SWT.NORMAL));
		label_2.setBounds(775, 631, 105, 17);
		showTable();
		

	}
	
	//ÏÔÊ¾ËðºÄÐÅÏ¢
	public void showTable(){
		JDBCTools jt=new JDBCTools();
		String sql="select * from sunhao";
		ResultSet rs=jt.query(sql);
		try {
			table.removeAll();
			double sum=0;
			while(rs.next()){
				TableItem item=new TableItem(table,SWT.None);
				String [] temp=new String[8];
				temp[0]=rs.getString("code");
				temp[1]=rs.getString("name");
				temp[2]=rs.getString("cost");
				temp[3]=rs.getString("retail");
				temp[4]=rs.getString("xcount");
				temp[5]=rs.getString("total");
				temp[6]=rs.getString("yuanyin");
				temp[7]=rs.getString("date");
				item.setText(temp);
				sum=sum+rs.getDouble("total");
				
			}
			
			label_2.setText(Double.toString(sum)+"Ôª");
			
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
