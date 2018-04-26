package beans;

public class FPrize {
	String type;
	int threshold;
	String filename;
	public FPrize(String typep, int pointsp, String filenamep){
		super();
		this.filename=filenamep;
		this.type=typep;

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
