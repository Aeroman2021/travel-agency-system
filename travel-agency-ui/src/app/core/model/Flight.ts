export interface Flight {
  id: number;
  flightNumber: string;
  airLine: number;
  originAirport: number;
  destinationAirport: number;
  departureTime: string;
  arrivalTime: string;
  price: number;
  currencyCode: string;
  availableSeats: number;
  status: string;
}
