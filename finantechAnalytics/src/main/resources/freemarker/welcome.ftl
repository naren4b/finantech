<!DOCTYPE html>

<html>
  <head>
    <title>Welcome</title>
    <style type="text/css">
      .label {text-align: right}
      .error {color: red}
    </style>

  </head>

  <body>
    <div class="no_print">
  <div id="date_control">

    <form  method="post" action="/ScreenEODPage" >
      <span>Showing expenses for</span>
      <select name="scripId" id="scripId" ">
     ${options}
	  </select>
	    <input type="submit">
    </form>
  </div>
</div>


  </body>

</html>
