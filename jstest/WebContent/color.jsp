

	var body = {
			setColor : function(color){
			//	document.querySelector('body').style.color = color;
			$('body').css('color', color); 
			},
			setBackgroundColor : function(color){
			//	document.querySelector('body').style.backgroundColor = color;
			$('body').css('backgroundColor', color); 
			}
	}
	
	var link = {
			setColor : function(color){
// 					var alist = document.querySelectorAll('a');
//					var i = 0;
//					while (i < alist.length) {
//						alist[i].style.color = color;
//						i = i + 1;
//					}
 
 				$('a').css('color',color);
 				}
	}
	
	function night_day(self) {
		if (self.value === 'night') {
			body.setBackgroundColor('black');
			body.setColor('white');
			self.value = 'day';
			link.setColor("white");
		} else {
			body.setBackgroundColor('white');
			body.setColor('black');
			self.value = 'night'
			link.setColor("blue");
		}

	}
	
	
	
	var menu = {
			'menu1':'Americano',
			'menu2':'Cafelatte'
	}
	
	menu.menu3 = 'Capucino';
	menu['not for sale'] = 'Orange Juice'
	
	for(var key in menu){
		document.write(key+' : '+ menu[key]+'<br>');
	}
		
	menu.ShowAll = function(){
		for(var key in this){
			document.write(key+' : '+ this[key]+'<br>');
		}
		
	}
