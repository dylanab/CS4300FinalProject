<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Editor Test</title>
   </head>
   <body>
	  <div id="sample">
             <script type="text/javascript" src="http://js.nicedit.com/nicEdit-latest.js"></script> <script type="text/javascript">
  	  	bkLib.onDomLoaded(function() { nicEditors.allTextAreas() });
             </script>
             <h4>
		First Textarea
    	     </h4>
	     <form method='get' action = 'editorTest.jsp'>
   	     <textarea name="area1" cols="100">
    	     </textarea><br />
              <input type = 'submit' value = 'Submit ticket' name = 'submit'> 
    	   </form>
       
	   ${param.area1}
       
	  </div>
   </body>
</html>