package beans;

public class Audio {
	String level;
	String name;
	int number;
	String filename;
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getFilename() {
		if (filename!=null){
			return filename;
		} else { 
			System.out.println("Filename de audio es null");
			return "nofilename";
		}
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Audio(String level, String name, int number, String filename) {
		super();
		this.level = level;
		this.name = name;
		this.number = number;
		this.filename = filename;
	}
	
}
