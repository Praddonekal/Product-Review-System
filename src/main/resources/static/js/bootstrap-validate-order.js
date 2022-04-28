$(document).ready(function() {
	loadControlsLogic();
	validateControls();
});
  
function loadControlsLogic()
{
	$('.next-to-checkout').click(function(e) {
		$(".order-checkout").delay(100).fadeIn(100);
		$(".order-product").fadeOut(100);
		e.preventDefault();
	});

	$('.back-to-product').click(function(e) {
		$(".order-product").delay(100).fadeIn(100);
		$(".order-checkout").fadeOut(100);
		e.preventDefault();
	});
	
	$( "#productId, #orderQuantity" ).change(function() {
	  var productPrice = $("#productId").find(':selected').data('price');
	  var quantity = $("#orderQuantity").val();
	  $(".amountPaid").val(parseInt(productPrice) * parseInt(quantity));
	});
	
	$(".orderDate").val(getFormatedCurrentDate());
	
	function getFormatedCurrentDate()
	{
		var d = new Date();
		var curr_date = d.getDate();
		var curr_month = d.getMonth();
		curr_month++;
		var curr_year = d.getFullYear();
		var hours = d.getHours();
		var minutes = d.getMinutes();
		var seconds = d.getSeconds();
		return curr_year + "-" + pad(curr_month, 2) + "-" +  pad(curr_date,2) + "  " +  pad(hours,2) + ":" +  pad(minutes,2) + ":" +  pad(seconds,2);
	}
	
	function pad(n, width, z) 
	{
	  z = z || '0';
	  n = n + '';
	  return n.length >= width ? n : new Array(width - n.length + 1).join(z) + n;
	}
	
}

function validateControls()
{
	v('#productId','required:Please fill out this field!'); 
	v('#orderDate','required:Please fill out this field!'); 
	v('#color','required:Please fill out this field!'); 
	v('#comments','required:Please fill out this field!'); 
	v('#status','required:Please fill out this field!'); 
	v('#amountPaid','required:Please fill out this field!'); 
	v('#credictCardNumber','required:Please fill out this field!|min:20:Enter 20 character!|max:20:Enter 20 chars at most!'); 
	v('#credictCardExpDate','required:Please fill out this field!|min:4:Enter 4 character!|max:4:Enter 4 chars at most!'); 
	v('#credictCardCode','required:Please fill out this field!|min:3:Enter 3 character!|max:3:Enter 3 chars at most!'); 
	v('#credictCardName','required:Please fill out this field!|min:3:Enter 3 character!|max:120:Enter 120 chars at most!'); 
	function v(control,rules){bootstrapValidate(control,rules);}
}
  