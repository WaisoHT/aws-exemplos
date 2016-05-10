$(document).ready(function() {
	var url = "http://localhost:8050/";
	$.ajax({
		type : 'GET',
		url : url + 'listarArquivos/',
		dataType: "json",
		crossDomain: false,
		data : {
			diretorio : "",
			nome : ""
		}
	})
	.done(function(result) {
		debugger;
			var lista = result;
            var conteudo = "";
			$.each(lista, function(indice, item){
               $("table.dados").append(getLinha(item));
            });
			$('#teste').html(conteudo);
		});
	
	var getLinha = function(item) {
		var linha = "<tr>";
		
		linha += "<td>" + item.nome + "</td>";
		linha += "<td>" + item.tamanho + "</td>";
		linha += "<td>" + "<a href=\"" + item.url + "\">" + item.url + "</a>" + "</td>";
		linha += "<td>" + item.tipo + "</td>";
		linha += "<br/>";
		
		linha += "</tr>";
		
		return linha;
	};
});