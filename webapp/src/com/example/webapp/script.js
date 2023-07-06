// script.js
const temperatureElement = document.getElementById('temperature');
const descriptionElement = document.getElementById('description');
const locationElement = document.getElementById('location');
const windSpeedElement = document.getElementById('windSpeed');

fetch('https://api.open-meteo.com/v1/forecast?latitude=-29.90&longitude=-50.27&current_weather=true')
  .then(response => response.json())
  .then(data => {
    const { temperature, weathercode, time, windspeed, winddirection } = data.current_weather;
    const location = `${data.latitude}, ${data.longitude}`;

    temperatureElement.innerHTML = `Temperatura: ${temperature}C`;
    descriptionElement.innerHTML = `Clima: ${getWeatherDescription(weathercode)}`;
    locationElement.innerHTML = `Localidade: ${location} (às ${time})`;
    windSpeedElement.innerHTML = `Velocidade do vento: ${windspeed} km/h, ${getWindDirection(winddirection)}`;
  })
  .catch(error => {
    console.error('Error:', error);
  });

  function getWeatherDescription(weathercode) {
    switch (weathercode) {
      case 0:
        return 'Céu limpo';
      case 1:
        return 'Principalmente limpo';
      case 2:
        return 'Parcialmente nublado';
      case 3:
        return 'Nublado';
      case 45:
        return 'Neblina';
      case 48:
        return 'Neblina com rime';
      case 51:
        return 'Chuvisco: Fraco';
      case 53:
        return 'Chuvisco: Moderado';
      case 55:
        return 'Chuvisco: Intenso';
      case 56:
        return 'Chuvisco congelante: Fraco';
      case 57:
        return 'Chuvisco congelante: Intenso';
      case 61:
        return 'Chuva: Fraca';
      case 63:
        return 'Chuva: Moderada';
      case 65:
        return 'Chuva: Forte';
      case 66:
        return 'Chuva congelante: Fraca';
      case 67:
        return 'Chuva congelante: Forte';
      case 71:
        return 'Neve: Fraca';
      case 73:
        return 'Neve: Moderada';
      case 75:
        return 'Neve: Forte';
      case 77:
        return 'Grãos de neve';
      case 80:
        return 'Chuvas: Fracas';
      case 81:
        return 'Chuvas: Moderadas';
      case 82:
        return 'Chuvas: Violentas';
      case 85:
        return 'Nevascas: Fracas';
      case 86:
        return 'Nevascas: Fortes';
      case 95:
        return 'Tempestade: Fraca ou moderada';
      case 96:
        return 'Tempestade com granizo fraco';
      case 99:
        return 'Tempestade com granizo forte';
      default:
        return 'Desconhecido';
    }
  }
  

function getWindDirection(degrees) {
    const directions = ['N', 'NNL', 'NL', 'LNL', 'L', 'LSL', 'LE', 'SSL', 'S', 'SSO', 'SO', 'OSO', 'O', 'ONO', 'NO', 'NNO'];
    
  const index = Math.round(degrees / 22.5) % 16;
  return directions[index];
}
