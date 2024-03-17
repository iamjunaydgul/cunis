import React, { useState } from 'react';
import './App.css';

function App() {
  const [countryName, setCountryName] = useState('');
  const [universities, setUniversities] = useState([]);

  const fetchData = async () => {
    try {
      const response = await fetch(`http://universities.hipolabs.com/search?country=${countryName}`);
      const data = await response.json();
      setUniversities(data);
      // Call API to save fetched data to backend
      saveUniversityData(countryName, data);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  const saveUniversityData = async (country, universities) => {
    try {
      const response = await fetch('/api/universities', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          country: country,
          universities: universities
        })
      });
      if (response.ok) {
        console.log('University data saved successfully');
      } else {
        console.error('Failed to save university data');
      }
    } catch (error) {
      console.error('Error saving university data:', error);
    }
  };

  const handleCountryChange = (event) => {
    setCountryName(event.target.value);
  };

  const handleUniversityClick = (webPage) => {
    window.open(webPage, '_blank');
  };

  const toggleDetails = (index) => {
    const newUniversities = [...universities];
    newUniversities[index].showDetails = !newUniversities[index].showDetails;
    setUniversities(newUniversities);
  };

  return (
    <div className="App">
      <h1>University Data</h1>
      <label htmlFor="country">Country Name:</label>
      <input
        type="text"
        id="country"
        value={countryName}
        onChange={handleCountryChange}
        placeholder="Enter country name"
      />
      <button onClick={fetchData}>Fetch Data</button>
      <h2>List of Universities</h2>
      {universities.map((uni, index) => (
        <div key={index}>
          <div className="university" onClick={() => toggleDetails(index)}>
            <strong>{uni.name}</strong>
            {uni.showDetails ? '-' : '+'}
          </div>
          {uni.showDetails && (
            <div className="details">
              <p>Domains: {uni.domains.join(', ')}</p>
              <p>Country Code: {uni.alpha_two_code}</p>
              <p>Web Pages: <span onClick={() => handleUniversityClick(uni.web_pages[0])}>{uni.web_pages[0]}</span></p>
              <p>State / Province: {uni['state-province'] || 'N/A'}</p>
              <p>Country: {uni.country}</p>
            </div>
          )}
        </div>
      ))}
    </div>
  );
}

export default App;
