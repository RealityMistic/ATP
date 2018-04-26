<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="beans.Audio, beans.Player, java.io.PrintWriter, 
    AudioTrainerPlay.AudioController, AudioTrainerPlay.JSONController, AudioTrainerPlay.PrizeController"%>
<!DOCTYPE html>
<html>
<head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1"><link href="css/materialize.min.css" rel="stylesheet" type="text/css">
<script src="js/jquery-3.1.1.min.js">
</script>
<script src="/audio.js/audio.min.js"></script>
<!-- Se activa audio.js para que los audios se oigan en cualquier navegador -->
<script>
  audiojs.events.ready(function() {
    var as = audiojs.createAll();
  });
</script>
<!-- Esta función es para que el select de materialize funcione bien -->
<script type="text/javascript">
  $(document).ready(function(){
	  ('select').material_select();
  })
</script> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Explorer Audio Game</title>

<% 
// Se inicializan las variables de sesión y audio
Audio currentaudio = null;
Audio oldaudio = null;
HttpSession s=request.getSession();
// Se carga la variable path desde el parámetro de sesión establecido por el servlet
String path = (String) s.getAttribute("path");
AudioController ac= new AudioController(path);
if (s.getAttribute("oldaudio")!=null){
	oldaudio = (Audio) s.getAttribute("oldaudio");
}
// System.out.println("ID Session "+ s.getId()+ " Audio "+ ac.getLevel());
Player player = null;
// Se lee el atributo de sesión con el bean Player establecido por la llamada anterior
player = (Player) s.getAttribute("player");
// System.out.println("Player "+player.getName());
response.setContentType("text/html");
PrintWriter dd=response.getWriter();
// Comprobación de que se ha podido cargar un jugador, es un chequeo de errores
/*
if (player!=null){
	
	dd.println("Player " + player.getUser());
} else {
	dd.println("Player is null!!!");
}
*/
String startnew="false";

// Se recibe el parámetro que indica si se empieza un nuevo juego
// y se asigna al flag String startnew
if (request.getParameter("startnew")!=null){
	startnew = request.getParameter("startnew");
	// System.out.println("Startnew: "+startnew);
}
int numaudio=1;
String level = "A1";
// System.out.println("Startnew Session: " + startnew);

