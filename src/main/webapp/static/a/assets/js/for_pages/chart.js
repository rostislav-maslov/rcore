$(function() {

  $('.dynamicsparkline').sparkline([10, 8, 5, 7, 5, 4, 1, 10, 8, 12, 7, 7, 4, 5, 8, 8, 7, 7, 11, 5, 9, 12, 7, 7, 4, 5, 8, 8], {
    type: 'line',
    lineColor: '#89b1e4',
    fillColor: '#d7e8fc'
  });

  $('.dynamicbars').sparkline([5, 6, 7, 2, 0, -4, -2, 4, 1, 10, 8, 12, 7, -2, 4, 8], {
    type: 'bar',
    barColor: '#89b1e4',
    negBarColor: '#c76868'
  });

  $(".knob").knob({
    thickness: '.05',
    font: "Open Sans",
    bgColor: "#f3eee7",
    readOnly: true
  });

  var morris_area_options = {
    element: "areachart",
    behaveLikeLine: true,
    data: [
      {
        x: "2011-01",
        y: 15,
        z: 7
      }, {
        x: "2011-02",
        y: 26,
        z: 4
      }, {
        x: "2011-03",
        y: 21,
        z: 18
      }, {
        x: "2011-04",
        y: 32,
        z: 18
      }, {
        x: "2011-05",
        y: 15,
        z: 7
      }, {
        x: "2011-06",
        y: 26,
        z: 4
      }, {
        x: "2011-07",
        y: 18,
        z: 14
      }, {
        x: "2011-08",
        y: 36,
        z: 11
      }, {
        x: "2011-09",
        y: 15,
        z: 12
      }, {
        x: "2011-10",
        y: 26,
        z: 4
      }, {
        x: "2011-11",
        y: 28,
        z: 11
      }, {
        x: "2011-12",
        y: 36,
        z: 14
      }
    ],
    xkey: "x",
    ykeys: ["y", "z"],
    labels: ["Y", "Z"],
    lineColors: ["#E8BEBE", "#BCCBDD", "#3498db", "#2c3e50", "#1abc9c", "#34495e", "#9b59b6", "#e74c3c"]
  };

  Morris.Area(morris_area_options);


  Morris.Donut({
    element: 'piechart',
    data: [
      {label: 'Jam', value: 25 },
      {label: 'Frosted', value: 40 },
      {label: 'Custard', value: 25 },
      {label: 'Sugar', value: 10 }
    ],
    colors: ["#3498db", "#34495e", "#1abc9c", "#34495e", "#9b59b6", "#95a5a6"],
    formatter: function (y) { return y + "%" }
  });




  Morris.Bar({
    element: 'topsellers_barchart',
    data: [
      {device: '3G', geekbench: 137},
      {device: '3GS', geekbench: 275},
      {device: '4', geekbench: 380},
      {device: '4S', geekbench: 655},
      {device: '5', geekbench: 1571}
    ],
    xkey: 'device',
    ykeys: ['geekbench'],
    labels: ['Geekbench'],
    barRatio: 0.4,
    xLabelAngle: 35,
    hideHover: 'auto'
  });


  var morris_area_options = {
    element: "areachart-small",
    behaveLikeLine: true,
    data: [
      {
        x: "2011-01",
        y: 15,
        z: 7
      }, {
        x: "2011-02",
        y: 26,
        z: 4
      }, {
        x: "2011-03",
        y: 21,
        z: 18
      }, {
        x: "2011-04",
        y: 32,
        z: 18
      }, {
        x: "2011-05",
        y: 15,
        z: 7
      }, {
        x: "2011-06",
        y: 26,
        z: 4
      }, {
        x: "2011-07",
        y: 18,
        z: 14
      }
    ],
    xkey: "x",
    ykeys: ["y", "z"],
    labels: ["Y", "Z"],
    grid: false,
    lineColors: ["#3498DB", "#E74C3C", "#F39C12", "#2C3E50", "#1abc9c", "#34495e", "#9b59b6", "#e74c3c"]
  };
  Morris.Line(morris_area_options);



  var g = new JustGage({
    id: "gauge-red",
    value: 46,
    min: 0,
    max: 100,
    showInnerShadow: false,
    showMinMax: false,
    gaugeColor: "#EAEAEA",
    levelColors:["#E74C3C","#E74C3C","#E74C3C"],
    title: "Server Load"
  });

  var g = new JustGage({
    id: "gauge-green",
    value: 67,
    min: 0,
    max: 100,
    showInnerShadow: false,
    showMinMax: false,
    gaugeColor: "#EAEAEA",
    levelColors:["#1ABC9C","#1ABC9C","#1ABC9C"],
    title: "New Visits"
  });

  var g = new JustGage({
    id: "gauge-blue",
    value: 25,
    min: 0,
    max: 100,
    showInnerShadow: false,
    showMinMax: false,
    gaugeColor: "#EAEAEA",
    levelColors:["#3498DB","#3498DB","#3498DB"],
    title: "Sales"
  });


  Morris.Bar({
    element: 'users_barchart',
    data: [
      {device: 'Ned', geekbench: 5},
      {device: 'Aus', geekbench: 3},
      {device: 'Fra', geekbench: 7},
      {device: 'Rus', geekbench: 6},
      {device: 'Nor', geekbench: 2}
    ],
    xkey: 'device',
    ykeys: ['geekbench'],
    labels: ['Geekbench'],
    barRatio: 0.2,
    xLabelAngle: 90,
    hideHover: 'auto',
    barColors: ["#61a9dc"]
  });


  var tax_data = [
    {"period": "2012 Q3", "licensed": 3407, "sorned": 660},
    {"period": "2012 Q1", "licensed": 2800, "sorned": 629},
    {"period": "2011 Q2", "licensed": 2700, "sorned": 618},
    {"period": "2010 Q4", "licensed": 3100, "sorned": 650},
    {"period": "2009 Q4", "licensed": 3600, "sorned": 900},
    {"period": "2008 Q4", "licensed": 2200, "sorned": 681},
    {"period": "2007 Q4", "licensed": 1800, "sorned": 620},
  ];
  Morris.Line({
    element: 'linechart',
    data: tax_data,
    xkey: 'period',
    ykeys: ['licensed', 'sorned'],
    labels: ['Licensed', 'Off the road']
  });


  $('.advanced-pie').each(function( index ) {
    var barColor = $(this).data('barColor')
    var size = $(this).data('size')
    $(this).easyPieChart({
      barColor: barColor,
      scaleColor: "#BFBDB7",
      trackColor: false,
      size: size
    });
  });


  // REAL TIME CHART IMPLEMENTATION START

  var data = [],
      totalPoints = 50;

  function getRandomData() {

    if (data.length > 0)
      data = data.slice(1);

    // Do a random walk

    while (data.length < totalPoints) {

      var prev = data.length > 0 ? data[data.length - 1] : 50;
      var random_value = randomFromInterval(1, 9);
      var random_value_2 = randomFromInterval(1, 9);
      var y = prev + random_value - random_value_2;
      if (y < 0) {
        y = 0;
      } else if (y > 100) {
        y = 100;
      }

      data.push(y);
    }

    // Zip the generated y values with the x values

    var res = [];
    for (var i = 0; i < data.length; ++i) {
      res.push([i, data[i]])
    }

    return [res, y];
  }

  function randomFromInterval(from,to)
  {
      return Math.floor(Math.random()*(to-from+1)+from);
  }

  // Set up the control widget

  var updateInterval = 150;
  $("#updateInterval").val(updateInterval).change(function () {
    var v = $(this).val();
    if (v && !isNaN(+v)) {
      updateInterval = +v;
      if (updateInterval < 1) {
        updateInterval = 1;
      } else if (updateInterval > 2000) {
        updateInterval = 2000;
      }
      $(this).val("" + updateInterval);
    }
  });

  random_data = getRandomData();
  var plot = $.plot("#placeholder", [ random_data[0] ], {
    grid: {
      borderWidth: 0
    },
    series: {
      color: "#3498DB",
      lines: {
        lineWidth: 4,
        fill: 0.1
      },
      shadowSize: 0, // Drawing is faster without shadows
      points: {
          radius: 5
      }
    },
    yaxis: {
      min: 0,
      max: 100,
      color: "#e5e5e5",
      ticks: 4,
      font: {
        color: "#aaa"
      }
    },
    xaxis: {
      color: "#eee",
      ticks: 10,
      // tickLength: 7,
      font: {
        color: "#aaa"
      }
      // show: false
    }
  });

  function update_plot() {
    random_data = getRandomData();
    plot.setData([random_data][0]);
    // Since the axes don't change, we don't need to call plot.setupGrid()
    $('#plot-chart-value').text(random_data[1]);
    plot.draw();
    setTimeout(update_plot, updateInterval);
  }


  update_plot();

  // END REAL TIME CHART IMPLEMENTATION

});