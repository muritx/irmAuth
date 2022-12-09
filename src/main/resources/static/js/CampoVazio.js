$(document).ready(function () {
    const Nome = document.getElementById('nome')
    const Telefone = document.getElementById('telefone')

    $('#nome').focusout(function(){
        const Nomes = Nome.value.trim()
        
        if(Nomes ===''){
            $(this).addClass('erro');
            $(this).removeClass('valido');
            
        }
        else{
            $(this).addClass('valido');
            $(this).removeClass('erro');
        }
    });
    $('#telefone').focusout(function(){
        const telefone1 = Telefone.value.trim()
        
        if(telefone1 ===''){
            $(this).addClass('erro');
            $(this).removeClass('valido');
            
        }
        else{
            $(this).addClass('valido');
            $(this).removeClass('erro');
        }
    });
})