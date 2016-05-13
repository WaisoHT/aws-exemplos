//var url = "http://localhost:8050/";
var url = "/";
var diretorioAtual = "";
var getLinha = function(item) {
	var linha = "<tr>";
	
	linha += "<td>" + item.nome + "</td>";
	linha += "<td>" + item.tamanho + "</td>";
	linha += "<td>" + "<a href=\"" + item.url + "\">" + item.url + "</a>" + "</td>";
	linha += "<td>" + item.tipo + "</td>";
	linha += "<td><input type=\"button\" class=\"btn-excluir \" data-nome=\""+ item.nome +"\" title=\"excluir\" /></td>";
	linha += "<br/>";
	
	linha += "</tr>";
	
	return linha;
};

var pesquisarArquivos = function(diretorio, nome) {
	$.ajax({
		type : 'GET',
		url : url + 'listarArquivos/',
		dataType: "json",
		crossDomain: false,
		data : {
			diretorio : diretorio,
			nome : nome
		}
	})
	.done(function(result) {
			var lista = result;
            var conteudo = "";
			$.each(lista, function(indice, item){
               $("table.dados").append(getLinha(item));
            });
			$('#teste').html(conteudo);
		});
};

var upload = function() {
	diretorioAtual = $("#inputDiretorioAtual").val();
    $.ajax({
        url: "/uploadFile",
        type: "POST",
        beforeSend : function() {
        	debugger;
			mostrar($(".loading"));
		},
        data: new FormData($("#upload-file-form")[0]),
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success: function (resultado) {
        	debugger;
        	$("#upload-file-message").text("Upload realizado com sucesso!");
        	pesquisarArquivos(diretorioAtual, "");
        	ocultar($(".loading"));
        },
        error: function () {
        	debugger;
        	$("#upload-file-message").text("Arquivo não salvo (talvez seu arquivo seja muito grande)");
        	ocultar($(".loading"));
        }
    });
	};

    var mostrar = function(elemento) {
		elemento.removeClass("oculto");
	};
	
	var ocultar = function(elemento) {
		elemento.addClass("oculto");
	};
	
	var configurarLinks = function() {
		$("table.dados a").on('click', function(e) {
		    exibeConteudo();
		    e.preventDefault();
		});
	};
    
$(document).ready(function() {
	pesquisarArquivos("", "");
	configurarLinks();
});