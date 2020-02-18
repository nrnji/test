package base.comp;

import java.awt.Font;

public class BaseFont extends Font{

	public BaseFont(String name, int style, int size) {
		super(name, style, size);
	}
	
	public BaseFont(int style, int size) {
		super("���� ���", style, size);
	}
	
	public BaseFont(int size) {
		super("���� ���", Font.PLAIN, size);
	}
	
}
