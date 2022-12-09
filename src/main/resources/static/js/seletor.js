$(document).ready(function () {
  $('#btpaciente').click(function(){
    if ($('#btfuncionario').hasClass('ativo')){
      $('#btfuncionario').removeClass('ativo');
      $('#btpaciente').addClass('ativo');
    }
    else{
      $('#btpaciente').addClass('ativo');
    }
  })
  $('#btfuncionario').click(function(){
    if ($('#btpaciente').hasClass('ativo')){
      $('#btpaciente').removeClass('ativo');
      $('#btfuncionario').addClass('ativo');
    }
    else{
      $('#btfuncionario').addClass('ativo');
    }
  })
})
