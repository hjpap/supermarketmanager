package cn.com.wei.core;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import cn.com.wei.action.AlterPerson;
import cn.com.wei.action.CloseSystem;
import cn.com.wei.action.OpenBoss;
import cn.com.wei.action.OpenCashier;
import cn.com.wei.action.OpenManager;
import cn.com.wei.action.Restart;
import cn.com.wei.action.ShowHelp;
import cn.com.wei.shell.LoginShell;
import org.eclipse.wb.swt.ResourceManager;


public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
		
		private OpenCashier openCashier;//�������˵�
		private OpenManager openManager;//�򿪹���Ա�˵�
		private OpenBoss openBoss;//�򿪾���˵�
		private CloseSystem closeSystem;//�ر�ϵͳ
		private Restart reStart;//�л��û�
		private AlterPerson alterPerson;//��������
		private ShowHelp showHelp;
		
		
		
		
    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);    
    }

    protected void makeActions(IWorkbenchWindow window) {
    	openCashier=new OpenCashier(window);
    	openCashier.setImageDescriptor(ResourceManager.getImageDescriptor(ApplicationActionBarAdvisor.class, "/img/meun3.png"));
    	openCashier.setToolTipText("������Ա�˵�");
    	openCashier.setText("����Ա�˵�");
    	
    	openManager=new OpenManager(window);
    	openManager.setImageDescriptor(ResourceManager.getImageDescriptor(ApplicationActionBarAdvisor.class, "/img/meun2.png"));
    	openManager.setToolTipText("�򿪹���Ա�˵�");
    	openManager.setText("����Ա�˵�");
    	
    	openBoss=new OpenBoss(window);
    	openBoss.setImageDescriptor(ResourceManager.getImageDescriptor(ApplicationActionBarAdvisor.class, "/img/meun1.png"));
    	openBoss.setText("����˵�");
    	openBoss.setToolTipText("�򿪾���˵�");
    	
    	alterPerson=new AlterPerson();
    	alterPerson.setImageDescriptor(ResourceManager.getImageDescriptor(ApplicationActionBarAdvisor.class, "/img/shezhi.png"));
    	alterPerson.setText("��������");
    	alterPerson.setToolTipText("��������");
    	
    	reStart=new Restart(window);
    	reStart.setImageDescriptor(ResourceManager.getImageDescriptor(ApplicationActionBarAdvisor.class, "/img/qiehuanyonghu.png"));
    	reStart.setText("�л��û�");
    	reStart.setToolTipText("�л��û�");
    	
    	showHelp=new ShowHelp();
    	showHelp.setImageDescriptor(ResourceManager.getImageDescriptor(ApplicationActionBarAdvisor.class, "/img/help.png"));
    	showHelp.setText("����");
    	showHelp.setToolTipText("����");
    	
    	
    	closeSystem=new CloseSystem(window);
    	closeSystem.setImageDescriptor(ResourceManager.getImageDescriptor(ApplicationActionBarAdvisor.class, "/img/close.png"));
    	closeSystem.setText("�˳�");
    	closeSystem.setToolTipText("�˳�ϵͳ");
    	
    	
    	
    }

    protected void fillMenuBar(IMenuManager menuBar) {
    	
    }

	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		if(LoginShell.getLimits().equals("����Ա")){
			IToolBarManager opencashier=new ToolBarManager();
		opencashier.add(openCashier);
		coolBar.add(opencashier);
		}
		
		if(LoginShell.getLimits().equals("����Ա")){
			IToolBarManager openmanager=new ToolBarManager();
		    openmanager.add(openManager);
		    coolBar.add(openmanager);
		}
		if(LoginShell.getLimits().equals("����")){
			IToolBarManager openboss=new ToolBarManager();
		    openboss.add(openBoss);
		    coolBar.add(openboss);
		    
		    IToolBarManager opencashier=new ToolBarManager();
			opencashier.add(openCashier);
			coolBar.add(opencashier);
			
			IToolBarManager openmanager=new ToolBarManager();
		    openmanager.add(openManager);
		    coolBar.add(openmanager);
		}
		
		IToolBarManager alterperson=new ToolBarManager();
		alterperson.add(alterPerson);
		coolBar.add(alterperson);
		
		
		IToolBarManager restart=new ToolBarManager();
		restart.add(reStart);
		coolBar.add(restart);
		
		IToolBarManager showhelp=new ToolBarManager();
		showhelp.add(showHelp);
		coolBar.add(showhelp);
		
		
		IToolBarManager closesystem=new ToolBarManager();
		closesystem.add(closeSystem);
		coolBar.add(closesystem);
		
		
	}
    
}
