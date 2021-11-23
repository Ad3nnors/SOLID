function apresentaDS() {
    var especialidade = document.getElementById("especialidade");
    var area = document.getElementById("area");
    var selEspecialidade = especialidade.options[especialidade.selectedIndex].text; 
    var selArea = area.options[area.selectedIndex].text;
    console.log(selEspecialidade + "/" + selArea);
    alert("Selecionado: " + selEspecialidade + "/" + selArea); 
}

$(document).ready(function() {

	$('#area').on('change', function() {
		var areaId = $(this).val();
			$.ajax({
				type : 'GET',
				url : 'http://localhost:8081/especialidades/areas/'+ areaId,
				success : function(result) {
					var s = '<option value="">Selecione</option>';
					for (var i = 0; i < result.length; i++) {
						s += '<option value="' + result[i].id + '">'
						+ result[i].nome
						+ '</option>';
					}
					$('#especialidade').html(s);
				},
				error: function (request, status, error) {
				       alert(request.responseText);
				}
			});
	})
	
	$.ajax({
		type : 'GET',  
		url: "http://localhost:8081/areas",
        success: function(result) {
            var s = '<option value="">Selecione</option>';
            for (var i = 0; i < result.length; i++) {
						s += '<option value="' + result[i].id + '">'
						+ result[i].sigla
						+ '</option>';
			}
			$('#estado').html(s);
		},
		error: function (request, status, error) {
				       alert(request.responseText);
		}
	});
});