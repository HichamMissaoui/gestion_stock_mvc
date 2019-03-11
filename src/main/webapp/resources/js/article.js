$(document).ready(function(){
	$('#tauxTva').on('keyup',function(){
		tvaKeyUpFunction();
	})
})

tvaKeyUpFunction = function(){
	var prixUnitHT = parseFloat($('#prixUnitaireHT').val());
	var tauxTva = parseFloat($('#tauxTva').val());
	var prixUnitTTC = (prixUnitHT * tauxTva/ 100) + prixUnitHT  ;
	$('#prixUnitaireTTC').val(prixUnitTTC);
	
}