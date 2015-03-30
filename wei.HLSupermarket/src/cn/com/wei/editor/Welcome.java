package cn.com.wei.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.wei.shell.LoginShell;
import cn.com.wei.shell.Notice;

public class Welcome extends EditorPart {

	public static final String ID = "cn.com.wei.editor.Welcome"; //$NON-NLS-1$

	public Welcome() {
		setTitleImage(SWTResourceManager.getImage(Welcome.class, "/img/22.png"));
		
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundImage(SWTResourceManager.getImage(Welcome.class, "/img/welcome.jpg"));
		if(LoginShell.getLimits().equals("管理员")){
			Notice.start();//打开提醒界面
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
