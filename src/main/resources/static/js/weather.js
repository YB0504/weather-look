			    function updateLink(dateId) {
			        var templow = document.getElementById(dateId + '_templow').textContent.slice(0, -1);
			        var temphigh = document.getElementById(dateId + '_temphigh').textContent.slice(0, -1);
			        var link = 'dailylist?templow=' + templow + '&temphigh=' + temphigh;
			        window.location.href = link;
			    }
			
			$(document).ready(function(){
		        getWeather(35.1028, 129.0403);
		    });
			
			
			function getWeather(lat, lon){
				const apiUrl = 'http://api.openweathermap.org/data/2.5/forecast';
				const apiKey = config.realtime_weather_key;

				const apiUrlWithParams = `http://api.openweathermap.org/data/2.5/forecast?lat=${lat}&lon=${lon}&appid=${apiKey}&units=metric`;
				
				fetch(apiUrlWithParams)
				  .then(response => response.json())
				  .then(data => {
			                
				    console.log(data);
				    displayWeatherInfo(data);
				   
				  })
				  .catch(error => {
				    console.error('API 호출 중 오류:', error);
				  });
				}

			  function displayWeatherInfo(data) {
		            
		            for (var i = 0; i < data.list.length; i++) {
		  
		                var weatherlist = data.list[i];

		                var dateTimeParts = weatherlist.dt_txt.split(' '); 
		                var date = dateTimeParts[0];
		                var time = dateTimeParts[1];
		                
		                
		                if(time === "21:00:00"){
			                var creatediv = document.createElement('div');
			                creatediv.classList.add('weathervalue');
			                creatediv.setAttribute('id', date+'_templow');
			                creatediv.setAttribute('value', weatherlist.main.temp);
			                creatediv.textContent = weatherlist.main.temp + '℃';
			               
			                var templow = document.getElementById(date+'_low');
			              
			                if(templow){
			                templow.appendChild(creatediv);
			                }
			                
			                creatediv = document.createElement('div');
			                creatediv.classList.add('weathervalue');
			                creatediv.setAttribute('id', date+'_icolow');
			                creatediv.innerHTML =   '<img src="https://openweathermap.org/img/wn/' +
			                weatherlist.weather[0].icon.substring(0, 2) +
			                'n@2x.png"/>';

			                temphigh = document.getElementById(date+'_low');
			              
			                if(temphigh){
				                temphigh.appendChild(creatediv);
				                }
			                
		                }
		                if(time === "12:00:00"){
			                var creatediv = document.createElement('div');
			                creatediv.classList.add('weathervalue');
			                creatediv.setAttribute('id', date+'_temphigh');
			                creatediv.setAttribute('value', weatherlist.main.temp);
			                creatediv.textContent = weatherlist.main.temp + '℃';
			               
			                var temphigh = document.getElementById(date+'_high');
			              
			                if(temphigh){
			                temphigh.appendChild(creatediv);
			                }
			                
			                creatediv = document.createElement('div');
			                creatediv.classList.add('weathervalue');
			                creatediv.setAttribute('id', date+'_icohigh');
			                creatediv.innerHTML =   '<img src="https://openweathermap.org/img/wn/' +
			                weatherlist.weather[0].icon.substring(0, 2) +
			                'd@2x.png"/>'; 
			                temphigh = document.getElementById(date+'_high');
			              
			                if(temphigh){
				                temphigh.appendChild(creatediv);
				                }
		                }
		            }
		        }
		        window.onload = displayWeatherInfo;
		        