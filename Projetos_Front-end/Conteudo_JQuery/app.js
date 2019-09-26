const SERVER = "http://172.16.7.16:8080/MuralMilGrau";
const NEWS_ENDPOINT = SERVER + "/rest/news";

//Load do Jquery
$(document).ready(function(){
    
    //Carrega as noticiaas do servidor
    carregarNoticias();

    $("#btnAddNews").on('click', function(){
        $("body").addClass("editing");
    });
     $("#btnCancelEdit").on('click', function(){
        $("body").removeClass("editing");
    });
     $("#btnCreate").on('click', function(){
        criarNoticia();
    });
});

/**
 * cria a noticia que usARA o endpoint de cadastro
 */
function criarNoticia(){
    var noticia = {
        title : $("#inputTitle").val(),
        content : $("#inputContent").val()
    };
    //FAZ A REQUISICAO 
    fetch(NEWS_ENDPOINT, {
        method: "POST",
body : JSON.stringify(noticia), // trensforma o Object em text e aplica no corppo
        headers : {
            'Content-Type' : 'application/json'
        }
    }).then(function(response){
        console.log(response);
    });

    adicionarNoticia(noticia); //chama a função que cria o html sde noticia
    $("body").removeClass("editing"); // fecha o modal
}

/**
 * Consome o endpoint de listagem de noticias e aplica-as no html
 */
function carregarNoticias(){

    //Aplica a classe loading para aparecer o gif
    $("body").addClass("loading");

    fetch(NEWS_ENDPOINT).then(function(response){

        //Recebe os dados em json
        response.json().then(function(dados){
            
            //Pega cada elemento do array derivado do NEWS_ENDPOINT
            dados.forEach(function(noticia){
                adicionarNoticia(noticia);
            });

            //Remove a class loading
            $("body").removeClass("loading");
        });
    });
}

/**
 * Gera o thml com dados derivados da noticia
 * @param {Object} noticia
 */
function adicionarNoticia(noticia){

    var html = 
    '<section class="noticia">'+
        '<div class="noticia-header">'+
            '<h3>'+noticia.title+'</h3>'+
            '<span class="noticia-data-criacao text-sm">'+noticia.cretionDate+'</span>'+
        '</div>' +
        '<div class="noticia-conteudo">' +
            '<p>'+noticia.content+'</p>'+
        '</div>'+
        '<div class="noticia-footer">'+
                    '<a class="btn btn-circular btn-like center">'+
                        '<span class="fas fa-thumbs-up">'+'</span>'+
                    '</a>'+
                '</div>'+
    '</section>';

    $("#mural").append(html);
}