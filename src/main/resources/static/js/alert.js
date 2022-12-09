$(document).ready(function () {
    $('.alert').click(function(e) {
        let resposta = confirm("Excluir esse paciente?")

        if(resposta == true){
            alert("Paciente excluído com sucesso.")
        }else{
            
        }
    });
  });