Boolean continueexplorergame= new Boolean(false);
Cookie[] cookies = request.getCookies();
// System.out.println("Parameter level: "+request.getParameter("level"));
if (request.getParameter("level")!=null){
	level = (String) request.getParameter("level");
}
Cookie levelCookie, numaudioCookie;
levelCookie=null; numaudioCookie=null;
// Si se empieza un nuevo juego, se carga el nivel recibido por parámetro
// y el número de audio que debe de estar a 1.
// Tambien se ponen los puntos del jugador a 0, ya que se entiende que
// renuncia a su puntuación anterior.
if ( startnew.equals("true")) {

//	System.out.println("Startnew again!");
	ac.setLevel(level);
//	System.out.println("Level- "+level+" numaudio- "+numaudio);
	ac.setNumaudio(numaudio);
	currentaudio = ac.loadAudio();
	PrizeController pc = new PrizeController(path);
	player.setExplopoints(0);
	pc.updateExploPoints(player.getUser(),0);
//	dd.println("Current Audio " + currentaudio.getFilename() );
} else {
// Este brazo se ejecuta si no se empieza una nueva partida, se leen los
// atributos nextaudio y level de sesión
	if (s.getAttribute("level")!= null){
		level = (String) s.getAttribute("level");
//		System.out.println("startfalse-else "+level);
	}
	if (s.getAttribute("nextaudio")!=null){ 
	//	System.out.println("Updating audio...");
// Se carga como audio actual, el que en la pasada ejecución era el próximo audio.
		currentaudio = (Audio) s.getAttribute("nextaudio");
		level = currentaudio.getLevel();
		numaudio = currentaudio.getNumber();
		
// Se comprueba que no se ha pulsado el botón CONTINUE
		if (s.getAttribute("continueexplorergame") != null){
	//		System.out.println("Evaluating continueexplorergame session variable");
			continueexplorergame = (Boolean) s.getAttribute("continueexplorergame");
	//		System.out.println("continueexplorer game is: "+ continueexplorergame.toString());
			if (continueexplorergame.booleanValue()==true){
// Si se ha pulsado el botón CONTINUE, se retrocede al audio anterior y se pone
// el flag de continue a false. Si no se ha jugado antes, se empieza en el primer audio
// del nivel más bajo.
			//	System.out.println("Decreasing audionumber");
			//	numaudio--;
				if (oldaudio != null){ 
					numaudio = oldaudio.getNumber();
					level = oldaudio.getLevel();
				} else {
					numaudio = 1;
					level = "A1";
				}
				
				
			    s.setAttribute("continueexplorergame", new Boolean(false));
			}
		}
		// System.out.println("Current Audio " + currentaudio.getFilename() );
	// dd.println("Current Audio "+ currentaudio.getFilename() );
	} else {
	//	System.out.println("Chequeando cookies");
	// Si se ejecuta este brazo, es que hay que leer las cookies para saber
	// en que nivel se quedó el jugador
		
		// Se recorren todas las cookies y se extraen la llamada "ATPLevel" y "levelCookie"
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("ATPlevel")){
				levelCookie = cookie;
			//	System.out.println("levelCookie" + levelCookie.toString());
			}
			if(cookie.getName().equals("ATPnumaudio")){
				numaudioCookie = cookie;
			//	System.out.println("numaudioCookie "+numaudioCookie.toString());
			}
		}
		// Si hay cookie con número de audición, se lee y se asigna el número
		if (numaudioCookie!=null){
			numaudio = Integer.parseInt(numaudioCookie.getValue());
		} else {
			System.out.println("numaudioCookie is null");
		}
		// Si hay cookie con el número de nivel, se lee y se asigna el nivel
		if (levelCookie!=null){
			
			level= levelCookie.getValue();
//			System.out.println("cookie-level: " + level);
		} else { 
			System.out.println("levelCookie is null");
		}
		
		
	}
	
	// numaudio=1;
	// currentaudio = ac.loadAudio();
	// numaudio=currentaudio.getNumber();
// Se carga el audio actual en currentaudio	
	ac.setLevel(level);
	ac.setNumaudio(numaudio);
	currentaudio = ac.loadAudio();
	// dd.println("Current Audio "+ currentaudio.getFilename() );
	if (currentaudio==null){
		if (s.getAttribute("continueexplorergame") != null){
			//		System.out.println("Evaluating continueexplorergame session variable");
					continueexplorergame = (Boolean) s.getAttribute("continueexplorergame");
			//		System.out.println("continueexplorer game is: "+ continueexplorergame.toString());
					if (continueexplorergame.booleanValue()==true){
						//numaudio--;
						s.setAttribute("continueexplorergame", new Boolean(false));
					}
			}
	// Se asigna al audio el nuevo nivel y el número 1 (para empezar desde el principio)
		ac.setLevel(level); ac.setNumaudio(1);
		currentaudio = ac.loadAudio();
	//	dd.println("Current Audio "+ currentaudio.getFilename());
	}
} // del else de chequear cookies
// En esta variable se almacena la cadena a visualizar en la página, con el nivel y el número
String level_and_number = "Language level: " + level + "<br/>Number of Audio: " + numaudio;
// Se prepara una cookie con el nivel actual
Cookie ATPlevel = new Cookie ("ATPlevel", currentaudio.getLevel());
ATPlevel.setMaxAge(60*24*365);
// Se prepara otra cookie con el número de audición y se envían las dos
String nextaudionumber = Integer.toString(currentaudio.getNumber()); 
Cookie ATPnumaudio = new Cookie ("ATPnumaudio", nextaudionumber);
ATPnumaudio.setMaxAge(60*34*365);
response.addCookie(ATPlevel);
response.addCookie(ATPnumaudio);
%>
<style>
body{
	background: no-repeat left top url(<%= "images//"+ currentaudio.getFilename()+".jpg" %>) ;
	width: 100%;
	height: 100%;
	height: auto !important;
	min-height:100%;
}
</style>


</head>

<!--  <body background="<%= "images/"+ currentaudio.getFilename()+".jpg" %>">
-->
  <body>

<nav>
<div class='nav-wrapper deep-purple blue accent-1'>
<a href="http://atlas.uned.es/" class='brand-logo left'>

