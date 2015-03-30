package cn.com.wei.action;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class Restart extends Action{
	private IWorkbenchWindow  window;
    public Restart(IWorkbenchWindow window) {
	         this.window = window;
      }
    public void run(){
	   IWorkbench iw =  PlatformUI.getWorkbench();
	   iw.restart();
	}
}
