package beans;


public class Player {
	String name, user, email, gender, edlevel, pass;
	int age, jourpoints, explopoints;
	
	public int getJourpoints() {
		return jourpoints;
	}


	public void setJourpoints(int jourpoints) {
		this.jourpoints = jourpoints;
	}


	public int getExplopoints() {
		return explopoints;
	}


	public void setExplopoints(int explopoints) {
		this.explopoints = explopoints;
	}


	public String getName(){
		return name;
	}


	public void setName(String namep){
		name = namep;
	}
	public void setEmail(){
		email="";
	}
	public void setEmail(String emailp){
		email = emailp;
	}
	public void setGender(){
		gender = "unknown";
	}
	public void setGender(String genderp){
		gender = genderp;
	}
	public String getGender(){
		return gender;
	}

	public void setEdlevel(String edlevelp){
		edlevel = edlevelp;
	}
	public void setAge(){
		age = 0;
	}
	public void setAge(int agep){
		age = agep;
	}
	
	public void setUser(String userp){
		user = userp;
	}
	public String getUser(){
		return user;
	}
	public String getEmail() {
		return email;
	}


	public String getEdlevel() {
		return edlevel;
	}


	public int getAge() {
		return age;
	}


	public Player() {
		setName("Anonymous");  setGender("Unknown"); setEdlevel("Unknown"); 
	}
	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}




	public Player(String name, String user, String email, String gender, String edlevel, String pass, int age,
			int jourpoints, int explopoints) {
		super();
		this.name = name;
		this.user = user;
		this.email = email;
		this.gender = gender;
		this.edlevel = edlevel;
		this.pass = pass;
		this.age = age;
		this.jourpoints = jourpoints;
		this.explopoints = explopoints;
	}
}
