package cn.com.wei.action;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import cn.com.wei.view.Boss;
import cn.com.wei.view.Cashier;
import cn.com.wei.view.Manager;

public class OpenBoss extends Action {
	private IWorkbenchWindow window;
	public OpenBoss(IWorkbenchWindow window){
		this.window=window;
	}
	
	
	public void run() {
		try {
			window.getActivePage().hideView(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Cashier.ID));
			window.getActivePage().hideView(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(Manager.ID));
			window.getActivePage().showView(Boss.ID);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	
	
}
