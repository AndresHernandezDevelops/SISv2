function doSimpleAjax(address)
{
	if(validate())
		{
			fetchResult(address);
		}
}

function validate() {
	var errorMessage = "";
	var ok = true;
	var namePrefix = document.getElementById("namePrefix").value;
	var minimumCreditTaken = document.getElementById("minimumCreditTaken").value;
	if(namePrefix.trim() == "")
	{
		errorMessage = "invalid name prefix!";
		ok = false;
	}
	if((minimumCreditTaken.trim() == "") && (isNaN(minimumCreditTaken) || (minimumCreditTaken < 0)))
	{
		errorMessage = "invalid minimum credits!!"
		ok = false;
	}
	if(!ok)
		alert(errorMessage);
	return ok;
}

function fetchResult(address){
	 var request = new XMLHttpRequest();
	 var namePrefix = document.getElementById("namePrefix").value;
	 var minimumCreditTaken = document.getElementById("minimumCreditTaken").value;
	 var submit = document.getElementById("submit").value;
	 
	 var data="namePrefix=" + namePrefix + "&minimumCreditTaken=" + minimumCreditTaken + "&submit=" + submit;

	 request.onreadystatechange = function()
	 {
			handler(request);
	 };
	 request.open("POST", address, true);
	 request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	 request.send(data); 
	} 

function handler(request){
	 if ((request.readyState == 4) && (request.status == 200))
	 {
		 var target = document.getElementById("result");
		 target.innerHTML = request.responseText;
	 }
	}