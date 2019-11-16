
<html lang="en">
<title>Scrip Info</title>
<link
	href='https://fonts.googleapis.com/css?family=Lato:400,400italic,700'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" media="all"
	href="https://dx0qysuen8cbs.cloudfront.net/assets/adorable-ebb76463d812e0376f1673681fb658a11e968cc813e2d4d08082b5adefa94a08.css" />

<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="resources/Chart.bundle.js"></script>

<head>
<meta charset="utf-8">
</head>
<body>


	<div id="container" style="width: 75%;">
		<canvas id="canvas"></canvas>
	</div>
	<script>
		var config = {
			type : 'line',
			data : {
				labels :  ${labels},
				datasets : [ {
					label : "${scripName}",
					data :${data},
					fill : false,
					borderDash : [ 5, 5 ],
				} ]
			},
			options : {
				responsive : true,
				title : {
					display : true,
					text : 'Chart.js Line Chart'
				},
				tooltips : {
					mode : 'label',
					callbacks : {
					}
				},
				hover : {
					mode : 'dataset'
				},
				scales : {
					xAxes : [ {
						display : true,
						scaleLabel : {
							display : true,
							labelString : 'Month'
						}
					} ],
					yAxes : [ {
						display : true,
						scaleLabel : {
							display : true,
							labelString : 'Value'
						},
						ticks : {
							suggestedMin : ${scale_Min},
							suggestedMax : ${scale_Max},
						}
					} ]
				}
			}
		};
		window.onload = function() {
            var ctx = document.getElementById("canvas").getContext("2d");
            window.myLine = new Chart(ctx, config);
        };
	</script>
	<a href="/FinanTechWebApp/welcome.do">Back</a>
	</div>
	<div class="group_summary" style="width: 75%;">
		<h2>${scripName}</h2>
		<h4>${date}</h4>

		<table class="expenses table-bordered table">
			<tbody>
				<tr class="gray">
					<th class="span2">SL</th>
					<th class="span2">Date</th>
					<th class="span2">Last close</th>
					<th class="span2">Last Volumn</th>
				</tr>

				${dataTable}
			</tbody>
		</table>
	</div>
</body>

</html>
