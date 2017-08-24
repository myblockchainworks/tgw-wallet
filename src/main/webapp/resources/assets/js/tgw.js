
function formatAndDisplayDate(value, className) {
	var date = new Date(value);
	var monthNames = [
	  "Jan", "Feb", "Mar",
	  "Apr", "May", "Jun", "Jul",
	  "Aug", "Sep", "Oct",
	  "Nov", "Dec"
	];
	
	var day = date.getDate();
	var monthIndex = date.getMonth();
	var year = date.getFullYear();
	
	var hour = date.getHours()
	var min = date.getMinutes();
	if (hour < 10) {
		hour = '0' + hour;
	} 
	
	if (min < 10) {
		min = '0' + min;
	}
	
	var formatted = day + '-' + monthNames[monthIndex] + '-' + year + ' ' + hour + ':' + min;
	$('.' + className).html(formatted); 
}


//Calculate the difference of two dates in total days
function diffDays(d1, d2)
{
  var ndays;
  var tv1 = d1.valueOf();  // msec since 1970
  var tv2 = d2.valueOf();

  ndays = (tv2 - tv1) / 1000 / 86400;
  ndays = Math.round(ndays - 0.5);
  return ndays;
}


function showErrorMessage(message) {
	if (message != "") {
		$(".errorMessageDiv").html('<div id="login-alert" class="alert alert-danger col-sm-12">' + message + '</div>');
		setTimeout(function() { 
			//window.location.href ="ownerhome";
			$(".errorMessageDiv").fadeOut(1000);
		}, 3000);
	}
}

function showSuccessMessage(message) {
	if (message != "") {
		$(".successMessageDiv").html('<div id="login-alert" class="alert alert-success col-sm-12">' + message + '</div>');
		setTimeout(function() { 
			//window.location.href ="ownerhome";
			$(".successMessageDiv").fadeOut(1000);
		}, 3000);
	}
}

function formatAddress(address, className, currentAddress) {
	var formatted = address;
	if (address == currentAddress) {
		formatted = '<span style="font-weight: bold;color: #087480;">' + address + '</span>'
	}
	$('.' + className).html(formatted); 
}

function showAmount(amount, type, className) {
	var formatted = amount + ' ETH';
	if (type == 'Token Transaction') {
		formatted = amount + ' TGW';
	}
	$('.' + className).html(formatted); 
}

function downloadKeyStore() {
	var privatekey = $('#privatekey').html();
	var downloadData = $('#keystoreJson').val();
	downloadData = downloadData.replace(/\\/g, '\"');
	var hiddenElement = document.createElement('a');
	document.body.appendChild(hiddenElement);
	hiddenElement.setAttribute("type", "hidden"); // make it hidden if needed
	hiddenElement.href = 'data:text/json;charset=utf-8,' + encodeURI(downloadData);
	hiddenElement.target = '_blank';
	hiddenElement.download = 'keystore-' + privatekey + '.json';
	hiddenElement.click();
}

function buyTokenConfirmation() {
	var ether = $('#etheramount').val();
	if (ether != undefined && ether != '') {
		if(confirm ("Are you sure you want to buy TGW for " + ether + " Ether?")) {
			$('#buyTokenForm').submit();
		}
	} else {
		alert ("Please enter the ether amount to buy TGW!");
	}
}

function sendTokenConfirmation() {
	var toaddress = $('#toaddress').val();
	var tokenamount = $('#tokenamount').val();
	if (toaddress != undefined && toaddress != '' && tokenamount != undefined && tokenamount != '') {
		if (tokenamount % 1 != 0) {
			alert ("Please enter a valid TWG amount!");
		} else {
			if(confirm ("Are you sure you want to send " + tokenamount + " TGW to " + toaddress + "?")) {
				$('#sendTokenForm').submit();
			}
		}
		
	} else {
		alert ("Please fill the recipient address and TGW amount!");
	}
}