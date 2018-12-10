
$(function() {
	var link  = document.createElement('link');
	link.rel  = 'stylesheet';
	link.type = 'text/css';
	link.href = 'css/cart.css';
	link.media = 'all';
	document.getElementsByTagName('head')[0].appendChild(link);
	
	$(".addToCart").click( function(e) {
		var ref = $(this).data("ref");
		$.ajax({
			method: 'POST',
			url: "cart/1/add.json",
			dataType: "json",
			contentType: 'application/json',
			data: JSON.stringify( {id: ref, qty: 1} )
		}).done(function(data){
			// refresh cart contents on success
			displayCartContent();
		});
	});
	
	var displayCartContent = function() {
		$.ajax({
			url: "cart/1.html",
			contentType: "text/html;charset=UTF-8",
		}).done(function(data){
			JSON.stringify( $('#cartInHeader').html(data) )
		});
	};
	
	displayCartContent();
});