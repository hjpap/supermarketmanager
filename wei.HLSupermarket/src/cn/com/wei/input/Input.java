package cn.com.wei.input;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class Input implements IEditorInput{
    private String name;
    private String toolTipText;
    
	public void setName(String name) {
		this.name = name;
	}
	public void setToolTipText(String toolTipText) {
		this.toolTipText = toolTipText;
	}
	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return toolTipText;
	}
	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}
    
}
