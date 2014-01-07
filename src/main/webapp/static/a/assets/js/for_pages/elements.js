$(function() {
  $('.ui-slider-simple').slider({});
  $('.ui-slider-range').slider({
    range: true,
    min: 0,
    max: 500,
    values: [ 75, 300 ]
  });

    $( "#eq span" ).each(function() {
      // read initial values from markup and remove that
      var value = parseInt( $( this ).text(), 10 );
      $( this ).empty().slider({
        value: value,
        range: "min",
        animate: true,
        orientation: "vertical"
      });
    });



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

  var g = new JustGage({
    id: "gauge-purple",
    value: 46,
    min: 0,
    max: 100,
    showInnerShadow: false,
    showMinMax: false,
    gaugeColor: "#EAEAEA",
    levelColors:["#C952B7","#C952B7","#C952B7"],
    title: "Server Load"
  });

  var g = new JustGage({
    id: "gauge-orange",
    value: 67,
    min: 0,
    max: 100,
    showInnerShadow: false,
    showMinMax: false,
    gaugeColor: "#EAEAEA",
    levelColors:["#BA871A","#BA871A","#BA871A"],
    title: "New Visits"
  });

  var g = new JustGage({
    id: "gauge-yellow",
    value: 25,
    min: 0,
    max: 100,
    showInnerShadow: false,
    showMinMax: false,
    gaugeColor: "#EAEAEA",
    levelColors:["#EDD942","#EDD942","#EDD942"],
    title: "Sales"
  });
});
