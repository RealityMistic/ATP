package beans;

public class Prize {
	String type, level;
	int threshold;
	String filename;
	public Prize(String levelp, String typep, int pointsp, String filenamep){
		super();
		this.filename=filenamep;
		this.type=typep;
		this.level=levelp;

	}
	public String getType() {
		return type;
	}
	public void setType(String typep) {
		this.type = typep;
	}
	public int getThreshold() {
		return threshold;
	}
	public void setThreshold(int thresholdp) {
		this.threshold = thresholdp;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filenamep) {
		this.filename = filenamep;
	}
	
}
