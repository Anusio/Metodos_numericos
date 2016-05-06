package metodos;

public class Exibir {
	private String temp = "";
	
	public void setText(String text) {
		temp += text;
	}
	
	public void newline() {
		temp += "\n";
	}
	
	public String getText() {
		try{
			return temp;
		}finally{
			temp = "";
		}
	}
}
