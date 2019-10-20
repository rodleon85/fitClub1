$( document ).ready(function() {
	
	
	if (acao == '/adicionarAluno'){
		$('.cpf').mask('000.000.000-00');
		
		$('.telefone').mask('(00)0000-00000').focusout(function (event) {  
            var target, phone, element;  
            target = (event.currentTarget) ? event.currentTarget : event.srcElement;  
            phone = target.value.replace(/\D/g, '');
            element = $(target);  
            element.unmask();  
            if(phone.length > 10) {  
                element.mask("(00) 00000-0000");  
            } else {  
                element.mask("(00) 0000-0000");  
            }  
        });
		
		$('.email').focusout(function(){
			var valorEmail = $('.email').val();
			if (!isEmail(valorEmail)){
				$('.email').val('');
			}
		});
		
		
//		$( ".submit" ).click(function( event ) {
//			$('.cpf').unmask();	
//			$('.telefone').unmask();
//		});
		
	}
	
	if (acao == '/adicionarPagamento'){
		$('.valor').attr("placeholder", " ");
		
		if ($('.valor').val() == ""){
			$('.valor').attr("disabled", "true");
		}
		
		
		
		$('.tipoPagamento').change(function() {
			if ($("input[name='tipoPagamento']:checked").val() == "mensal") {
				$('.valor').attr("placeholder", "R$");
				$('.valor').removeAttr("disabled");
			}
			if ($("input[name='tipoPagamento']:checked").val() == "ferias"){
				$('.valor').attr("placeholder", "Dias");
				$('.valor').removeAttr("disabled");
			} 
		})
	}
	
});

function isEmail(email) {
	  var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	  if(!regex.test(email)) {
	    return false;
	  }else{
	    return true;
	  }
	}