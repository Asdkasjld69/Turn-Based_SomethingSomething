/**
 * 
 */
function inheritOptions(t,tag) {
	var category = document.getElementById(tag);
	var ops = category.options;
	for ( var i = 0; i < ops.length; i++) {

		if (ops[i].value == t) {
			ops[i].selected = true;
			return;
		}
	}
};

function check_empty(tag){
	var cont = document.getElementById(tag);
	if(cont==null||cont.trim().equals("")){
		return false;
	}
	return true;
};

function scrollToBottom(tag){
	var t = document.getElementById(tag);
	t.scrollTop=t.scrollTop+t.scrollHeight;
}

function resizeToParent(tag){
	var obj = parent.document.getElementById(tag);
    obj.height = this.document.body.scrollHeight;
}

function evalScript(html){
	var reg = /<script[^>]*>([^\x00]+)$/i;
	var htmlBlock = html.split("<\/script>");
	for (var i in htmlBlock){
		var blocks;
		if (blocks = htmlBlock[i].match(reg)){
			var code = blocks[1].replace(/<!--/, '');
			try {
				eval(code);
			}
			catch (e) {
				console.log(e);
				console.log(code);
			}
		}
	}
}

function refreshDiv(tag,type,form,target,bottom)
{
  var xmlhttp;
  var dfd = $.Deferred();
  if (window.XMLHttpRequest)
  {
    // IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
    xmlhttp=new XMLHttpRequest();
  }
  else
  {
    // IE6, IE5 浏览器执行代码
    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
  xmlhttp.onreadystatechange=function()
  {
    if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    	var r = xmlhttp.responseText;
    	document.getElementById(tag).innerHTML=r;
    	evalScript(r);
    	if(bottom==true){
    		scrollToBottom(tag);
    	}
    	dfd.resolve();
    }
  }
  target = target+"?t="+new Date().getTime();
  if(form!=""){
	  target = target+"&"+$(form).serialize();
  }
  xmlhttp.open(type,target,true);
  xmlhttp.send();
  return dfd.promise();
}



