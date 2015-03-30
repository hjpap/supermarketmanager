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
        //configurer.setShellStyle(SWT.TITLE | SWT.MIN);//��������С
        Display display = Display.getDefault();//����һ��display����
        Rectangle rect = display.getBounds();//ͨ��display�������getBounds()������õ�����Ļ�ĳ��Ϳ�
        configurer.setInitialSize(new Point(rect.width,rect.height));
        configurer.setShowCoolBar(true);
        configurer.setShowStatusLine(false);
        configurer.setTitle("�������н�������ϵͳ"); //$NON-NLS-1$
    }

	@Override
	public void postWindowClose() {
		//�رս���ʱɾ��������͹ҵ����к��˻���ʱ���е���ʱ�ļ�
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
	    	Input input = new Input();//Ĭ�ϴ򿪵��Ǹ�editor��input
	    	input.setName("");
	    	input.setToolTipText("Welcome");
	    	try {
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(input, Welcome.ID);//Ĭ�ϴ�һ��editor!!!
			} catch (PartInitException e) {
				e.printStackTrace();
			}
	    }
}
