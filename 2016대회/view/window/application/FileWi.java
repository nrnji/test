package window.application;

import java.awt.FileDialog;
import java.io.FileWriter;

import base.comp.BaseFrame;

public class FileWi extends BaseFrame{

	public FileWi(String title, String file) {
		
		FileDialog fdl = new FileDialog(this, "�ؽ�Ʈ ���Ϸ� �����ϱ�", FileDialog.SAVE);
		fdl.setFile(title+"������Ȳ");
		fdl.setDirectory(".");
		fdl.setVisible(true);
		
		String path = fdl.getDirectory() + fdl.getFile() + ".txt";
		
		try {
			if (!(path.equals("nullnull.txt"))) {
				FileWriter writer = new FileWriter(path);
				writer.write(file);
				writer.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
//		new FileWi("������Ȳ", "asdf");
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAction() {
		// TODO Auto-generated method stub
		
	}

}
