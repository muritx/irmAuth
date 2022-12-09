$('#cpf').focusout(function(){
    var strCPF = $('#cpf').val();
    TestaCPF(strCPF);
});

function CPFInvalido() {
    document.getElementById('cpfinvalido').style.display = 'inherit';
    document.getElementById('cpfvalidado').style.display = 'none';
}

function CPFValidado() {
    document.getElementById('cpfvalidado').style.display = 'inherit';
    document.getElementById('cpfinvalido').style.display = 'none';    
}

function TestaCPF(strCPF) {
    var Soma;
    var Resto;
    Soma = 0;
  if (strCPF == "00000000000") return false;

  for (i=1; i<=9; i++) Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (11 - i);
  Resto = (Soma * 10) % 11;

    if ((Resto == 10) || (Resto == 11))  Resto = 0;
    if (Resto != parseInt(strCPF.substring(9, 10)) ) {
        CPFInvalido();
         return false;
    }

  Soma = 0;
    for (i = 1; i <= 10; i++) Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (12 - i);
    Resto = (Soma * 10) % 11;

    if ((Resto == 10) || (Resto == 11))  Resto = 0;
    if (Resto != parseInt(strCPF.substring(10, 11) ) ) {
         CPFInvalido(); 
         return false;
    }
    CPFValidado()
    return true;
}
