//Edpoint = Termo utilizado para uma URl que gera um resultado no web service
const CEP_ENDPOINT = "https://viacep.com.br/ws";

window.addEventListener('load', function(){
    var inputCep = document.getElementById('inputCep');

    //blur - Evento quando o campo perde o foco
    inputCep.addEventListener('blur', function(){
        buscarCep(inputCep.value);
    });
});

/**
 * Busca os dados do endereço através de um cep incerido
 * webservice do viacep.com.br
 * @param {String} cep
 */
function buscarCep(cep){
    //Gera a url para consumo do web service do viacep
    let url = CEP_ENDPOINT + "/" + cep + "/json";

    //fetch - Função 
    fetch(url).then(function(response){

        //Vamos ler a resposta em json
        //dados * Dados providos do endpoint do viavep
        response.json().then(function(dados){
            carregarDadosEndereco(dados);
        });
    });
}

/**
 * Carrega os dados de um objeto e aplica-o nos campos do fprmulario
 * @param {Object} endereco
*/
function carregarDadosEndereco(endereco){
    document.getElementById("inputLogradouro").value = endereco.logradouro;
    document.getElementById("inputComplemento").value = endereco.complemento;
    document.getElementById("inputBairro").value = endereco.bairro;
    document.getElementById("inputLocalidade").value = endereco.localidade;
    document.getElementById("inputUf").value = endereco.uf;
    document.getElementById("inputUnidade").value = endereco.unidade;
    document.getElementById("inputIbge").value = endereco.ibge;
    document.getElementById("inputGia").value = endereco.gia;

}