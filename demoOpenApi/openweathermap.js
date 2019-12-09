const UL = document.querySelector('ul');
let result;

function createElement(name, text) {
	let element = document.createElement(name);
	element.innerText = text;
	return element;
}

function init() {
	const url = 'https://api.openweathermap.org/data/2.5/weather?appid=d790af796b577bd08faa56af9c5e3820&units=metric&q=Seoul';
	
	const opt = {
		method: "GET"
	};
	
	fetch(url, opt)
	.then(response => {
		if(response.ok) {
			return response.json();
		} else {
			console.log('else!');
		}
	})
	.then(result => {
		UL.appendChild(createElement('li', '도시명:' + result.name));
		UL.appendChild(createElement('li', '위도:' + result.coord.lon));
		UL.appendChild(createElement('li', '경도:' + result.coord.lat));
		UL.appendChild(createElement('li', '기온(C):' + result.main.temp));
		UL.appendChild(createElement('li', '기압:' + result.main.pressure));
		UL.appendChild(createElement('li', '습도:' + result.main.humidity));
		UL.appendChild(createElement('li', '풍속:' + result.wind.speed));
		
	})
	.catch(response => {
		console.log(response);
	});
}

init();