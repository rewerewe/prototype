let contextPath = '';

function reqByForm(url, method, target, elemArr) {
	let thisForm = document.createElement('form');
	
	thisForm.setAttribute('action', url);
	thisForm.setAttribute('method', method);
	thisForm.setAttribute('target', target);
	
	if(elemArr !== undefined && elemArr !== null) {
		elemArr.forEach(elem => {
			console.dir(elem);
		});
	}
	
	document.body.appendChild(thisForm);
	
	thisForm.submit();
}