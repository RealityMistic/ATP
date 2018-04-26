<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="beans.Audio,
    	 beans.Player, beans.Prize, beans.Noprize,
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

<script src="js/jquery-3.1.1.min.js">
</script>
<script src="/audio.js/audio.min.js"></script>
<!-- Se activa audio.js para que los audios se oigan en cualquier navegador -->

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
<!--  
<script>
  $(document).ready(function(){
    $('.collapsible').collapsible({
      accordion : false // A setting that changes the collapsible behavior to expandable instead of the default accordion style
    });
});
</script>
-->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Price page</title>
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
String path = (String) s.getAttribute("path");
//System.out.println("ID Session "+ s.getId());
Player player = null;
String level="A1";
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
String nexttrybutton="";
Audio nextaudio = (Audio) s.getAttribute("nextaudio");
if (morepoints == 0){
	cont1="<h1>Opssss... maybe do you want to try again?</h1>";
	Noprize noprize = (Noprize) pc.getNoprize(level);
//	System.out.println("Noprize: "+noprize.getFilename()+ " Noprize Type: "+ noprize.getType());
	
	
		cont1 +="<div class='row'>";
		switch (noprize.getType()){
		case "IMG": cont1+="<img src='noprizes/"+noprize.getFilename()+"' class='img-responsive center-block materialboxed'/>";
				break;
		case "AUD": cont1+="<audio src='noprizes/"+noprize.getFilename()+"'/>";
				break;
		case "VID": cont1+="<video src='noprizes/"+noprize.getFilename()+"' type='video/mp4'/>";
				break;
		}
		cont1 +="</div>";
		
	cont1 += "<div class='row'>";
	nexttrybutton="Try again";
//	cont1 +="<a class='btn btn-large yellow' href='ExplorerPlay.jsp?startnew=false&level="+level+ ">Try again!"
//	+ "</a></div>";
	nextaudio.setNumber(nextaudio.getNumber()-1);
	level= nextaudio.getLevel();
//	System.out.println("Nextaudio number "+nextaudio.getNumber()+ " of level: "+ level);
	s.setAttribute("nextaudio",nextaudio);
	
}else {
	
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
	
	Prize nextprize = pc.getNewPrize(level, player.getExplopoints());
	nexttrybutton="Next";
%>
<br/>
<br/>
<% 
cont1+="<br/>";
if (nextprize!=null){
	// System.out.println("Prize: "+nextprize.getFilename()+ " of type " + nextprize.getType() );
	switch (nextprize.getType()){
		case "IMG": cont1+="<img src='prizes/"+nextprize.getFilename()+"' class='img-responsive center-block materialboxed'/>";
				break;
		case "AUD": cont1+="<audio src='prizes/"+nextprize.getFilename()+"' type='audio/mpeg' controls='controls' autoplay/>";
				break;
		case "VID": cont1+="<video src='prizes/"+nextprize.getFilename()+"' type='video/mp4'/>";
				break;
	}
}
cont1+="</div></div>";
cont1+="<br/>";

nextaudio = (Audio) s.getAttribute("nextaudio");
level= nextaudio.getLevel();
// System.out.println("Setting nextlevel: "+level);
//Cookie ATPlevel = new Cookie ("ATPlevel", nextaudio.getLevel());
//ATPlevel.setMaxAge(60*24*365);
//String nextaudionumber = Integer.toString(nextaudio.getNumber()); 
//Cookie ATPnumaudio = new Cookie ("ATPnumaudio", nextaudionumber);
//ATPnumaudio.setMaxAge(60*34*365);
//response.addCookie(ATPlevel);
//response.addCookie(ATPnumaudio);
}
Cookie ATPlevel = new Cookie ("ATPlevel", nextaudio.getLevel());
ATPlevel.setMaxAge(60*24*365);
String nextaudionumber = Integer.toString(nextaudio.getNumber()); 
Cookie ATPnumaudio = new Cookie ("ATPnumaudio", nextaudionumber);
ATPnumaudio.setMaxAge(60*34*365);
response.addCookie(ATPlevel);
response.addCookie(ATPnumaudio);
%>
<%=cont1 %>
<audio src="audios/<%= checked_audio.getFilename() %>" type="audio/mpeg" controls="controls" >
Your browser does not support Audio
</audio>
<div class='row'>
<form action="ExplorerPlay.jsp?startnew=false&level=<%=level %>" method="post">
    <button class='btn btn-large custom-btn-s orange' type="submit" value='<%=nexttrybutton %>'> 
    <%= nexttrybutton %>
    </button>
</form>
</div>
<!--  
<div class='row'>
 <form action="main-menu.html">
    <button class="btn btn-large btn-floating red" value="Menu">Menu</button>  
</form>
</div>
-->
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