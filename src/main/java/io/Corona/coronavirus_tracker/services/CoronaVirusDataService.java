package io.Corona.coronavirus_tracker.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import io.Corona.coronavirus_tracker.models.LocationsStats;

@Service
public class CoronaVirusDataService {
	
	private String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	private List<LocationsStats> stats = new ArrayList<>();
	
	
	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException {
		
		List<LocationsStats> newStats = new ArrayList<>();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL)).build();
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		//System.out.println(httpResponse.body());
		StringReader csvBodyReader = new StringReader(httpResponse.body());
		
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		
		for(CSVRecord record: records) {
			LocationsStats locationStat = new LocationsStats();
			locationStat.setState(record.get("Province/State"));
			locationStat.setCountry(record.get("Country/Region"));
			int latest_cases = Integer.parseInt(record.get(record.size()-1));
			int prevDayCases = Integer.parseInt(record.get(record.size()-2));
			locationStat.setLatestTotal_Cases(latest_cases);
			locationStat.setDiffFromPrevDay(latest_cases - prevDayCases); 
		
			newStats.add(locationStat);		
			
		
		}
		this.stats = newStats;
	}


	public List<LocationsStats> getStats() {
		return stats;
	}
}
