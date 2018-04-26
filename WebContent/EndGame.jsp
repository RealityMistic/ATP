<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="beans.Audio,
    	 beans.Player, beans.FPrize, beans.Noprize,
    	 java.io.PrintWriter, 
    	AudioTrainerPlay.AudioController,
     	AudioTrainerPlay.JSONController, 
     	AudioTrainerPlay.PrizeController,
     	AudioTrainerPlay.TranscriptController,
     	java.util.Arrays"%>
     	
<!DOCTYPE html>
<html>
<head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
       <!--         <link href="ANT-Cesar.css" rel="stylesheet" type="text/css">  -->
        <link href="css/materialize.min.css" rel="stylesheet" type="text/css">
        <link href="css/ATP-button.css" rel="stylesheet" type="text/css">

<script src="/audio.js/audio.min.js"></script>
<script>
  audiojs.events.ready(function() {
    var as = audiojs.createAll();
  });
</script>
<script>
function viewTranscript(){   
        document.getElementById("Transcript").style.visibility="visible";
        return true;
}
</script>
<title>ATP Final Screen</title>
</head>
<body>
<div class="container">
<div class="row">                    
<div style="text-align:center;">

<% 
PrintWriter dd=response.getWriter();

%>



<%
HttpSession s=request.getSession();
response.setContentType("text/html");
String level="A1";
String path = (String) s.getAttribute("path");
Player player = (Player) s.getAttribute("player");
if (request.getParameter("level")!=null){
	level = (String) request.getParameter("level");
}
player = (Player) s.getAttribute("player");
Audio checked_audio=(Audio) s.getAttribute("currentaudio");
JSONController jc = new JSONController(path);
int[] resultados = jc.retrieveAnswers(checked_audio);

// System.out.println(Arrays.toString(resultados)); //debug

int oldpoints= player.getExplopoints();
// System.out.println("Player points" + oldpoints);
int morepoints=0;
int rightones=0;
if (resultados!=null){
for (int i=0;i<resultados.length;i++){
	String questi= "quest" + String.valueOf(i);
	String useranswer = request.getParameter(questi);
	if (useranswer!=null){
	//	System.out.println("useranswer "+questi+" is "+useranswer); //debug
		int intansweri = java.lang.Integer.parseInt(useranswer); 
		if ( intansweri == resultados[i]){
			switch (level){
			case "A1": morepoints +=100;
			break;
			case "A2": morepoints +=200;
			break;
			case "A2.2": morepoints +=250;
			break;
			case "B1": morepoints +=350;
			break;
			case "B1.2": morepoints +=400;
			break;
			case "B2": morepoints +=500;
			break;
			default: morepoints +=100;
			break;
			}
			
			rightones++;
		}
	}
} // del for
} // del if
PrizeController pc=new PrizeController(path);
String cont1="";
Audio nextaudio = (Audio) s.getAttribute("nextaudio");

	
	Integer morepointswrap = new Integer(morepoints);
	Integer newpointswrap = new Integer(morepoints + oldpoints);
	player.setExplopoints(newpointswrap.intValue());

	if (pc.updateExploPoints(player.getUser(), player.getExplopoints()))
		{// System.out.println("Success update");
		}
	else {//System.out.println("List not updated");
	}
	s.setAttribute("player", player);
	
	if (player!=null){
		cont1+="<div class='container'><div class='row'><div style='text-align:center;'>" + "<h1>Well done!!!</h1>"
	+ "<h2>Player " + player.getUser()+"!</h2><br/>";
	cont1+="<h3>Number of right answers: "+rightones+"<br/>You added: "+ morepointswrap.toString()
	+ " points out of "+newpointswrap.toString()+ "</h3><br/>";
	} else {
		cont1+="Player is null!!!";
	}
//	level = request.getParameter("level");
	
	FPrize finalprize = pc.getFinalPrize(player.getExplopoints());

%>
<br/>
<br/>
<% 
cont1+="<br/>";
if (finalprize!=null){
	System.out.println("FPrize: "+finalprize.getFilename()+ " of type " + finalprize.getType() );
	switch (finalprize.getType()){
		case "IMG": cont1+="<img src='fprizes/"+finalprize.getFilename()+"' class='img-responsive center-block materialboxed'/>";
				break;
		case "AUD": cont1+="<audio src='fprizes/"+finalprize.getFilename()+"' type='audio/mpeg' controls='controls' autoplay/>";
				break;
		case "VID": cont1+="<video src='fprizes/"+finalprize.getFilename()+"' type='video/mp4'/>";
				break;
	}
}
cont1+="</div></div>";
cont1+="<br/><h1>CONGRATULATIONS<br/> You have finished the game</h1>";


%>
<%=cont1 %>
<div class='row' style="text-align:center">
<form action="main-menu.html">
    <input type="submit" class="btn btn-large btn-floating red" value="Menu"> 
</form>
</div>
<div class='row'>
 <button class="btn btn-large btn-floating purple" value="Trancript" onclick='viewTranscript();'>
 View
 </button>  

</div>
<% TranscriptController tc = new TranscriptController(path);
String transcript = tc.loadTranscript(checked_audio);

%>

<div class='col s12 m10 l10'/>

<span id="Transcript" style="visibility: hidden;">
<div class='row'>

<%= jc.generateTestAnswers(checked_audio) %>

</div>
<div class='row'>
<div class='card'>
<%=transcript %>
</div>
</div>
</span>

</div>

<br/>
</div>
</div>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/materialize.min.js"></script>
<script>

    $('.collapsible').collapsible();
</script>
</body>
</html>