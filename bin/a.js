<!DOCTYPE html>
<html xmlns:layout="http://www.w3.org/1999/xhtml"
	layout:decorate="~{layouts/layout}">
<head>
<title>Registrar m&eacute;trica</title>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<script th:inline="javascript" type="text/javascript">
    google.charts.load('current', {packages: ['line']});
	google.setOnLoadCallback(drawChart);
	
    function drawChart() {

        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Semana');
        data.addColumn('number', 'Valor Planeado(PV)');
        data.addColumn('number', 'Valor Ganado(EV)');
        data.addColumn('number', 'Varianza del Costo(AC)');
        
        /*<![CDATA[*/
        
        var cpvdata = /*[[${cpvdata}]]]*/;
        var cacdata = /*[[${cacdata}]]]*/;
        var cevdata = /*[[${cevdata}]]]*/;

      	/*]]>*/
        
        var list = [
            { week: 'Semana 1', pv: 37.5, ev: 50.67, ac: 70.45 },
            { week: 'Semana 2', pv: 58.9, ev: 34.78, ac: 80.43 },
            { week: 'Semana 3', pv: 63.2, ev: 43.54, ac: 98.65 }
        ];

        for (var i = 0; i < list.length; i ++ ){
        	
            data.addRows([
            	[list[i].week, list[i].pv, list[i].ev, list[i].ac]
            	]);
         }

        var options = {
          chart: {
            title: 'Gr&aacute;fico de valor ganado',
            subtitle: 'En d&ocaute;lares (USD)'
          },
          width: 900,
          height: 500,
          axes: {
            x: {
              0: {side: 'top'}
            }
          }
        };

        var chart = new google.charts.Line(document.getElementById('graphic'));

        chart.draw(data, google.charts.Line.convertOptions(options));
      }
</script>
</head>
<body>
	<div class="container">
	<div class="row">
			<div class="col" layout:fragment="content">
				<div class="graphic"></div>
			</div>
		</div>
	</div>
</body>
</html>