package base.comp;

import java.awt.Font;

public class BaseFont extends Font{

	public BaseFont(String name, int style, int size) {
		super(name, style, size);
	}
	
	public BaseFont(int style, int size) {
		super("¸¼Àº °íµñ", style, size);
	}
	
	public BaseFont(int size) {
		super("¸¼Àº °íµñ", Font.PLAIN, size);
	}
	
}