<img src='images/logos/ATLASlogo-s.png'>

</a>
<a href="http://www.uned.es/" class='brand-logo center'>

<img src='images/logos/logo_uned.png'>

</a>
<a href="http://www.etsisi.upm.es/" class='brand-logo right'>
<img src='images/logos/ETSISIlogo.png'>
</a>

</div>
</nav>

<div class="container">
<div class="row">
<div class="col s8 card grey lighten-1">
 <h3><%=player.getUser() %> playing!</h3>
 <h4>Listening to: <%=currentaudio.getName() %></h4>
 <h4><%=level_and_number%></h4>
 <br/>
 <h4>Total points: <%=player.getExplopoints() %></h4>
<audio src="audios/<%= currentaudio.getFilename() %>" type="audio/mpeg" controls="controls" autoplay>
Your browser does not support Audio
</audio>    
</div> <!-- del col -->
</div>  <!-- del row -->
<div class='container'>
<div class='row' >
<div class='col s8' style="text-align:center">
<form action="main-menu.html">
    <input type="submit" class="btn btn-large btn-floating red" value="Menu"> 
</form>
</div>
</div>   
</div>   <!-- del container del botón -->      
</div>   <!--  del container -->             

<%
//Creamos el atributo oldaudio con el audio recién escuchado, para que al pasar de nivel
// se pueda retroceder.
oldaudio = currentaudio;
s.setAttribute("oldaudio", oldaudio);
// Se asigna a audionumber el número de la audición recién escuchada
int audionumber= currentaudio.getNumber();
// La incrementamos para que se escuche el siguiente audio
// en la próxima ejecución de ExplorerPlay.jsp
audionumber++;
// System.out.println("Audionumber " + audionumber);


// System.out.println("Next audionumber " + audionumber);

// System.out.println("Phase 2: setting level "+level+" and audionumber: "+audionumber);
ac.setLevel(level);
ac.setNumaudio(audionumber);
// System.out.println("Audio controller numaudio "+ac.getNumaudio() );
Audio nextaudio = ac.loadAudio();
// Si se ha podido cargar un Audio, se asigna a la variable de sesión nextaudio
if ( nextaudio!=null){
//	dd.println("Next Audio "+ nextaudio.getFilename());
	s.setAttribute("nextaudio", nextaudio);
} else {
	// En caso de que no, se entiende que ha acabado el nivel, se pasa al siguiente
	// o al final del juego.
//	System.out.println("Level up!");
	switch (level){
		case "A1": level = "A2";
		break;
		case "A2": level = "A2.2";
		break;
		case "A2.2": level = "B1";
		break;
		case "B1": level = "B1.2";
		break;
		case "B1.2": level = "B2";
		break;
		case "B2": request.getRequestDispatcher("EndGame.jsp").forward(request, response);
		break;
		//case "C1": level = "A1";
		// break;
		default: 
			level ="A1";
			break;
	}
	// Se asigna al audio el nuevo nivel y el número 1 (para empezar desde el principio)
	ac.setLevel(level); ac.setNumaudio(1);
	nextaudio = ac.loadAudio();
//	dd.println("Next Audio "+ nextaudio.getFilename());
// Pasamos a sesión el próximo audio en nextaudio, para que se pueda avanzar
	s.setAttribute("nextaudio", ac.loadAudio());
	s.setAttribute("level", level);

}
// Cargamos el controlador de JSON
JSONController jc= new JSONController(path);

String generatedtest= jc.generateTest(currentaudio);
s.setAttribute("currentaudio", currentaudio);
%>
<br/>
<div class="container">
<div class="row">
<div class="col s8 card grey lighten-1 white-text">
<h3>Check your understanding!<br/>
Level:<%=currentaudio.getLevel() %>
</h3>
</div>
</div>
</div>
<div class="container">
<div class="col s8">
<%=generatedtest %>
<!--  del container -->

</div>
</div>
<div class='container'>
<div class="col s8">
<button class='btn btn-large custom-btn-s' type='submit'>Check result!</button></form>
<a class='btn btn-large custom-btn-s orange' href="ExplorerPlay.jsp?startnew=false&level=<%=level %>" >
Next Audio!
</a>
</div> 
</div>
<br/>
</body>
</html>