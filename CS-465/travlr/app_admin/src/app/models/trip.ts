export interface Trip {
    _id: string; // Unique identifier for the trip in MongoDB
    code: string; // Unique code for the trip
    name: string; // Name of the trip
    length: string; // Duration of the trip (e.g., "5 days")
    start: Date; // Start date of the trip (e.g., "2024-06-01")
    resort: string; // Resort associated with the trip
    perPerson: string; // Cost per person for the trip
    image: string; // URL of the trip's image
    description: string; // Description of the trip
}