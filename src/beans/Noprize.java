package beans;

public class Noprize {
		String type, level;
		String filename;
		public Noprize(String type, String level, String filename) {
			super();
			this.type = type;
			this.level = level;
			this.filename = filename;
		}
		
		public String getLevel(){
			return level;
		}
		public void setLevel(String levelp){
			this.level= levelp;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}

		public String getFilename() {
			return filename;
		}
		public void setFilename(String filenamep) {
			this.filename = filenamep;
		}
		
	}


