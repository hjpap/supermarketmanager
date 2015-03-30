package cn.com.wei.core;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;



import cn.com.wei.editor.Welcome;
import cn.com.wei.input.Input;
import cn.com.wei.tools.JDBCTools;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }
    
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        //configurer.setShellStyle(SWT.TITLE | SWT.MIN);//控制面板大小
        Display display = Display.getDefault();//创建一个display对象
        Rectangle rect = display.getBounds();//通过display对像调用getBounds()方法获得电脑屏幕的长和宽
        configurer.setInitialSize(new Point(rect.width,rect.height));
        configurer.setShowCoolBar(true);
        configurer.setShowStatusLine(false);
        configurer.setTitle("海量超市进销管理系统"); //$NON-NLS-1$
    }

	@Override
	public void postWindowClose() {
		//关闭界面时删除收银表和挂单表中和退货临时表中的临时文件
		JDBCTools jt=new JDBCTools();
		String sql="DELETE  FROM cashier";
		String sql1="Delete from cashier2";
		String sql2="Delete from returns";
		jt.update(sql);
		jt.update(sql1);
		jt.update(sql2);
		jt.close(null);
	}
    
	  public void postWindowOpen() {
	    	Input input = new Input();//默认打开的那个editor的input
	    	input.setName("");
	    	input.setToolTipText("Welcome");
	    	try {
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(input, Welcome.ID);//默认打开一个editor!!!
			} catch (PartInitException e) {
				e.printStackTrace();
			}
	    }
}
